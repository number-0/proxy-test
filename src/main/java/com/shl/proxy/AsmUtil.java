package com.shl.proxy;

import com.shl.proxy.domain.Cat;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * @author songhengliang
 * @date 2020/3/21
 */
public class AsmUtil {

  public static void main(String[] args) throws IOException {
    String path = "/" + "com.shl.proxy.domain.Cat".replaceAll("\\.", "/") + ".class";
    InputStream inputStream = AsmUtil.class.getResourceAsStream(path);

    //class读取器
    ClassReader classReader = new ClassReader(inputStream);
    //反编译成指令码 打印到控制台
    TraceClassVisitor traceClassVisitor = new TraceClassVisitor(new PrintWriter(System.out));

    classReader.accept(traceClassVisitor, ClassReader.SKIP_FRAMES);
  }


}
