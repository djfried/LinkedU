/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.UniversityBean;

/**
 *
 * @author djfried
 */
public interface UniversityDAO {
    
    public ArrayList<UniversityBean> searchUniversity();
    public ArrayList<UniversityBean> searchUniversity(String name,String city,
            String state);
    
}
