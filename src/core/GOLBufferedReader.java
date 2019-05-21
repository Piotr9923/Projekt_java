package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import core.GameEngine.*;
import gui.Menu;

public class GOLBufferedReader {

    
   private int n=0,next=0,x=0,y=0;
   private int line=1;
   
public GOLBufferedReader(String path){
    
   FileReader fr = null;
   String linia = "";
   
   if(path!=null){
   // OTWIERANIE PLIKU:
   try {
     fr = new FileReader(path);
   } catch (FileNotFoundException e) {
     JOptionPane.showMessageDialog(null, "FILE OPEN ERROR");
 
   }
   BufferedReader bfr = new BufferedReader(fr);
   // ODCZYT KOLEJNYCH LINII Z PLIKU:
   try {
     while((linia = bfr.readLine()) != null){
        next=linia.indexOf(',');
        
        if(next>0){
        try {
              
                x = Integer.parseInt(linia.substring(0, next));
                y = Integer.parseInt(linia.substring(next+1, linia.length()));
                
            if(x<Menu.width&&x>=0&&y<Menu.height&&y>=0) GameEngine.g.load(x,y,1);
            
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(null, "In file in line "+line+" is wrong value!!");
            }
        
        } else JOptionPane.showMessageDialog(null, "In file in line "+line+" is wrong value!!");
        line++;
     }
    } catch (IOException e) {
     JOptionPane.showMessageDialog(null, "READ FILE ERROR");
   }

   // ZAMYKANIE PLIKU
   try {
     fr.close();
    } catch (IOException e) {
     JOptionPane.showMessageDialog(null, "FILE CLOSE ERROR");
         System.exit(3);
        }
    }
}

public static void main(String[]args){
    
}
}
