package com.github.binarylei.log.classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * @author leigang
 * @version 2019-04-27
 * @since 2.0.0
 */
public class ChildFirstClassLoader extends URLClassLoader {
    public ChildFirstClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    protected Class loadClass(String name, boolean resolve) throws ClassNotFoundException {

        Class c = findLoadedClass(name);
        if (c == null) {
            try {
                c = findClass(name);
            } catch (ClassNotFoundException cnfe) {
            }
        }

        if (c == null) {
            if (getParent() != null) {
                c = getParent().loadClass(name);
            } else {
                c = getSystemClassLoader().loadClass(name);
            }
        }

        if (resolve) {
            resolveClass(c);
        }
        return c;
    }

    public URL getResource(String name) {
        URL url = findResource(name);
        if (url == null) {
            url = getParent().getResource(name);
        }
        return url;
    }
}
