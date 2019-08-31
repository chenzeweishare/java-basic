package com.czw.basic.design.创建型模式.工厂模式;

public class Square implements Shape {
 
   @Override
   public void draw() {
      System.out.println("Inside Square::draw() method.");
   }
}