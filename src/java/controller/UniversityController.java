/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UniversityDAO;
import dao.UniversityDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.UniversityBean;

/**
 *
 * @author djfried
 */
@ManagedBean(name = "universityController")
@ViewScoped
public class UniversityController implements Serializable {
    
    private ArrayList<UniversityBean> universityResults;
    private UniversityBean theModel;
    /**
     * Creates a new instance of UniversityController
     */
    public UniversityController() {
        theModel=new UniversityBean();
    }
    public ArrayList<UniversityBean> getUniversityResults() {
        return universityResults;
    }

    public void setUniversityResults(ArrayList<UniversityBean> universityResults) {
        this.universityResults = universityResults;
    }

    public UniversityBean getTheModel() {
        return theModel;
    }

    public void setTheModel(UniversityBean theModel) {
        this.theModel = theModel;
    }
    
    public void populateUniversitySearch()
    {
        
        UniversityDAO aUniDAO = new UniversityDAOImpl();
        setUniversityResults(aUniDAO.searchUniversity(theModel));  
    }
    public String viewProfile(int index)
    {
        
        return("home.xhtml");
    }
    
}
