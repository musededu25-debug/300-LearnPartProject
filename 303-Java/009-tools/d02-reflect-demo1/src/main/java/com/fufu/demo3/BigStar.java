package com.fufu.demo3;

public class BigStar  implements Star {

    private String name;

    public BigStar() {
    }

    public BigStar(String name){
        this.name = name;
    }

    public String sing(String name){
        System.out.println(this.name + "优美的唱了" + name);
        return this.name + "谢谢大家！";
    }

    public void dance(){
        System.out.println(this.name + "优雅的跳舞");
    }

}
