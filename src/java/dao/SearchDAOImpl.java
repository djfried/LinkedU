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
import model.ProfileBean;

/**
 *
 * @author dbaile2
 */
public class SearchDAOImpl implements SearchDAO {
    @Override
    public ArrayList<ProfileBean> search(ProfileBean theModel){
//        String answer ="<table>";
        //System.out.println("searching from the "+theModel.getFirstName());
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
            
            //String statementString = "Select * From linkedU.users order by lastname";
            String statementString = "Select * From linkedU.users ";
            boolean hasWhere = false;
            if(theModel.getFirstName()!=null&&!theModel.getFirstName().equals("")){
                if(!hasWhere){
                    statementString+="Where ";
                    hasWhere=true;
                }
                statementString+="FirstName = '"+theModel.getFirstName()+"' ";
            }
            if(theModel.getLastName()!=null&&!theModel.getLastName().equals("")){
                if(!hasWhere){
                    statementString+="Where ";
                    hasWhere=true;
                }else{
                    statementString+="and ";
                }
                statementString+="LastName = '"+theModel.getLastName()+"' ";
            }
            if(theModel.getACT()!=null){
                if(!hasWhere){
                    statementString+="Where ";
                    hasWhere=true;
                }else{
                    statementString+="and ";
                }
                statementString+="ACT = "+theModel.getACT()+" ";
            }
            if(theModel.getSAT()!=null){
                if(!hasWhere){
                    statementString+="Where ";
                    hasWhere=true;
                }else{
                    statementString+="and ";
                }
                statementString+="SAT > "+theModel.getSAT()+" ";
            }
            if(theModel.getGPA()!=null){
                if(!hasWhere){
                    statementString+="Where ";
                    hasWhere=true;
                }else{
                    statementString+="and ";
                }
                statementString+="GPA > "+theModel.getGPA()+" ";
            }
            
            statementString+="order by LASTNAME";
            System.out.println(statementString);
            PreparedStatement statement = DBConn.prepareStatement(statementString);
            ResultSet rs= statement.executeQuery();
             
            while(rs.next()){
               answer.add(new ProfileBean(rs.getString("FIRSTNAME"),
                       rs.getString("LASTNAME"),rs.getString("EMAIL"),
                       rs.getString("PASSWORD"),rs.getDouble("GPA"),
                       rs.getDouble("OUTOF"),rs.getInt("ACT"),
                       rs.getInt("SAT"),rs.getString("TAGLINE"),
                       rs.getString("PICTURE"),rs.getString("MATERIAL"),
                       rs.getString("SECURITYQUESTION"),rs.getString("SECURITYANSWER")));
               System.out.println("ADDED");
           }
            DBConn.close();
        }   catch (SQLException ex) {
            ex.printStackTrace();
            //Logger.getLogger(LogInImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
}
