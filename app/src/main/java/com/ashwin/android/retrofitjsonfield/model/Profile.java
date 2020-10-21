package com.ashwin.android.retrofitjsonfield.model;

import org.json.JSONObject;

public class Profile {
    private String id;
    private String firstname;
    private String lastname;

    // Approach 1: Use HashMap
    //private Map<String, Object> address;

    // Approach 2: Use GsonDeserializer
    private JSONObject address;

    public Profile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /*public Map<String, Object> getAddress() {
        return address;
    }*/

    public JSONObject getAddress() {
        return address;
    }

    /*public void setAddress(Map<String, Object> address) {
        this.address = address;
    }*/

    public void setAddress(JSONObject address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address=" + address +
                '}';
    }
}
