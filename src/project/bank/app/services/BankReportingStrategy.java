package project.bank.app.services;

import project.bank.app.model.BankAccount;
import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.TransactionType;
import project.framework.core.accountdetails.model.service.DefaultReportingStrategy;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Set;

public class BankReportingStrategy extends DefaultReportingStrategy<BankAccount> {

    @Override
    public String generateReport(BankAccount bankAccount) {
        Set<Entry> entries = bankAccount.getEntries();
        LocalDate currentMonth = LocalDate.now();
        LocalDate previousMonthEndDate = currentMonth
                .minusMonths(1)
                .with(TemporalAdjusters.lastDayOfMonth());


        List<Entry> entryOfThisMonth = entries.stream()
                .filter(entry -> entry.getDate().isAfter(previousMonthEndDate)).toList();

        double sumOfEntriesThisMonth = entryOfThisMonth.stream().mapToDouble(Entry::getTxAmount).sum();
        double withdrawls = entryOfThisMonth.stream()
                .filter(e -> e.getTransactionType() == TransactionType.OUTGOING)
                .mapToDouble(Entry::getTxAmount)
                .map(Math::abs)
                .sum();
        double deposits = sumOfEntriesThisMonth - withdrawls;


        double previousMonthBalance = bankAccount.getBalance() - sumOfEntriesThisMonth;

        StringBuilder reportText = new StringBuilder();
        reportText.append("Report for the month ")
                .append(currentMonth.getMonth() + "/" + currentMonth.getYear() + "/" + currentMonth.getDayOfMonth())
                .append(System.lineSeparator())
                .append("Balance for the last month: " + previousMonthBalance)
                .append(System.lineSeparator());

        reportText.append("Withdrawls this month: " + withdrawls);
        reportText.append("Deposit this month: " + deposits);
        reportText.append(System.lineSeparator());
        reportText.append("Current balance: " + bankAccount.getBalance());
        return reportText.toString();
    }
}
