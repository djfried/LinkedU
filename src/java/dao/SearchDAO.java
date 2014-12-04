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
 * @author dbaile2
 */
public interface SearchDAO {
    public ArrayList<ProfileBean> search(ProfileBean p);
}
