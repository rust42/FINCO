package context.config;

import core.accountdetails.AbstractAccountService;
import core.accountdetails.IEmailPartyService;
import core.accountdetails.IInterestCalculationStrategy;

public class FrameworkContextConfigurer {
    private AbstractAccountService abstractAccountService;

    public static void loadDefaultContext(){

    }

    public static FrameworkContextConfigurer getInstance(){
        return new FrameworkContextConfigurer();
    }

    public void setIEmailService(IEmailPartyService iEmailPartyService){

    }

    public void setAbstractAccountService(AbstractAccountService abstractAccountService)
    {

    }

    public void setInterestCalculationStrategy(IInterestCalculationStrategy iInterestCalculationStrategy){

    }
}
