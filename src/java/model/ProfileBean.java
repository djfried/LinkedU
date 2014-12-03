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
    private String GPA;
    private String OUTOF;
    private String ACT;
    private String SAT;
    private String TAGLINE;
    private String PICTURE;
    private String MATERIAL;
    private String SECURITYQUESTION;
    private String SECURITYANSWER;

    public ProfileBean(String fname, String lname, String em) {
        firstName=fname;
        lastName=lname;
        email=em;
    }
        public ProfileBean(String fname, String lname, String em,String pw, String GPA, String OUTOF, String ACT, String SAT, String TAGLINE, String PICTURE, String MATERIAL, String SECURITYQUESTION, String SECURITYANSWER) {
        firstName=fname;
        lastName=lname;
        email=em;
        password=pw;
        this.GPA = GPA;
        this.OUTOF = OUTOF;
        this.ACT = ACT;
        this.SAT = SAT;
        this.TAGLINE = TAGLINE;
        this.PICTURE = PICTURE;
        this.MATERIAL = MATERIAL;
        this.SECURITYQUESTION = SECURITYQUESTION;
        this.SECURITYANSWER = SECURITYANSWER;
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

    /**
     * @return the GPA
     */
    public String getGPA() {
        return GPA;
    }

    /**
     * @param GPA the GPA to set
     */
    public void setGPA(String GPA) {
        this.GPA = GPA;
    }

    /**
     * @return the OUTOF
     */
    public String getOUTOF() {
        return OUTOF;
    }

    /**
     * @param OUTOF the OUTOF to set
     */
    public void setOUTOF(String OUTOF) {
        this.OUTOF = OUTOF;
    }

    /**
     * @return the ACT
     */
    public String getACT() {
        return ACT;
    }

    /**
     * @param ACT the ACT to set
     */
    public void setACT(String ACT) {
        this.ACT = ACT;
    }

    /**
     * @return the SAT
     */
    public String getSAT() {
        return SAT;
    }

    /**
     * @param SAT the SAT to set
     */
    public void setSAT(String SAT) {
        this.SAT = SAT;
    }

    /**
     * @return the TAGLINE
     */
    public String getTAGLINE() {
        return TAGLINE;
    }

    /**
     * @param TAGLINE the TAGLINE to set
     */
    public void setTAGLINE(String TAGLINE) {
        this.TAGLINE = TAGLINE;
    }

    /**
     * @return the PICTURE
     */
    public String getPICTURE() {
        return PICTURE;
    }

    /**
     * @param PICTURE the PICTURE to set
     */
    public void setPICTURE(String PICTURE) {
        this.PICTURE = PICTURE;
    }

    /**
     * @return the MATERIAL
     */
    public String getMATERIAL() {
        return MATERIAL;
    }

    /**
     * @param MATERIAL the MATERIAL to set
     */
    public void setMATERIAL(String MATERIAL) {
        this.MATERIAL = MATERIAL;
    }

    /**
     * @return the SECURITYQUESTION
     */
    public String getSECURITYQUESTION() {
        return SECURITYQUESTION;
    }

    /**
     * @param SECURITYQUESTION the SECURITYQUESTION to set
     */
    public void setSECURITYQUESTION(String SECURITYQUESTION) {
        this.SECURITYQUESTION = SECURITYQUESTION;
    }

    /**
     * @return the SECURITYANSWER
     */
    public String getSECURITYANSWER() {
        return SECURITYANSWER;
    }

    /**
     * @param SECURITYANSWER the SECURITYANSWER to set
     */
    public void setSECURITYANSWER(String SECURITYANSWER) {
        this.SECURITYANSWER = SECURITYANSWER;
    }
    
}
