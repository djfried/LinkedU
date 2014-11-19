/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/tdhasz_Fall14_LinkedUDB;create=true";
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
    public String searchStudents(){
        String answer ="<table>";
             try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/tdhasz_Fall14_LinkedUDB;create=true";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
             //System.out.println("HELLOOOOOOOOOOOOO222");
            Statement statement = DBConn.createStatement();
            String statementString = "Select * From linkedU.users";
            ResultSet rs= statement.executeQuery(statementString);
            
            while(rs.next()){
                answer+="<tr  height=\"100\"><td>"+rs.getString("FIRSTNAME")+"</td>";
                answer+="<td>"+rs.getString("LASTNAME")+"</td>";
                answer+="<td>"+rs.getString("EMAIL")+"</td>";
                answer+="<td width=\"100\"><h:commandButton class=\"btn btn-success\" value=\"Sign Out\" action=\"#{profileController.logout()}\" style=\"font-size: 50px\"/></td></tr>";
            }
            answer+="</table>";
            DBConn.close();
        }   catch (SQLException ex) {
            //Logger.getLogger(LogInImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
    
    @Override
    public boolean login(ProfileBean theModel){
   
         System.out.println("HELLOOOOOOOOOOOOO11111");
        
         try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/tdhasz_Fall14_LinkedUDB;create=true";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
             //System.out.println("HELLOOOOOOOOOOOOO222");
            Statement statement = DBConn.createStatement();
            String statementString = "Select * From linkedU.users Where email='"+theModel.getLoginEmail()+"'";
            ResultSet rs= statement.executeQuery(statementString);
            if(rs.next()){
              //  System.out.println("HELLOOOOOOOOOOOOO");
                if(rs.getString(2).equals(theModel.getLoginPassword())){
                    return true;
                }
            }
            DBConn.close();
        }   catch (SQLException ex) {
            //Logger.getLogger(LogInImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    @Override
    public ProfileBean findByName(String aName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
