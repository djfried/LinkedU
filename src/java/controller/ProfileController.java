/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProfileDAOImpl;
import dao.ProfileDAO;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import model.ProfileBean;

/**
 *
 * @author djfried
 */
@ManagedBean
@SessionScoped
public class ProfileController implements Serializable {
    
    String userEmail="";
    private String status ="";
    private String signUpStatus="";
    private ArrayList<ProfileBean> studentResults=new ArrayList<ProfileBean>(); 
    private ProfileBean theModel;

    /**
     * Creates a new instance of profileController
     */
    public ProfileController() {
        theModel = new ProfileBean();
    }
    public ProfileBean getTheModel() {
        return theModel;
    }

    public void setTheModel(ProfileBean theModel) {
        this.theModel = theModel;
    }

    public String createProfile() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        theModel.setEmail(theModel.getEmail().toLowerCase());
        int rowCount = aProfileDAO.createProfile(theModel);  // Doing anything with the object after this?

        if (rowCount == 1) {
            return "home.xhtml"; // navigate to "response.xhtml"
        } else {
            signUpStatus = "Email address already exist!";
            return "";
        }
    }
    public String login(){
        
       ProfileDAO aProfileDAO = new ProfileDAOImpl();
       theModel.setLoginEmail(theModel.getLoginEmail().toLowerCase());
       ProfileBean tempBean = aProfileDAO.login(theModel);
      System.out.println("AFTER DAO!!!!!!!!!");

       if(tempBean!=null ){
           System.out.println(tempBean.getEmail());
           theModel.setEmail(tempBean.getEmail());
           theModel.setPassword(tempBean.getPassword());
           theModel.setFirstName(tempBean.getFirstName());
           theModel.setLastName(tempBean.getLastName());
           System.out.println("login model first name is "+theModel.getFirstName());
           userEmail = theModel.getEmail();
           return "home.xhtml";
       }else{
           status = "Login Failed";
       }
        
        return "";
    }
    
    public String logout()
    {   
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }
    public String deleteAccount()
    {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        aProfileDAO.delete(theModel);
        System.out.println("DELETED");
        return this.logout();
    }
    public String searchForUniversity(){
    
        return "universitySearch.xhtml";
    }
   
    public void populateStudentSearch(){
    
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        studentResults = aProfileDAO.searchStudents(theModel);
    } 
    public ArrayList<ProfileBean> getStudentResults(){
            return studentResults;
    }
    public String viewProfile()
    {
        String response="profile.xhtml?faces-redirect=true";
        return response;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the signUpStatus
     */
    public String getSignUpStatus() {
        return signUpStatus;
    }

    /**
     * @param signUpStatus the signUpStatus to set
     */
    public void setSignUpStatus(String signUpStatus) {
        this.signUpStatus = signUpStatus;
    }
    
}
