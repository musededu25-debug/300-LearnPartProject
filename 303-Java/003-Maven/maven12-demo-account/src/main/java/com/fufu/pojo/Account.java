package com.fufu.pojo;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/10/16 21:53
 */


public class Account {

    private Integer id;
    private String name;
    private Double memory;

    public Account() {
    }

    public Account(Integer id, String name, Double memory) {
        this.id = id;
        this.name = name;
        this.memory = memory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", memory=" + memory +
                '}';
    }
}
