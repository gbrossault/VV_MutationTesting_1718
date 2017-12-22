package fr.istic.testRunner;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import fr.istic.model.DummyClass;

public class TestRunner {
	
	public String runTests(List<DummyClass> classes, String projectPath, String mutantPath) throws MalformedURLException {
		List<URL> urls = new ArrayList<URL>();
        for(DummyClass dclass : classes){
        	String mutantFilePath = dclass.getPath().replace(projectPath, mutantPath.substring(2, mutantPath.length()-1));
        	File file = new File(mutantFilePath);
        	URL url = file.toURL();
        	if(!urls.contains(url)){
        		urls.add(url);
        	}
        }
        URL[] urlsTab = new URL[urls.size()];
        for(int i = 0; i<urls.size(); i++) {
        	urlsTab[i] = urls.get(i);
        }
        URLClassLoader urlClassLoader = URLClassLoader.newInstance(urlsTab);
        for(DummyClass dclass : classes){
        	if(dclass.isTestClass()) {
        		for(URL url : urlClassLoader.getURLs()) {
        			System.out.println(url.toString());
        		}
        		String packageName = dclass.getCorrespondingCtClass().getPackageName();
        		try {
					Class<?> c = urlClassLoader.loadClass(packageName+"."+dclass.getName().replace(".class", ""));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
        	}
        }
        return "";
	}
}
