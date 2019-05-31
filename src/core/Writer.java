/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import gui.Menu;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    
    void write(int number) throws IOException{
        for(int i=0;i<Menu.getCAHeight();i++)
            for(int j=0;j<Menu.getCAWidth();j++)
            {
                if(Menu.cellularAutomaton==Menu.CA.WW){
                    
                   if(CellularAutomaton.g.getCell(i, j).state==Cell.State.HEAD)writeWireworld("ElectronHead",j,i,number);
                   else if(CellularAutomaton.g.getCell(i, j).state==Cell.State.TAIL)writeWireworld("ElectronTail",j,i,number);
                   else if(CellularAutomaton.g.getCell(i, j).state==Cell.State.CONDUCTOR)writeWireworld("Conductor",j,i,number);
                    
                }
                else {
                    if(CellularAutomaton.g.getCell(i, j).state==Cell.State.ALIVE) writeGOL(j,i,number);
                }
                
            }
        
    }
    
    
     void writeGOL(int x,int y, int number) throws FileNotFoundException, IOException{
            FileWriter file = new FileWriter("Results/"+Menu.filename+number+".txt", true);
            BufferedWriter out = new BufferedWriter(file);
            out.write(x+","+y);
            out.newLine();
            out.close();
        }
    
    
    void writeWireworld(String state,int x,int y, int number) throws FileNotFoundException, IOException{
            FileWriter file = new FileWriter("Results/"+Menu.filename+number+".txt", true);
            BufferedWriter out = new BufferedWriter(file);
            out.write(state+": "+x+","+y);
            out.newLine();
            out.close();
        }
    
    
    
}
