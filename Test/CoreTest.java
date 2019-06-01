package core;


import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class CoreTest {

	 private Cell[] neighbourhood=new Cell[8];

	
	@Test
	public void GOLNeighbourhoodTest() {
	
		 
		        for(int a=0;a<8;a++) {neighbourhood[a]=new Cell(); neighbourhood[a].state=Cell.State.DEAD;}
				        
		        neighbourhood[0].state=Cell.State.ALIVE;
		        neighbourhood[4].state=Cell.State.ALIVE;
		        neighbourhood[3].state=Cell.State.ALIVE;
		        neighbourhood[7].state=Cell.State.ALIVE;
		              
		        Assert.assertEquals(GOLNeighbourhood.count(neighbourhood, 8),4);  
	}

	


	@Test
	public void WWNeighbourhoodTest() {

	 
	        for(int a=0;a<8;a++) {neighbourhood[a]=new Cell();neighbourhood[a].state=Cell.State.EMPTY;}
			        
	        neighbourhood[0].state=Cell.State.TAIL;
	        neighbourhood[2].state=Cell.State.CONDUCTOR;
	        neighbourhood[1].state=Cell.State.HEAD;
	        neighbourhood[4].state=Cell.State.HEAD;
	        neighbourhood[3].state=Cell.State.TAIL;
	        neighbourhood[7].state=Cell.State.HEAD;
	              
	        
	     //Zakładamy, że komórka ma tylko 5 sąsiadów
	        Assert.assertEquals(WWNeighbourhood.count(neighbourhood, 5),2);  
	}
}


