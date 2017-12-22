package fr.istic.vv.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.Mnemonic;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	System.out.println(System.getProperty("user.dir"));
        String jarFile = "./resources/VV_BDM_1718_DummyProject-1.0-SNAPSHOT.jar";
        JarFile jar = new JarFile(jarFile);
        
        Enumeration<JarEntry> jarEntries = jar.entries();

        while (jarEntries.hasMoreElements()) {
            final JarEntry jarEntry = jarEntries.nextElement();

            if (jarEntry.getName().endsWith(".class")) {
                InputStream is = null;
                CtClass ctClass = null;
                try {
                    is = jar.getInputStream(jarEntry);
                    ClassPool cp = ClassPool.getDefault();
                    ctClass = cp.makeClass(is);
                } catch (IOException ioex1) {
                    throw new Exception(
                            "Could not load class from JAR entry [" + jarEntry.getName() + "].");
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException ignored) {
                    }
                }
                System.out.println(ctClass.getName());
                ClassFile cf = new ClassFile(false, ctClass.getName(), null);
                CtMethod[] methods = ctClass.getDeclaredMethods();
                for(CtMethod method : methods) {
                	System.out.println(method.getName());
                	CodeAttribute ca = method.getMethodInfo().getCodeAttribute();
                	CodeIterator ci = ca.iterator();
                	while(ci.hasNext()) {
                		int index = ci.next();
                		int op = ci.byteAt(index);
                		System.out.println("op : "+op + " - " + Mnemonic.OPCODE[op]);
                		if(Mnemonic.OPCODE[op].equals("iadd")) {
                			System.out.println("addition");
//                			Bytecode b = new Bytecode(cf.getConstPool());
//                			b.add(64);
                		}
                	}
                }
            }
        }
    }
}
