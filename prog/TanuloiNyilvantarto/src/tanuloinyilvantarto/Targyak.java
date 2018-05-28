

package tanuloinyilvantarto;

import javafx.beans.property.SimpleStringProperty;


public class Targyak {
    private final SimpleStringProperty id;
    private final SimpleStringProperty targy;
    private final SimpleStringProperty date;
    private final SimpleStringProperty ido;
    
    public Targyak(){
        this.id = new SimpleStringProperty("");
        this.targy= new SimpleStringProperty("");
        this.date = new SimpleStringProperty("");
        this.ido= new SimpleStringProperty("");
    } 
    
    
    public Targyak(String targy, String date, String ido){
        this.id = new SimpleStringProperty("");
        this.targy= new SimpleStringProperty(targy);
        this.date = new SimpleStringProperty(date);
        this.ido= new SimpleStringProperty(ido);
    } 
        
   public Targyak(Integer id, String targy, String date, String ido){
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.targy= new SimpleStringProperty(targy);
        this.date = new SimpleStringProperty(date);
        this.ido= new SimpleStringProperty(ido);
   }

    public String getId() {
        return id.get();
    }

    public String getTargy() {
        return targy.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getIdo() {
        return ido.get();
    }
   
   
}
