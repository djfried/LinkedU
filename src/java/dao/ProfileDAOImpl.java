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
import java.util.ArrayList;
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
    public ArrayList<ProfileBean> searchStudents(ProfileBean theModel){
//        String answer ="<table>";
        System.out.println("searching from the "+theModel.getFirstName());
          ArrayList<ProfileBean> answer= new ArrayList<ProfileBean>();
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
            String statementString = "Select * From linkedU.users order by lastname";
            ResultSet rs= statement.executeQuery(statementString);
             
            while(rs.next()){
//                answer+="<tr  height=\"100\"><td>"+rs.getString("FIRSTNAME")+"</td>";
//                answer+="<td>"+rs.getString("LASTNAME")+"</td>";
//                answer+="<td>"+rs.getString("EMAIL")+"</td>";
////                answer+="<td width=\"100\"><h:commandButton class=\"btn btn-success\" value=\"Sign Out\" action=\"#{profileController.logout()}\" style=\"font-size: 50px\"/></td></tr>";
//                answer+="<td width=\"100\"><button class=\"btn btn-success\">button</button></td></tr>";
//            }
//            answer+="</table>";
               String testName = rs.getString("FIRSTNAME");
               System.out.println("testname "+testName);
               System.out.println("modelname "+theModel.getFirstName());
              if( !testName.equals(theModel.getFirstName()) ) answer.add(new ProfileBean(testName,rs.getString("LASTNAME"),
              rs.getString("EMAIL")));
           }
            DBConn.close();
        }   catch (SQLException ex) {
            //Logger.getLogger(LogInImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
    
    @Override
    public ProfileBean login(ProfileBean theModel){
   
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
            
            String statementString = "Select * From linkedU.users Where email='"+theModel.getLoginEmail()+"'";
            System.out.print(statementString);
            PreparedStatement ps = DBConn.prepareStatement(statementString);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                if(theModel.getLoginPassword().equals(rs.getString("PASSWORD"))){
                    ProfileBean returnBean = new ProfileBean(rs.getString("FIRSTNAME"),rs.getString("LASTNAME"),rs.getString("EMAIL"),rs.getString("PASSWORD"));
                    return returnBean;
                }
            }
            else{
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!1USER NOT FOUND");
            }
            DBConn.close();
        }   catch (SQLException ex) {
            //Logger.getLogger(LogInImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    @Override
    public ProfileBean findByName(String aName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ProfileBean theModel) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/tdhasz_Fall14_LinkedUDB";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
             //System.out.println("HELLOOOOOOOOOOOOO222");
            Statement statement = DBConn.createStatement();
            String statementString = "Delete From linkedU.users Where email='"+theModel.getLoginEmail()+"'";
            statement.executeUpdate(statementString);
            DBConn.close();
        }   catch (SQLException ex) {
            //Logger.getLogger(LogInImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
