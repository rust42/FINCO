package project.framework.core.accountdetails;

import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.storage.AbstractStorageService;
import project.framework.core.accountdetails.storage.service.StorageServiceException;

import java.util.Optional;

public abstract class AbstractAccountService {

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
    }

    public void withdrawMoney(String uniqueAccId, double withdrawAmount) {
        Optional<IAccount> iAccount = abstractStorageService.getIAccountByUniqueId(uniqueAccId);
        if (!iAccount.isPresent()) {
            throw new StorageServiceException("Not Fount, Invalid Account");
        }
        IAccount account = iAccount.get();
        double newBalance = account.getBalance() - withdrawAmount;
        account.setBalance(newBalance);
        abstractStorageService.store(account);
    }

    public void depositMoney(String uniqueAccId, double depositAmount) {
        Optional<IAccount> iAccount = abstractStorageService.getIAccountByUniqueId(uniqueAccId);
        if (!iAccount.isPresent()) {
            throw new StorageServiceException("Not Fount, Invalid Account");
        }
        IAccount account = iAccount.get();
        double newBalance = account.getBalance() + depositAmount;
        account.setBalance(newBalance);
        abstractStorageService.store(account);
    }

    public void addInterest() {
        // TODO
    }

    public void calculateInterest(String uniqueAccId) {
        Optional<IAccount> iAccount = abstractStorageService.getIAccountByUniqueId(uniqueAccId);
        if (!iAccount.isPresent()) {
            throw new StorageServiceException("Not Fount, Invalid Account");
        }
        iInterestCalculationStrategy.calculateInterest(iAccount.get());
        // TODO return or do something with calculated interest result
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

    public void sendEmailToParty(IAccount iAccount) {
        iEmailPartyService.onSendEmailToPartyTriggered(iAccount);
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
