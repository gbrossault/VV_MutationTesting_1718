package fr.istic.model;

import java.util.HashMap;
import java.util.Map;

public class DummyMethod {
	
	private String name;
	private String returnType;
	private Map<Integer, Integer> content;
	
	public DummyMethod(String name) {
		this.name = name;
		this.setContent(new HashMap<Integer, Integer>());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public void addBytecode(Integer index, Integer op) {
		this.content.put(index, op);
	}
	
	public Map<Integer, Integer> getContent() {
		return content;
	}

	public void setContent(Map<Integer, Integer> content) {
		this.content = content;
	}
	
	public boolean contains(Integer opCode) {
		if(this.content.values().contains(opCode)) {
			return true;
		}
		return false;
	}
}
