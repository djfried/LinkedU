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
import java.util.ArrayList;
import model.UniversityBean;

/**
 *
 * @author djfried
 */
public class UniversityDAOImpl implements UniversityDAO {

    @Override
    public ArrayList<UniversityBean> searchUniversity(UniversityBean theModel) {

        ArrayList<UniversityBean> answer = new ArrayList();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        try {

            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/tdhasz_Fall14_LinkedUDB;create=true";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            boolean hasWhere = false;
            String statementString = "SELECT * FROM linkedU.university ";

            if (theModel.getName() != null && !theModel.getName().equals("")) {
                if (!hasWhere) {
                    statementString += "WHERE ";
                    hasWhere = true;
                }
                statementString += "INSTITUTION_NAME LIKE '%" + theModel.getName() + "%' ";
            }
            if (theModel.getCity() != null && !theModel.getCity().equals("")) {
                if (!hasWhere) {
                    statementString += "WHERE ";
                    hasWhere = true;
                } else {
                    statementString += "AND ";
                }
                statementString += "INSTITUTION_CITY= '" + theModel.getCity() + "' ";
            }
            if (theModel.getState() != null && !theModel.getState().equals("")) {
                if (!hasWhere) {
                    statementString += "WHERE ";

                } 
                else 
                {
                    statementString += "AND ";
                }
                statementString += "INSTITUTION_STATE= '" + theModel.getState() + "' ";
            }
            statementString += "ORDER BY INSTITUTION_NAME";
            System.out.println("NAME: " + theModel.getName());
            System.out.println("CITY: " + theModel.getCity());
            System.out.println("STATE: " + theModel.getState());

            PreparedStatement ps = DBConn.prepareStatement(statementString);

            System.out.println(statementString);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                answer.add(new UniversityBean(rs.getString("INSTITUTION_ID"),
                        rs.getString("INSTITUTION_NAME"),
                        rs.getString("INSTITUTION_ADDRESS"),
                        rs.getString("INSTITUTION_CITY"),
                        rs.getString("INSTITUTION_STATE"),
                        rs.getString("INSTITUTION_ZIP"),
                        rs.getString("INSTITUTION_PHONE"),
                        rs.getString("INSTITUTION_WEB_ADDRESS")
                ));

            }
            ps.close();
            DBConn.close();
        } catch (SQLException ex) {

        }
        return answer;
    }
    @Override
    public UniversityBean getProfile(String viewName)
    {
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
            
            String statementString = "Select * From linkedU.users Where INSTITUTION_NAME='"+viewName+"'";
            System.out.print(statementString);
            PreparedStatement ps = DBConn.prepareStatement(statementString);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                //if(theModel.getLoginPassword().equals(rs.getString("PASSWORD"))){
                    UniversityBean returnBean = new UniversityBean(rs.getString("INSTITUTION_ID"),
                            rs.getString("INSTITUTION_NAME"),
                            rs.getString("INSTITUTION_ADDRESS"),
                            rs.getString("INSTITUTION_CITY"),
                            rs.getString("INSTITUTION_STATE"),
                            rs.getString("INSTITUTION_ZIP"),
                            rs.getString("INSTITUTION_PHONE"),
                            rs.getString("INSTITUTION_WEB_ADDRESS"));
                    return returnBean;
                //}
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

}
