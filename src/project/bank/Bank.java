package project.bank;

import project.bank.app.controller.BankFrmController;
import project.bank.app.view.BankFrame;
import project.framework.Finco;

import javax.swing.*;

public class Bank extends Finco {

    public Bank(String[] args) {
        super(args, Bank.class);
    }

    static public void main(String args[]) {
        new Bank(args);
    }

    @Override
    public void onContextLoaded() {
        System.out.println("Bank Application");
        try {
            // Add the following code if you want the Look and Feel
            // to be set to the Look and Feel of the native system.

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
            new BankFrame().getCurrJFrame().setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }

}
