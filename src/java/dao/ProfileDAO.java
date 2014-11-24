/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.ProfileBean;

/**
 *
 * @author djfried
 */
public interface ProfileDAO {
    public int createProfile(ProfileBean aProfile);
    public ProfileBean[] findAll();
    public ProfileBean findByName(String aName); 
    public ProfileBean login(ProfileBean theModel);
    public ArrayList<ProfileBean> searchStudents(ProfileBean theModel);

    public void delete(ProfileBean theModel);
}
