package com.alin.benchmark.dbflow;

import com.dbflow5.annotation.Column;
import com.dbflow5.annotation.PrimaryKey;
import com.dbflow5.annotation.Table;
import com.dbflow5.annotation.Unique;
import com.dbflow5.structure.BaseModel;

import java.io.Serializable;

@Table(database = AppDatabase.class)
public class User extends BaseModel implements Serializable {
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    @Unique
    private String name; // private with getters and setters

    @Column
    int age;
    @Column
    long timestamp;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
