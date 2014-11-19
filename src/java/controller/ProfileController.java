/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProfileDAOImpl;
import dao.ProfileDAO;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import model.ProfileBean;

/**
 *
 * @author djfried
 */
@ManagedBean
@SessionScoped
public class ProfileController implements Serializable {
    
    String userEmail="";
    
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
        int rowCount = aProfileDAO.createProfile(theModel);  // Doing anything with the object after this?

        if (rowCount == 1) {
            return "home.xhtml"; // navigate to "response.xhtml"
        } else {
            return "error.xhml";
        }
    }
    public String login(){
        
       ProfileDAO aProfileDAO = new ProfileDAOImpl();
       boolean good = aProfileDAO.login(theModel);
       System.out.println("login email is "+ theModel.getLoginEmail());
       System.out.println("login password "+ theModel.getLoginPassword());
       System.out.println("result "+good);

       if(good ){
           userEmail = theModel.getEmail();
           return "home.xhtml";
       }
        
        return "";
    }
    
    public String logout()
    {   
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }
    public String searchForUniversity(){
    
        return "universitySearch.xhtml";
    }
    String studentResults ="";
    public void populateStudentSearch(){
    
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        studentResults = aProfileDAO.searchStudents();
          
    } 
    public String getStudentResults(){
            return studentResults;
    }


}
