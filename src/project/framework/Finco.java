package project.framework;

import project.framework.context.FactoryServiceRetriever;
import project.framework.context.FrameworkContextConfigurer;
import project.framework.gui.defaults.FincoFrame;

public class Finco {

    private String[] args;

    public Finco(String[] args, Class<?>... primarySources) {
        this.args = args;
        System.out.println("FINCO: Starting through " + primarySources[0]);
        FrameworkContextConfigurer.loadDefaultContext();
        onContextLoaded();
    }

    public static void main(String[] args) {
        new Finco(args, Finco.class);
    }

    public void onContextLoaded() {
        System.out.println("FINCO: Initalized framework context, loading default GUI");
        FincoFrame fincoFrame = FactoryServiceRetriever.getService(FincoFrame.class);
        fincoFrame.getCurrJFrame().setVisible(true);
    }

}
