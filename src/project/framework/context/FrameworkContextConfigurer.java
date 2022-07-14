package project.framework.context;

import project.framework.core.accountdetails.AbstractAccountService;
import project.framework.core.accountdetails.model.account.Account;
import project.framework.core.accountdetails.model.service.DefaultAccountService;
import project.framework.core.accountdetails.model.service.DefaultEmailToPartyService;
import project.framework.core.accountdetails.model.service.DefaultInterestCalculationStrategy;
import project.framework.core.accountdetails.model.service.DefaultReportingStrategy;
import project.framework.core.accountdetails.storage.service.DefaultInMemoryStorageService;
import project.framework.gui.defaults.FincoFrame;
import project.framework.gui.GenericJTableModel;
import project.framework.gui.defaults.modal.FincoAccountModelResponseMapper;

public final class FrameworkContextConfigurer {

    private AbstractAccountService abstractAccountService;
    private FincoFrame fincoFrame;

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

        GenericJTableModel<Account> accountGenericJTableModel = new GenericJTableModel<>(new FincoAccountModelResponseMapper<>());
        instance.fincoFrame = new FincoFrame(accountGenericJTableModel);

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

    public FincoFrame getFincoFrame() {
        return fincoFrame;
    }

    public void setFincoFrame(FincoFrame fincoFrame) {
        this.fincoFrame = fincoFrame;
    }
}
