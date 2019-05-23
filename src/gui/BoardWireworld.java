/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import core.Cell;
import core.GameEngine;
import core.ResourceLoader;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


  public class BoardWireworld extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

      
        private final int width=Menu.width;
        private final int height=Menu.height;
        private int board_width,board_location;
        private Cell[][]grid=new Cell[height][width];
        
        
        public BoardWireworld(){
         addMouseListener(this);
         addMouseMotionListener(this);
         for(int i=0;i<height;i++)
             for(int j=0;j<width;j++) grid[i][j]=new Cell();
         
            if(width<50) board_width=50;
            else board_width=width;
            if(width>=50) setSize(board_width * 10 + 6, height * 10 + 100);
            else setSize(506, height * 10 + 100);
            board_location=(board_width-width)*5;
        }
        
        //Zmienne pomocnicze
       public int start = 0, rp = 0,edit=0,next=0;
        //Tablica która będzie obrazować stany komórek
        JLabel[][] obrazek = new JLabel[height][width];
        //Tablica stanów komórek oraz sprawdzenie czy dana komóra jest do zmiany
       
        
        //Sprawdznie czy dana generacja jest pierwszą czy nie
        public int ile = 0;
        //Wczytanie obrazka z odpowiednim kolorem do danego stanu komórki
        ImageIcon empty = new ImageIcon("src/image/" + Menu.empty_color + ".png");
        ImageIcon head = new ImageIcon("src/image/" + Menu.head_color + ".png");
        ImageIcon tail = new ImageIcon("src/image/" + Menu.tail_color + ".png");
        ImageIcon conductor = new ImageIcon("src/image/" + Menu.conductor_color + ".png");

        public int number_shape=1;
      
     
//Obrazki układów
         ImageIcon im_shape1 = new ImageIcon("src/image/DIODE1.png");
         ImageIcon im_shape1d = new ImageIcon("src/image/DIODE1d.png");
         ImageIcon im_shape2 = new ImageIcon("src/image/DIODE2.png");
         ImageIcon im_shape2d = new ImageIcon("src/image/DIODE2d.png");
         ImageIcon im_shape3 = new ImageIcon("src/image/DIODE3.png");
         ImageIcon im_shape3d = new ImageIcon("src/image/DIODE3d.png");
        //Przyciski sterujące
        JButton button_stop = new JButton("Stop");
        JButton button_start = new JButton("Start");
        JButton button_next = new JButton("Next");
        
        JButton shape= new JButton();
        JButton next_shape= new JButton(new ImageIcon("src/image/NEXT.png"));
        
        JLabel cell_state=new JLabel();
        JButton next_cell_state=new JButton(new ImageIcon("src/image/NEXT.png"));
        private int cell_s;
        JLabel s_name=new JLabel();
        JLabel fly_shape=new JLabel();
        private int shape_x=-55,shape_y=-55;
        private int drawing=0; 
        JLabel n=new JLabel();
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            remove(n);
            n.setBounds(board_width*5+150, height * 10 + 15, 35, 20);
            n.setText(ResourceLoader.generationNumber+"");
            add(n);
            
            
            remove(fly_shape);
            if(drawing==1) {fly_shape.setIcon(im_shape1d); fly_shape.setBounds(shape_x-5, shape_y-5, 120, 30);}
            if(drawing==2) {fly_shape.setIcon(im_shape2d); fly_shape.setBounds(shape_x-5, shape_y-5, 120, 30);}
            if(drawing==3) {fly_shape.setIcon(im_shape3d); fly_shape.setBounds(shape_x-5, shape_y-5, 170, 90);}
           fly_shape.setLocation(shape_x-5, shape_y-5);
           
            add(fly_shape);
            //Dodanie przycisków
            button_stop.setBounds(board_width*5-30, height * 10 + 15, 60, 40);
            add(button_stop);
            button_stop.removeActionListener(this);
            button_stop.addActionListener(this);
            
            button_start.setBounds(board_width*5-130, height * 10 + 15, 80, 40);
            add(button_start);
            button_start.removeActionListener(this);
            button_start.addActionListener(this);

            button_next.setBounds(board_width*5+50, height * 10 + 15, 80, 40);
            add(button_next);
            button_next.removeActionListener(this);
            button_next.addActionListener(this);
          
            
            cell_state.setBounds(75, height*10+25, 20, 20);
            next_cell_state.setBounds(70, height*10+50, 30, 15);
            s_name.setBounds(60, height*10+5, 70, 15);
            shape.setBounds(5, height * 10 + 15, 50, 30);
            next_shape.setBounds(10, height * 10 + 50, 30, 15);
            
            
            if(edit==1){
                add(shape);
                shape.removeActionListener(this);
                shape.addActionListener(this);
                add(s_name);
                if(number_shape==1){shape.setIcon(im_shape1);}
                else if(number_shape==2){shape.setIcon(im_shape2);}
                else if(number_shape==3){shape.setIcon(im_shape3);}
                add(next_shape);
                next_shape.removeActionListener(this);
                next_shape.addActionListener(this);
                
                //**************************************************//
                add(cell_state);
             
                
                if(cell_s==0){cell_state.setIcon(empty);s_name.setText("empty");}
                else if(cell_s==1){cell_state.setIcon(head);s_name.setText("head");}
                else if(cell_s==2){cell_state.setIcon(tail);s_name.setText("tail");}
                else if(cell_s==3){cell_state.setIcon(conductor);s_name.setText("conductor");}

                add(next_cell_state);
                next_cell_state.removeActionListener(this);
                next_cell_state.addActionListener(this);
                
                
            }
            else {remove(shape);remove(next_shape);remove(cell_state);remove(next_cell_state);remove(s_name);}
            
            
            
         
            //Jeśli nie jest to piersza generacja to usuwamy wczytane wcześniej obrazki oraz zmieniamy stany komórek
            if (ile >= 1) {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        remove(obrazek[i][j]);
                      
                    }
                }
            }
            
            //Wczytujemy odpowiednie obrazki na podstawie stanu danej komórki
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (grid[i][j].state == 1) {
                        
                            obrazek[i][j] = new JLabel(head);
                            
                    }
                    if (grid[i][j].state == 2) {
                    
                            obrazek[i][j] = new JLabel(tail);
                     } 
                    if (grid[i][j].state == 3) {
                 
                            obrazek[i][j] = new JLabel(conductor);
                    } 
                    if (grid[i][j].state == 0) {
                        obrazek[i][j] = new JLabel(empty);
                    }
                    obrazek[i][j].setBounds(j * 10+board_location, i * 10, 10, 10);
                    add(obrazek[i][j]);
                }
            }
            if(ile==0) {start=1;edit=1;}
           ile++;
        }

        public void actionPerformed(ActionEvent e) {
            //Rozpoczęcie generacji
         
              if (e.getSource() == button_start) {
                start = 0;
                rp = 0;
                edit=0;
            }
            //Zatrzymanie generacji
            if (e.getSource() == button_stop) {
                start = 1;
                next=0;
                edit=1;
                repaint();
                rp=1;
                
            }
            //Wczytanie następnej generacji
            if (e.getSource() == button_next) {
                start = 0;
                repaint();
                start=1;
                next=1;
                rp = 1;
                edit=0;
            }
            if(e.getSource() == shape)
            {
              
               drawing=number_shape;
               repaint();
            }
            if(e.getSource() == next_shape)
            {
               number_shape++;
               if(number_shape==4) number_shape=1;
               repaint();
            }
            
            if(e.getSource() == next_cell_state)
            {
               cell_s++;
               if(cell_s==4) cell_s=0;
               repaint();
            }
            
        }

       @Override
	public void mouseClicked(MouseEvent e) {
          
            //Wczytanie współrzędnych w które kliknęło się myszko, a następnie zmiana stanu komórki na przeciwny
            if(drawing==0&&start==1&&e.getY()<height*10&&e.getX()<width*10+board_location&&e.getX()>board_location){
              grid[e.getY()/10][(e.getX()-board_location)/10].state=cell_s;
                repaint();
            }
           
            if(drawing>0&&e.getY()<height*10&&e.getX()<width*10+board_location&&e.getX()>board_location){
               if(drawing==1) drawDiode1((e.getX()-board_location)/10,e.getY()/10);
               if(drawing==2) drawDiode2((e.getX()-board_location)/10,e.getY()/10);
               if(drawing==3) drawDiode3((e.getX()-board_location)/10,e.getY()/10);
                drawing=0;
                shape_x=-155;
                shape_y=-155;
                repaint();
            }
	}
       

	@Override
	public void mouseMoved(MouseEvent e) {
            if(drawing>0){
		shape_x=e.getX();
                shape_y=e.getY();
                repaint();
            }
            else {
            shape_x=-155;
            shape_y=-155;
            
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
                for(int j=0;j<width;j++) GameEngine.g.grid[i][j].state=grid[i][j].state;
        }
         
        public void updateBoard(){
            
            for(int i=0;i<height;i++)
                for(int j=0;j<width;j++) grid[i][j].state=GameEngine.g.grid[i][j].state;
            
            repaint();
    
        }
        
        private void drawDiode1(int x,int y){
           if(y+1<height&&x+0<width) grid[y+1][x+0].state=3;
           if(y+1<height&&x+1<width) grid[y+1][x+1].state=3;
           if(y+1<height&&x+2<width) grid[y+1][x+2].state=3;
           if(y+1<height&&x+3<width) grid[y+1][x+3].state=3;
           if(y+1<height&&x+4<width) grid[y+1][x+4].state=3;
           if(y+1<height&&x+5<width) grid[y+1][x+5].state=3;
           if(y+1<height&&x+7<width) grid[y+1][x+7].state=3;
           if(y+1<height&&x+8<width) grid[y+1][x+8].state=3;
           if(y+1<height&&x+9<width) grid[y+1][x+9].state=3;
           if(y+1<height&&x+10<width) grid[y+1][x+10].state=3;
           if(y+1<height&&x+11<width) grid[y+1][x+11].state=3;
           if(y+0<height&&x+5<width) grid[y+0][x+5].state=3;
           if(y+0<height&&x+6<width) grid[y+0][x+6].state=3;
           if(y+2<height&&x+5<width) grid[y+2][x+5].state=3;
           if(y+2<height&&x+6<width) grid[y+2][x+6].state=3;
           
            
          
        }
        private void drawDiode2(int x,int y){
           if(y+1<height&&x+0<width) grid[y+1][x+0].state=3;
           if(y+1<height&&x+1<width) grid[y+1][x+1].state=3;
           if(y+1<height&&x+2<width) grid[y+1][x+2].state=3;
           if(y+1<height&&x+3<width) grid[y+1][x+3].state=3;
           if(y+1<height&&x+4<width) grid[y+1][x+4].state=3;
           if(y+1<height&&x+6<width) grid[y+1][x+6].state=3;
           if(y+1<height&&x+7<width) grid[y+1][x+7].state=3;
           if(y+1<height&&x+8<width) grid[y+1][x+8].state=3;
           if(y+1<height&&x+9<width) grid[y+1][x+9].state=3;
           if(y+1<height&&x+10<width) grid[y+1][x+10].state=3;
           if(y+1<height&&x+11<width) grid[y+1][x+11].state=3;
           if(y+0<height&&x+5<width) grid[y+0][x+5].state=3;
           if(y+0<height&&x+6<width) grid[y+0][x+6].state=3;
           if(y+2<height&&x+5<width) grid[y+2][x+5].state=3;
           if(y+2<height&&x+6<width) grid[y+2][x+6].state=3;
        }
        
        private void drawDiode3(int x,int y){
           if(y+0<height&&x+1<width) grid[y+0][x+1].state=3;
           if(y+0<height&&x+2<width) grid[y+0][x+2].state=3;
           if(y+0<height&&x+3<width) grid[y+0][x+3].state=3;
           if(y+0<height&&x+4<width) grid[y+0][x+4].state=3;
           if(y+0<height&&x+5<width) grid[y+0][x+5].state=3;
           if(y+0<height&&x+6<width) grid[y+0][x+6].state=3;
           if(y+1<height&&x+0<width) grid[y+1][x+0].state=3;
           if(y+1<height&&x+7<width) grid[y+1][x+7].state=3;
           if(y+1<height&&x+8<width) grid[y+1][x+8].state=3;
           if(y+1<height&&x+9<width) grid[y+1][x+9].state=3;
           if(y+1<height&&x+10<width) grid[y+1][x+10].state=3;
           if(y+2<height&&x+1<width) grid[y+2][x+1].state=3;
           if(y+2<height&&x+2<width) grid[y+2][x+2].state=3;
           if(y+2<height&&x+3<width) grid[y+2][x+3].state=3;
           if(y+2<height&&x+4<width) grid[y+2][x+4].state=3;
           if(y+2<height&&x+5<width) grid[y+2][x+5].state=3;
           if(y+2<height&&x+6<width) grid[y+2][x+6].state=3;
           if(y+2<height&&x+11<width) grid[y+2][x+11].state=3;
           if(y+3<height&&x+10<width) grid[y+3][x+10].state=3;
           if(y+3<height&&x+11<width) grid[y+3][x+11].state=3;
           if(y+3<height&&x+12<width) grid[y+3][x+12].state=3;
           if(y+3<height&&x+13<width) grid[y+3][x+13].state=3;
           if(y+4<height&&x+10<width) grid[y+4][x+10].state=3;
           if(y+4<height&&x+13<width) grid[y+4][x+13].state=3;
           if(y+4<height&&x+14<width) grid[y+4][x+14].state=3;
           if(y+4<height&&x+15<width) grid[y+4][x+15].state=3;
           if(y+4<height&&x+16<width) grid[y+4][x+16].state=3;
           if(y+5<height&&x+10<width) grid[y+5][x+10].state=3;
           if(y+5<height&&x+11<width) grid[y+5][x+11].state=3;
           if(y+5<height&&x+12<width) grid[y+5][x+12].state=3;
           if(y+5<height&&x+13<width) grid[y+5][x+13].state=3;
           if(y+6<height&&x+1<width) grid[y+6][x+1].state=3;
           if(y+6<height&&x+2<width) grid[y+6][x+2].state=3;
           if(y+6<height&&x+3<width) grid[y+6][x+3].state=3;
           if(y+6<height&&x+4<width) grid[y+6][x+4].state=3;
           if(y+6<height&&x+5<width) grid[y+6][x+5].state=3;
           if(y+6<height&&x+6<width) grid[y+6][x+6].state=3;
           if(y+6<height&&x+11<width) grid[y+6][x+11].state=3;
           if(y+7<height&&x+0<width) grid[y+7][x+0].state=3;
           if(y+7<height&&x+7<width) grid[y+7][x+7].state=3;
           if(y+7<height&&x+8<width) grid[y+7][x+8].state=3;
           if(y+7<height&&x+9<width) grid[y+7][x+9].state=3;
           if(y+7<height&&x+10<width) grid[y+7][x+10].state=3;
           if(y+8<height&&x+1<width) grid[y+8][x+1].state=3;
           if(y+8<height&&x+2<width) grid[y+8][x+2].state=3;
           if(y+8<height&&x+3<width) grid[y+8][x+3].state=3;
           if(y+8<height&&x+4<width) grid[y+8][x+4].state=3;
           if(y+8<height&&x+5<width) grid[y+8][x+5].state=3;
           if(y+8<height&&x+6<width) grid[y+8][x+6].state=3;
        }
        
       
        
    }

