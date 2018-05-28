

package tanuloinyilvantarto;

import java.util.StringTokenizer;
import javafx.beans.property.SimpleStringProperty;


public class Tanulo {
    
private final SimpleStringProperty id;        
private final SimpleStringProperty nev; 
private final SimpleStringProperty oktatasi_azonosíto;
private final SimpleStringProperty megye; 
private final SimpleStringProperty telepules; 
private final SimpleStringProperty tantargy_1; 
private final SimpleStringProperty tantargy_2;
private final SimpleStringProperty tantargy_3; 
private final SimpleStringProperty tantargy_4;  


public Tanulo(){
    this.id = new SimpleStringProperty("");
    this.nev=new SimpleStringProperty("");
    this.oktatasi_azonosíto= new SimpleStringProperty("");
    this.megye=new SimpleStringProperty("");
    this.telepules=new SimpleStringProperty("");
    this.tantargy_1=new SimpleStringProperty("");
    this.tantargy_2=new SimpleStringProperty("");
    this.tantargy_3=new SimpleStringProperty("");
    this.tantargy_4=new SimpleStringProperty("");
            
}

public Tanulo(Integer id, String sor){
    StringTokenizer stk = new StringTokenizer(sor, ";"); 
    this.oktatasi_azonosíto=new SimpleStringProperty( stk.nextToken());
    this.nev=new SimpleStringProperty(stk.nextToken());
    this.megye=new SimpleStringProperty(stk.nextToken());
    this.telepules=new SimpleStringProperty(stk.nextToken());
    this.tantargy_1=new SimpleStringProperty(stk.nextToken());
    this.tantargy_2=new SimpleStringProperty(stk.nextToken());
    this.tantargy_3=new SimpleStringProperty(stk.nextToken());
    this.tantargy_4=new SimpleStringProperty(stk.nextToken());
    this.id = new SimpleStringProperty(String.valueOf(id));
   
            
}

public Tanulo(Integer id, String okAz, String nev, String megye, String telepules, String targy1, String targy2, String targy3, String targy4){
    this.oktatasi_azonosíto= new SimpleStringProperty(okAz);
    this.nev=new SimpleStringProperty(nev);
    this.megye=new SimpleStringProperty(megye);
    this.telepules=new SimpleStringProperty(telepules);
    this.tantargy_1=new SimpleStringProperty(targy1);
    this.tantargy_2=new SimpleStringProperty(targy2);
    this.tantargy_3=new SimpleStringProperty(targy3);
    this.tantargy_4=new SimpleStringProperty(targy4);
    this.id = new SimpleStringProperty(String.valueOf(id));
    
        }

public Tanulo( String sor){
    StringTokenizer stk = new StringTokenizer(sor, ";"); 
    this.oktatasi_azonosíto=new SimpleStringProperty( stk.nextToken());
    this.nev=new SimpleStringProperty(stk.nextToken());
    this.megye=new SimpleStringProperty(stk.nextToken());
    this.telepules=new SimpleStringProperty(stk.nextToken());
    this.tantargy_1=new SimpleStringProperty(stk.nextToken());
    this.tantargy_2=new SimpleStringProperty(stk.nextToken());
    this.tantargy_3=new SimpleStringProperty(stk.nextToken());
    this.tantargy_4=new SimpleStringProperty(stk.nextToken());
    this.id = new SimpleStringProperty("");
   
            
}



    public String getNev() {
        return nev.get();
    }

    public String getOktatasi_azonosíto() {
        return oktatasi_azonosíto.get();
    }

    public String getMegye() {
        return megye.get();
    }

    public String getTelepules() {
        return telepules.get();
    }

    public String getTantargy_1() {
        return tantargy_1.get();
    }

    public String getTantargy_2() {
        return tantargy_2.get();
    }

    public String getTantargy_3() {
        return tantargy_3.get();
    }

    public String getTantargy_4() {
        return tantargy_4.get();
    }
    
    public String getId(){
       return id.get();
    }
    
    

}
