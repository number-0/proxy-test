package com.shl.proxy.javassist;

/**
 * 添加string
 * @author songhengliang
 * @date 2020/3/21
 */
public class AddStringOperate {

  /**
   * 注释部分代码为要通过javassit修改字节码插入的代码
   * 但是通过javassit加上注释部分后，编译会报错
   *    原因[不能引用在方法中其他地方定义的局部变量]：javassist插入的第一行注释部分代码和其他注释部分代码都是一个代码块(可以认为代码块是作用域)，
   *                                            而java的变量是有作用域的，变量只在当前作用域有效，所以start在sout中访问不了
   * 解决方式：见下方注释代码 "结果方案：addString2"
   * @param lenth
   * @return
   */
  public String addString(int lenth) {
//    long start = System.nanoTime();

    StringBuilder sb = new StringBuilder();
    for (int i=0; i<lenth; i++) {
      sb.append(i).append(",");
    }
    String result = sb.toString();

//    long end = System.nanoTime();
//    System.out.println(end - start);

    return result;
  }


  /*
  结果方案：addString2
  将原有的addString复制到addString$timeMonitor中，然后在addString$timeMonitor前和后插入时间监控代码
  public String addString(int lenth) {
    long start = System.nanoTime();

    String result = addString$timeMonitor(10000);

    long end = System.nanoTime();
    System.out.println(end - start);

    return result;
  }

  public String addString$timeMonitor(int lenth) {
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<lenth; i++) {
      sb.append(i).append(",");
    }
    String result = sb.toString();
    return result;
  }
  */
}
