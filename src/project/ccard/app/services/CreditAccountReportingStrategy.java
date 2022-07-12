package project.ccard.app.services;

import project.ccard.app.model.CCAccount;
import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.TransactionType;
import project.framework.core.accountdetails.model.service.DefaultReportingStrategy;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Set;

public class CreditAccountReportingStrategy extends DefaultReportingStrategy<CCAccount> {

    /**
     * - previous balance: balance from last month
     * - total charges: total of all charges for this month
     * - total credits: total of all payments for this month
     * - new balance = previous balance – total credits + total charges + MI * (previous balance – total credits)
     * - total due = MP * new balance
     */
    @Override
    public String generateReport(CCAccount ccAccount) {
        Set<Entry> entries = ccAccount.getEntries();
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


        double previousMonthBalance = ccAccount.getBalance() - sumOfEntriesThisMonth;

        StringBuilder reportText = new StringBuilder();
        reportText.append("Report for the month ")
                .append(System.lineSeparator())
                .append("-----------------")
                .append(currentMonth.getMonth() + "-" + currentMonth.getYear())
                .append(System.lineSeparator())
                .append("(previous balance) Balance for the last month: " + previousMonthBalance)
                .append(System.lineSeparator());

        reportText.append("(total charges) Withdrawals this month: " + withdrawls);
        reportText.append(System.lineSeparator());
        reportText.append("(total credits) Deposit this month: " + deposits);
        reportText.append(System.lineSeparator());

        double newBalance = previousMonthBalance - deposits + withdrawls + ccAccount.getTierLevel().getMonthlyInterest() * (previousMonthBalance - deposits);
        reportText.append("New balance: " + newBalance);
        reportText.append(System.lineSeparator());
        double totalDue = ccAccount.getTierLevel().getMinimumPayment() * newBalance;
        reportText.append("Total due: " + totalDue);
        return reportText.toString();
    }

}
