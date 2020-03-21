package com.shl.proxy;

import com.shl.proxy.domain.Cat;
import java.io.InputStream;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;
import sun.misc.IOUtils;

/**
 * @author songhengliang
 * @date 2020/3/19
 */
public class JavassistProxyTest {
  @Test
  public void test() throws Exception {
    byte[] bytes = build();

    Cat cat = new Cat();
    cat.catRun();
  }

  /**
   * 在原有的Cat.class字节码的catRun中插入一行语句
   *        System.out.println(System.currentTimeMillis() + ":cat");
   * @return
   * @throws Exception
   */
  private byte[] build() throws Exception {
//    String path = "/" + "com.shl.proxy.domain.Cat".replaceAll("\\.", "/") + ".class";
//    InputStream inputStream = JavassistProxyTest.class.getResourceAsStream(path);
//    byte[] bytes = IOUtils.readFully(inputStream, -1, false);
    ClassPool classPool = new ClassPool();
    classPool.appendSystemPath();
    CtClass ctClass = classPool.getCtClass("com.shl.proxy.domain.Cat");
    CtMethod ctMethod = ctClass.getDeclaredMethod("catRun");
    ctMethod.insertBefore("System.out.println(System.currentTimeMillis() + \":cat\");");
    ctClass.toClass();

    return ctClass.toBytecode();
  }
}
