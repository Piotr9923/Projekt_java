package core;

import gui.Menu;

public class Grid {

    private final int width=Menu.getCAWidth();
    private final int height=Menu.getCAHeight();
    private int n;
    
    private Cell[][]grid=new Cell[height][width];

    private Cell[] neighbourhood=new Cell[8];

    
    public Grid(){
        for(int a=0;a<8;a++) neighbourhood[a]=new Cell();
         
         
          for(int i=0;i<height;i++)
             for(int j=0;j<width;j++) grid[i][j]=new Cell();
   }
    
    
    public Cell getCell(int y, int x){
        return grid[y][x];
    }
    
    public void setCell(int y,int x,Cell c){
        
        grid[y][x]=c;
        
    }
    
    void setNeighbourhood(int y,int x){
        n=0;
        
 	if (x-1>=0&&y-1>=0)         {neighbourhood[n]=grid[y-1][x-1];n++;}
    	if (x>=0&&y-1>=0)           {neighbourhood[n]=grid[y-1][x];n++;}
    	if (x+1<width&&y-1>=0)      {neighbourhood[n]=grid[y-1][x+1];n++;}
   	if (x-1>=0&&y>=0)           {neighbourhood[n]=grid[y][x-1];n++;}
    	if (x+1<width&&y>=0)        {neighbourhood[n]=grid[y][x+1];n++;}
    	if (x-1>=0&&y+1<height)     {neighbourhood[n]=grid[y+1][x-1];n++;}
    	if (x>=0&&y+1<height)       {neighbourhood[n]=grid[y+1][x];n++;}
    	if (x+1<width&&y+1<height)  {neighbourhood[n]=grid[y+1][x+1];n++;}
    }
    

    void updateGrid(){
      
            for(int y=0;y<height;y++)
            {
        	for(int x=0;x<width;x++)
        	{
                    
                   setNeighbourhood(y,x);
                   
                   CANeighbourhood.countNeighbourhood(neighbourhood, n);
                   
                   int howManyNeighbourhood=CANeighbourhood.howManyNeighbourhood();
                                        
                    if(Menu.cellularAutomaton==Menu.CA.GOL){                
                        if(grid[y][x].state==Cell.State.DEAD&&howManyNeighbourhood==3) {grid[y][x].change=true;}
                        if(grid[y][x].state==Cell.State.ALIVE&&(howManyNeighbourhood<2||howManyNeighbourhood>3)){grid[y][x].change=true;}
                
                    }
                    else{
                       if(grid[y][x].state==Cell.State.HEAD) {grid[y][x].change=true;};
                       if(grid[y][x].state==Cell.State.TAIL) {grid[y][x].change=true;};
                       if(grid[y][x].state==Cell.State.CONDUCTOR&&(howManyNeighbourhood==1||howManyNeighbourhood==2)){grid[y][x].change=true;}
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
