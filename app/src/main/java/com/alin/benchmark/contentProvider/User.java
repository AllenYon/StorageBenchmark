package com.alin.benchmark.contentProvider;

public class User {
    private int _id;
    private int id;
    private String name;
    private String phone;

    /**
     * @return the _id
     */
    public int get_id() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void set_id(int _id) {
        this._id = _id;
    }

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

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
