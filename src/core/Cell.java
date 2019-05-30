package core;

import gui.Menu;

public class Cell {
    
    public enum State{DEAD,ALIVE,EMPTY,HEAD,TAIL,CONDUCTOR};
    public State state;
    public boolean change=false;

    
    public Cell(){
        
        if(Menu.cellularAutomaton==Menu.CA.GOL) state=State.DEAD;
        else state=State.EMPTY;
        
    }
    
    
     public void updateCell(int x,int y){

         if(change==true){
            if(Menu.cellularAutomaton==Menu.CA.WW){
                if(state==State.HEAD) {state=State.TAIL;change=false;}
                else if(state==State.TAIL) {state=State.CONDUCTOR;change=false;}
                else if(state==State.CONDUCTOR) {state=State.HEAD;change=false;}
            }
          
            else if(Menu.cellularAutomaton==Menu.CA.GOL){
          
                if(state==State.DEAD) {state=State.ALIVE;change=false;}
                else {state=State.DEAD;change=false;}
            
            }
          
        }
    }
}
