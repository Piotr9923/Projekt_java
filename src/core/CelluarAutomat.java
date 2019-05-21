
package core;

import gui.*;
import gui.Menu;
import javax.swing.JFrame;
import javax.swing.Timer;

public class CelluarAutomat extends JFrame{
    
    private final int DELAY=Menu.time;
    public static Grid g;
    static BoardGOL b;
    static BoardWireworld bw;
    static Writer w;
    private int board_width;
    public CelluarAutomat(){
         super("Game of Life");
          
        if(Menu.celluarautomat==1){ 
            if(Menu.width<40) board_width=40;
            else board_width=Menu.width;
        }
        else{
            if(Menu.width<50) board_width=50;
            else board_width=Menu.width;
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(board_width * 10 + 6, Menu.height * 10 + 100);
        setResizable(false);
        setLocationRelativeTo(null);
        
        b=new BoardGOL();
        bw=new BoardWireworld();
        g=new Grid();
        w=new Writer();
         if(Menu.celluarautomat==0) new WireworldBufferedReader(Menu.filepath);
         else new GOLBufferedReader(Menu.filepath);
                 
         if(Menu.celluarautomat==1){             
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
        CelluarAutomat ge=new CelluarAutomat();
    }
 
}