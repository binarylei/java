package com.github.binarylei.bean;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class ProductFactory {

	private BundleContext bundleContext;
	private Bundle bundle;
	
	public void init()
	{
		System.out.println(bundleContext.getBundle().getSymbolicName());
		System.out.println(bundle.getLocation());
	}

	public BundleContext getBundleContext() {
		return bundleContext;
	}

	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	public Bundle getBundle() {
		return bundle;
	}

	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}
}
