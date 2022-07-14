package project.ccard;

import project.ccard.app.controller.CreditFrmController;
import project.ccard.app.services.CreditAccountReportingStrategy;
import project.ccard.app.view.CardFrame;
import project.framework.Finco;
import project.framework.context.FactoryServiceRetriever;
import project.framework.core.accountdetails.AbstractAccountService;

import javax.swing.*;

public class Credit extends Finco {

    /*****************************************************
     * The entry point for this application.
     * Sets the Look and Feel to the System Look and Feel.
     * Creates a new JFrame1 and makes it visible.
     *****************************************************/
    public Credit(String[] args) {
        super(args, Credit.class);
    }

    static public void main(String args[]) {
        new Credit(args);
    }

    @Override
    public void onContextLoaded() {
        System.out.println("Credit Application");
        try {
            // Add the following code if you want the Look and Feel
            // to be set to the Look and Feel of the native system.

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
            AbstractAccountService abstractAccountService = FactoryServiceRetriever.getService(AbstractAccountService.class);
            abstractAccountService.setiReportingStrategy(new CreditAccountReportingStrategy());

            new CardFrame().getCurrJFrame().setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }


}
