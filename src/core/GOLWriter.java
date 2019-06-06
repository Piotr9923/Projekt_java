package core;

import gui.Menu;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class GOLWriter {

    static void writeGOL(int x, int y, int number) throws FileNotFoundException, IOException {
        FileWriter file = new FileWriter("Results/" + Menu.filename + number + ".txt", true);
        BufferedWriter out = new BufferedWriter(file);
        out.write(x + "," + y);
        out.newLine();
        out.close();
    }
}
