package com.alin.benchmark.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(useGetSet = true, columnName = "name")
    private String name;

    @DatabaseField(useGetSet = true, columnName = "age")
    private int age;

    @DatabaseField(useGetSet = true, columnName = "timestamp")
    private long timestamp;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * @param name the name to set
     */
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

    /**
     *
     */
    public User() {
        super();
    }

}
