/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;


/**
 *
 * @author djfried
 */
public class UniversityBean implements Serializable{
    String id;
    String name;
    String address;
    String city;
    String state;
    String zip;
    String phone;
    String web;
    /**
     * Creates a new instance of UniversityBean
     * @param theID
     * @param theName
     * @param theAddress
     * @param theZip
     * @param theState
     * @param theCity
     * @param thePhone
     * @param theWeb
     */
    public UniversityBean(String theID, String theName, String theAddress, String theCity, String theState,
            String theZip, String thePhone, String theWeb) 
    {
        id=theID;
        name=theName;
        address=theAddress;
        city=theCity;
        state=theState;
        zip=theZip;
        phone=thePhone;
        web=theWeb;
    }
    public UniversityBean(String theName, String theCity, String theState, String theWeb)
    {
        name=theName;
        city=theCity;
        state=theState;
        web=theWeb;
    }
    public UniversityBean()
    {
        
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    
}
