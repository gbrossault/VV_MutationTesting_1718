package fr.istic;

import java.io.File;
import java.util.List;

import org.junit.Test;

import fr.istic.bytecodeModificator.BytecodeElements;
import fr.istic.bytecodeModificator.MutationTargetFinderForArithmeticOperators;
import fr.istic.bytecodeModificator.MutationTargetFinderForMethodsReturnType;
import fr.istic.classFinder.ClassFinder;
import fr.istic.model.DummyClass;
import fr.istic.model.DummyMethod;
import fr.istic.mutationTarget.MutationTarget;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import junit.framework.TestCase;

/**
 * Unit test.
 */
public class AppTest extends TestCase {
    
	private final String path = "/home/gbrossault/Documents/M2_ILA/Java_FX/My512";
    private static final String CLASS_FILE_SUFFIX = ".class";
    
	private int getNbClasses(String folder) {
		int count = 0;
		File file = new File(folder);
		if(file.exists()) {
			return countClassesInFolder(file);
		}
		return count;
	}
	
	private int countClassesInFolder(File file) {
		if (file.isDirectory()) {
			int count = 0;
            for (File child : file.listFiles()) {
                count += countClassesInFolder(child);
            }
            return count;
        } else if (file.getName().toString().toLowerCase().endsWith(CLASS_FILE_SUFFIX)) {
        	return 1;
        } else {
        	return 0;
        }
	}
	
	//Test if ClassFinder.find find classes in project
	@Test
	public final void testFindClasses() throws BadBytecode, NotFoundException {
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
		assertTrue(classes.size()>0);
	}
	
	//Test if ClassFinder.find create the DummyClass corresponding to 
	//all the classes in the project
	@Test
	public final void testFindAllClasses() throws BadBytecode, NotFoundException {
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
		assertEquals(classes.size(), getNbClasses(path));
	}
	
	//Test if ClassFinder.find find methods in classes
	@Test
	public final void testFindMethodInClasses() throws BadBytecode, NotFoundException {
		boolean method = false;
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
		for(DummyClass dclass : classes) {
			if(dclass.getMethods().size() > 0) {
				method = true;
			}
		}
		assertTrue(method);
	}
	
	////Test if ClassFinder.find get method's bytecode
	@Test
	public final void testFindBytecodeOfClasses() throws BadBytecode, NotFoundException {
		boolean bytecodeGot = true;
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
		for(DummyClass dclass : classes) {
			for(DummyMethod method : dclass.getMethods()) {
				if(method.getContent().isEmpty()) {
					bytecodeGot = false;
				}
			}
		}
		assertTrue(bytecodeGot);
	}
	
	//Test if MutationTargetFinderForArithmeticOperator create the correct number of target
	//for add operator
	@Test
	public final void testTargetNumberForADDOperator() throws BadBytecode, NotFoundException {
		int nbTargetNeeded = 0;
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
        MutationTargetFinderForArithmeticOperators ao = new MutationTargetFinderForArithmeticOperators();
		List<MutationTarget> targets = ao.findTarget(classes, BytecodeElements.IADD_OPERATOR, BytecodeElements.IADD_OPERATOR);
	}
	
	//Test if MutationTargetFinderForArithmeticOperator create the correct number of target
	//for sub operator
	@Test
	public final void testTargetNumberForSUBOperator() throws BadBytecode, NotFoundException {
		int nbTargetNeeded = 0;		
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
        MutationTargetFinderForArithmeticOperators ao = new MutationTargetFinderForArithmeticOperators();
		List<MutationTarget> targets = ao.findTarget(classes, BytecodeElements.ISUB_OPERATOR, BytecodeElements.ISUB_OPERATOR);
	}
	
	//Test if MutationTargetFinderForArithmeticOperator create the correct number of target
	//for mul operator
	@Test
	public final void testTargetNumberForMULOperator() throws BadBytecode, NotFoundException {
		int nbTargetNeeded = 0;
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
        for(DummyClass dclass : classes) {
        	for(DummyMethod method : dclass.getMethods()) {
        		if(method.contains(BytecodeElements.IMUL_OPERATOR)) {
        			nbTargetNeeded++;
        		}
        	}
        }
        MutationTargetFinderForArithmeticOperators ao = new MutationTargetFinderForArithmeticOperators();
		List<MutationTarget> targets = ao.findTarget(classes, BytecodeElements.IMUL_OPERATOR, BytecodeElements.IMUL_OPERATOR);
		assertEquals(nbTargetNeeded, targets.size());
	}
	
	//Test if MutationTargetFinderForArithmeticOperator create the correct number of target
	//for div operator
	@Test
	public final void testTargetNumberForDIVOperator() throws BadBytecode, NotFoundException {
		int nbTargetNeeded = 0;
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
        for(DummyClass dclass : classes) {
        	for(DummyMethod method : dclass.getMethods()) {
        		if(method.contains(BytecodeElements.IDIV_OPERATOR)) {
        			nbTargetNeeded++;
        		}
        	}
        }
        MutationTargetFinderForArithmeticOperators ao = new MutationTargetFinderForArithmeticOperators();
		List<MutationTarget> targets = ao.findTarget(classes, BytecodeElements.IDIV_OPERATOR, BytecodeElements.IDIV_OPERATOR);
		assertEquals(nbTargetNeeded, targets.size());
	}

	//Test if MutationTargetFinderForArithmeticOperator create target if
	//void methods have been found
	@Test
	public final void testTargetForVoidMethods() throws BadBytecode, NotFoundException {
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
		boolean voidMethods = false;
		for(DummyClass dclass : classes) {
			for(DummyMethod method : dclass.getMethods()) {
				if(method.getReturnType().equals(MutationTargetFinderForMethodsReturnType.VOID_RETURN_TYPE)) {
					voidMethods = true;
					break;
				}
			}
		}
        MutationTargetFinderForMethodsReturnType ao = new MutationTargetFinderForMethodsReturnType();
		List<MutationTarget> targets = ao.findTarget(classes, MutationTargetFinderForMethodsReturnType.VOID_RETURN_TYPE);
		assertTrue(!targets.isEmpty() && voidMethods);
	}
	
	//Test if MutationTargetFinderForArithmeticOperator don't take in account main
	@Test
	public final void testTargetMainForVoidMethods() throws BadBytecode, NotFoundException {
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
        MutationTargetFinderForMethodsReturnType ao = new MutationTargetFinderForMethodsReturnType();
		List<MutationTarget> targets = ao.findTarget(classes, MutationTargetFinderForMethodsReturnType.VOID_RETURN_TYPE);
		boolean main = false;
		for(MutationTarget target : targets) {
			if(target.getCtClassName().equals(MutationTargetFinderForMethodsReturnType.MAIN_FUNCTION)) {
				main = true;
			}
		}
		assertFalse(main);
	}
	
	//Test if MutationTargetFinderForArithmeticOperator create all target for void method expected the main
	@Test
	public final void testTargetNbForVoidMethods() throws BadBytecode, NotFoundException {
        ClassFinder cf = new ClassFinder();
        List<DummyClass> classes = cf.findAllClasses(new File(path), path);
		int count = 0;
		for(DummyClass dclass : classes) {
			for(DummyMethod method : dclass.getMethods()) {
				if(method.getReturnType().equals(MutationTargetFinderForMethodsReturnType.VOID_RETURN_TYPE) && !method.getName().equals(MutationTargetFinderForMethodsReturnType.MAIN_FUNCTION)) {
					count++;
				}
			}
		}
        MutationTargetFinderForMethodsReturnType ao = new MutationTargetFinderForMethodsReturnType();
		List<MutationTarget> targets = ao.findTarget(classes, MutationTargetFinderForMethodsReturnType.VOID_RETURN_TYPE);
		assertEquals(count, targets.size());
	}
}
