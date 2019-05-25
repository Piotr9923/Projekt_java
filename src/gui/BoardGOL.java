
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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


 public class BoardGOL extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

        private final int width=Menu.width;
        private final int height=Menu.height;
        private int board_width,board_location;
        private Cell[][]grid=new Cell[height][width];
        
        public BoardGOL(){
            for(int i=0;i<height;i++)
             for(int j=0;j<width;j++) grid[i][j]=new Cell();
            
            addMouseListener(this);
            addMouseMotionListener(this);
         
            if(width<40) board_width=40;
            else board_width=width;
            if(width>=40) setSize(board_width * 10 + 6, height * 10 + 100);
            else setSize(406, height * 10 + 100);
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
        ImageIcon live = new ImageIcon(getClass().getResource("/image/" + Menu.live_color + ".png"));
        ImageIcon dead = new ImageIcon(getClass().getResource("/image/" + Menu.dead_color + ".png"));
        
        public int number_shape=1;
      
 
//Obrazki układów
         ImageIcon im_shape1 = new ImageIcon(getClass().getResource("/image/SHAPE1.png"));
         ImageIcon im_shape1d = new ImageIcon(getClass().getResource("/image/SHAPE1d.png"));
         ImageIcon im_shape2 = new ImageIcon(getClass().getResource("/image/SHAPE2.png"));
         ImageIcon im_shape2d = new ImageIcon(getClass().getResource("/image/SHAPE2d.png"));
         ImageIcon im_shape3 = new ImageIcon(getClass().getResource("/image/SHAPE3.png"));
         ImageIcon im_shape3d = new ImageIcon(getClass().getResource("/image/SHAPE3d.png"));
        //Przyciski sterujące
        JButton button_stop = new JButton("Stop");
        JButton button_start = new JButton("Start");
        JButton button_next = new JButton("Next");
        
        JButton shape= new JButton();
        JButton next_shape= new JButton(new ImageIcon(getClass().getResource("/image/NEXT.png")));
        
        JLabel fly_shape=new JLabel();
        private int shape_x=-55,shape_y=-55;
        private int drawing=0; 
        
        JLabel n=new JLabel();
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            remove(n);
            n.setBounds(board_width*5+150, height * 10 + 15, 35, 20);
            n.setText(GameEngine.generationNumber+"");
            add(n);
            
            remove(fly_shape);
            if(drawing==1) {fly_shape.setIcon(im_shape1d); fly_shape.setBounds(shape_x-5, shape_y-5, 30, 30);}
            if(drawing==2) {fly_shape.setIcon(im_shape2d); fly_shape.setBounds(shape_x-5, shape_y-5, 50, 40);}
            if(drawing==3) {fly_shape.setIcon(im_shape3d); fly_shape.setBounds(shape_x-5, shape_y-5, 90, 90);}
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
          
            
            shape.setBounds(10, height * 10 + 15, 30, 30);
            next_shape.setBounds(10, height * 10 + 50, 30, 15);
            if(edit==1){
                add(shape);
                shape.removeActionListener(this);
                shape.addActionListener(this);
                
                if(number_shape==1){shape.setIcon(im_shape1);}
                else if(number_shape==2){shape.setIcon(im_shape2);}
                else if(number_shape==3){shape.setIcon(im_shape3);}
                add(next_shape);
                next_shape.removeActionListener(this);
                next_shape.addActionListener(this);
            }
            else {remove(shape);remove(next_shape);}
            
            
            
   
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
                       
                            obrazek[i][j] = new JLabel(live);
                       
                    } 
                    if (grid[i][j].state == 0) {
                        obrazek[i][j] = new JLabel(dead);
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
            
           
        }

        public void loadGrid(){
            
            for(int i=0;i<height;i++)
                for(int j=0;j<width;j++) CellularAutomaton.g.grid[i][j].state=grid[i][j].state;
        }
         
        public void updateBoard(){
            
            for(int i=0;i<height;i++)
                for(int j=0;j<width;j++) grid[i][j].state=CellularAutomaton.g.grid[i][j].state;
            
            repaint();
    
        }
        
       @Override
	public void mouseClicked(MouseEvent e) {
          
            //Wczytanie współrzędnych w które kliknęło się myszko, a następnie zmiana stanu komórki na przeciwny
            if(drawing==0&&start==1&&e.getY()<height*10&&e.getX()<width*10+board_location&&e.getX()>board_location){
                if(grid[e.getY()/10][(e.getX()-board_location)/10].state==1) grid[e.getY()/10][(e.getX()-board_location)/10].state=0;
                else if(grid[e.getY()/10][(e.getX()-board_location)/10].state==0) grid[e.getY()/10][(e.getX()-board_location)/10].state=1;
                repaint();
            }
           
            if(drawing>0&&e.getY()<height*10&&e.getX()<width*10+board_location&&e.getX()>board_location){
               if(drawing==1) drawGlider((e.getX()-board_location)/10,e.getY()/10);
               if(drawing==2) drawLwss((e.getX()-board_location)/10,e.getY()/10);
               if(drawing==3) drawLoafer((e.getX()-board_location)/10,e.getY()/10);
                drawing=0;
                shape_x=-55;
                shape_y=-55;
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
            shape_x=-55;
            shape_y=-55;
            
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
                if(y+0<height&&x+1<width) grid[0+y][1+x].state=1;
                if(y+1<height&&x+2<width) grid[1+y][2+x].state=1;
                if(y+2<height&&x+0<width) grid[2+y][0+x].state=1;
                if(y+2<height&&x+1<width) grid[2+y][1+x].state=1;
                if(y+2<height&&x+2<width) grid[2+y][2+x].state=1;
        }
        private void drawLwss(int x,int y){
                if(y+0<height&&x+0<width) grid[0+y][0+x].state=1;
                if(y+0<height&&x+3<width) grid[0+y][3+x].state=1;
                if(y+1<height&&x+4<width) grid[1+y][4+x].state=1;
                if(y+2<height&&x+4<width) grid[2+y][4+x].state=1;
                if(y+2<height&&x+0<width) grid[2+y][0+x].state=1;
                if(y+3<height&&x+1<width) grid[3+y][1+x].state=1;
                if(y+3<height&&x+2<width) grid[3+y][2+x].state=1;
                if(y+3<height&&x+3<width) grid[3+y][3+x].state=1;
                if(y+3<height&&x+4<width) grid[3+y][4+x].state=1;
        }
        
        private void drawLoafer(int x,int y){
                if(y+0<height&&x+0<width) grid[0+y][0+x].state=1;
                if(y+0<height&&x+1<width) grid[0+y][1+x].state=1;
                if(y+0<height&&x+6<width) grid[0+y][6+x].state=1;
                if(y+0<height&&x+7<width) grid[0+y][7+x].state=1;
                if(y+1<height&&x+0<width) grid[1+y][0+x].state=1;
                if(y+1<height&&x+1<width) grid[1+y][1+x].state=1;
                if(y+1<height&&x+2<width) grid[1+y][2+x].state=1;
                if(y+1<height&&x+4<width) grid[1+y][4+x].state=1;
                if(y+1<height&&x+5<width) grid[1+y][5+x].state=1;
                if(y+1<height&&x+8<width) grid[1+y][8+x].state=1;
                if(y+2<height&&x+5<width) grid[2+y][5+x].state=1;
                if(y+2<height&&x+7<width) grid[2+y][7+x].state=1;
                if(y+3<height&&x+6<width) grid[3+y][6+x].state=1;
                if(y+4<height&&x+1<width) grid[4+y][0+x].state=1;
                if(y+5<height&&x+1<width) grid[5+y][0+x].state=1;
                if(y+5<height&&x+1<width) grid[5+y][1+x].state=1;
                if(y+5<height&&x+2<width) grid[5+y][2+x].state=1;
                if(y+6<height&&x+3<width) grid[6+y][3+x].state=1;
                if(y+7<height&&x+1<width) grid[7+y][1+x].state=1;
                if(y+7<height&&x+2<width) grid[7+y][2+x].state=1;
                if(y+8<height&&x+1<width) grid[8+y][1+x].state=1;
        }
        
      
    }


