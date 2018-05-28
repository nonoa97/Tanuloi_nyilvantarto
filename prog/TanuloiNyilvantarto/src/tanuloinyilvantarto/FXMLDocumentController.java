/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanuloinyilvantarto;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Felhasználó
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Pane helpPane;
    @FXML
    private Pane tantargyPane;
    @FXML
    private Pane menuPane;
    @FXML
    private Pane mainPane;
    @FXML
    private Pane sortPane;
    @FXML
    private Pane alertPane;
    @FXML
    private Label tableLabel;
    @FXML
    private Label vbk;
    @FXML
    private Label fajl;
    @FXML
    private Label tf;
    @FXML
    private Label tfl;
    @FXML
    private Label tsr;
    @FXML
    private Label cs1;
    @FXML
    private Label cs2;
    @FXML
    private Label cs3;
    @FXML
    private Label cs4;
    @FXML
    private Label cs5;
    @FXML
    private Label l1;
    @FXML
    private Label alertText;
    @FXML
    private Label exitText;
     @FXML
    private TableView table;
     @FXML
    private TableView targyTable;
     @FXML
    private TableView vizsgaTable;
     @FXML
    private TextField exportName;
     @FXML
    private TextField vizsgaName;
    @FXML
    private TextField okAzonosito;
    @FXML
    private TextField nevTextField;
    @FXML
    private TextField megyeTextFiled;
    @FXML
    private TextField telepulesTextFiled;
    @FXML
    private TextField tantargyTextFiled1;
    @FXML
    private TextField tantargyTextFiled2;
    @FXML
    private TextField tantargyTextFiled3;
    @FXML
    private TextField tantargyTextFiled4;
    @FXML
    private TextField filePlace;
    @FXML
    private ComboBox targyCombo;
     @FXML
    private ComboBox exportCombo;
    @FXML
    private RadioButton nyolc;
    @FXML
    private RadioButton kilenc;
    @FXML
    private RadioButton tiz;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ImageView alertimage;
    @FXML
    private ImageView doneimage;
    @FXML
    private ImageView worktimage;
   @FXML
    private TextArea leiras;
    
    
    private ObservableList<Tanulo> list =FXCollections.observableArrayList();
    private ObservableList<Tanulo> list2 =FXCollections.observableArrayList();
    private ObservableList comboList =FXCollections.observableArrayList();
    ObservableList exportList =FXCollections.observableArrayList();
    private ObservableList<Targyak> targyList =FXCollections.observableArrayList();
    
    String radioLabel ="8:00";
    private int counter =0;
    PDF pdf = new PDF();
    DB db = new DB();
    Tanulo data;
    Targyak targy;
    
    
    @FXML
    private void newExam(ActionEvent event){ 
      String nev= vizsgaName.getText();
      LocalDate date= datePicker.getValue();
      String datum = date.toString();
      nyolc.setOnAction(e -> radioLabel = nyolc.getText());
      kilenc.setOnAction(e ->radioLabel = kilenc.getText());
      tiz.setOnAction(e -> radioLabel = tiz.getText());
      targy = new Targyak(nev,datum,radioLabel);
      targyList.add(targy);
      db.addNewExam(targy);
      vizsgaTable.setItems(targyList);
      comboList.add(nev);
      vizsgaName.clear();
      
        
        
    }
            
    @FXML
    private void beosztas(ActionEvent event){     
       alert("A menüpont fejlesztés alatt", worktimage);
    }        
    
      @FXML
    private void feltolt(ActionEvent event){     
        beolvas(filePlace.getText());
    }
    
    @FXML
    private void deleteExam(ActionEvent event){     
        db.removeExam();
    }
    
    @FXML
    private void nevjegy(ActionEvent event){     
        
    }
    
    
    
    private void beolvas(String eleres){
        
         try {   
       FileInputStream fis = new FileInputStream(eleres);
       BufferedInputStream bin = new BufferedInputStream(fis);
       int i= 0;
       int x=0;
       String result = "";
         while((i=bin.read()) != -1){
         result += (char) i;
            if((char) i == '\n'){
              
                if (x == 0){
                 x++;   
                }else{
                data= new Tanulo(result);
                list.add(data);
                db.addStudent(data);
                     }
                result="";    
                }    
            }     
        fis.close();    
        }
    catch(Exception e) {
        if(filePlace.getText().equals(""))
        {
            alert("Addja meg a fálj elérésí útvonalát", alertimage);
        }else{
        alert("Nem megfelelő fájl formátum", alertimage);
                       }   
        
    }
     filePlace.clear();
    table.setItems(list);
        
    }
    
   
    
    public void listafeltoltes(String x){
        for (int k = 0; k <list.size(); k++) {
                 
                 String t1 = list.get(k).getTantargy_1();
                 String t2 = list.get(k).getTantargy_2();
                 String t3 = list.get(k).getTantargy_3();
                 String t4 = list.get(k).getTantargy_4();
                 
                 if(x.equals(t1) || x.equals(t2) || x.equals(t3) || x.equals(t4))
                 list2.add(list.get(k));
            }
         exportName.clear();
    }
    
    
    @FXML
    private void exportButtonAction(ActionEvent event){
        String x = (String) exportCombo.getValue();
        switch(x){
            case "Táblázat pdf-be mentése.": exportPDF(); break;
            case "Táblázat excel-be mentése.": export(); break;
            case "Az összes vizsgázó pdf-be mentése.": exportPDFAll(); break;
            case "Az összes vizsgázó excel-be mentése.":  exportAll();  break;
                
            
        
        }
        
    }
    
   
    private void exportPDFAll() {
        list2.clear();
        String name = exportName.getText();
         name=name.replaceAll("\\s+", "");
            if(name.equals("")){
                alert("Add meg a fájl nevét!", alertimage);
            }else{
        try{
       pdf.toPDFAll(name, list, list2, comboList);
       exportName.clear();
       alert("PDF fájl sikeresen elkészült!", doneimage);
        }catch(Exception e){
            alert("Hiba a fájl beolvasása közben!", alertimage);
        }
            }
    }
    
    
    private void exportPDF() {
        String name = exportName.getText();
         name=name.replaceAll("\\s+", "");
            if(name.equals("")){
                alert("Add meg a fájl nevét!", alertimage);
            }else{
        try{
       pdf.PDFGeneration(exportName.getText(),(String) targyCombo.getValue(), list2);
       exportName.clear();
       alert("A PDF fájl sikeresen létrehozva!", doneimage);
        }catch(Exception e){
            alert("Hiba a PDF létrehozása közben!", alertimage);
        }
            }
            
             exportName.clear();
    }
   
    private void exportAll() {
        
        list2.clear();
        String name = exportName.getText();
         name=name.replaceAll("\\s+", "");
            if(name.equals("")){
                alert("Add meg a fájl nevét!", alertimage);
            }else{
        XSSFWorkbook wbook = new XSSFWorkbook();
        
        for (int i = 0; i < comboList.size(); i++) {
            
            String r=   (String)  comboList.get(i);
             XSSFSheet sh = wbook.createSheet((r));
            XSSFRow header = sh.createRow(0);
        header.createCell(0).setCellValue("OK azonosító");
        header.createCell(1).setCellValue("Név");
        header.createCell(2).setCellValue("Település");
        header.createCell(3).setCellValue("Megye");
        list2.clear();
                
        listafeltoltes(r);
        for (int j = 0; j < list2.size(); j++) {
                XSSFRow row = sh.createRow(j+1);
                row.createCell(0).setCellValue(list2.get(j).getOktatasi_azonosíto());
                row.createCell(1).setCellValue(list2.get(j).getNev());
                row.createCell(2).setCellValue(list2.get(j).getTelepules());
                row.createCell(3).setCellValue(list2.get(j).getMegye());
               }
            
        
            
        }
        
        try {
            FileOutputStream fos = new FileOutputStream(exportName.getText()+".xlsx");
            wbook.write(fos);
            fos.close();
            
            alert("Sikeres mentés", doneimage);
        } catch (FileNotFoundException  ex) {
            alert("Sikertelen mentés", alertimage);
        } catch (IOException ex) {
           alert("Sikertelen mentés", alertimage);
        }
            }
             exportName.clear();
        
    }
    
    
      private void export() {
        
        String name = exportName.getText();
        name=name.replaceAll("\\s+", "");
            if(name.equals("")){
                alert("Add meg a fájl nevét!", alertimage);
            }else{
         XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sh = wb.createSheet((String) targyCombo.getValue());
        XSSFRow header = sh.createRow(0);
        header.createCell(0).setCellValue("OK azonosító");
        header.createCell(1).setCellValue("Név");
        header.createCell(2).setCellValue("Település");
        header.createCell(3).setCellValue("Megye");
        header.createCell(4).setCellValue("Tárgy");
        
            for (int i = 0; i < list2.size(); i++) {
                XSSFRow row = sh.createRow(i+1);
                row.createCell(0).setCellValue(list2.get(i).getOktatasi_azonosíto());
                row.createCell(1).setCellValue(list2.get(i).getNev());
                row.createCell(2).setCellValue(list2.get(i).getTelepules());
                row.createCell(3).setCellValue(list2.get(i).getMegye());
               }
            
        try {
            FileOutputStream fos = new FileOutputStream(exportName.getText()+".xlsx");
            wb.write(fos);
            fos.close();
            
            alert("Sikeres mentés", doneimage);
        } catch (FileNotFoundException  ex) {
            alert("Sikertelen mentés", alertimage);
        } catch (IOException ex) {
           alert("Sikertelen mentés", alertimage);
        }
        
        
            }
            
            exportName.clear();
    }
    
    @FXML
    private void talloz(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        
        if (selectedFile !=null){
            beolvas(selectedFile.getParent()+"\\"+selectedFile.getName());
            
        }
    }
       
     @FXML
    private void handleButtonAction(ActionEvent event) {
           
        String ok=okAzonosito.getText();
        String nev = nevTextField.getText();
        String megye = megyeTextFiled.getText();
        String telepules = telepulesTextFiled.getText();
        String t1=tantargyTextFiled1.getText();
        String t2=tantargyTextFiled2.getText();
        String t3=tantargyTextFiled3.getText();
        String t4=tantargyTextFiled4.getText();
        ok=ok.replaceAll("\\s+", "");
        megye=megye.replaceAll("\\s+", "");
        telepules=telepules.replaceAll("\\s+", "");
        if(ok.equals("") || nev.equals("") || megye.equals("") || telepules.equals("") || t1.equals("")){
               alert("A csillaggal jelölt mezőket kötelező kitölteni!", alertimage);
               cs1.setVisible(true);
               cs2.setVisible(true);
               cs3.setVisible(true);
               cs4.setVisible(true);
               cs5.setVisible(true);
            }else{
        if(ok.length()<11 || ok.length()>11){
            alert("Az azonosítónak 11 számból kell állnia!", alertimage);
            
                }else{
            try{
        long x = Long.parseLong(ok);
        if(t2.equals(""))
            t2="-";
        if(t3.equals(""))
            t3="-";
        if(t4.equals(""))
            t4="-";
        String result=ok+";"+nev+";"+megye+";"+telepules+";"+t1+";"+t2+";"+t3+";"+t4;
         data = new Tanulo(result);
        list.add(data);
        db.addStudent(data);
        table.setItems(list);
               cs1.setVisible(false);
               cs2.setVisible(false);
               cs3.setVisible(false);
               cs4.setVisible(false);
               cs5.setVisible(false);
                
            }catch(Exception e){
                alert("Az azonosítónak számokból kell állnia", alertimage);
                System.out.println(e);
            }
        
                    }
        }
        okAzonosito.clear();
        nevTextField.clear();
        megyeTextFiled.clear();
        telepulesTextFiled.clear();
        tantargyTextFiled1.clear();
        tantargyTextFiled2.clear();
        tantargyTextFiled3.clear();
        tantargyTextFiled4.clear();
        
    }
    
    
    public void setTable(){
        
        TableColumn okAzCol = new TableColumn("Oktatási azonosító");
        okAzCol.setMinWidth(150);
        okAzCol.setCellFactory(TextFieldTableCell.forTableColumn());
        okAzCol.setCellValueFactory(new PropertyValueFactory<Tanulo, String>("oktatasi_azonosíto"));
        
         TableColumn nevCol = new TableColumn("Név");
        nevCol.setMinWidth(150);
        nevCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nevCol.setCellValueFactory(new PropertyValueFactory<Tanulo, String>("nev"));
        
        
        TableColumn megyeCol = new TableColumn("Megye");
        megyeCol.setMinWidth(80);
        megyeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        megyeCol.setCellValueFactory(new PropertyValueFactory<Tanulo, String>("megye"));
        
        TableColumn telepulesCol = new TableColumn("Település");
        telepulesCol.setMinWidth(100);
        telepulesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        telepulesCol.setCellValueFactory(new PropertyValueFactory<Tanulo, String>("telepules"));
        
        TableColumn targyCol1 = new TableColumn("Tantárgy 1");
        targyCol1.setMinWidth(80);
        targyCol1.setCellFactory(TextFieldTableCell.forTableColumn());
        targyCol1.setCellValueFactory(new PropertyValueFactory<Tanulo, String>("tantargy_1"));
        
        TableColumn targyCol2 = new TableColumn("Tantárgy 2");
        targyCol2.setMinWidth(80);
        targyCol2.setCellFactory(TextFieldTableCell.forTableColumn());
        targyCol2.setCellValueFactory(new PropertyValueFactory<Tanulo, String>("tantargy_2"));
        
        TableColumn targyCol3 = new TableColumn("Tantárgy 3");
        targyCol3.setMinWidth(80);
        targyCol3.setCellFactory(TextFieldTableCell.forTableColumn());
        targyCol3.setCellValueFactory(new PropertyValueFactory<Tanulo, String>("tantargy_3"));
        
        TableColumn targyCol4 = new TableColumn("Tantárgy 4");
        targyCol4.setMinWidth(80);
        targyCol4.setStyle("-fx-background-color: transparent;");
        targyCol4.setCellFactory(TextFieldTableCell.forTableColumn());
        targyCol4.setCellValueFactory(new PropertyValueFactory<Tanulo, String>("tantargy_4"));
                
        table.getColumns().addAll(okAzCol,nevCol,megyeCol,telepulesCol,targyCol1,targyCol2,targyCol3,targyCol4);
        list.addAll(db.getAllStudent());
         table.setItems(list);
                
    }
  
       public void setTargyTable(){
        
        TableColumn okAzCol = new TableColumn("Oktatási azonosító");
        okAzCol.setMinWidth(150);
        okAzCol.setCellFactory(TextFieldTableCell.forTableColumn());
        okAzCol.setCellValueFactory(new PropertyValueFactory<Tanulo, String>("oktatasi_azonosíto"));
        
         TableColumn nevCol = new TableColumn("Név");
        nevCol.setMinWidth(150);
        nevCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nevCol.setCellValueFactory(new PropertyValueFactory<Tanulo, String>("nev"));
        
        
        TableColumn megyeCol = new TableColumn("Megye");
        megyeCol.setMinWidth(80);
        megyeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        megyeCol.setCellValueFactory(new PropertyValueFactory<Tanulo, String>("megye"));
        
        TableColumn telepulesCol = new TableColumn("Település");
        telepulesCol.setMinWidth(100);
        telepulesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        telepulesCol.setCellValueFactory(new PropertyValueFactory<Tanulo, String>("telepules"));
        
         targyTable.getColumns().addAll(okAzCol,nevCol,megyeCol,telepulesCol);
       }
    
    public void setVizsgaTable(){
        
        TableColumn targyCol = new TableColumn("Tantárgy");
        targyCol.setMinWidth(100);
        targyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        targyCol.setCellValueFactory(new PropertyValueFactory<Targyak, String>("targy"));
        
        TableColumn napCol = new TableColumn("Vizsga nap");
        napCol.setMinWidth(100);
        napCol.setCellFactory(TextFieldTableCell.forTableColumn());
        napCol.setCellValueFactory(new PropertyValueFactory<Targyak, String>("date"));
        
         TableColumn idoCol = new TableColumn("Kezdés");
        idoCol.setMinWidth(50);
        idoCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idoCol.setCellValueFactory(new PropertyValueFactory<Targyak, String>("ido"));
        
        vizsgaTable.getColumns().addAll(targyCol, napCol, idoCol);
        targyList.addAll(db.getAllExam());
         vizsgaTable.setItems(targyList);
        
    }
       
    
    public void alert(String text, ImageView kep ){
        
        kep.setVisible(true);
        alertimage.setVisible(true);
        menuPane.setDisable(true);
        menuPane.setOpacity(0.3);
        mainPane.setDisable(true);
        mainPane.setOpacity(0.3);
        helpPane.setDisable(true);
        helpPane.setOpacity(0.3);
        sortPane.setDisable(true);
        sortPane.setOpacity(0.3);
        tantargyPane.setDisable(true);
        tantargyPane.setOpacity(0.3);
        alertPane.setVisible(true);
        alertText.setText(text);
        
        
        exitText.setOnMouseClicked(e -> {
            mainPane.setDisable(false);
            mainPane.setOpacity(1);
            menuPane.setDisable(false);
            menuPane.setOpacity(1);
            helpPane.setDisable(false);
        helpPane.setOpacity(1);
        sortPane.setDisable(false);
        sortPane.setOpacity(1);
        tantargyPane.setDisable(false);
        tantargyPane.setOpacity(1);
            alertPane.setVisible(false);
            kep.setVisible(false);
        });
  
    }
     
    
    private void setExportCombo() {
            exportList.add("Táblázat pdf-be mentése.");
             exportList.add("Táblázat excel-be mentése.");
              exportList.add("Az összes vizsgázó pdf-be mentése.");
               exportList.add("Az összes vizsgázó excel-be mentése.");
               exportCombo.setItems(exportList);
    }
    
     @FXML
    private void comboAction(ActionEvent event) {
        list2.clear();
        counter=0;
         for (int i = 0; i <list.size(); i++) {
          String x=   (String) targyCombo.getValue();
          String t1 = list.get(i).getTantargy_1();
          String t2 = list.get(i).getTantargy_2();
          String t3 = list.get(i).getTantargy_3();
          String t4 = list.get(i).getTantargy_4();
         
          
          
             if(x.equals(t1) || x.equals(t2) || x.equals(t3) || x.equals(t4)){
                 list2.add(list.get(i));
                 counter++;
               
             }
             if( counter == 0){
                 tableLabel.setText("Ebből a tárgyból nincs vizsgázó");
                 l1.setText("A(z) "+x+" vizsgára "+counter+" darab tanuló jelentkezett!");
             }else{
             targyTable.setItems(list2);
             l1.setText("A(z) "+x+" vizsgára "+counter+" darab tanuló jelentkezett!");
           }
             }
    }
    
    @FXML
    private void nextP1(ActionEvent event) {
        mainPane.setVisible(false);
      sortPane.setVisible(true);
      helpPane.setVisible(false);
      tantargyPane.setVisible(false);
    }
   
            
    @FXML
    private void nextP2(ActionEvent event) {
      mainPane.setVisible(true);
      sortPane.setVisible(false);
      helpPane.setVisible(false);
      tantargyPane.setVisible(false);
    }
    
    @FXML
    private void nextP3(ActionEvent event) {
      mainPane.setVisible(false);
      sortPane.setVisible(false);
      helpPane.setVisible(false);
      tantargyPane.setVisible(true);
    }
    
    @FXML
    private void showHelp(ActionEvent event) {
      mainPane.setVisible(false);
      sortPane.setVisible(false);
      helpPane.setVisible(true);
      tantargyPane.setVisible(false);
    
      
      leiras.setText("Tanulói Vizsgabeosztó Rendszer\n" +
"A program rövid leírása:\n" +
"A program egy emelt szintű érettségi vizsgajelentkezéseket kezelő szoftver. Egy külső adatállományból (.xlsx fájl) információkat olvas be, \nvagy mi is megadhatunk új adatokat a programon belül, majd ezeket adatbázisba menti. \n" +
"Importálás során lehetővé teszi az adathalmaz rendezését, és szűrését a felhasználó által megadott kritériumok szerint (pl.: tantárgy).\n Ezt az adatállományt igény szerint mentheti, vagy kilistázhatja a képernyőre.\n" +
"A program képes lesz egy vizsgabeosztást készíteni a meglévő információk alapján,\n hogy a tanulók vizsgái ne ütközzenek (egy tanuló csak egy tárgyból vizsgázhat egy nap), \nvalamint a vizsgázó tanulókat csoportokba sorolja a megadott feltételek szerint.\n" +
"Menü pontok leírása.\n" +
"•	Tanulók (kezdőképernyő)\n" +
"Tanulók hozzáadása az adatbázishoz külső állományon keresztül, vagy manuális felvitele a rendszerbe.\n" +
"Külső állományból való beolvasás:\n" +
"1.	Kattintsunk a Tallózás gombra, majd a fájl kiválasztása \n" +
"2.	Az adatok importálásához kattintsunk Táblázat feltöltése lehetőségre.\n" +
"Tanulók felvitele manuálisan:\n" +
"1.	Töltsük ki az Új tanuló hozzáadása mezőket. (az első 4 mező megadása kötelező)\n" +
"2.	Kattintsunk az Új tanuló gombra.\n" +
"Tanulók törlése.\n" +
"1.	A táblázatban a törölni kívánt tanuló sorának kijelölése \n" +
"2.	Tanuló törlése gombra kattintva hajtja végre a műveletet.\n" +
"A hozzáadás után a Tanulókat rendezhetjük a táblázat fejlécére kattintva pl.: Oktatási azonosító, Név, Megye, Település, és tantárgyak szerint is.\n" +
"•	Tantárgyak\n" +
"Vizsgatárgyak rögzítése és időpontjainak felvitele a rendszerbe.\n" +
"Rögzítés: \n" +
"1.	A jobb oldalon rögzíthetjük a tantárgyak vizsgaidőpontjait (napot és időt). Töltsük ki a megfelelő adatokat.\n" +
"2.	A hozzá ad gombra kattintva rendelhetjük hozzá az adatbázishoz.\n" +
"Törlés\n" +
"A tárgyak törlése gombra kattintva törölhetjük az adatokat.\n" +
"A készített táblázatot rendezhetjük Tantárgy, Vizsga nap, Kezdés szerint.\n" +
"•	Vizsgajelentkezések\n" +
"Tantárgyanként vizsgázó tanulók lekérése és mentése.\n" +
"Vizsgázók lekérdezése:\n" +
"1.	A tárgyak feliratú lenyíló menüre kattintva lekérdezhetjük, hogy a kiválasztott tantárgyból hány tanuló vizsgázik \n" +
"2.	A táblázat fejlécén állíthatjuk, hogy milyen tulajdonság szerint rendezze az adatokat (Oktatási azonosító, Név, Megye, Település).\n" +
"Adatok mentése:\n" +
"1.	Ha menteni szeretnénk az adatokat, akkor a jobb oldali menüből kiválaszthatjuk, \nhogy a lekérdezett táblázatot vagy az összes adatot szeretnénk-e lementeni és milyen formátumban (excell vagy pdf). \n" +
"2.	A kiválasztás után meg kell adnunk a fájlnak a nevét majd a Táblázat mentése opcióra kattintani.\n" +
"");
      
    }
    @FXML
    private void delete(ActionEvent event) {
     db.removeStudent();
     list.clear();
     table.setItems(list);
    }
    
  public void setCombo(){
      for (int i = 0; i < targyList.size(); i++) {
         comboList.add(targyList.get(i).getTargy());
      }
      targyCombo.setItems(comboList);
  }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTable();
        setTargyTable();
        setVizsgaTable();
        setCombo();
        setExportCombo();
        
        
    }    
    
    
 }
    

