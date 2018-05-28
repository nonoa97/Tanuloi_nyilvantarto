
package tanuloinyilvantarto;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    
    final String URL = "jdbc:derby:Database ;create=true";
    final String USERNAME = "";
    final String PASSWORD = "";
    
    
    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;
    
    
     public DB() {
        
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("A kapcsolat a DB-vel létrejött");
        } catch (SQLException ex) {
            System.out.println("Valami baj van a connection  létrehozásakor.");
            System.out.println(""+ex);
        }
        
       
        if (conn != null){
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Valami baj van van a createStatament  létrehozásakor.");
                System.out.println(""+ex);
            }
        }
        
        
        try {           
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a DatabaseMetaData létrehozásakor..");
            System.out.println(""+ex);
        }
        
        try {
            ResultSet rs = dbmd.getTables(null, "APP", "TANULO", null);
            
            if(!rs.next())
            { 
             createStatement.execute("create table tanulo(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), okaz varchar(20), name varchar(20), megye varchar(30), telepules varchar(30), t1 varchar(30), t2 varchar(30), t3 varchar(30), t4 varchar(30))");
             createStatement.execute("create table targyak(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), tantargy varchar(20), datum varchar(20), ido varchar(5))");
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van az adattáblák létrehozásakor.");
            System.out.println(""+ex);
        }       
    }
     
     
     public ArrayList<Tanulo> getAllStudent(){
        String sql = "select * from tanulo";
        ArrayList<Tanulo> tanulo = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            tanulo = new ArrayList<>();
            
            while (rs.next()){
                Tanulo actualPerson = new Tanulo(rs.getInt("id"),rs.getString("okaz"),rs.getString("name"),rs.getString("megye"),rs.getString("telepules"),rs.getString("t1"),rs.getString("t2"),rs.getString("t3"),rs.getString("t4"));
                tanulo.add(actualPerson);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van a hallgatók kiolvasásakor");
            System.out.println(""+ex);
        }
      return tanulo;
    }
     
     public ArrayList<Targyak> getAllExam(){
        String sql = "select * from targyak";
        ArrayList<Targyak> targyak = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            targyak = new ArrayList<>();
            
            while (rs.next()){
                Targyak actualSubject = new Targyak(rs.getInt("id"),rs.getString("tantargy"),rs.getString("datum"),rs.getString("ido"));
                targyak.add(actualSubject);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van a hallgatók kiolvasásakor");
            System.out.println(""+ex);
        }
      return targyak;
    }

     public void addStudent(Tanulo tanulo){
      try {
        String sql = "insert into tanulo (okaz, name, megye, telepules, t1, t2, t3, t4) values (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, tanulo.getOktatasi_azonosíto());
        preparedStatement.setString(2, tanulo.getNev());
        preparedStatement.setString(3, tanulo.getMegye());
        preparedStatement.setString(4, tanulo.getTelepules());
        preparedStatement.setString(5, tanulo.getTantargy_1());
        preparedStatement.setString(6, tanulo.getTantargy_2());
        preparedStatement.setString(7, tanulo.getTantargy_3());
        preparedStatement.setString(8, tanulo.getTantargy_4());
        preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a hallgatók hozzáadásakor");
            System.out.println(""+ex);
        }
    }
     
     public void addNewExam(Targyak targyak){
      try {
        String sql = "insert into targyak (tantargy, datum, ido) values (?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, targyak.getTargy());
        preparedStatement.setString(2, targyak.getDate());
        preparedStatement.setString(3, targyak.getIdo());
        
        preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a tárgyak hozzáadásakor");
            System.out.println(""+ex);
        }
    }
     
     
     
  public void removeStudent(){
      String sql = "drop table tanulo";
        try {
            createStatement.execute(sql);
            createStatement.execute("create table tanulo(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), okaz varchar(20), name varchar(20), megye varchar(30), telepules varchar(30), t1 varchar(30), t2 varchar(30), t3 varchar(30), t4 varchar(30))");
            System.out.println("Sikeres");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
     
    }
  
   public void removeExam(){
      String sql = "drop table targyak";
        try {
            createStatement.execute(sql);
           createStatement.execute("create table targyak(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), tantargy varchar(20), datum varchar(20), ido varchar(5))");
            
            System.out.println("Sikeres");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
     
    }
}