/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.SearchDAO;
import dao.SearchDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.ProfileBean;

/**
 *
 * @author dbaile2
 */
@ManagedBean
@SessionScoped
public class SearchController implements Serializable {
    private ProfileBean theModel;
    private ProfileBean viewModel;
    private ArrayList<ProfileBean> searchResult = new ArrayList<ProfileBean>();
    private String messageStudentContent="Enter Message Here";
    @ManagedProperty(value="#{profileController.theModel}")
    private ProfileBean userProfile;
    
    public SearchController(){
        theModel = new ProfileBean();
    }
    
    public void search(){
        System.out.println("BEEEEEEEEEEEEEEFFFFFFFFFFFFFFFFOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRRRRRRRRRRRREEEEEEEEEEEEEEEEEEEEE");
        SearchDAO search = new SearchDAOImpl();
        setSearchResult(search.search(getTheModel()));
        System.out.println(getSearchResult().size());
    }

    /**
     * @return the theModel
     */
    public ProfileBean getTheModel() {
        return theModel;
    }

    /**
     * @param theModel the theModel to set
     */
    public void setTheModel(ProfileBean theModel) {
        this.theModel = theModel;
    }

    /**
     * @return the searchResult
     */
    public ArrayList<ProfileBean> getSearchResult() {
        return searchResult;
    }

    /**
     * @param searchResult the searchResult to set
     */
    public void setSearchResult(ArrayList<ProfileBean> searchResult) {
        this.searchResult = searchResult;
    }
    
    public String viewProfile(int index)
    {
        System.out.println(index);
        //this method needs to some how save the email of which profile to view.
        
        //for testing purposes, any viewing of proviles will be taken to the hasz1012@gmail.com profile.
        setViewModel(searchResult.get(index));
        System.out.println(userProfile.getFirstName());
        System.out.println(userProfile.getFirstName());
        System.out.println(userProfile.getFirstName());
        System.out.println(userProfile.getFirstName());
        String response="searchedProfile.xhtml?faces-redirect=true";
        return response;
    }

    /**
     * @return the viewingProfile
     */
    public ProfileBean getViewModel() {
        return viewModel;
    }

    /**
     * @param viewModel the viewingProfile to set
     */
    public void setViewModel(ProfileBean viewModel) {
        this.viewModel = viewModel;
    }
    
    public String messageStudent() {return "messageStudent.xhtml";}
    
    public String sendMessageToStudent(){
    
    System.out.println("Message will be:");
    System.out.println(getMessageStudentContent());
    //actually send an email here... SETH?
   // JOptionPane.showMessageDialog(null,"Message Sent To "+viewModel.getEmail());
    return "home.xhtml";
    }

    /**
     * @return the messageStudentContent
     */
    public String getMessageStudentContent() {
        return messageStudentContent;
    }

    /**
     * @param messageStudentContent the messageStudentContent to set
     */
    public void setMessageStudentContent(String messageStudentContent) {
        this.messageStudentContent = messageStudentContent;
    }

    /**
     * @return the userProfile
     */
    public ProfileBean getUserProfile() {
        return userProfile;
    }

    /**
     * @param userProfile the userProfile to set
     */
    public void setUserProfile(ProfileBean userProfile) {
        this.userProfile = userProfile;
    }
    
    public String goHome(){
        return "home.xhtml";
    }
        
    
}
