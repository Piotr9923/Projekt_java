package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener {

    JButton btn, browse1;
    JTextField w1, h1, t1, f1;
    public static int width = 40, height = 40, time;
    private JComboBox<String> ch, ct, cd, ce, cdc, clc;
    public static String head_color, tail_color, conductor_color, empty_color;
    int index_h = 0, index_t = 0, index_d = 0, index_e = 0;
    int index_live, index_dead;
    public static String live_color, dead_color;
    public int celluarautomat=0;
    public static String filename;

    public Menu() {
        super("Menu");
        setSize(400, 650);
        setLocationRelativeTo(null);

        setResizable(false);
        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel w, h, t, eh, et, d, ee, f, edc, elc, browse;

        w = new JLabel("Width");
        w.setBounds(20, 20, 50, 25);
        add(w);

        w1 = new JTextField("40");
        w1.setBounds(150, 20, 50, 25);
        add(w1);

        h = new JLabel("Height");
        h.setBounds(20, 80, 50, 25);
        add(h);

        h1 = new JTextField("40");
        h1.setBounds(150, 80, 50, 25);
        add(h1);

        t = new JLabel("Generation time [ms]");
        t.setBounds(20, 140, 130, 25);
        add(t);

        t1 = new JTextField("1000");
        t1.setBounds(150, 140, 50, 25);
        add(t1);

        f = new JLabel("Output file name");
        f.setBounds(20, 200, 130, 25);
        add(f);

        f1 = new JTextField("name");
        f1.setBounds(150, 200, 50, 25);
        add(f1);

        String[] choices = {"BLACK", "RED", "BLUE", "GREEN", "YELLOW", "WHITE"};

        if (celluarautomat == 0) {
            ee = new JLabel("Empty Color");
            ee.setBounds(20, 260, 130, 25);
            add(ee);

            ce = new JComboBox<String>(choices);
            ce.setBounds(150, 260, 80, 20);
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

            eh = new JLabel("ElectronHead Color");
            eh.setBounds(20, 320, 130, 25);
            add(eh);

            ch = new JComboBox<String>(choices);
            ch.setBounds(150, 320, 80, 20);
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

            et = new JLabel("ElectronTail Color");
            et.setBounds(20, 380, 130, 25);
            add(et);

            ct = new JComboBox<String>(choices);
            ct.setBounds(150, 380, 80, 20);
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

            d = new JLabel("Conductor Color");
            d.setBounds(20, 440, 130, 25);
            add(d);

            cd = new JComboBox<String>(choices);
            cd.setBounds(150, 440, 80, 20);
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
        } else if (celluarautomat == 1) {
            elc = new JLabel("Live color");
            elc.setBounds(20, 260, 130, 25);
            add(elc);

            clc = new JComboBox<String>(choices);
            clc.setBounds(150, 260, 80, 20);
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

            edc = new JLabel("Dead color");
            edc.setBounds(20, 320, 130, 25);
            add(edc);

            cdc = new JComboBox<String>(choices);
            cdc.setBounds(150, 320, 80, 20);
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
        }

        browse = new JLabel("File");
        if (celluarautomat == 0) {
            browse.setBounds(20, 500, 130, 25);
        } else if (celluarautomat == 1) {
            browse.setBounds(20, 380, 130, 25);
        }
        add(browse);

        browse1 = new JButton("Browse");
        if (celluarautomat == 0) {
            browse1.setBounds(150, 500, 100, 30);
        } else if (celluarautomat == 1) {
            browse1.setBounds(150, 380, 100, 30);
        }
        add(browse1);
        browse1.addActionListener(this);

        btn = new JButton("OK");
        if (celluarautomat == 0) {
            btn.setBounds(85, 560, 55, 30);
        } else if (celluarautomat == 1) {
            btn.setBounds(85, 440, 55, 30);
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
        int ok = 0;
        Object source = e.getSource();
        if (source == btn) {
            try {
                width = Integer.parseInt(w1.getText());
                ok = 0;
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(null, "In field Width is wrong value!!");
                ok = 1;
            }

            try {
                height = Integer.parseInt(h1.getText());
                ok = 0;
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(null, "In field Height is wrong value!!");
                ok = 1;
            }

            try {
                time = Integer.parseInt(t1.getText());
                ok = 0;
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(null, "In field Generation time is wrong value!!");
                ok = 1;
            }
            if (FileBrowser.path == null) {
                JOptionPane.showMessageDialog(null, "You did not choose text file!!");
               
            }
            if (celluarautomat == 0) {
                empty_color = ce.getItemAt(index_e);
                head_color = ch.getItemAt(index_h);
                tail_color = ct.getItemAt(index_t);
                conductor_color = cd.getItemAt(index_d);
            }
            if (celluarautomat == 1) {
                live_color = clc.getItemAt(index_live);
                dead_color = cdc.getItemAt(index_dead);
            }
            filename = f1.getText();
            if (filename.length() < 1 || filename.charAt(0) == ' ') {
                JOptionPane.showMessageDialog(null, "In field File name is wrong value!!");
                ok = 1;
            }
            new File("Results").mkdir();

            if (ok == 0) {
                dispose();
                //
            }

        } else if (source == browse1) {
            new FileBrowser();
        }

    }

}
