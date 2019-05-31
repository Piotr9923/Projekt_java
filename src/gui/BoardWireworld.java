package gui;

import core.Cell;
import core.GameEngine;
import core.CellularAutomaton;
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


  public class BoardWireworld extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

      
        private final int width=Menu.getCAWidth();
        private final int height=Menu.getCAHeight();
        private int boardWidth,boardLocation;
        private Cell[][]grid=new Cell[height][width];
        
        
        public BoardWireworld(){
         addMouseListener(this);
         addMouseMotionListener(this);
         for(int i=0;i<height;i++)
             for(int j=0;j<width;j++) grid[i][j]=new Cell();
         
            if(width<50) boardWidth=50;
            else boardWidth=width;
            if(width>=50) setSize(boardWidth * 10 + 6, height * 10 + 100);
            else setSize(506, height * 10 + 100);
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
        ImageIcon empty = new ImageIcon(getClass().getResource("/image/" + Menu.emptyColor + "SQUARE.png"));
        ImageIcon head = new ImageIcon(getClass().getResource("/image/" + Menu.headColor + "SQUARE.png"));
        ImageIcon tail = new ImageIcon(getClass().getResource("/image/" + Menu.tailColor + "SQUARE.png"));
        ImageIcon conductor = new ImageIcon(getClass().getResource("/image/" + Menu.conductorColor + "SQUARE.png"));

        public int numberShape=1;
      
     
    //Obrazki układów
        ImageIcon imShape1 = new ImageIcon(getClass().getResource("/image/DIODE1.png"));
        ImageIcon imShape1d = new ImageIcon(getClass().getResource("/image/DIODE1d.png"));
        ImageIcon imShape2 = new ImageIcon(getClass().getResource("/image/DIODE2.png"));
        ImageIcon imShape2d = new ImageIcon(getClass().getResource("/image/DIODE2d.png"));
        ImageIcon imShape3 = new ImageIcon(getClass().getResource("/image/DIODE3.png"));
        ImageIcon imShape3d = new ImageIcon(getClass().getResource("/image/DIODE3d.png"));
        //Przyciski sterujące
        JButton buttonStop = new JButton("Stop");
        JButton buttonStart = new JButton("Start");
        JButton buttonNext = new JButton("Next");
        
        JButton shape= new JButton();
        JButton nextShape= new JButton(new ImageIcon(getClass().getResource("/image/NEXT.png")));
        
        JLabel cellState=new JLabel();
        JButton nextCellState=new JButton(new ImageIcon(getClass().getResource("/image/NEXT.png")));
        private int cellS;
        JLabel shapeName=new JLabel();
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
            
            
            if(drawing==1) {iconShape.setIcon(imShape1d); iconShape.setBounds(shapeX-5, shapeX-5, 120, 30);}
            if(drawing==2) {iconShape.setIcon(imShape2d); iconShape.setBounds(shapeX-5, shapeX-5, 120, 30);}
            if(drawing==3) {iconShape.setIcon(imShape3d); iconShape.setBounds(shapeX-5, shapeX-5, 170, 90);}
            iconShape.setLocation(shapeX-5, shapeY-5);
           
            if(ile==0) add(iconShape,1);
            
            //Dodanie przycisków
            if(ile==0){
            add(buttonStop);
            buttonStop.addActionListener(this);
            
            add(buttonStart);
            buttonStart.addActionListener(this);

            add(buttonNext);
            buttonNext.addActionListener(this);
            }
            
            buttonStop.setBounds(boardWidth*5-30, height * 10 + 15, 60, 40);
            buttonStart.setBounds(boardWidth*5-130, height * 10 + 15, 80, 40);
            buttonNext.setBounds(boardWidth*5+50, height * 10 + 15, 80, 40);
           
            cellState.setBounds(75, height*10+25, 20, 20);
            nextCellState.setBounds(70, height*10+50, 30, 15);
            shapeName.setBounds(60, height*10+5, 70, 15);
            shape.setBounds(5, height * 10 + 15, 50, 30);
            nextShape.setBounds(10, height * 10 + 50, 30, 15);
            
            
            if(edit==1){
                add(shape);
                shape.removeActionListener(this);
                shape.addActionListener(this);
                add(shapeName);
                if(numberShape==1){shape.setIcon(imShape1);}
                else if(numberShape==2){shape.setIcon(imShape2);}
                else if(numberShape==3){shape.setIcon(imShape3);}
                add(nextShape);
                nextShape.removeActionListener(this);
                nextShape.addActionListener(this);
                
                //**************************************************//
                add(cellState);
             
                
                if(cellS==0){cellState.setIcon(empty);shapeName.setText("empty");}
                else if(cellS==1){cellState.setIcon(head);shapeName.setText("head");}
                else if(cellS==2){cellState.setIcon(tail);shapeName.setText("tail");}
                else if(cellS==3){cellState.setIcon(conductor);shapeName.setText("conductor");}

                add(nextCellState);
                nextCellState.removeActionListener(this);
                nextCellState.addActionListener(this);  
            }
            else {remove(shape);remove(nextShape);remove(cellState);remove(nextCellState);remove(shapeName);}
            
            
            
         
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
                  
                    if (grid[i][j].state == Cell.State.HEAD) {
                        
                            cellImage[i][j].setIcon(head); 
                            
                    }
                    if (grid[i][j].state == Cell.State.TAIL) {
                    
                            cellImage[i][j].setIcon(tail);
                     } 
                    if (grid[i][j].state == Cell.State.CONDUCTOR) {
                 
                            cellImage[i][j].setIcon(conductor); 
                    } 
                    if (grid[i][j].state == Cell.State.EMPTY) {
                       cellImage[i][j].setIcon(empty); 
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
            
            if(e.getSource() == nextCellState)
            {
               cellS++;
               if(cellS==4) cellS=0;
               repaint();
            }
            
        }

       @Override
	public void mouseClicked(MouseEvent e) {
          
            //Wczytanie współrzędnych w które kliknęło się myszko, a następnie zmiana stanu komórki na przeciwny
            if(drawing==0&&start==1&&e.getY()<height*10&&e.getX()<width*10+boardLocation&&e.getX()>boardLocation){
            if(cellS==0) grid[e.getY()/10][(e.getX()-boardLocation)/10].state=Cell.State.EMPTY;
            else if(cellS==1) grid[e.getY()/10][(e.getX()-boardLocation)/10].state=Cell.State.HEAD;
            else if(cellS==2) grid[e.getY()/10][(e.getX()-boardLocation)/10].state=Cell.State.TAIL;
            else if(cellS==3) grid[e.getY()/10][(e.getX()-boardLocation)/10].state=Cell.State.CONDUCTOR;
                repaint();
            }
           
            if(drawing>0&&e.getY()<height*10&&e.getX()<width*10+boardLocation&&e.getX()>boardLocation){
               if(drawing==1) drawDiode1((e.getX()-boardLocation)/10,e.getY()/10);
               if(drawing==2) drawDiode2((e.getX()-boardLocation)/10,e.getY()/10);
               if(drawing==3) drawDiode3((e.getX()-boardLocation)/10,e.getY()/10);
                drawing=0;
                shapeX=-155;
                shapeY=-155;
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
            shapeX=-155;
            shapeY=-155;
            
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
        
        public void loadGrid(){
            
            for(int i=0;i<height;i++)
                for(int j=0;j<width;j++) CellularAutomaton.g.setCell(i, j, grid[i][j]);
        }
         
        public void updateBoard(){
            
            for(int i=0;i<height;i++)
                for(int j=0;j<width;j++) grid[i][j]=CellularAutomaton.g.getCell(i, j);
            
            repaint();
    
        }
        
        private void drawDiode1(int x,int y){
           if(y+1<height&&x+0<width) grid[y+1][x+0].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+1<width) grid[y+1][x+1].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+2<width) grid[y+1][x+2].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+3<width) grid[y+1][x+3].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+4<width) grid[y+1][x+4].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+5<width) grid[y+1][x+5].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+7<width) grid[y+1][x+7].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+8<width) grid[y+1][x+8].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+9<width) grid[y+1][x+9].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+10<width) grid[y+1][x+10].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+11<width) grid[y+1][x+11].state=Cell.State.CONDUCTOR;
           if(y+0<height&&x+5<width) grid[y+0][x+5].state=Cell.State.CONDUCTOR;
           if(y+0<height&&x+6<width) grid[y+0][x+6].state=Cell.State.CONDUCTOR;
           if(y+2<height&&x+5<width) grid[y+2][x+5].state=Cell.State.CONDUCTOR;
           if(y+2<height&&x+6<width) grid[y+2][x+6].state=Cell.State.CONDUCTOR;
           
            
          
        }
        
        private void drawDiode2(int x,int y){
           if(y+1<height&&x+0<width) grid[y+1][x+0].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+1<width) grid[y+1][x+1].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+2<width) grid[y+1][x+2].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+3<width) grid[y+1][x+3].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+4<width) grid[y+1][x+4].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+6<width) grid[y+1][x+6].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+7<width) grid[y+1][x+7].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+8<width) grid[y+1][x+8].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+9<width) grid[y+1][x+9].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+10<width) grid[y+1][x+10].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+11<width) grid[y+1][x+11].state=Cell.State.CONDUCTOR;
           if(y+0<height&&x+5<width) grid[y+0][x+5].state=Cell.State.CONDUCTOR;
           if(y+0<height&&x+6<width) grid[y+0][x+6].state=Cell.State.CONDUCTOR;
           if(y+2<height&&x+5<width) grid[y+2][x+5].state=Cell.State.CONDUCTOR;
           if(y+2<height&&x+6<width) grid[y+2][x+6].state=Cell.State.CONDUCTOR;
        }
        
        private void drawDiode3(int x,int y){
           if(y+0<height&&x+1<width) grid[y+0][x+1].state=Cell.State.CONDUCTOR;
           if(y+0<height&&x+2<width) grid[y+0][x+2].state=Cell.State.CONDUCTOR;
           if(y+0<height&&x+3<width) grid[y+0][x+3].state=Cell.State.CONDUCTOR;
           if(y+0<height&&x+4<width) grid[y+0][x+4].state=Cell.State.CONDUCTOR;
           if(y+0<height&&x+5<width) grid[y+0][x+5].state=Cell.State.CONDUCTOR;
           if(y+0<height&&x+6<width) grid[y+0][x+6].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+0<width) grid[y+1][x+0].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+7<width) grid[y+1][x+7].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+8<width) grid[y+1][x+8].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+9<width) grid[y+1][x+9].state=Cell.State.CONDUCTOR;
           if(y+1<height&&x+10<width) grid[y+1][x+10].state=Cell.State.CONDUCTOR;
           if(y+2<height&&x+1<width) grid[y+2][x+1].state=Cell.State.CONDUCTOR;
           if(y+2<height&&x+2<width) grid[y+2][x+2].state=Cell.State.CONDUCTOR;
           if(y+2<height&&x+3<width) grid[y+2][x+3].state=Cell.State.CONDUCTOR;
           if(y+2<height&&x+4<width) grid[y+2][x+4].state=Cell.State.CONDUCTOR;
           if(y+2<height&&x+5<width) grid[y+2][x+5].state=Cell.State.CONDUCTOR;
           if(y+2<height&&x+6<width) grid[y+2][x+6].state=Cell.State.CONDUCTOR;
           if(y+2<height&&x+11<width) grid[y+2][x+11].state=Cell.State.CONDUCTOR;
           if(y+3<height&&x+10<width) grid[y+3][x+10].state=Cell.State.CONDUCTOR;
           if(y+3<height&&x+11<width) grid[y+3][x+11].state=Cell.State.CONDUCTOR;
           if(y+3<height&&x+12<width) grid[y+3][x+12].state=Cell.State.CONDUCTOR;
           if(y+3<height&&x+13<width) grid[y+3][x+13].state=Cell.State.CONDUCTOR;
           if(y+4<height&&x+10<width) grid[y+4][x+10].state=Cell.State.CONDUCTOR;
           if(y+4<height&&x+13<width) grid[y+4][x+13].state=Cell.State.CONDUCTOR;
           if(y+4<height&&x+14<width) grid[y+4][x+14].state=Cell.State.CONDUCTOR;
           if(y+4<height&&x+15<width) grid[y+4][x+15].state=Cell.State.CONDUCTOR;
           if(y+4<height&&x+16<width) grid[y+4][x+16].state=Cell.State.CONDUCTOR;
           if(y+5<height&&x+10<width) grid[y+5][x+10].state=Cell.State.CONDUCTOR;
           if(y+5<height&&x+11<width) grid[y+5][x+11].state=Cell.State.CONDUCTOR;
           if(y+5<height&&x+12<width) grid[y+5][x+12].state=Cell.State.CONDUCTOR;
           if(y+5<height&&x+13<width) grid[y+5][x+13].state=Cell.State.CONDUCTOR;
           if(y+6<height&&x+1<width) grid[y+6][x+1].state=Cell.State.CONDUCTOR;
           if(y+6<height&&x+2<width) grid[y+6][x+2].state=Cell.State.CONDUCTOR;
           if(y+6<height&&x+3<width) grid[y+6][x+3].state=Cell.State.CONDUCTOR;
           if(y+6<height&&x+4<width) grid[y+6][x+4].state=Cell.State.CONDUCTOR;
           if(y+6<height&&x+5<width) grid[y+6][x+5].state=Cell.State.CONDUCTOR;
           if(y+6<height&&x+6<width) grid[y+6][x+6].state=Cell.State.CONDUCTOR;
           if(y+6<height&&x+11<width) grid[y+6][x+11].state=Cell.State.CONDUCTOR;
           if(y+7<height&&x+0<width) grid[y+7][x+0].state=Cell.State.CONDUCTOR;
           if(y+7<height&&x+7<width) grid[y+7][x+7].state=Cell.State.CONDUCTOR;
           if(y+7<height&&x+8<width) grid[y+7][x+8].state=Cell.State.CONDUCTOR;
           if(y+7<height&&x+9<width) grid[y+7][x+9].state=Cell.State.CONDUCTOR;
           if(y+7<height&&x+10<width) grid[y+7][x+10].state=Cell.State.CONDUCTOR;
           if(y+8<height&&x+1<width) grid[y+8][x+1].state=Cell.State.CONDUCTOR;
           if(y+8<height&&x+2<width) grid[y+8][x+2].state=Cell.State.CONDUCTOR;
           if(y+8<height&&x+3<width) grid[y+8][x+3].state=Cell.State.CONDUCTOR;
           if(y+8<height&&x+4<width) grid[y+8][x+4].state=Cell.State.CONDUCTOR;
           if(y+8<height&&x+5<width) grid[y+8][x+5].state=Cell.State.CONDUCTOR;
           if(y+8<height&&x+6<width) grid[y+8][x+6].state=Cell.State.CONDUCTOR;
        } 
    }

