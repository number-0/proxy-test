package com.shl.proxy.domain;

import com.shl.proxy.JavaProxyTest;

/**
 * @author songhengliang
 * @date 2020/3/19
 */
public class Cat implements ICat {

  @Override
  public void catRun() {
    System.out.println("猫跑的很快");
  }
}
