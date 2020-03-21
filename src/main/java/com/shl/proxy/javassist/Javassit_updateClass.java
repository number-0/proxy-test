package com.shl.proxy.javassist;

import com.shl.proxy.javassist.Javassit_newClass.IHello;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

/**
 * 在程序运行时，修改class
 * @author songhengliang
 * @date 2020/3/21
 */
public class Javassit_updateClass {

  public static void main(String[] args) throws NotFoundException, CannotCompileException {
    //ClassPool(boolean useDefaultPath)  useDefaultPath：是否使用默认的path
    ClassPool classPool = new ClassPool(true);
    //实际场景，不会直接写上，会通过扫描注解，然后做修改，比如类似于spring扫描com.shl.*目录下带注解的类或方法
    String targetClassName = "com.shl.proxy.javassist.AddStringOperate";
    CtClass targetClass = classPool.get(targetClassName);
    CtMethod method = targetClass.getDeclaredMethod("addString");
    CtMethod timeMonitorMethod = CtNewMethod.copy(method, method.getName() + "$timeMonitor", targetClass, null);
    targetClass.addMethod(timeMonitorMethod);
    String src = "{"
        + "long start = System.nanoTime();"
        + "Object result=" + method.getName() + "$timeMonitor($$);"
        + "long end = System.nanoTime();"
        + "System.out.println(end - start);"
        + "return ($r)result;"
        + "}";
    method.setBody(src);
    //加载到当前classLoader
    targetClass.toClass(); //todo debug看一下源码

    //修改完class后，调用方法
    AddStringOperate addStringOperate = new AddStringOperate();
    addStringOperate.addString(10000);
  }
}
