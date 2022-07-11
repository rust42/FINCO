package project.framework.context.config;

import project.framework.core.accountdetails.AbstractAccountService;
import project.framework.gui.AbstractAccountGUI;

public class FactoryServiceRetriever {
    private static FrameworkContextConfigurer frameworkContextConfigurer = FrameworkContextConfigurer.getInstance();

    public static <T> T getService(Class<T> tClass) {
        if (tClass.isAssignableFrom(AbstractAccountService.class)) {
            return (T) frameworkContextConfigurer.getAbstractAccountService();
        } else if (tClass.isAssignableFrom(AbstractAccountGUI.class)) {
            return (T) frameworkContextConfigurer.getAbstractAccountGUI();
        }
        else if (tClass.isAssignableFrom(FrameworkContextConfigurer.class)) {
            return (T) frameworkContextConfigurer;
        }
        throw new UnsupportedOperationException("Can't provide a bean of required type " + tClass);
    }
}
