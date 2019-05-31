
package core;

import gui.*;
import gui.Menu;
import javax.swing.JFrame;
import javax.swing.Timer;

public class CellularAutomaton extends JFrame{
    
    private final int DELAY=Menu.getCATime();
    public static Grid g;
    static BoardGOL b;
    static BoardWireworld bw;
    static Writer w;
    private int board_width;
    public CellularAutomaton(){
         super("Cellular Automaton");
          
        if(Menu.cellularAutomaton==Menu.CA.GOL){ 
            if(Menu.getCAWidth()<40) board_width=40;
            else board_width=Menu.getCAWidth();
        }
        else{
            if(Menu.getCAWidth()<50) board_width=50;
            else board_width=Menu.getCAWidth();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(board_width * 10 + 6, Menu.getCAHeight() * 10 + 110);
        setResizable(false);
        setLocationRelativeTo(null);
        
        b=new BoardGOL();
        bw=new BoardWireworld();
        g=new Grid();
        w=new Writer();
         if(Menu.cellularAutomaton==Menu.CA.WW) new WireworldBufferedReader(Menu.filepath);
         else new GOLBufferedReader(Menu.filepath);
                 
         if(Menu.cellularAutomaton==Menu.CA.GOL){             
             add(b);
             b.updateBoard();
         }
         else{
             add(bw);
             bw.updateBoard();
         }
         
              
        GameEngine rl=new GameEngine();
        Timer t = new Timer(DELAY, rl);
        t.start();
        
       
        setVisible(true);

        
    }
    
    public static void main(String []args){
        CellularAutomaton ge=new CellularAutomaton();
    }
 
}