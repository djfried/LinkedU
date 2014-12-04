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
    
    private UniversityBean viewModel;
    private ArrayList<UniversityBean> universityResults;
    private UniversityBean theModel;
    private String viewingUniversity="";
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

    public UniversityBean getViewModel() {
        return viewModel;
    }

    public void setViewModel(UniversityBean viewModel) {
        this.viewModel = viewModel;
    }

    public String getViewingUniversity() {
        return viewingUniversity;
    }

    public void setViewingUniversity(String viewingUniversity) {
        this.viewingUniversity = viewingUniversity;
    }
    
    public void populateUniversitySearch()
    {
        
        UniversityDAO aUniDAO = new UniversityDAOImpl();
        setUniversityResults(aUniDAO.searchUniversity(theModel));  
    }
    public String viewProfile(int index)
    {
        setViewModel(universityResults.get(index));
        System.out.println("NAME: "+viewModel.getName());
        return("searchedUniversity.xhtml?faces-redirect=true");
    }
    public void setUpViewProfile()
    {
        UniversityDAO aUniDAO = new UniversityDAOImpl();
      
       UniversityBean tempBean = aUniDAO.getProfile(getViewingUniversity());
        System.out.println("in setupVIEWUNIVERSITY");

       if(tempBean!=null ){
           System.out.println(tempBean.getName());
           setViewModel(tempBean);
           
        }
    }
    
}
