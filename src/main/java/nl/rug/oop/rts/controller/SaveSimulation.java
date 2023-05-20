package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that saves the simulation to a file.
 */
public class SaveSimulation extends AbstractAction {

    private Map map;

    public SaveSimulation(String name, Map map){
        super(name);
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser();
        int response = jFileChooser.showSaveDialog(null); // select file to save
        if(response == JFileChooser.APPROVE_OPTION){
            File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(map.stringToJson());
                fileWriter.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
