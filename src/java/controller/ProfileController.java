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
import javax.faces.bean.ManagedBean;
import model.ProfileBean;

/**
 *
 * @author djfried
 */
@ManagedBean
@SessionScoped
public class ProfileController implements Serializable {

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
            return "response.xhtml"; // navigate to "response.xhtml"
        } else {
            return "error.xhml";
        }
    }

}
