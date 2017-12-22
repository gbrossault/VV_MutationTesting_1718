package fr.istic.classFinder;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class ClassLoader {

	private String path;
	private ClassPool pool;
	
	public ClassLoader(String path) throws NotFoundException {
		this.path = path;
		this.pool = ClassPool.getDefault();
		this.pool.appendClassPath(this.path);
	}
	
	public CtClass getCtClass(String packageName, String className) throws NotFoundException{
		CtClass ctClass = this.pool.get(packageName + "."+className);
		return ctClass;
	}
	
	public CtMethod getMethodByName(CtClass ctClass, String name) throws NotFoundException {
		CtMethod ctm = ctClass.getDeclaredMethod(name);
		return ctm;
	}
}
