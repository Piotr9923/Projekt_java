package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import core.CellularAutomaton.*;
import gui.Menu;

public class WireworldBufferedReader {

    
   private int n=0,next=0,x=0,y=0;
   private int line=1;
   
public WireworldBufferedReader(String path){
   FileReader fr = null;
   String linia = "";
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
                if(state.equals("ElectronHead")==true) if(x<Menu.getCAWidth()&&x>=0&&y<Menu.getCAHeight()&&y>=0) CellularAutomaton.g.load(x,y,Cell.State.HEAD);
                else if(state.equals("ElectronTail")==true) if(x<Menu.getCAWidth()&&x>=0&&y<Menu.getCAHeight()&&y>=0) CellularAutomaton.g.load(x,y,Cell.State.TAIL);
                else if(state.equals("Conductor")==true) if(x<Menu.getCAWidth()&&x>=0&&y<Menu.getCAHeight()&&y>=0) CellularAutomaton.g.load(x,y,Cell.State.CONDUCTOR);
                else JOptionPane.showMessageDialog(null, "In file in line "+line+" is wrong cell's state!!");
                
            

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

}
