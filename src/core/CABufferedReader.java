
package core;

import gui.Menu;


public class CABufferedReader {
    
    public static void read(String InputFileStream){
        
        if(Menu.cellularAutomaton==Menu.CA.GOL) new GOLBufferedReader(InputFileStream);
        else new WireworldBufferedReader(InputFileStream);
    }
    
}
