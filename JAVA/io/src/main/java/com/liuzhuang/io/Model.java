package com.liuzhuang.io;

import java.io.Serializable;

public class Model implements Serializable {

  private static final long serialVersionUID = 1L;
  private int age;
  private String name;

  public Model() {
    this.age = 18;
    this.name = "xixi";
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Model [age=" + age + ", name=" + name + "]";
  }

}
