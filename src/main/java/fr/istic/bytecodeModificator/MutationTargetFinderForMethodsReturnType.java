package fr.istic.bytecodeModificator;

import java.util.ArrayList;
import java.util.List;

import fr.istic.model.DummyClass;
import fr.istic.model.DummyMethod;
import fr.istic.mutationTarget.MutationTarget;

public class MutationTargetFinderForMethodsReturnType {	
	
	public static final String VOID_RETURN_TYPE = "void"; 
	public static final String MAIN_FUNCTION = "main";
	public static final String INT_RETURN_TYPE = "int"; 
	
    public List<MutationTarget> findTarget(List<DummyClass> classes, String returnType){
    	List<MutationTarget> targets = new ArrayList<MutationTarget>();
    	for(DummyClass dclass : classes) {
    		for(DummyMethod method : dclass.getMethods()) {
    			if(method.getReturnType().equals(returnType) && !method.getName().equals(MAIN_FUNCTION)) {
    				MutationTarget target = new MutationTarget(dclass.getName(), dclass.getCorrespondingCtClass().getPackageName(), method.getName(), 0, 0, 0);
					targets.add(target);
    			}
    		}
    	}
    	return targets;
    }
}
