package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import core.CellularAutomaton;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Menu extends JFrame implements ActionListener , MouseListener, KeyListener {
    //Przyciski umożliwiające wybór pliku z danymi oraz zakończenie działanie Menu
    JButton btn, browse1;
    JButton btn_gol,btn_ww;
    //Pola tekstowe umożliwiające wprowadzenie szerokości (w1), wysokości (h1) planszy, czasu generacji(t1), nazwy pliku wynikowego(f1)
    JTextField w1, h1, t1, f1;
    //Zmienne przechowujące wprowadzone ustawienia
    public static int width = 40, height = 40, time;
    public static String filename="";
    public static int cellularAutomaton=0;
    
    //Rozwijane Menu
    private JComboBox<String> ch, ct, cd, ce, cdc, clc;
    //Kolory stanów Wireworld
    public static String head_color="BLACK", tail_color="BLACK", conductor_color="BLACK", empty_color="BLACK";
    //Zmienne pomocnicze przy wyborze kolory stanu
    int index_h = 0, index_t = 0, index_d = 0, index_e = 0;
    int index_live, index_dead;
    //Kolory stanów Game of Life
    public static String live_color="BLACK", dead_color="BLACK";
    //Ścieżka do pliku z danymi
    public static String filepath;
            JLabel w, h, t, eh, et, d, ee, f, edc, elc, browse;


    public Menu() {
        super("Menu");
        setSize(500, 650);
        setLocationRelativeTo(null);

        setResizable(false);
        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Etykiety zawierające nazwy wprowadzanych wartości
        
        //Dodanie przycisku umożliwiającego wybór Game of life
        btn_gol = new JButton(new ImageIcon(getClass().getResource("/image/gol_name.png")));
        btn_gol.setBounds(150, 100, 165, 143);
        add(btn_gol);
        btn_gol.addActionListener(this);
        btn_gol.addMouseListener(this);
        
        //Dodanie przycisku umożliwiającego wybór Wireworld
        btn_ww = new JButton(new ImageIcon(getClass().getResource("/image/ww_name.png")));
        btn_ww.setBounds(80, 350, 300, 120);
        add(btn_ww);
        btn_ww.addActionListener(this);
        btn_ww.addMouseListener(this);
        
        /**********************************/
        //Szerokość
        w = new JLabel("Width");
        w.setBounds(20, 20, -300, -300);
        add(w);
        
        w1 = new JTextField("40");
        w1.setBounds(150, 20, -300, -300);
        w1.addKeyListener(this);
        add(w1);
        
     
        /**********************************/
        //Wysokość
        h = new JLabel("Height");
        h.setBounds(20, 80, -300, -300);
        add(h);

        h1 = new JTextField("40");
        h1.setBounds(150, 80, -300, -300);
        h1.addKeyListener(this);
        add(h1);
      
        /**********************************/
        //Czas generacji
        t = new JLabel("Generation time [ms]");
        t.setBounds(20, 140, -300, -300);
        add(t);

        t1 = new JTextField("100");
        t1.setBounds(150, 140, -300, -300);
        t1.addKeyListener(this);
        add(t1);
        
        /**********************************/
        //Nazwa pliku wynikowego
        f = new JLabel("Output file name");
        f.setBounds(20, 200, -300, -300);
        add(f);

        f1 = new JTextField("name");
        f1.setBounds(150, 200, -300, -300);
        add(f1);
        
        //Możliwe kolory do wyboru
        String[] choices = {"BLACK", "RED", "BLUE", "GREEN", "YELLOW", "WHITE"};
       
     //Sprawdzenie dla jakiego automatu wybierany kolory i wyświetlenie odpowiedznych stanów
        //Wybór kolorów dla Wireworld
     
            /**********************************/
            //Kolor pustej komórki
            ee = new JLabel("Empty Color");
            ee.setBounds(20, 260, -300, -300);
            add(ee);

            ce = new JComboBox<String>(choices);
            ce.setBounds(150, 260, -300, -300);
            ce.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                index_e = ce.getSelectedIndex();
                            }
                        }
                    }
            );
            add(ce);
            /**********************************/
            //Kolor głowy elektronu
            eh = new JLabel("ElectronHead Color");
            eh.setBounds(20, 320, -300, -300);
            add(eh);

            ch = new JComboBox<String>(choices);
            ch.setBounds(150, 320, -300, -300);
            ch.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                index_h = ch.getSelectedIndex();
                            }
                        }
                    }
            );
            add(ch);
            /**********************************/
            //Kolor ogonu elektronu
            et = new JLabel("ElectronTail Color");
            et.setBounds(20, 380, -300, -300);
            add(et);

            ct = new JComboBox<String>(choices);
            ct.setBounds(150, 380, -300, -300);
            ct.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                index_t = ct.getSelectedIndex();
                            }
                        }
                    }
            );
            add(ct);
            /**********************************/
            //Kolor przewodnika
            d = new JLabel("Conductor Color");
            d.setBounds(20, 440, -300, -300);
            add(d);

            cd = new JComboBox<String>(choices);
            cd.setBounds(150, 440, -300, -300);
            cd.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                index_d = cd.getSelectedIndex();
                            }
                        }
                    }
            );
            add(cd);
            //Wybór kolorów dla Gry w życie
        
            /**********************************/
            //Kolor żywej komórki
            elc = new JLabel("Live color");
            elc.setBounds(20, 260, -300, -300);
            add(elc);

            clc = new JComboBox<String>(choices);
            clc.setBounds(150, 260, -300, -300);
            clc.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                index_live = clc.getSelectedIndex();
                            }
                        }
                    }
            );
            add(clc);
            /**********************************/
            //Kolor martwej komórki
            edc = new JLabel("Dead color");
            edc.setBounds(20, 320, -300, -300);
            add(edc);

            cdc = new JComboBox<String>(choices);
            cdc.setBounds(150, 320, -300, -300);
            cdc.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                index_dead = cdc.getSelectedIndex();
                            }
                        }
                    }
            );
            add(cdc);
        
        /**********************************/
        //Wybór pliku z danymi
        browse = new JLabel("File");
    
        add(browse);

        browse1 = new JButton("Browse");
        add(browse1);
        browse1.addActionListener(this);
        
        /**********************************/
        //Zakończenie Menu
        btn = new JButton("OK");
        if (cellularAutomaton == 0) {
            btn.setBounds(85, 560, -300, -300);
        } else if (cellularAutomaton == 1) {
            btn.setBounds(85, 440, -300, -300);
        }
        add(btn);
        btn.addActionListener(this);

        setVisible(true);
    }

    public static void main(String[] args) {
      new Menu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //zmienna wskazująca czy podane wartości są poprawne (ok=0 są poprane, ok =1 są złe)
        int ok = 0;
        
        Object source = e.getSource();
        
        
        if(source ==w1){
            
            System.out.println("ta");
             
        }
        
        
        
        if (source == btn_gol) {
            
            cellularAutomaton=1;
                    
            btn_gol.setLocation(-300, -300);
            btn_ww.setLocation(-300, -300);
            
            uploadGOLMenu();
            
        }
        
        if (source == btn_ww) {
            
            cellularAutomaton=0;
            
            btn_gol.setLocation(-300, -300);
            btn_ww.setLocation(-300, -300);
            
            uploadWireworldMenu();
        }
        
        //Instrukcje wykonywane, gdy został wciśnięty przycisk "OK", zatwierdzający wprowadzone dane
        if (source == btn) {
            //Sprawdznie czy w polu jest podana poprawna wartość
            //Jeśli wartość jest zła to wyświetla się komunikat i zmienna ok=1
            try {
                width = Integer.parseInt(w1.getText());
                
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(null, "In field Width is wrong value!!");
                ok = 1;
            }
            //Sprawdznie czy w polu jest podana poprawna wartość
            //Jeśli wartość jest zła to wyświetla się komunikat i zmienna ok=1
            try {
                height = Integer.parseInt(h1.getText());
               
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(null, "In field Height is wrong value!!");
                ok = 1;
            }
            //Sprawdznie czy w polu jest podana poprawna wartość
            //Jeśli wartość jest zła to wyświetla się komunikat i zmienna ok=1
            try {
                time = Integer.parseInt(t1.getText());
            
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(null, "In field Generation time is wrong value!!");
                ok = 1;
            }
            //Sprawdznie czy został wybrany plik za danymi. Jeśli nie to wyświetli się komunikat
            if (FileBrowser.path == null) {
                JOptionPane.showMessageDialog(null, "You did not choose text file!!");
            }
            //Jeśli wybrane zostało Wireworld to przypisanie wartości poszczególnych kolorów
            if (cellularAutomaton == 0) {
                empty_color = ce.getItemAt(index_e);
                head_color = ch.getItemAt(index_h);
                tail_color = ct.getItemAt(index_t);
                conductor_color = cd.getItemAt(index_d);
                
            }
            //Jeśli wybrane zostało Game of Life to przypisanie wartości poszczególnych kolorów
            if (cellularAutomaton == 1) {
                live_color = clc.getItemAt(index_live);
                dead_color = cdc.getItemAt(index_dead);
            }
            //Przypisanie wartości nazwy pliku wynikowego
            filename = f1.getText();
            //Przypisanie wartości ścieżki do pliku
            filepath=FileBrowser.path;
            //Sprawdzenie czy nazwa pliku wynikowego jest dłuższa niż 1 i czy nie jest znakiem spacji
            if (filename.length() < 1 || filename.charAt(0) == ' ') {
                JOptionPane.showMessageDialog(null, "In field File name is wrong value!!");
                ok = 1;
            }
            //Utworzenie folderu do którego zapisywane będą pliki wynikowe
            new File("Results").mkdir();
            

            //Jeśli podane wartości są poprawne, to program czyta dane z pliku tekstowego i uruchamia odpowiednią planszę
            if (ok == 0) {
                dispose();

                new CellularAutomaton();
            }
        
        } 
        //Gdy został wciśnięty przycisk "Browse" to uruchamia się klasa do przeglądania plików
        else if (source == browse1) {
            new FileBrowser();
        }

    }
    
     @Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
            if(e.getSource()==btn_gol) btn_gol.setIcon(new ImageIcon(getClass().getResource("/image/gol_animation.gif")));
            else if(e.getSource()==btn_ww) btn_ww.setIcon(new ImageIcon(getClass().getResource("/image/ww_animation.gif")));
	}

	@Override
	public void mouseExited(MouseEvent e) {
            if(e.getSource()==btn_gol) btn_gol.setIcon(new ImageIcon(getClass().getResource("/image/gol_name.png")));
            else if(e.getSource()==btn_ww) btn_ww.setIcon(new ImageIcon(getClass().getResource("/image/ww_name.png")));
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
        
         @Override
    public void keyTyped(KeyEvent e) {
        
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
         
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()==w1){

            try {
                width = Integer.parseInt(w1.getText());
                
                w1.setBackground(Color.white);
                if(width<=0) w1.setBackground(Color.red);
            } catch (NumberFormatException w) {
                w1.setBackground(Color.red);
            }
            
        }
        if(e.getSource()==h1){

            try {
                height = Integer.parseInt(h1.getText());
                h1.setBackground(Color.white);
                if(height<=0) h1.setBackground(Color.red);
            } catch (NumberFormatException w) {
                h1.setBackground(Color.red);
            }
            
        }
        if(e.getSource()==t1){

            try {
                time = Integer.parseInt(t1.getText());
                t1.setBackground(Color.white);
                if(time<=0) t1.setBackground(Color.red);
            } catch (NumberFormatException w) {
                t1.setBackground(Color.red);
            }
            
        }
        
        
        
        
        
    }
        
        
        
        void uploadGOLMenu(){
            
        w.setBounds(20+110, 20, 50, 25);
        w1.setBounds(150+110, 20, 50, 25);
        h.setBounds(20+110, 80, 50, 25);
        h1.setBounds(150+110, 80, 50, 25);
        t.setBounds(20+110, 140, 130, 25);
        t1.setBounds(150+110, 140, 50, 25);
        f.setBounds(20+110, 200, 130, 25);
        f1.setBounds(150+110, 200, 50, 25);

        elc.setBounds(20+110, 260, 130, 25);
        clc.setBounds(150+110, 260, 80, 20);
        edc.setBounds(20+110, 320, 130, 25);
        cdc.setBounds(150+110, 320, 80, 20);

        browse.setBounds(20+110, 380, 130, 25);
        browse1.setBounds(150+110, 380, 100, 30);
        btn.setBounds(85+110, 440, 55, 30);

            
        }

        
        void uploadWireworldMenu(){
            
        w.setBounds(20+110, 20, 50, 25);
        w1.setBounds(150+110, 20, 50, 25);
        h.setBounds(20+110, 80, 50, 25);
        h1.setBounds(150+110, 80, 50, 25);
        t.setBounds(20+110, 140, 130, 25);
        t1.setBounds(150+110, 140, 50, 25);
        f.setBounds(20+110, 200, 130, 25);
        f1.setBounds(150+110, 200, 50, 25);
        
        ee.setBounds(20+110, 260, 130, 25);
        ce.setBounds(150+110, 260, 80, 20);
        eh.setBounds(20+110, 320, 130, 25);
        ch.setBounds(150+110, 320, 80, 20);
        et.setBounds(20+110, 380, 130, 25);
        ct.setBounds(150+110, 380, 80, 20);
        d.setBounds(20+110, 440, 130, 25);
        cd.setBounds(150+110, 440, 80, 20);
            
        browse.setBounds(20+110, 500, 130, 25);
        browse1.setBounds(150+110, 500, 100, 30);
        btn.setBounds(85+110, 560, 55, 30);

        }
}
