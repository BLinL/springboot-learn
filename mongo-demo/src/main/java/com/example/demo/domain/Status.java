package com.example.demo.domain;

public class Status {
    private Integer weight;
    private Integer height;

    public Status(Integer weight, Integer height) {
        this.weight = weight;
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Status{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }
}
