package com.alin.benchmark.realm;

import io.realm.RealmObject;

public class UserRealm extends RealmObject {
    private String name;
    private int age;
    private long timestamp;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
