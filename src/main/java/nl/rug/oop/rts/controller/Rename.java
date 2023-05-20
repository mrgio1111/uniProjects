package nl.rug.oop.rts.controller;

import lombok.Setter;
import nl.rug.oop.rts.model.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class for renaming Edges/Nodes.
 */
@Setter
public class Rename extends AbstractAction {
    private Map map;
    private JTextField textField;

    public Rename(Map map, JTextField textField) {
        this.map = map;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newName = textField.getText();
        if (map.getNodeOne() != null) {
            map.renameNode(newName);
        }else if (map.getAnEdge() != null) {
            map.renameEdge(newName);
        }
    }
}
//map.renameNode(frame.getTextfield().getText());