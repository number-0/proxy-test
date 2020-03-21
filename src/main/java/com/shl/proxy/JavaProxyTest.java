package com.shl.proxy;

import com.shl.proxy.domain.Cat;
import com.shl.proxy.domain.ICat;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;
import sun.misc.IOUtils;
import sun.misc.ProxyGenerator;

/**
 * @author songhengliang
 * @date 2020/3/19
 */
public class JavaProxyTest {

  class DynamicProxyHandler implements InvocationHandler {

    private Object realObject;

    public DynamicProxyHandler(Object realObject) {
      this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      //代理扩展逻辑
      System.out.println("代理前置逻辑");

      return method.invoke(realObject, args);
    }
  }


  @Test
  public void test3() throws IOException {
    ICat cat = new Cat();
    ICat proxy =
        (ICat) Proxy.newProxyInstance(
                JavaProxyTest.class.getClassLoader(),
                cat.getClass().getInterfaces(),
                new DynamicProxyHandler(cat));
    proxy.catRun();


    //proxy是新增的class
    proxy.getClass();

    byte[] bytes = ProxyGenerator.generateProxyClass("ICat$Proxy", new Class[]{ICat.class});
    Files.write(new File("/Users/workoffice/java/workspace-shl/proxy-test/src/main/java/com/shl/proxy/domain/ICat$Proxy.class").toPath(),
        bytes);

  }

}
