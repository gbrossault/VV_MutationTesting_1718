package fr.istic.model;

import java.util.ArrayList;
import java.util.List;

import javassist.CtClass;

public class DummyClass {
	
	private String name;
	private String path;
	private List<DummyMethod> methods;
	private CtClass correspondingCtClass;
	private boolean testClass;
	
	public DummyClass(String name, String path) {
		this.setPath(path);
		this.setTestClass(false);
		this.setName(name);
		this.methods = new ArrayList<DummyMethod>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void addMethods(DummyMethod method) {
		this.methods.add(method);
	}
	
	public DummyMethod getMethod(String name) {
		for(DummyMethod method : methods) {
			if(method.getName().equals(name)) {
				return method;
			}
		}
		return null;
	}
	
	public List<DummyMethod> getMethods() {
		return this.methods;
	}

	public CtClass getCorrespondingCtClass() {
		return correspondingCtClass;
	}

	public void setCorrespondingCtClass(CtClass correspondingCtClass) {
		this.correspondingCtClass = correspondingCtClass;
	}

	public boolean isTestClass() {
		return testClass;
	}

	public void setTestClass(boolean testClass) {
		this.testClass = testClass;
	}
}
