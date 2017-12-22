package fr.istic.bytecodeModificator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.istic.model.DummyClass;
import fr.istic.model.DummyMethod;
import fr.istic.mutationTarget.MutationTarget;

public class MutationTargetFinderForArithmeticOperators {	
	
    public List<MutationTarget> findTarget(List<DummyClass> classes, Integer oldOperator, Integer newOperator){
    	List<MutationTarget> targets = new ArrayList<MutationTarget>();
    	for(DummyClass dclass : classes) {
    		for(DummyMethod method : dclass.getMethods()) {
				Map<Integer, Integer> methodContent = method.getContent();
				for(Integer index : methodContent.keySet()) {
					if(methodContent.get(index) == oldOperator) {
    					MutationTarget target = new MutationTarget(dclass.getName(), method.getName(), index, methodContent.get(index), newOperator);
    					targets.add(target);
					}
				}
    		}
    	}
    	return targets;
    }
}
