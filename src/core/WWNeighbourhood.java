package core;

public class WWNeighbourhood {
    
    
   public static int count(Cell[] neighbourhood,int n){
       
       int howMany=0;
       
       for(int i=0;i<n;i++) if(neighbourhood[i].state==Cell.State.HEAD) howMany++;
       
       return howMany;
    }  
    
}