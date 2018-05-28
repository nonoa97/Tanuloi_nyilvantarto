

package tanuloinyilvantarto;



import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class PDF {
     
    public void PDFGeneration(String fileName, String targy, ObservableList list){
    Document document = new Document();
    
    try{
        
        PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));
        document.open();
       
            
            float[] columnWidths = {10, 6, 6, 6};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Phrase(targy));
            cell.setBackgroundColor(GrayColor.GRAYWHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(4);
            table.addCell(cell);
            
            table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("Ok azonosító");
            table.addCell("Név");
            table.addCell("Település");
            table.addCell("Megye");
            table.setHeaderRows(1);
            
            
            table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            for (int i = 1; i <= list.size(); i++) {
                Tanulo actualStudent = (Tanulo) list.get(i-1);
                
                
                table.addCell(actualStudent.getOktatasi_azonosíto());
                table.addCell(actualStudent.getNev());
                table.addCell(actualStudent.getTelepules());
                table.addCell(actualStudent.getMegye());
                
            }
            
            document.add(table);
            
            
            
            Chunk signature = new Chunk("\n\n Készítette: Aczél Norebert és Titkovics Ferenc");
            Paragraph base = new Paragraph(signature);
            document.add(base);
            
      
        }catch(Exception e){
    
        
        }
    
    document.close();
    }
    
    public void toPDFAll(String fileName,ObservableList list ,ObservableList list2, ObservableList combo){
    Document document = new Document();
    
    
    try{
        
        PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));
        document.open();
            
        for (int i = 0; i < combo.size(); i++) {
 
            String x = (String) combo.get(i);
             float[] columnWidths = {10, 6, 6, 6};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Phrase(x));
            cell.setBackgroundColor(GrayColor.GRAYWHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(4);
            table.addCell(cell);
            
            table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("Ok azonosító");
            table.addCell("Név");
            table.addCell("Település");
            table.addCell("Megye");
            table.setHeaderRows(1);
            
            
            table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
                    for (int k = 0; k <list.size(); k++) {
                         Tanulo actualStudent = (Tanulo) list.get(k);
                 
                 String t1 = actualStudent.getTantargy_1();
                 String t2 = actualStudent.getTantargy_2();
                 String t3 = actualStudent.getTantargy_3();
                 String t4 = actualStudent.getTantargy_4();
                 
                if(x.equals(t1) || x.equals(t2) || x.equals(t3) || x.equals(t4))
                 list2.add(list.get(k));
            
            
        }
                    
                    for (int d = 1; d <= list2.size(); d++) {
                Tanulo tanulok = (Tanulo) list2.get(d-1);
                
                
                table.addCell(tanulok.getOktatasi_azonosíto());
                table.addCell(tanulok.getNev());
                table.addCell(tanulok.getTelepules());
                table.addCell(tanulok.getMegye());
                
            }
            document.add(table);
            document.add(new Paragraph("\n"+list2.size()+" darab vizsgázó van ebblő a tárgyból! \n\n\n"));
                       list2.clear();
        }
    }catch(Exception e){
        
    }
                  
    
    document.close();
    }
    }

