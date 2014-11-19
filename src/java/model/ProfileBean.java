/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author djfried
 */
public class ProfileBean {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String loginEmail;
    private String loginPassword;

    public ProfileBean(String fname, String lname, String em) {
        firstName=fname;
        lastName=lname;
        email=em;
    }
    public ProfileBean()
    {
     
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the loginEmail
     */
    public String getLoginEmail() {
        return loginEmail;
    }

    /**
     * @param loginEmail the loginEmail to set
     */
    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    /**
     * @return the loginPassword
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * @param loginPassword the loginPassword to set
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    
}
