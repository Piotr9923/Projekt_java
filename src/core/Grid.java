
package core;

import gui.Menu;

public class Grid {

    private final int width=Menu.width;
    private final int height=Menu.height;
    
    public Cell[][]grid=new Cell[height][width];

    public Grid(){
      
          for(int i=0;i<height;i++)
             for(int j=0;j<width;j++) grid[i][j]=new Cell();
   }
    
    
    int countNeighbourhood(int y,int x){
        int how_many_neighbourhood=0;
            
 	/*sprawdzenie czy		**sprawdzenie czy sąsiad		**zwiększenie liczby 
 	**komórka posiada 		**jest żywą komórką			**żwych sąsiadów komórki
 	**sąsiada 		*/	
  	if (x-1>=0&&y-1>=0)		 if (grid[y-1][x-1].state==Cell.State.ALIVE)   how_many_neighbourhood++;
    	if (x>=0&&y-1>=0)		 if (grid[y-1][x].state==Cell.State.ALIVE) 	how_many_neighbourhood++;
    	if (x+1<width&&y-1>=0) 		 if (grid[y-1][x+1].state==Cell.State.ALIVE)   how_many_neighbourhood++;
   	if (x-1>=0&&y>=0)		 if (grid[y][x-1].state==Cell.State.ALIVE) 	how_many_neighbourhood++;
    	if (x+1<width&&y>=0)		 if (grid[y][x+1].state==Cell.State.ALIVE) 	how_many_neighbourhood++;
    	if (x-1>=0&&y+1<height)		 if (grid[y+1][x-1].state==Cell.State.ALIVE)   how_many_neighbourhood++;
    	if (x>=0&&y+1<height)		 if (grid[y+1][x].state==Cell.State.ALIVE) 	how_many_neighbourhood++;
    	if (x+1<width&&y+1<height)	 if (grid[y+1][x+1].state==Cell.State.ALIVE)   how_many_neighbourhood++;
        
        return how_many_neighbourhood;
     }
    

    void updateGrid(){
      
            for(int y=0;y<height;y++)
            {
        	for(int x=0;x<width;x++)
        	{
                    
                    int how_many_neighbourhood=countNeighbourhood(y,x);
                   
                    if(Menu.cellularAutomaton==Menu.CA.GOL){                
                        if(grid[y][x].state==Cell.State.DEAD&&how_many_neighbourhood==3) {grid[y][x].change=true;}
                        if(grid[y][x].state==Cell.State.ALIVE&&(how_many_neighbourhood<2||how_many_neighbourhood>3)){grid[y][x].change=true;}
                
                    }
                    else{
                       if(grid[y][x].state==Cell.State.HEAD) {grid[y][x].change=true;};
                       if(grid[y][x].state==Cell.State.TAIL) {grid[y][x].change=true;};
                       if(grid[y][x].state==Cell.State.CONDUCTOR&&(how_many_neighbourhood==1||how_many_neighbourhood==2)){grid[y][x].change=true;}
                     }
        	}
            }
        
        
        
        
            for(int y=0;y<height;y++)
            {
        	for(int x=0;x<width;x++)
        	{
                    grid[y][x].updateCell(x,y);
                }
            }
        }
    
    void load(int x,int y,Cell.State s){
        
      grid[y][x].state=s;
      
    }
    
}
