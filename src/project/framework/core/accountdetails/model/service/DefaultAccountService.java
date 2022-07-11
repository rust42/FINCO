package project.framework.core.accountdetails.model.service;

import project.framework.core.accountdetails.AbstractAccountService;
import project.framework.core.accountdetails.IEmailPartyService;
import project.framework.core.accountdetails.IInterestCalculationStrategy;
import project.framework.core.accountdetails.IReportingStrategy;
import project.framework.core.accountdetails.storage.AbstractStorageService;

public class DefaultAccountService extends AbstractAccountService {
    public DefaultAccountService(IInterestCalculationStrategy iInterestCalculationStrategy, IEmailPartyService iEmailPartyService, AbstractStorageService abstractStorageService, IReportingStrategy iReportingStrategy) {
        super(iInterestCalculationStrategy, iEmailPartyService, abstractStorageService, iReportingStrategy);
    }

}
