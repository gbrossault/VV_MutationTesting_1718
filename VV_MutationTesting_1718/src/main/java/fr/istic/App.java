package fr.istic;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.net.URL;

import fr.istic.bytecodeModificator.BytecodeElements;
import fr.istic.bytecodeModificator.MutationTargetFinderForArithmeticOperators;
import fr.istic.classFinder.ClassFinder;
import fr.istic.classFinder.ClassLoader;
import fr.istic.model.DummyClass;
import fr.istic.model.DummyMethod;
import fr.istic.mutationTarget.MutationTarget;
import fr.istic.testRunner.TestRunner;
import javassist.CtClass;
import javassist.bytecode.Mnemonic;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws Exception {
        //Load classes
    	String path = "/home/gbrossault/Documents/M2_ILA/Java_FX/My512/target/classes/model";
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
        
        //Find target
        MutationTargetFinderForArithmeticOperators ao = new MutationTargetFinderForArithmeticOperators();
        List<MutationTarget> targets = ao.findTarget(classes, BytecodeElements.IADD_OPERATOR, BytecodeElements.ISUB_OPERATOR);
        
        //Create mutant
        
        
        //Execute test suit for mutant
//        List<URL> urls = new ArrayList<URL>();
//        for(DummyClass dclass : classes){
//        	urls.add(new URL("file://"+dclass.getPath()+"/"));
//        }
//        TestRunner tr = new TestRunner(urls);
//        for(DummyClass dclass : classes) {
//        	if(dclass.isTestClass()) {
//        		tr.runTests(dclass.getName().replace(".class", ""));
//        	}
//        }
        
        //Get reports
    }
    
    public void createProjectCopy(String path) {
    	
    }
}
