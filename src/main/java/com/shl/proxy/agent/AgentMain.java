package com.shl.proxy.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author songhengliang
 * @date 2020/3/21
 */
public class AgentMain {

  public static void main(String[] args) {
    System.out.println("hello main excute...");
  }

  public static void premain(String[] args, Instrumentation instrumentation) {
    System.out.println("hello premain agent ...");
  }
}
