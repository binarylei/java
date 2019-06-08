package com.github.binarylei;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

public class HelloBundleActivator implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("bundle start...");
        context.addBundleListener(new BundleListener() {
            @Override
            public void bundleChanged(BundleEvent event) {
                System.out.println(event.getBundle().getSymbolicName());
                System.out.println(event.getType());
            }
        });
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("bundle stop...");
    }
}
