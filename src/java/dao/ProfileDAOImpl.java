/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import model.ProfileBean;

/**
 *
 * @author djfried
 */
public class ProfileDAOImpl implements ProfileDAO{

    @Override
    public int createProfile(ProfileBean aProfile) {
        int rowCount = 0;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        try {
            String myDB = "jdbc:derby://localhost:1527/linkedu";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            String insertSQL="INSERT INTO linkedu.Users"
                    +"(FIRSTNAME, LASTNAME, EMAIL, PASSWORD) VALUES"
                    +"(?,?,?,?)";
          PreparedStatement ps=DBConn.prepareStatement(insertSQL);
          ps.setString(1, aProfile.getFirstName());
          ps.setString(2, aProfile.getLastName());
          ps.setString(3, aProfile.getEmail());
          ps.setString(4, aProfile.getPassword());
            rowCount=ps.executeUpdate();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }

    @Override
    public ProfileBean[] findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProfileBean findByName(String aName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
