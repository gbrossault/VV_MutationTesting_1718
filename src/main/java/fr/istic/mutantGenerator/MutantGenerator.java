package fr.istic.mutantGenerator;

import java.io.IOException;
import java.util.List;

import fr.istic.classFinder.ClassLoader;
import fr.istic.mutationTarget.MutationTarget;
import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;

public class MutantGenerator {
    public String scannedPackage;
    
    public void mutateProjectForOperation(List<MutationTarget> targets, String path, String mutantPath) throws NotFoundException, BadBytecode, IOException {
        FileTools.copyFolderToFolder(path,"", path, mutantPath);
        ClassLoader loader = new ClassLoader(mutantPath);
    	for(MutationTarget target : targets) {
    		CtClass ctclass = loader.getCtClass(target.getPackageName(), target.getCtClassName().replace(".class", ""));
    		CtMethod ctMethod = loader.getMethodByName(ctclass, target.getCtMethodName());
    		CodeAttribute ca = ctMethod.getMethodInfo().getCodeAttribute();
    	    CodeIterator ci = ca.iterator();
    	    
    	    while (ci.hasNext()) {
    	        int index = ci.next();
    	        if(index == target.getTargetIndex()){
    		        int valueOfIndex8Bit = ci.byteAt(index);
    		        if(valueOfIndex8Bit==target.getCurrentInstructions()) {
    		            ci.writeByte(target.getMutatedInstructions(), index);
    		            break;
    		        }
    	        }
    	    }
    	}
    }
    
    public void mutateProjectForVoidMetho(List<MutationTarget> targets, String path, String mutantPath) throws NotFoundException, BadBytecode, IOException, CannotCompileException {
        FileTools.copyFolderToFolder(path,"", path, mutantPath);
        ClassLoader loader = new ClassLoader(mutantPath);
    	for(MutationTarget target : targets) {
    		CtClass ctclass = loader.getCtClass(target.getPackageName(), target.getCtClassName().replace(".class", ""));
    		CtMethod ctMethod = loader.getMethodByName(ctclass, target.getCtMethodName());
    		ctMethod.setBody("");    	    
    	}
    }
}
