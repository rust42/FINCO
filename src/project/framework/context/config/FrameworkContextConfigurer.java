package project.framework.context.config;

import project.framework.core.accountdetails.AbstractAccountService;
import project.framework.core.accountdetails.model.service.DefaultAccountService;
import project.framework.core.accountdetails.model.service.DefaultEmailToPartyService;
import project.framework.core.accountdetails.model.service.DefaultInterestCalculationStrategy;
import project.framework.core.accountdetails.model.service.DefaultReportingStrategy;
import project.framework.core.accountdetails.storage.service.DefaultInMemoryStorageService;

public final class FrameworkContextConfigurer {

    private AbstractAccountService abstractAccountService;

    private static FrameworkContextConfigurer singletonInstance = new FrameworkContextConfigurer();

    private FrameworkContextConfigurer() {
    }

    public static void loadDefaultContext() {
        FrameworkContextConfigurer instance = getInstance();

        DefaultInterestCalculationStrategy defaultInterestCalculationStrategy = new DefaultInterestCalculationStrategy();
        DefaultEmailToPartyService defaultEmailToPartyService = new DefaultEmailToPartyService();
        DefaultReportingStrategy defaultReportingStrategy = new DefaultReportingStrategy();
        DefaultInMemoryStorageService defaultInMemoryStorageService = new DefaultInMemoryStorageService();
        instance.abstractAccountService = new DefaultAccountService(defaultInterestCalculationStrategy, defaultEmailToPartyService,
                defaultInMemoryStorageService, defaultReportingStrategy);
    }

    static FrameworkContextConfigurer getInstance() {
        if (singletonInstance != null) {
            return singletonInstance;
        }
        return new FrameworkContextConfigurer();
    }

    public void setAbstractAccountService(AbstractAccountService abstractAccountService) {
        this.abstractAccountService = abstractAccountService;
    }

    public AbstractAccountService getAbstractAccountService() {
        return abstractAccountService;
    }

}
