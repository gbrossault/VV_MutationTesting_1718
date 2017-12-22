package fr.istic;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import fr.istic.bytecodeModificator.BytecodeElements;
import fr.istic.bytecodeModificator.MutationTargetFinderForArithmeticOperators;
import fr.istic.bytecodeModificator.MutationTargetFinderForMethodsReturnType;
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
	
	private String path = "/home/gbrossault/Documents/M2_ILA/Java_FX/My512";
	
    public static void main( String[] args ) throws Exception {
    	boolean testMutant = true;
        //Load classes
    	String path = "";
    	File file = null;
    	while(file == null || !file.exists()) {
	    	System.out.println("Enter a project folder : ");
	    	
	    	BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
	    	path = entree.readLine();
	    		    
			file = new File(path);
    	}
    	System.out.println("Your path is : " + path );
    	
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
        System.out.println("Classes loaded");
        
        while(testMutant) {
	        String mutantType = null;
	        while(mutantType == null || !(mutantType.equals("1") || mutantType.equals("2") || mutantType.equals("3"))) {
		        System.out.println("Select a mutation type : ");
		        System.out.println("1 : Mutate operators");
		        System.out.println("2 : Mutate comparators");
		        System.out.println("3 : Mutate void methods");
	        
		    	BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
		    	mutantType = entree.readLine();
	    	}
	        
	
	        //Find target
	        List<MutationTarget> targets = null;
	        if(mutantType.equals("1")) {
	        	String opMutantType = null;
	            while(opMutantType == null || !(mutantType.equals("1") || mutantType.equals("2") || mutantType.equals("3") || mutantType.equals("4"))) {
	    	        System.out.println("Select a operator mutation : ");
	    	        System.out.println("1 : Mutate + in -");
	    	        System.out.println("2 : Mutate - in +");
	    	        System.out.println("3 : Mutate * in /");
	    	        System.out.println("4 : Mutate / in *");
	            
	    	    	BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
	    	    	opMutantType = entree.readLine();
	        	}
	            int oldOp = 0;
	            int mutateOp = 0;
	            switch(opMutantType) {
	            case "2":
	            	oldOp = BytecodeElements.ISUB_OPERATOR;
	            	mutateOp = BytecodeElements.IADD_OPERATOR;
	            	break;
	            case "3":
	            	oldOp = BytecodeElements.IMUL_OPERATOR;
	            	mutateOp = BytecodeElements.IDIV_OPERATOR;
	            	break;
	            case "4":
	            	oldOp = BytecodeElements.IDIV_OPERATOR;
	            	mutateOp = BytecodeElements.IMUL_OPERATOR;
	            	break;
	            default:
	            	oldOp = BytecodeElements.IADD_OPERATOR;
	            	mutateOp = BytecodeElements.ISUB_OPERATOR;
	            	break;
	            }
	        	MutationTargetFinderForArithmeticOperators ao = new MutationTargetFinderForArithmeticOperators();
	            targets = ao.findTarget(classes, oldOp, mutateOp);
	        } else if(mutantType.equals("2")) {
	        	String opMutantType = null;
	            while(opMutantType == null || !(mutantType.equals("1") || mutantType.equals("2") || mutantType.equals("3"))) {
	    	        System.out.println("Select a operator mutation : ");
	    	        System.out.println("1 : Mutate < in >=");
	    	        System.out.println("2 : Mutate > in <=");
	    	        System.out.println("3 : Mutate = in !=");
	            
	    	    	BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
	    	    	opMutantType = entree.readLine();
	        	}
	            int oldOp = 0;
	            int mutateOp = 0;
	            switch(opMutantType) {
	            case "2":
	            	oldOp = BytecodeElements.GREATER_THAN_OPERATOR;
	            	mutateOp = BytecodeElements.LESS_OR_EQUALS_OPERATOR;
	            	break;
	            case "3":
	            	oldOp = BytecodeElements.EQUALS_OPERATOR;
	            	mutateOp = BytecodeElements.NOT_EQUALS_OPERATOR;
	            	break;
	            default:
	            	oldOp = BytecodeElements.LESS_THAN_OPERATOR;
	            	mutateOp = BytecodeElements.GREATER_OR_EQUALS_OPERATOR;
	            	break;
	            }
	        	MutationTargetFinderForArithmeticOperators ao = new MutationTargetFinderForArithmeticOperators();
	            targets = ao.findTarget(classes, oldOp, mutateOp);
	        } else {
	        	MutationTargetFinderForMethodsReturnType ao = new MutationTargetFinderForMethodsReturnType();
	        	targets = ao.findTarget(classes, MutationTargetFinderForMethodsReturnType.VOID_RETURN_TYPE);
	        }
	        System.out.println(targets.size() + " targets found");        
	        
	        //Create mutant
	        String mutantPath = "./resources/mutant/";
	        MutantGenerator mg = new MutantGenerator();
	        mg.mutateProjectForOperation(targets, path, mutantPath);
	        System.out.println("A mutant has been created");
	        
	        //Execute test suit for mutant
	//        TestRunner testRunner = new TestRunner();
	//        testRunner.runTests(classes, path, mutantPath);
	        
	        FileTools.deleteDirectoryContent(mutantPath);
	        System.out.println("Delete of the mutant");
	        
	        String c = null;
	        while(c == null || !(c.equals("1") || c.equals("2"))) {
	        	System.out.println("\nDo you want to generate another mutant ?");
		        System.out.println("1 : Yes");
		        System.out.println("2 : No");
            
    	    	BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
    	    	c = entree.readLine();
        	}
	        if(c.equals("2")) {
	        	testMutant = false;
	        }
        }
        System.out.println("\nGoodbye.");
    }
}
