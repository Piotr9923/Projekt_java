package core;

import gui.Menu;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class CAWriter {

    void write(int number) throws IOException {

        //Tworzenie pliku do którego będziemy zapisywać, a jeśli istnieje to nadpisanie go pustym plikiem
        FileWriter f = new FileWriter("Results/" + Menu.filename + number + ".txt");

        for (int i = 0; i < Menu.getCAHeight(); i++) {
            for (int j = 0; j < Menu.getCAWidth(); j++) {
                if (Menu.cellularAutomaton == Menu.CA.WW) {

                    if (CellularAutomaton.g.getCell(i, j).state == Cell.State.HEAD) {
                        WireworldWriter.writeWireworld("ElectronHead", j, i, number);
                    } else if (CellularAutomaton.g.getCell(i, j).state == Cell.State.TAIL) {
                        WireworldWriter.writeWireworld("ElectronTail", j, i, number);
                    } else if (CellularAutomaton.g.getCell(i, j).state == Cell.State.CONDUCTOR) {
                        WireworldWriter.writeWireworld("Conductor", j, i, number);
                    }

                } else {
                    if (CellularAutomaton.g.getCell(i, j).state == Cell.State.ALIVE) {
                        GOLWriter.writeGOL(j, i, number);
                    }
                }

            }
        }

    }

}
