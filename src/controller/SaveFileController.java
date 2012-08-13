package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;

import model.Container;
import util.XMLFilter;

public class SaveFileController implements ActionListener {

    private JTree tree;

    public SaveFileController(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser("Speichern");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.addChoosableFileFilter(new XMLFilter());
        chooser.setAcceptAllFileFilterUsed(false);


        int returnval = chooser.showSaveDialog(null);

        if (returnval == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();

            File toSave = new File(path);

            if (toSave.exists()) {
                int n = JOptionPane.showConfirmDialog(null,
                        "M�chten Sie diese Datei �berschreiben?",
                        "Datei �berschreiben?", JOptionPane.YES_NO_OPTION);

                // ja == 0
                if (n == 1) {
                    return;
                }
            }

            File toWrite;

            int i = path.lastIndexOf('.');

            if (i > 0) {
                toWrite = new File(path);
            } else {
                toWrite = new File(path + ".xml");
            }

            try {
                //FileWriter writer = new FileWriter(toWrite, false);

                FileOutputStream tt = new FileOutputStream(toWrite, false);
                OutputStreamWriter charSet = new OutputStreamWriter(tt, "UTF-8");
                BufferedWriter writer = new BufferedWriter(charSet);
                
                // Datei anfang
                writer.write("<?xml version=\"1.0\"?>");
                writer.write(System.getProperty("line.separator"));
                writer.write("<rules>");

                XML.buildFromModel((Container) this.tree.getModel().getRoot(), writer);

                // Datei ende
                writer.write(System.getProperty("line.separator"));
                writer.write("</rules>");

                writer.flush();
                writer.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
