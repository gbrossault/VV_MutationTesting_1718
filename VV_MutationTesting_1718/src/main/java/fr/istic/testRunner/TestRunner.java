package fr.istic.testRunner;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javassist.NotFoundException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class TestRunner {


	private List<URL> pathToClasses;
	
	public TestRunner(List<URL> pathToClasses){
		this.pathToClasses = pathToClasses;
	}
	
	public String runTests(String testClass) throws ClassNotFoundException, MalformedURLException, NotFoundException{
		
		StringBuilder sb = new StringBuilder();
		URL[] urls = new URL[pathToClasses.size()];
		for(int i = 0; i<pathToClasses.size(); i++) {
			urls[i] = pathToClasses.get(i);
			System.out.println(pathToClasses.get(i));
		}
		URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls);
		Class<?> clazz = urlClassLoader.loadClass("");
		System.out.println("Loaded test class : "+ testClass +'\n');
		
		JUnitCore core = new JUnitCore();
        Result result = new Result();
        result = core.run(clazz);
        System.out.println("TESTS FINISHED");
        System.out.println(String.format("| RUN: %d", result.getRunCount())+'\n');
        if(result.wasSuccessful())
        	System.out.println("| ALL TESTS SUCCEEDED !");
        else
        	System.out.println("| FAILURE ! ");
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString()+'\n');
        }
        System.out.println(String.format("| TIME: %dms", result.getRunTime())+'\n');
        System.out.println("********************************");
        System.out.println("********************************");
        return sb.toString();
	}
}
