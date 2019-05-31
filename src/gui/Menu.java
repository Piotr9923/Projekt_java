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
    
    public enum CA{GOL,WW};
    
//Przyciski umożliwiające wybór pliku z danymi oraz zakończenie działanie Menu
    JButton btnOk, btnBrowse;
    JButton btnGol,btnWW;
    
//Pola tekstowe umożliwiające wprowadzenie szerokości, wysokości planszy, czasu generacji, nazwy pliku wynikowego
    JTextField widthTextField, heightTextField, timeTextField, fileTextField;
    
//Zmienne przechowujące wprowadzone ustawienia
    private static int width = 40, height = 40, time;
    public static String filename="";
    public static CA cellularAutomaton=CA.WW;
    
//Rozwijane Menu
    private JComboBox<String> cHead, cTail, cConductor, cEmpty, cDead, cAlive,cShape;
    //Kolory stanów Wireworld
    public static String headColor="BLACK", tailColor="BLACK", conductorColor="BLACK", emptyColor="BLACK",shape="SQUARE";
    
//Zmienne pomocnicze przy wyborze koloru stanu komórki
    private int indexH, indexT, indexC, indexE,indexS;
    private int indexLive, indexDead;
    
//Kolory stanów Game of Life
    public static String liveColor="BLACK", deadColor="BLACK";
    
//Ścieżka do pliku z danymi
    public static String filepath;
    
//Etykiety zawierające nazwy wprowadzanych wartości
    JLabel widthJLabel, heightJLabel, timeJLabel, headJLabel, tailJLabel, conductorJLabel, emptyJLabel, fileJLabel, deadJLabel, aliveJLabel, browseJLabel,shapeJLabel;

//Grupa przycisków czy chcemy zapisać układ komórek do pliku teksowego
    private ButtonGroup yesNo;
    
//Zmienna wskazująca czy chcemy zapisać układ komórek
    private static boolean save=true;
    
JRadioButton yes,no;
    public Menu() {
        super("Menu");
        setSize(500, 650);
        setLocationRelativeTo(null);

        setResizable(false);
        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        yesNo=new ButtonGroup();
       
        yes=new JRadioButton("yes",true);
        yes.addActionListener(this);
        
        no=new JRadioButton("no",false);
        no.addActionListener(this);
        
        yesNo.add(yes);
        yesNo.add(no);
        
    //Dodanie przycisku umożliwiającego wybór Game of life
        btnGol = new JButton(new ImageIcon(getClass().getResource("/image/gol_name.png")));
        btnGol.setBounds(150, 100, 165, 143);
        add(btnGol);
        btnGol.addActionListener(this);
        btnGol.addMouseListener(this);
        
    //Dodanie przycisku umożliwiającego wybór Wireworld
        btnWW = new JButton(new ImageIcon(getClass().getResource("/image/ww_name.png")));
        btnWW.setBounds(80, 350, 300, 120);
        add(btnWW);
        btnWW.addActionListener(this);
        btnWW.addMouseListener(this);
        
    /**********************************/
     //Szerokość
        widthJLabel = new JLabel("Width");
        
        widthTextField = new JTextField("40");
        widthTextField.addKeyListener(this);
  
        
     
    /**********************************/
    //Wysokość
        heightJLabel = new JLabel("Height");
        heightJLabel.setBounds(20, 80, -300, -300);

        heightTextField = new JTextField("40");
        heightTextField.addKeyListener(this);
      
    /**********************************/
    //Czas generacji
        timeJLabel = new JLabel("Generation time [ms]");

        timeTextField = new JTextField("100");
        timeTextField.addKeyListener(this);
        
        /**********************************/
    //Nazwa pliku wynikowego
        fileJLabel = new JLabel("Output file");

        fileTextField = new JTextField("name");
        
    //Możliwe kolory do wyboru
        String[] choices = {"BLACK", "RED", "BLUE", "GREEN", "YELLOW", "WHITE"};
       
    //Sprawdzenie dla jakiego automatu wybierany kolory i wyświetlenie odpowiedznych stanów
    
    //Wybór kolorów dla Wireworld
     
        /**********************************/
        //Kolor pustej komórki
            emptyJLabel = new JLabel("Empty Color");

            cEmpty = new JComboBox<String>(choices);
            cEmpty.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                indexE = cEmpty.getSelectedIndex();
                            }
                        }
                    }
            );
        /**********************************/
        //Kolor głowy elektronu
            headJLabel = new JLabel("ElectronHead Color");

            cHead = new JComboBox<String>(choices);
            cHead.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                indexH = cHead.getSelectedIndex();
                            }
                        }
                    }
            );
        /**********************************/
        //Kolor ogonu elektronu
            tailJLabel = new JLabel("ElectronTail Color");

            cTail = new JComboBox<String>(choices);
            cTail.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                indexT = cTail.getSelectedIndex();
                            }
                        }
                    }
            );
            
        /**********************************/
        //Kolor przewodnika
            conductorJLabel = new JLabel("Conductor Color");

            cConductor = new JComboBox<String>(choices);
            cConductor.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                indexC = cConductor.getSelectedIndex();
                            }
                        }
                    }
            );
            
            
        //Wybór kolorów dla Gry w życie
        
        /**********************************/
        //Kolor żywej komórki
            aliveJLabel = new JLabel("Live color");

            cAlive = new JComboBox<String>(choices);
            cAlive.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                indexLive = cAlive.getSelectedIndex();
                            }
                        }
                    }
            );
        /**********************************/
        //Kolor martwej komórki
            deadJLabel = new JLabel("Dead color");

            cDead = new JComboBox<String>(choices);
            cDead.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                indexDead = cDead.getSelectedIndex();
                            }
                        }
                    }
            );
            
            
        //Możliwe kształty komórki do wyboru
            String[] shapes = {"SQUARE", "CIRCLE"};
       
            
            shapeJLabel = new JLabel("Cell shape");

            cShape = new JComboBox<String>(shapes);
            cShape.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                indexS = cShape.getSelectedIndex();
                            }
                        }
                    }
            );
            
            
        
    /**********************************/
    //Wybór pliku z danymi
        browseJLabel = new JLabel("File");
    

        btnBrowse = new JButton("Browse");
        btnBrowse.addActionListener(this);
        
        /**********************************/
    //Zakończenie Menu
        btnOk = new JButton("OK");
               btnOk.addActionListener(this);
        setVisible(true);

    }

    public static void main(String[] args) {
      new Menu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
    
        if (source == btnGol) {
            
            cellularAutomaton=CA.GOL;
                    
            btnGol.setLocation(-300, -300);
            btnWW.setLocation(-300, -300);
            
            uploadGOLMenu();
            
        }
        
        if (source == btnWW) {
            
            cellularAutomaton=CA.WW;
            
            btnGol.setLocation(-300, -300);
            btnWW.setLocation(-300, -300);
            
            uploadWireworldMenu();
        }
        
    //Instrukcje wykonywane, gdy został wciśnięty przycisk "OK", zatwierdzający wprowadzone dane
        if (source == btnOk) {
            
     //Utworzenie folderu do którego zapisywane będą pliki wynikowe
           new File("Results").mkdir();
            
            confirmSettings();
        
        } 
        //Gdy został wciśnięty przycisk "Browse" to uruchamia się klasa do przeglądania plików
        else if (source == btnBrowse) {
            new FileBrowser();
        }
        
        else if(source==yes){
            save=true;
            fileTextField.setLocation(310, 200);
        }
        else if (source==no){
            save=false;
            fileTextField.setLocation(-310, -200);

        }

    }
    
        @Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
            if(e.getSource()==btnGol) btnGol.setIcon(new ImageIcon(getClass().getResource("/image/gol_animation.gif")));
            else if(e.getSource()==btnWW) btnWW.setIcon(new ImageIcon(getClass().getResource("/image/ww_animation.gif")));
	}

	@Override
	public void mouseExited(MouseEvent e) {
            if(e.getSource()==btnGol) btnGol.setIcon(new ImageIcon(getClass().getResource("/image/gol_name.png")));
            else if(e.getSource()==btnWW) btnWW.setIcon(new ImageIcon(getClass().getResource("/image/ww_name.png")));
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
        if(e.getSource()==widthTextField){

            areSettingsCorrect(widthTextField);   
        }
        
        if(e.getSource()==heightTextField){

            areSettingsCorrect(heightTextField);  
        }
        
        if(e.getSource()==timeTextField){

            areSettingsCorrect(timeTextField);  
        }
        
    }
        
    //Sprawdzanie na bieżąco wprowadzanych danych i podświetlanie na czerwono pola, gdy są błędne
        void areSettingsCorrect(JTextField textField){
        
        try {
                 Integer.parseInt(textField.getText());
                
                textField.setBackground(Color.white);
                if(Integer.parseInt(textField.getText())<=0) textField.setBackground(Color.red);
            } catch (NumberFormatException w) {
                textField.setBackground(Color.red);
            }
        
    }
        
    //Wczytanie wpisanych danych. Gdy są błędne wyświetla się kominikat
        void confirmSettings(){
            //Zmienna pomocnicza wskazująca poprawość danych
            int ok=0;
        
            //Sprawdznie czy w polu jest podana poprawna wartość
            //Jeśli wartość jest zła to wyświetla się komunikat i zmienna ok=1
            try {
                width = Integer.parseInt(widthTextField.getText());
                
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(null, "In field Width is wrong value!!");
                ok = 1;
            }
            //Sprawdznie czy w polu jest podana poprawna wartość
            //Jeśli wartość jest zła to wyświetla się komunikat i zmienna ok=1
            try {
                height = Integer.parseInt(heightTextField.getText());
               
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(null, "In field Height is wrong value!!");
                ok = 1;
            }
            //Sprawdznie czy w polu jest podana poprawna wartość
            //Jeśli wartość jest zła to wyświetla się komunikat i zmienna ok=1
            try {
                time = Integer.parseInt(timeTextField.getText());
            
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(null, "In field Generation time is wrong value!!");
                ok = 1;
            }
            //Sprawdznie czy został wybrany plik za danymi. Jeśli nie to wyświetli się komunikat
            if (FileBrowser.path == null) {
                JOptionPane.showMessageDialog(null, "You did not choose text file!!");
            }
            //Jeśli wybrane zostało Wireworld to przypisanie wartości poszczególnych kolorów
            if (cellularAutomaton == CA.WW) {
                emptyColor = cEmpty.getItemAt(indexE);
                headColor = cHead.getItemAt(indexH);
                tailColor = cTail.getItemAt(indexT);
                conductorColor = cConductor.getItemAt(indexC);
                
            }
            //Jeśli wybrane zostało Game of Life to przypisanie wartości poszczególnych kolorów
            if (cellularAutomaton == CA.GOL) {
                liveColor = cAlive.getItemAt(indexLive);
                deadColor = cDead.getItemAt(indexDead);
                shape=cShape.getItemAt(indexS);
            }
            //Przypisanie wartości nazwy pliku wynikowego
            if(save==true) filename = fileTextField.getText();
            //Przypisanie wartości ścieżki do pliku
            filepath=FileBrowser.path;
            

            //Jeśli podane wartości są poprawne, to program czyta dane z pliku tekstowego i uruchamia odpowiednią planszę
            if (ok == 0) {
                dispose();

                new CellularAutomaton();
            }
    }
    
    //Wcztanie Menu do Game of Life 
        void uploadGOLMenu(){
            
        add(widthJLabel);       widthJLabel.setBounds(20+110, 20, 50, 25);
        add(widthTextField);    widthTextField.setBounds(150+110, 20, 50, 25);
        add(heightJLabel);      heightJLabel.setBounds(20+110, 80, 50, 25);
        add(heightTextField);   heightTextField.setBounds(150+110, 80, 50, 25);
        add(timeJLabel);        timeJLabel.setBounds(20+110, 140, 130, 25);
        add(timeTextField);     timeTextField.setBounds(150+110, 140, 50, 25);
        add(fileJLabel);        fileJLabel.setBounds(20+110, 200, 130, 25);
        add(fileTextField);     fileTextField.setBounds(200+110, 200, 50, 25);
        
        add(yes);               yes.setBounds(150+110, 183, 50, 25);
        add(no);                no.setBounds(150+110, 213, 50, 25);
        
        add(aliveJLabel);       aliveJLabel.setBounds(20+110, 260, 130, 25);
        add(cAlive);            cAlive.setBounds(150+110, 260, 80, 20);
        add(deadJLabel);        deadJLabel.setBounds(20+110, 320, 130, 25);
        add(cDead);             cDead.setBounds(150+110, 320, 80, 20);

        add(shapeJLabel);       shapeJLabel.setBounds(20+110, 380, 130, 25);
        add(cShape);            cShape.setBounds(150+110, 380, 80, 20); 
        
        add(browseJLabel);      browseJLabel.setBounds(20+110, 440, 130, 25);
        add(btnBrowse);         btnBrowse.setBounds(150+110, 440, 100, 30);
        add(btnOk);             btnOk.setBounds(85+110, 500, 55, 30);

            
        }

    //Wczytanie Menu do Wireworld        
        void uploadWireworldMenu(){
            
        add(widthJLabel);       widthJLabel.setBounds(20+110, 20, 50, 25);
        add(widthTextField);    widthTextField.setBounds(150+110, 20, 50, 25);  
        add(heightJLabel);      heightJLabel.setBounds(20+110, 80, 50, 25);     
        add(heightTextField);   heightTextField.setBounds(150+110, 80, 50, 25); 
        add(timeJLabel);        timeJLabel.setBounds(20+110, 140, 130, 25);    
        add(timeTextField);     timeTextField.setBounds(150+110, 140, 50, 25);  
        add(fileJLabel);        fileJLabel.setBounds(20+110, 200, 130, 25);   
        add(fileTextField);     fileTextField.setBounds(200+110, 200, 50, 25);
        
        add(yes);               yes.setBounds(150+110, 183, 50, 25);
        add(no);                no.setBounds(150+110, 213, 50, 25);
  
        
        add(emptyJLabel);       emptyJLabel.setBounds(20+110, 260, 130, 25);    
        add(cEmpty);            cEmpty.setBounds(150+110, 260, 80, 20);         
        add(headJLabel);        headJLabel.setBounds(20+110, 320, 130, 25);    
        add(cHead);             cHead.setBounds(150+110, 320, 80, 20);          
        add(tailJLabel);        tailJLabel.setBounds(20+110, 380, 130, 25);     
        add(cTail);             cTail.setBounds(150+110, 380, 80, 20);          
        add(conductorJLabel);   conductorJLabel.setBounds(20+110, 440, 130, 25);
        add(cConductor);        cConductor.setBounds(150+110, 440, 80, 20);     
        
        add(browseJLabel);      browseJLabel.setBounds(20+110, 500, 130, 25);   
        add(btnBrowse);         btnBrowse.setBounds(150+110, 500, 100, 30);     
        add(btnOk);             btnOk.setBounds(85+110, 560, 55, 30);           

        }
        
        public static int getCAWidth(){
            return width;
        }
        
        public static int getCAHeight(){
            return height;
        }
        
        public static int getCATime(){
            return time;
        }
        
        public static boolean getSave(){
            return save;
        }
}
