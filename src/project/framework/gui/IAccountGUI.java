package project.framework.gui;

import javax.swing.*;

public interface IAccountGUI {

    JFrame createJFrame(String frameTitle);

    JFrame getCurrJFrame();

    void onExitApplication();

}
