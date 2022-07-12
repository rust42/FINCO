package project.framework.gui;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractFrameworkGUI implements IAccountGUI {

    private JFrame jFrame;

    public AbstractFrameworkGUI(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public AbstractFrameworkGUI(String frameTitle) {
        this.createJFrame(frameTitle);
    }

    @Override
    public JFrame createJFrame(String frameTitle) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle(frameTitle);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        jFrame.setSize(575, 310);
        jFrame.setVisible(false);
        this.jFrame = jFrame;
        return jFrame;
    }

    @Override
    public void onExitApplication() {
        try {
            this.jFrame.setVisible(false);    // hide the Frame
            this.jFrame.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    @Override
    public JFrame getCurrJFrame() {
        return this.jFrame;
    }
}
