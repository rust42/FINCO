package project.framework.context;

import project.framework.context.config.FrameworkContextConfigurer;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class FrameworkApplicationBootstrapper {

    private Set<Class<?>> primarySources;

    public FrameworkApplicationBootstrapper(Class<?>... primarySources) {
        this.primarySources = new LinkedHashSet(Arrays.asList(primarySources));
        if (this.primarySources.size() <= 0) {
            System.out.println("FINCO: Starting through framework default startup");
        } else {
            System.out.println("FINCO: Starting through " + primarySources[0]);
        }
        FrameworkContextConfigurer.loadDefaultContext();
    }

    public static void main(String[] args) {
        initializeFincoConstructor(new Class[0], args);
    }

    // public methods
    public static void startFinco(Class<?> primarySource, String... args) {
        initializeFincoConstructor(new Class[]{primarySource}, args);
    }

    // framework private methods built in for the instantiation
    private static void initializeFincoConstructor(Class<?>[] primarySource, String[] args) {
        new FrameworkApplicationBootstrapper(primarySource).initFramework(args);
    }

    private void initFramework(String[] args) {
        System.out.println("FINCO: Initializing framework context");
    }

}
