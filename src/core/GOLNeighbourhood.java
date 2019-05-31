package core;

public class GOLNeighbourhood {
    
    
   public static int count(Cell[] neighbourhood,int n){
       
       int howMany=0;
       
       for(int i=0;i<n;i++) {if(neighbourhood[i].state==Cell.State.ALIVE) howMany++;}
       
       
       return howMany;
    }
   
}

