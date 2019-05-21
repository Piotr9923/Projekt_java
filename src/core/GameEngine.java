
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

        if(Menu.celluarautomat==1){

            CelluarAutomat.b.loadGrid();
            if(CelluarAutomat.b.start==0) {try {
                CelluarAutomat.g.updateGrid();generationNumber++;CelluarAutomat.w.write(generationNumber);
                } catch (IOException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            else if(CelluarAutomat.b.next==1) {try {
                CelluarAutomat.g.updateGrid();generationNumber++;CelluarAutomat.w.write(generationNumber);CelluarAutomat.b.next=0;
                } catch (IOException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            CelluarAutomat.b.updateBoard();
        }
        else{ 
            CelluarAutomat.bw.loadGrid();
            if(CelluarAutomat.bw.start==0) {try {
                CelluarAutomat.g.updateGrid();CelluarAutomat.w.write(generationNumber);generationNumber++;
                } catch (IOException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            else if(CelluarAutomat.bw.next==1) {try {
                CelluarAutomat.g.updateGrid();CelluarAutomat.w.write(generationNumber);generationNumber++;CelluarAutomat.bw.next=0;
                } catch (IOException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            CelluarAutomat.bw.updateBoard();        
        }
        
    }
}