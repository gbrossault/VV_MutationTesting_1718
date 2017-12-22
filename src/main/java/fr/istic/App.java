package fr.istic;

import java.util.List;

import java.io.File;

import fr.istic.bytecodeModificator.BytecodeElements;
import fr.istic.bytecodeModificator.MutationTargetFinderForArithmeticOperators;
import fr.istic.classFinder.ClassFinder;
import fr.istic.model.DummyClass;
import fr.istic.mutantGenerator.FileTools;
import fr.istic.mutantGenerator.MutantGenerator;
import fr.istic.mutationTarget.MutationTarget;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws Exception {
        //Load classes
    	String path = "/home/gbrossault/Documents/M2_ILA/Java_FX/My512";
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
        
        //Find target
        MutationTargetFinderForArithmeticOperators ao = new MutationTargetFinderForArithmeticOperators();
        List<MutationTarget> targets = ao.findTarget(classes, BytecodeElements.IADD_OPERATOR, BytecodeElements.ISUB_OPERATOR);
        
        //Create mutant
        String mutantPath = "./resources/mutant/";
        MutantGenerator mg = new MutantGenerator();
        mg.mutateProjectForOperation(targets, path, mutantPath);
        
        //Execute test suit for mutant
//        TestRunner testRunner = new TestRunner();
//        testRunner.runTests(classes, path, mutantPath);
        
        FileTools.deleteDirectoryContent(mutantPath);
        System.out.println("end");
    }
}
