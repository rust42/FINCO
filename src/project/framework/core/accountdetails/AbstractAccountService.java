package project.framework.core.accountdetails;

import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.model.account.IEntry;
import project.framework.core.accountdetails.model.account.TransactionType;
import project.framework.core.accountdetails.storage.AbstractStorageService;
import project.framework.core.accountdetails.storage.service.StorageServiceException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class AbstractAccountService<T extends IAccount> {

    private IInterestCalculationStrategy iInterestCalculationStrategy;
    private IEmailPartyService iEmailPartyService;
    private AbstractStorageService abstractStorageService;
    private IReportingStrategy iReportingStrategy;

    public AbstractAccountService(IInterestCalculationStrategy iInterestCalculationStrategy, IEmailPartyService iEmailPartyService,
                                  AbstractStorageService abstractStorageService, IReportingStrategy iReportingStrategy) {
        this.iInterestCalculationStrategy = iInterestCalculationStrategy;
        this.iEmailPartyService = iEmailPartyService;
        this.abstractStorageService = abstractStorageService;
        this.iReportingStrategy = iReportingStrategy;
    }

    public void addAccount(IAccount iAccount) {
        abstractStorageService.store(iAccount);
        iAccount.attach(iAccount.getParty());
    }

    public void withdrawMoney(String uniqueAccId, double withdrawAmount) {
        Optional<IAccount> iAccount = abstractStorageService.getIAccountByUniqueId(uniqueAccId);
        if (!iAccount.isPresent()) {
            throw new StorageServiceException("Not Fount, Invalid Account");
        }
        IAccount account = iAccount.get();
        double newBalance = account.getBalance() - withdrawAmount;
        account.setBalance(newBalance);

        Entry entry = new Entry(withdrawAmount, LocalDate.now(), TransactionType.OUTGOING);
        account.addEntry(entry);
        abstractStorageService.update(account);

        account.notifyObserver(entry);

    }

    public void depositMoney(String uniqueAccId, double depositAmount) {
        Optional<IAccount> iAccount = abstractStorageService.getIAccountByUniqueId(uniqueAccId);
        if (!iAccount.isPresent()) {
            throw new StorageServiceException("Not Fount, Invalid Account");
        }
        IAccount account = iAccount.get();
        double newBalance = account.getBalance() + depositAmount;
        account.setBalance(newBalance);

        Entry entry = new Entry(depositAmount, LocalDate.now(), TransactionType.INCOMING);
        account.addEntry(entry);
        abstractStorageService.update(account);

        account.notifyObserver(entry);

    }

    public void addInterest(double interestRate) {
        List<IAccount> iAccountList = abstractStorageService.getAllAccounts();

        for (IAccount account : iAccountList) {
            account.setInterestRate(interestRate);
            abstractStorageService.update(account);
        }

    }

    public void calculateInterest(String uniqueAccId) {
        Optional<IAccount> iAccount = abstractStorageService.getIAccountByUniqueId(uniqueAccId);
        if (!iAccount.isPresent()) {
            throw new StorageServiceException("Not Fount, Invalid Account");
        }
        iInterestCalculationStrategy.calculateInterest(iAccount.get());
        // TODO return or do something with calculated interest result
    }

    private void addEntry(String uniqueAccId, double amount, TransactionType type) {
        addEntry(uniqueAccId, new Entry(amount, LocalDate.now(), type));
    }

    public void addEntry(String uniqueAccId, Entry entry) {
        abstractStorageService.addEntry(uniqueAccId, entry);
    }

    public double getCurrentBalance(String uniqueAccId) {
        Optional<IAccount> iAccount = abstractStorageService.getIAccountByUniqueId(uniqueAccId);
        if (!iAccount.isPresent()) {
            throw new StorageServiceException("Not Fount, Invalid Account");
        }
        return iAccount.get().getBalance();
    }

    public void sendEmailToParty(IAccount iAccount, IEntry iEntry) {
        iEmailPartyService.onSendEmail(iAccount, iEntry);
    }

    public void removeAccount(String uniqueAccId) {
        abstractStorageService.remove(uniqueAccId);
    }

    public String generateReport(String uniqueAccId) {
        Optional<IAccount> iAccount = abstractStorageService.getIAccountByUniqueId(uniqueAccId);
        if (!iAccount.isPresent()) {
            throw new StorageServiceException("Not Fount, Invalid Account");
        }
        String report = this.iReportingStrategy.generateReport(iAccount.get());
        return report;
    }


    // context getters and setters
    public IInterestCalculationStrategy getiInterestCalculationStrategy() {
        return iInterestCalculationStrategy;
    }

    public void setiInterestCalculationStrategy(IInterestCalculationStrategy iInterestCalculationStrategy) {
        this.iInterestCalculationStrategy = iInterestCalculationStrategy;
    }

    public IEmailPartyService getiEmailPartyService() {
        return iEmailPartyService;
    }

    public void setiEmailPartyService(IEmailPartyService iEmailPartyService) {
        this.iEmailPartyService = iEmailPartyService;
    }

    public AbstractStorageService getAbstractStorageService() {
        return abstractStorageService;
    }

    public void setAbstractStorageService(AbstractStorageService abstractStorageService) {
        this.abstractStorageService = abstractStorageService;
    }

    public IReportingStrategy getiReportingStrategy() {
        return iReportingStrategy;
    }

    public void setiReportingStrategy(IReportingStrategy iReportingStrategy) {
        this.iReportingStrategy = iReportingStrategy;
    }
}
