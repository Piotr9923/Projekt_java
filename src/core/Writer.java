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

/**
 *
 * @author Użytkownik
 */
public class Writer {
    
    void write(int number) throws IOException{
        for(int i=0;i<Menu.height;i++)
            for(int j=0;j<Menu.width;j++)
            {
                if(Menu.celluarautomat==0){
                    
                   if(GameEngine.g.grid[i][j].state==1)writeWireworld("ElectronHead",j,i,number);
                   else if(GameEngine.g.grid[i][j].state==2)writeWireworld("ElectronTail",j,i,number);
                   else if(GameEngine.g.grid[i][j].state==3)writeWireworld("Conductor",j,i,number);
                    
                }
                else {
                    if(GameEngine.g.grid[i][j].state==1) writeGOL(j,i,number);
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
