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
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.swing.JOptionPane;
import model.ProfileBean;

/**
 *
 * @author djfried
 */
@ManagedBean
@SessionScoped
public class ProfileController implements Serializable {
    private String testVid = "http://www.youtube.com/v/XGSy3_Czz8k";
    private String userEmail="";
    private String status ="";
    private String pwEmail="";
    private String pwQuestion="";
    private String pwAnswer = "";
    private String signUpStatus="";
    private String pwResetResult="";
    private ArrayList<ProfileBean> studentResults=new ArrayList<ProfileBean>(); 
    private ProfileBean theModel;
    private ProfileBean viewModel;
    private String viewingProfile ="";
    private String messageStudentContent="Enter Message Here";
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
        getTheModel().setEmail(getTheModel().getEmail().toLowerCase());
        int rowCount = aProfileDAO.createProfile(getTheModel());  // Doing anything with the object after this?

        if (rowCount == 1) {
            return "home.xhtml"; // navigate to "response.xhtml"
        } else {
            setSignUpStatus("Email address already exist!");
            return "";
        }
    }
    
    public String saveProfile(){
    
    ProfileDAO aProfileDAO = new ProfileDAOImpl();
    boolean saved  = aProfileDAO.save(getTheModel());
    if(saved)return "home.xhtml";
    else return "";
    }
    
    public String login(){
        
      ProfileDAO aProfileDAO = new ProfileDAOImpl();
      getTheModel().setLoginEmail(getTheModel().getLoginEmail().toLowerCase());
      ProfileBean tempBean = aProfileDAO.login(getTheModel());
      System.out.println("AFTER DAO!!!!!!!!!");

       if(tempBean!=null ){
           System.out.println(tempBean.getEmail());
            setTheModel(tempBean);
           System.out.println("login model question is "+getTheModel().getSECURITYQUESTION());
           System.out.println("login model first name is "+getTheModel().getFirstName());
            setUserEmail(getTheModel().getEmail());
            if(getTheModel().getEmail().equalsIgnoreCase("admin@gmail.com")){
                return "admin.xhtml";
            }
           return "home.xhtml";
       }else{
            setStatus("Login Failed");
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
        aProfileDAO.delete(getTheModel());
        System.out.println("DELETED");
        return this.logout();
    }
    public String searchForUniversity(){
    
        return "universitySearch.xhtml";
    }
    public void populatePWResetQuestion(){
        //check DB for email, if it has one, get the question and set to pwQuestion, else set error message to pwQuestion
        setPwQuestion("Test Question");
    }
    public void requestPWReset(){
        //check if answer = the email DB account password, 
        //if correct, email a link to reset, else print error message.
        setPwResetResult(getPwAnswer());
    }
    public void populateStudentSearch(){
    
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        setStudentResults(aProfileDAO.searchStudents(getTheModel()));
    } 
    public ArrayList<ProfileBean> getStudentResults(){
            return studentResults;
    }
    public void setUpViewProfile(){
            
               ProfileDAO aProfileDAO = new ProfileDAOImpl();
      
       ProfileBean tempBean = aProfileDAO.getProfile(getViewingProfile());
        System.out.println("in setupVIEWPPROFILE");

       if(tempBean!=null ){
           System.out.println(tempBean.getEmail());
            setViewModel(tempBean);
           System.out.println("view model question is "+getTheModel().getSECURITYQUESTION());
           System.out.println("view model first name is "+getTheModel().getFirstName());
           //userEmail = theModel.getEmail();
          // return "home.xhtml";
       }
        
  
        
        
    }
    
    public String sendMessageToStudent(){
    
    System.out.println("Message will be:");
    System.out.println(getMessageStudentContent());
    //actually send an email here... SETH?
   // JOptionPane.showMessageDialog(null,"Message Sent To "+viewModel.getEmail());
    
    return "home.xhtml";
            
    
    }
    
    public String goToEditProfile(){return "editProfile.xhtml";}
    public String messageStudent() {return "messageStudent.xhtml";}
    public String viewOwnProfile(){
    
        setViewingProfile(getTheModel().getEmail());
        
        
        String response="profile.xhtml?faces-redirect=true";
        return response;
    }
    public String viewProfile(int index)
    {
        System.out.println(index);
        //this method needs to some how save the email of which profile to view.
        
        //for testing purposes, any viewing of proviles will be taken to the hasz1012@gmail.com profile.
        setViewingProfile("hasz1012@gmail.com");
//        viewModel= searchList.get(index);
        String response="profile.xhtml?faces-redirect=true";
        return response;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    public String goHome(){
        return "home.xhtml";
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

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @param studentResults the studentResults to set
     */
    public void setStudentResults(ArrayList<ProfileBean> studentResults) {
        this.studentResults = studentResults;
    }

    /**
     * @return the viewModel
     */
    public ProfileBean getViewModel() {
        return viewModel;
    }

    /**
     * @param viewModel the viewModel to set
     */
    public void setViewModel(ProfileBean viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * @return the viewingProfile
     */
    public String getViewingProfile() {
        return viewingProfile;
    }

    /**
     * @param viewingProfile the viewingProfile to set
     */
    public void setViewingProfile(String viewingProfile) {
        this.viewingProfile = viewingProfile;
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
     * @return the testVid
     */
    public String getTestVid() {
        return testVid;
    }

    /**
     * @param testVid the testVid to set
     */
    public void setTestVid(String testVid) {
        this.testVid = testVid;
    }

    /**
     * @return the pwEmail
     */
    public String getPwEmail() {
        return pwEmail;
    }

    /**
     * @param pwEmail the pwEmail to set
     */
    public void setPwEmail(String pwEmail) {
        this.pwEmail = pwEmail;
    }

    /**
     * @return the pwQuestion
     */
    public String getPwQuestion() {
        return pwQuestion;
    }

    /**
     * @param pwQuestion the pwQuestion to set
     */
    public void setPwQuestion(String pwQuestion) {
        this.pwQuestion = pwQuestion;
    }

    /**
     * @return the pwAnswer
     */
    public String getPwAnswer() {
        return pwAnswer;
    }

    /**
     * @param pwAnswer the pwAnswer to set
     */
    public void setPwAnswer(String pwAnswer) {
        this.pwAnswer = pwAnswer;
    }

    /**
     * @return the pwResetResult
     */
    public String getPwResetResult() {
        return pwResetResult;
    }

    /**
     * @param pwResetResult the pwResetResult to set
     */
    public void setPwResetResult(String pwResetResult) {
        this.pwResetResult = pwResetResult;
    }

   
    
}
