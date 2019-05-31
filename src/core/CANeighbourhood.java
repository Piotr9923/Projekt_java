
package core;

import gui.Menu;

public class CANeighbourhood {
    
    private static int howManyNeighbourhood;
    
    public static void countNeighbourhood(Cell[] neighbourhood,int n){

       if(Menu.cellularAutomaton==Menu.CA.GOL)howManyNeighbourhood=GOLNeighbourhood.count(neighbourhood,n);
       else howManyNeighbourhood=WWNeighbourhood.count(neighbourhood,n);
    }
    
    public static int howManyNeighbourhood(){
        return howManyNeighbourhood;
    }

}