package project.ccard;

import project.ccard.app.view.CardFrame;
import project.framework.context.FrameworkApplicationBootstrapper;

import javax.swing.*;

public class CreditCardApplication {

    /*****************************************************
     * The entry point for this application.
     * Sets the Look and Feel to the System Look and Feel.
     * Creates a new JFrame1 and makes it visible.
     *****************************************************/
    static public void main(String args[]) {
        FrameworkApplicationBootstrapper.startFinco(CreditCardApplication.class, args);

        System.out.println("CreditCard Application");

        try {
            // Add the following code if you want the Look and Feel
            // to be set to the Look and Feel of the native system.

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
            (new CardFrame()).getCurrJFrame().setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }

}
