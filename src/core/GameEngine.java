
package core;

import gui.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameEngine implements ActionListener{

    public static int generationNumber=0;
    
    public GameEngine(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        update();
          
    }
    
    private void update(){
        if(Menu.cellularAutomaton==Menu.CA.GOL){

            CellularAutomaton.b.loadGrid();
            if(CellularAutomaton.b.start==0) {try {
                CellularAutomaton.g.updateGrid();generationNumber++;CellularAutomaton.w.write(generationNumber);
                } catch (IOException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            else if(CellularAutomaton.b.next==1) {try {
                CellularAutomaton.g.updateGrid();generationNumber++;CellularAutomaton.w.write(generationNumber);CellularAutomaton.b.next=0;
                } catch (IOException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            CellularAutomaton.b.updateBoard();
        }
        else{ 
            CellularAutomaton.bw.loadGrid();
            if(CellularAutomaton.bw.start==0) {try {
                CellularAutomaton.g.updateGrid();CellularAutomaton.w.write(generationNumber);generationNumber++;
                } catch (IOException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            else if(CellularAutomaton.bw.next==1) {try {
                CellularAutomaton.g.updateGrid();CellularAutomaton.w.write(generationNumber);generationNumber++;CellularAutomaton.bw.next=0;
                } catch (IOException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            CellularAutomaton.bw.updateBoard();        
        }
        
        
    }
}