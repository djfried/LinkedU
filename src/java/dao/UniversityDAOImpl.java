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
import model.UniversityBean;

/**
 *
 * @author djfried
 */
public class UniversityDAOImpl implements UniversityDAO {

    @Override
    public ArrayList<UniversityBean> searchUniversity() {

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

            Statement statement = DBConn.createStatement();
            String statementString = "SELECT * FROM linkedu.university where INSTITUTION_STATE='IL' order by INSTITUTION_NAME";
            ResultSet rs = statement.executeQuery(statementString);
            while (rs.next()) {
                String id = rs.getString("INSTITUTION_ID");
                String name = rs.getString("INSTITUTION_NAME");
                String address = rs.getString("INSTITUTION_ADDRESS");
                String city = rs.getString("INSTITUTION_CITY");
                String state = rs.getString("INSTITUTION_STATE");
                String zip = rs.getString("INSTITUTION_ZIP");
                String phone = rs.getString("INSTITUTION_PHONE");
                String web = rs.getString("INSTITUTION_WEB_ADDRESS");

                answer.add(new UniversityBean(id, name, address, city, state, zip, phone, web));
            }
            DBConn.close();
        } catch (SQLException ex) {

        }
        return answer;

    }

    @Override
    public ArrayList<UniversityBean> searchUniversity(String name, String city, String state) {

        System.out.println("CITY: " + city);
        System.out.println("STATE: " + state);
        System.out.println("NAME: " + name);
        ArrayList<UniversityBean> answer = new ArrayList();
        String sqlName = "";
        String sqlCity = "";
        String sqlState = "";
        boolean hasWhere = false;
        String statementString = "SELECT INSTITUTION_NAME, INSTITUTION_CITY, "
                + "INSTITUTION_STATE, INSTITUTION_WEB_ADDRESS FROM linkedu.university ";
        if (name != null) {
            sqlName = "WHERE INSTITUTION_NAME LIKE ? ";
            hasWhere = true;
        }
        if (city != null) {
            if (!hasWhere) {
                sqlCity = "WHERE INSTITUTION_CITY= ? ";
                hasWhere = true;
            } else {
                sqlCity = "OR INSTITUTION_CITY= ? ";
            }
        }

        if (!hasWhere) {
            sqlState = "WHERE INSTITUTION_STATE= ? ";
            
        } else {
            sqlState = "OR INSTITUTION_STATE= ? ";
        }
        
        statementString += sqlName + sqlCity + sqlState + "ORDER BY INSTITUTION_NAME";

        //System.out.println(statementString);
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        try {

            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/tdhasz_Fall14_LinkedUDB;create=true";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            PreparedStatement ps = DBConn.prepareStatement(statementString);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, city);
            ps.setString(3, state);

            System.out.println(statementString);
            ResultSet rs = ps.executeQuery(statementString);
            while (rs.next()) {
                String n = rs.getString("INSTITUTION_NAME");
                String c = rs.getString("INSTITUTION_CITY");
                String s = rs.getString("INSTITUTION_STATE");
                String web = rs.getString("INSTITUTION_WEB_ADDRESS");
                answer.add(new UniversityBean(n, c, s, web));
            }
            DBConn.close();
        } catch (SQLException ex) {

        }
        return answer;
    }

}
