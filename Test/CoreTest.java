package core;


import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import core.Cell.State;
import gui.Menu;
import gui.Menu.CA;

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
	              
	        
	     //Zak³adamy, ¿e komórka ma tylko 5 s¹siadów
	        Assert.assertEquals(WWNeighbourhood.count(neighbourhood, 5),2);  
	}
	
//Test, gdy wybierzemy Wireworld
	@Test
	public void CANeighbourhoodTest() {

	 
	        for(int a=0;a<8;a++) {neighbourhood[a]=new Cell();neighbourhood[a].state=Cell.State.EMPTY;}
			        
	        neighbourhood[0].state=Cell.State.TAIL;
	        neighbourhood[2].state=Cell.State.HEAD;
	        neighbourhood[1].state=Cell.State.HEAD;
	        neighbourhood[4].state=Cell.State.HEAD;
	        neighbourhood[3].state=Cell.State.TAIL;
	        neighbourhood[7].state=Cell.State.HEAD;
	              
	        Menu.cellularAutomaton=CA.WW;
	        
	        CANeighbourhood.countNeighbourhood(neighbourhood, 8);
	        
	        Assert.assertEquals(CANeighbourhood.howManyNeighbourhood(),4);  
	}
	
//Test, gdy wybierzemy Game of life
	@Test
	public void CANeighbourhoodTest1() {

		 
		      for(int a=0;a<8;a++) {neighbourhood[a]=new Cell();neighbourhood[a].state=Cell.State.DEAD;}
				        
		        neighbourhood[0].state=Cell.State.ALIVE;
		        neighbourhood[2].state=Cell.State.ALIVE;
		        neighbourhood[1].state=Cell.State.ALIVE;
		        neighbourhood[4].state=Cell.State.ALIVE;
		        neighbourhood[3].state=Cell.State.ALIVE;
		        neighbourhood[7].state=Cell.State.ALIVE;
		              
		        Menu.cellularAutomaton=CA.GOL;
		        
		        CANeighbourhood.countNeighbourhood(neighbourhood, 8);
		        
		    
		        Assert.assertEquals(CANeighbourhood.howManyNeighbourhood(),6);  
		}
	
	
	
    private Cell[][]grid=new Cell[5][5];
    
	@Test
	public void updateCellTest() {
		
		
		 for(int i=0;i<5;i++)
             for(int j=0;j<5;j++) {grid[i][j]=new Cell();grid[i][j].state=State.DEAD;}
		      
		 		grid[0][0].state=Cell.State.ALIVE;
		 		grid[2][2].state=Cell.State.ALIVE;
		 		grid[1][1].state=Cell.State.ALIVE;
		        grid[3][4].state=Cell.State.ALIVE;
		        grid[2][0].state=Cell.State.ALIVE;
		              
		        
		        grid[1][1].change=true;
		        grid[2][0].change=true;
		        grid[4][4].change=true;
		        grid[2][3].change=true;
		        
		        Menu.cellularAutomaton=CA.GOL;
		        
		        for(int i=0;i<5;i++)
		             for(int j=0;j<5;j++) grid[i][j].updateCell(j, i);
		        
		        Assert.assertTrue(grid[1][1].state==State.DEAD);
		        Assert.assertTrue(grid[2][0].state==State.DEAD);
		        Assert.assertTrue(grid[4][4].state==State.ALIVE);
		        Assert.assertTrue(grid[2][3].state==State.ALIVE);
		        Assert.assertTrue(grid[0][0].state==State.ALIVE);
		        Assert.assertTrue(grid[1][4].state==State.DEAD);
		        
		}
	
	
	@Test
	public void getCellsetCellTest() {
		
		Menu.cellularAutomaton=CA.GOL;
		Grid g=new Grid();
		
		Cell c=new Cell();
		
		c.state=State.ALIVE;
		
		g.setCell(3, 3, c);
		g.setCell(7, 6, c);
	
        Assert.assertTrue(g.getCell(3, 3).state==State.ALIVE);
  
        Assert.assertTrue(g.getCell(7, 6).state==State.ALIVE);
        
        Assert.assertTrue(g.getCell(5, 1).state==State.DEAD);		
	}
	
	
	@Test
	public void updateGridTest() {
	
		Menu.cellularAutomaton=CA.GOL;
		Grid g=new Grid();
		
		Cell c=new Cell();
		c.state=State.ALIVE;
		
		g.setCell(0, 0, c);
		g.setCell(1, 0, c);
		g.setCell(0, 1, c);
		
		Assert.assertTrue(g.getCell(1, 1).state==State.DEAD);
		
		g.updateGrid();
		
		Assert.assertTrue(g.getCell(1, 1).state==State.ALIVE);	
	}
	
	
}


