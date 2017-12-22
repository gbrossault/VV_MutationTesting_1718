package fr.istic.classFinder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.istic.bytecodeModificator.MutationTargetFinderForMethodsReturnType;
import fr.istic.model.DummyClass;
import fr.istic.model.DummyMethod;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.MethodInfo;

public class ClassFinder {
    private static final char PKG_SEPARATOR = '.';
    
    private static final String PKG_TEST = ".test.";
    //Represent the method
    private static final String CLASS_FILE_SUFFIX = ".class";

    private ClassPool pool = null;
    
    public ClassFinder() {
    }
    
    public List<DummyClass> findAllClasses(File file, String scannedPackage) throws BadBytecode, NotFoundException{
    	this.pool = ClassPool.getDefault();
    	return this.find(file, scannedPackage);
    }
    
    public List<DummyClass> find(File file, String scannedPackage) throws BadBytecode, NotFoundException {
        List<DummyClass> classes = new ArrayList<DummyClass>();
        String resource = scannedPackage + PKG_SEPARATOR + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(find(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            DummyClass innerClass = new DummyClass(file.getName(), file.getAbsolutePath().replace("/"+file.getName(), ""));
            CtClass ctClass = null;
			try {
				InputStream is = new FileInputStream(file);
				ctClass = pool.makeClass(is);
				innerClass.setCorrespondingCtClass(ctClass);
				if(resource.contains(PKG_TEST)) {
					innerClass.setTestClass(true);
				}
				CtMethod[] methods = ctClass.getDeclaredMethods();
	            for(CtMethod method : methods) {
	            	DummyMethod dmethod = new DummyMethod(method.getName());
	            	MethodInfo methodInfo = method.getMethodInfo();
	            	if(methodInfo != null) {
	            		dmethod.setReturnType(this.extractReturnTypeFromSignature(method.getSignature()));
		            	CodeAttribute ca = methodInfo.getCodeAttribute();
		            	if(ca != null) {
		            		CodeIterator ci = ca.iterator();
			            	while(ci.hasNext()) {
			            		int index = ci.next();
			            		int op = ci.byteAt(index);
			            		dmethod.addBytecode(index, op);
			            	}
			    			innerClass.addMethods(dmethod);
		            	}
	            	}
	            }
	            classes.add(innerClass);
			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return classes;
    }
    
    public String extractReturnTypeFromSignature(String signature) {
    	String returnType = null;
    	String[] signatureElts = signature.split("\\)");
    	if(signatureElts.length > 1) {
	    	String[] type = signatureElts[1].replace(";", "").split("/");
	    	switch(type[type.length-1]) {
		    	case "I":
		    		returnType = MutationTargetFinderForMethodsReturnType.INT_RETURN_TYPE;
		    		break;
		    	case "V":
		    		returnType = MutationTargetFinderForMethodsReturnType.VOID_RETURN_TYPE;
		    		break;
		    	default:
		    		returnType = type[type.length-1];
		    		break;
	    	}
    	}
    	return returnType;
    }
}
