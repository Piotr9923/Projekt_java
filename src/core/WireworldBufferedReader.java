package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import core.GameEngine.*;
import gui.Menu;

public class WireworldBufferedReader {

    
   private int n=0,next=0,x=0,y=0;
   private int line=1;
   
public WireworldBufferedReader(String path){
   FileReader fr = null;
   String linia = "";
   int s=0;
   String state="";
   
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
        n=linia.indexOf(':');
        
        if(n>0&&next>0){
        try {
              
                state=linia.substring(0, n);
                x = Integer.parseInt(linia.substring(n+2, next));
                y = Integer.parseInt(linia.substring(next+1, linia.length()));
                if(state.equals("ElectronHead")==true) s=1;
                else if(state.equals("ElectronTail")==true) s=2;
                else if(state.equals("Conductor")==true) s=3;
                else JOptionPane.showMessageDialog(null, "In file in line "+line+" is wrong cell's state!!");
                
            if(x<Menu.width&&x>=0&&y<Menu.height&&y>=0) GameEngine.g.load(x,y,s);

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
