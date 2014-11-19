/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.ProfileBean;

/**
 *
 * @author djfried
 */
public interface ProfileDAO {
    public int createProfile(ProfileBean aProfile);
    public ProfileBean[] findAll();
    public ProfileBean findByName(String aName); 
    public boolean login(ProfileBean theModel);
    
}
