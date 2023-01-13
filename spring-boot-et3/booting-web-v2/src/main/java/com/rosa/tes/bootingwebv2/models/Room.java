package com.rosa.tes.bootingwebv2.models;

public class Room {
    private long ig;
    private String number;
    private String name;
    private String info;

    public Room() {
    }

    public Room(long ig, String number, String name, String info) {
        this.ig = ig;
        this.number = number;
        this.name = name;
        this.info = info;
    }

    public long getIg() {
        return ig;
    }

    public void setIg(long ig) {
        this.ig = ig;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
