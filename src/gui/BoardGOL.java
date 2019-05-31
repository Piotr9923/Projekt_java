package gui;

import core.Cell;
import core.CellularAutomaton;
import core.Grid;
import core.GameEngine;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


 public class BoardGOL extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

        private final int width=Menu.getCAWidth();
        private final int height=Menu.getCAHeight();
        private int boardWidth,boardLocation;
        private Cell[][]grid=new Cell[height][width];
        
        public BoardGOL(){
            
            for(int i=0;i<height;i++)
             for(int j=0;j<width;j++) grid[i][j]=new Cell();
            
            addMouseListener(this);
            addMouseMotionListener(this);
         
            if(width<40) boardWidth=40;
            else boardWidth=width;
            if(width>=40) setSize(boardWidth * 10 + 6, height * 10 + 100);
            else setSize(406, height * 10 + 100);
            boardLocation=(boardWidth-width)*5;
        }
        
        
        //Zmienne pomocnicze
        public int start = 0, rp = 0,edit=0,next=0;
        //Tablica która będzie obrazować stany komórek
        JLabel[][] cellImage = new JLabel[height][width];
        //Tablica stanów komórek oraz sprawdzenie czy dana komóra jest do zmiany
       
        
        //Sprawdznie czy dana generacja jest pierwszą czy nie
        public int ile = 0;
        //Wczytanie obrazka z odpowiednim kolorem do danego stanu komórki
        ImageIcon live = new ImageIcon(getClass().getResource("/image/" + Menu.liveColor + Menu.shape + ".png"));
        ImageIcon dead = new ImageIcon(getClass().getResource("/image/" + Menu.deadColor + Menu.shape + ".png"));
        
        public int numberShape=1;
      
 
        //Obrazki układów
         ImageIcon imShape1 = new ImageIcon(getClass().getResource("/image/SHAPE1.png"));
         ImageIcon imShape1d = new ImageIcon(getClass().getResource("/image/SHAPE1d.png"));
         ImageIcon imShape2 = new ImageIcon(getClass().getResource("/image/SHAPE2.png"));
         ImageIcon imShape2d = new ImageIcon(getClass().getResource("/image/SHAPE2d.png"));
         ImageIcon imShape3 = new ImageIcon(getClass().getResource("/image/SHAPE3.png"));
         ImageIcon imShape3d = new ImageIcon(getClass().getResource("/image/SHAPE3d.png"));
        //Przyciski sterujące
        JButton buttonStop = new JButton("Stop");
        JButton buttonStart = new JButton("Start");
        JButton buttonNext = new JButton("Next");
        
        JButton shape= new JButton();
        JButton nextShape= new JButton(new ImageIcon(getClass().getResource("/image/NEXT.png")));
        
        JLabel iconShape=new JLabel();
        private int shapeX=-55,shapeY=-55;
        private int drawing=0; 
        
        JLabel n=new JLabel();
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
          
            n.setBounds(boardWidth*5+150, height * 10 + 15, 35, 20);
            n.setText(GameEngine.generationNumber+"");
            if(ile==0) add(n);
            
            if(drawing==1) {iconShape.setIcon(imShape1d); iconShape.setBounds(shapeX-5, shapeY-5, 30, 30);}
            if(drawing==2) {iconShape.setIcon(imShape2d); iconShape.setBounds(shapeX-5, shapeY-5, 50, 40);}
            if(drawing==3) {iconShape.setIcon(imShape3d); iconShape.setBounds(shapeX-5, shapeY-5, 90, 90);}
            iconShape.setLocation(shapeX-5, shapeY-5);
           
            if(ile==0) add(iconShape,1);
            
            //Dodanie przycisków
           if(ile==0){ add(buttonStop);
            buttonStop.addActionListener(this);
           
            add(buttonStart);
            buttonStart.addActionListener(this);

            add(buttonNext);
            buttonNext.addActionListener(this);
          
           }
            buttonNext.setBounds(boardWidth*5+50, height * 10 + 15, 80, 40);
            buttonStart.setBounds(boardWidth*5-130, height * 10 + 15, 80, 40);
            buttonStop.setBounds(boardWidth*5-30, height * 10 + 15, 60, 40);

            shape.setBounds(10, height * 10 + 15, 30, 30);
            nextShape.setBounds(10, height * 10 + 50, 30, 15);
           
           
            if(edit==1){
                add(shape);
                shape.removeActionListener(this);
                shape.addActionListener(this);
                
                if(numberShape==1){shape.setIcon(imShape1);}
                else if(numberShape==2){shape.setIcon(imShape2);}
                else if(numberShape==3){shape.setIcon(imShape3);}
                add(nextShape);
                nextShape.removeActionListener(this);
                nextShape.addActionListener(this);
            }
            else {remove(shape);remove(nextShape);}
            
          
          if(ile==0){
                for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    
                            cellImage[i][j] = new JLabel();add(cellImage[i][j]);

                        cellImage[i][j].setBounds(j * 10+boardLocation, i * 10, 10, 10);
                        
                    } 
                }
            }
            
            //Wczytujemy odpowiednie obrazki na podstawie stanu danej komórki
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                cellImage[i][j].setLocation(j * 10+boardLocation, i * 10);

                    if (grid[i][j].state == Cell.State.ALIVE) {
                            cellImage[i][j].setIcon(live);                       
                    } 
                    if (grid[i][j].state == Cell.State.DEAD) {
                        cellImage[i][j].setIcon(dead);
                    }
                   
                }
            }
            if(ile==0) {start=1;edit=1;}
           ile++;

        }

        public void actionPerformed(ActionEvent e) {
            //Rozpoczęcie generacji
         
            if (e.getSource() == buttonStart) {
                start = 0;
                rp = 0;
                edit=0;
            }
            //Zatrzymanie generacji
            if (e.getSource() == buttonStop) {
                start = 1;
                next=0;
                edit=1;
                repaint();
                rp=1;
                
            }
            //Wczytanie następnej generacji
            if (e.getSource() == buttonNext) {
                start = 0;
                repaint();
                start=1;
                next=1;
                rp = 1;
                edit=0;
            }
            if(e.getSource() == shape)
            {
              
               drawing=numberShape;
               repaint();
            }
            if(e.getSource() == nextShape)
            {
               numberShape++;
               if(numberShape==4) numberShape=1;
               repaint();
            }
            
           
        }

       public void loadGrid(){
            
            for(int i=0;i<height;i++)
                for(int j=0;j<width;j++) CellularAutomaton.g.setCell(i, j, grid[i][j]);
        }
         
        public void updateBoard(){
            
            for(int i=0;i<height;i++)
                for(int j=0;j<width;j++) grid[i][j]=CellularAutomaton.g.getCell(i, j);
            
            repaint();
    
        }
        
       @Override
	public void mouseClicked(MouseEvent e) {
          
            //Wczytanie współrzędnych w które kliknęło się myszko, a następnie zmiana stanu komórki na przeciwny
            if(drawing==0&&start==1&&e.getY()<height*10&&e.getX()<width*10+boardLocation&&e.getX()>boardLocation){
                if(grid[e.getY()/10][(e.getX()-boardLocation)/10].state==Cell.State.ALIVE) grid[e.getY()/10][(e.getX()-boardLocation)/10].state=Cell.State.DEAD;
                else if(grid[e.getY()/10][(e.getX()-boardLocation)/10].state==Cell.State.DEAD) grid[e.getY()/10][(e.getX()-boardLocation)/10].state=Cell.State.ALIVE;
                repaint();
            }
           
            if(drawing>0&&e.getY()<height*10&&e.getX()<width*10+boardLocation&&e.getX()>boardLocation){
               if(drawing==1) drawGlider((e.getX()-boardLocation)/10,e.getY()/10);
               if(drawing==2) drawLwss((e.getX()-boardLocation)/10,e.getY()/10);
               if(drawing==3) drawLoafer((e.getX()-boardLocation)/10,e.getY()/10);
                drawing=0;
                shapeX=-95;
                shapeX=-95;
                repaint();
            }
	}
       

	@Override
	public void mouseMoved(MouseEvent e) {
            if(drawing>0){
		shapeX=e.getX();
                shapeY=e.getY();
                repaint();
            }
            else {
            shapeX=-95;
            shapeY=-95;
            
            }
	}
        
        @Override
	public void mouseDragged(MouseEvent e) {
             
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
            
	}
        
       
   
        
        private void drawGlider(int x,int y){
                if(y+0<height&&x+1<width) grid[0+y][1+x].state=Cell.State.ALIVE;
                if(y+1<height&&x+2<width) grid[1+y][2+x].state=Cell.State.ALIVE;
                if(y+2<height&&x+0<width) grid[2+y][0+x].state=Cell.State.ALIVE;
                if(y+2<height&&x+1<width) grid[2+y][1+x].state=Cell.State.ALIVE;
                if(y+2<height&&x+2<width) grid[2+y][2+x].state=Cell.State.ALIVE;
        }
        private void drawLwss(int x,int y){
                if(y+0<height&&x+0<width) grid[0+y][0+x].state=Cell.State.ALIVE;
                if(y+0<height&&x+3<width) grid[0+y][3+x].state=Cell.State.ALIVE;
                if(y+1<height&&x+4<width) grid[1+y][4+x].state=Cell.State.ALIVE;
                if(y+2<height&&x+4<width) grid[2+y][4+x].state=Cell.State.ALIVE;
                if(y+2<height&&x+0<width) grid[2+y][0+x].state=Cell.State.ALIVE;
                if(y+3<height&&x+1<width) grid[3+y][1+x].state=Cell.State.ALIVE;
                if(y+3<height&&x+2<width) grid[3+y][2+x].state=Cell.State.ALIVE;
                if(y+3<height&&x+3<width) grid[3+y][3+x].state=Cell.State.ALIVE;
                if(y+3<height&&x+4<width) grid[3+y][4+x].state=Cell.State.ALIVE;
        }
        
        private void drawLoafer(int x,int y){
                if(y+0<height&&x+0<width) grid[0+y][0+x].state=Cell.State.ALIVE;
                if(y+0<height&&x+1<width) grid[0+y][1+x].state=Cell.State.ALIVE;
                if(y+0<height&&x+6<width) grid[0+y][6+x].state=Cell.State.ALIVE;
                if(y+0<height&&x+7<width) grid[0+y][7+x].state=Cell.State.ALIVE;
                if(y+1<height&&x+0<width) grid[1+y][0+x].state=Cell.State.ALIVE;
                if(y+1<height&&x+1<width) grid[1+y][1+x].state=Cell.State.ALIVE;
                if(y+1<height&&x+2<width) grid[1+y][2+x].state=Cell.State.ALIVE;
                if(y+1<height&&x+4<width) grid[1+y][4+x].state=Cell.State.ALIVE;
                if(y+1<height&&x+5<width) grid[1+y][5+x].state=Cell.State.ALIVE;
                if(y+1<height&&x+8<width) grid[1+y][8+x].state=Cell.State.ALIVE;
                if(y+2<height&&x+5<width) grid[2+y][5+x].state=Cell.State.ALIVE;
                if(y+2<height&&x+7<width) grid[2+y][7+x].state=Cell.State.ALIVE;
                if(y+3<height&&x+6<width) grid[3+y][6+x].state=Cell.State.ALIVE;
                if(y+4<height&&x+1<width) grid[4+y][0+x].state=Cell.State.ALIVE;
                if(y+5<height&&x+1<width) grid[5+y][0+x].state=Cell.State.ALIVE;
                if(y+5<height&&x+1<width) grid[5+y][1+x].state=Cell.State.ALIVE;
                if(y+5<height&&x+2<width) grid[5+y][2+x].state=Cell.State.ALIVE;
                if(y+6<height&&x+3<width) grid[6+y][3+x].state=Cell.State.ALIVE;
                if(y+7<height&&x+1<width) grid[7+y][1+x].state=Cell.State.ALIVE;
                if(y+7<height&&x+2<width) grid[7+y][2+x].state=Cell.State.ALIVE;
                if(y+8<height&&x+1<width) grid[8+y][1+x].state=Cell.State.ALIVE;
        }
        
      
    }


