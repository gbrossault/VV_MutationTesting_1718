package fr.istic.classFinder;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class ClassLoader {

	private String path;
	private ClassPool pool;
	
	public ClassLoader(String path) {
		this.path = path;
		this.pool = ClassPool.getDefault();
		try {
			this.pool.appendClassPath(this.path);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public CtClass getCtClass(String className) throws NotFoundException{
		CtClass ctClass = this.pool.get(className);
		return ctClass;
	}
	
	public CtMethod getMethodByName(CtClass ctClass, String name) throws NotFoundException {
		CtMethod ctm = ctClass.getDeclaredMethod(name);
		return ctm;
	}
}
