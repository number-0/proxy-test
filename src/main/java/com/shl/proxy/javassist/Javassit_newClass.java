package com.shl.proxy.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

/**
 * 在程序运行时，动态生成一个新的class
 * @author songhengliang
 * @date 2020/3/21
 */
public class Javassit_newClass {

  public static void main(String[] args)
      throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
    //ClassPool(boolean useDefaultPath)  useDefaultPath：是否使用默认的path
    ClassPool classPool = new ClassPool(true);
    //插入类路径，通过类路径去搜索我们要的类
    classPool.insertClassPath(new LoaderClassPath(Javassit_newClass.class.getClassLoader()));

    //构建一个新的Class对象
    CtClass targetClass = classPool.makeClass("com.shl.hello");
    //实现一个接口
    targetClass.addInterface(classPool.get(IHello.class.getName()));
    CtClass returnType = classPool.get(void.class.getName());
    String methodName = "sayHello";
    CtClass[] parameters = {classPool.get(String.class.getName())};
    CtMethod method = new CtMethod(returnType, methodName, parameters, targetClass);

    String src = "{"
        + "System.out.println($1);"
        + "System.out.println($args.toString());"
        + "System.out.println(\"hello\"+$1);"
        + "}";
    method.setBody(src);
    targetClass.addMethod(method);

    Class clazz = targetClass.toClass();
    IHello hello = (IHello) clazz.newInstance();
    hello.sayHello(" 通过javassist生成了一个class ");



  }

  public interface IHello {
    void sayHello(String name);
  }
}
