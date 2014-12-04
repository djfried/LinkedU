package dao;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author djfried
 */
public class Uinsert {
    public static void main(String args[]) throws FileNotFoundException, IOException
  {
//      makeFile();
      System.out.println(loadDB());
  }
  private static void makeFile() throws IOException
  {
         String id="";
      String name="";
      String address="";
      String city="";
      String state="";
      String zip="";
      String phone="";
      String web="";
    File file= new File("H:\\Project\\university.csv");
    Scanner scanner= new Scanner(file);
    FileWriter fw = new FileWriter("H:\\Project\\List.csv");
     PrintWriter pw = new PrintWriter(fw);
    while(scanner.hasNextLine())
    {
    String temp=scanner.nextLine();
    StringTokenizer st= new StringTokenizer(temp,",");
   
    id=st.nextToken();
  
    name=st.nextToken();
    
    if(!name.contains("university")&& !name.contains("University")&&!name.contains("College")&&!name.contains("college"))
    {
        
        System.out.println(name);
        continue;
    }
    address=st.nextToken();
    
    city=st.nextToken();
    if(city.contains("Suite")||city.contains("Floor")||city.contains("suite")||city.contains("floor")||city.contains("Ste."))
        city=st.nextToken();
    
    state=st.nextToken();
   
    zip=st.nextToken();
    
    phone=st.nextToken();
    if(!st.hasMoreTokens())
        continue;
    web=st.nextToken();
    if(!web.contains(".edu"))
        continue;
    
//    System.out.println(address);
//    System.out.println(city);
//    System.out.println(state);
//    System.out.println(zip);
//    System.out.println(phone);
//    System.out.println(id);
//    System.out.println(web);
     
        
        //Write to file for the first row
    pw.print(id);
    pw.print(",");
    pw.print(name);
    pw.print(",");
    pw.print(address);
    pw.print(",");
    pw.print(city);
    pw.print(",");
    pw.print(state);
    pw.print(",");
    pw.print(zip);
    pw.print(",");
    pw.print(phone);
    pw.print(",");
    pw.println(web);

    //Flush the output to the file
    pw.flush();
        
             
  }
    //Close the Print Writer
    pw.close();
        
        //Close the File Writer
    fw.close();   
    scanner.close();
      
   
  }
  private static int loadDB() throws FileNotFoundException
  {
      int rowCount = 0;
      String id="";
      String name="";
      String address="";
      String city="";
      String state="";
      String zip="";
      String phone="";
      String web="";
          File file= new File("H:\\Project\\List.csv");
    Scanner scanner= new Scanner(file);  
    String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/tdhasz_Fall14_LinkedUDB;create=true";
    try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    while(scanner.hasNextLine())
    {
        String temp=scanner.nextLine();
        StringTokenizer st= new StringTokenizer(temp,",");

        id=st.nextToken();
        name=st.nextToken();
        address=st.nextToken();
        city=st.nextToken();
        state=st.nextToken();
        zip=st.nextToken();
        phone=st.nextToken();
        web=st.nextToken();
    
        
        try {
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
             String insertSQL="INSERT INTO linkedu.University"
                    +"(INSTITUTION_ID,INSTITUTION_NAME,INSTITUTION_ADDRESS,INSTITUTION_CITY,"
                    + "INSTITUTION_STATE,INSTITUTION_ZIP,INSTITUTION_PHONE,INSTITUTION_WEB_ADDRESS) VALUES"
                    +"(?,?,?,?,?,?,?,?)";
          PreparedStatement ps=DBConn.prepareStatement(insertSQL);
          
          ps.setString(1, id);
          ps.setString(2, name);
          ps.setString(3, address);
          ps.setString(4, city);
          ps.setString(5, state);
          ps.setString(6, zip);
          ps.setString(7, phone);
          ps.setString(8, web);
          System.out.println(ps.toString());
          rowCount=ps.executeUpdate();
          rowCount++;
          DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
       
    }
        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        System.out.println(rowCount);
        scanner.close();
        return rowCount;
  }
    
}
