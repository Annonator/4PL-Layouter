package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTree;

import model.Container;
import model.RegelTreeModel;

import util.XMLFilter;

public class OpenFileController implements ActionListener {

    private JTree tree;

    public OpenFileController(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser("Datendatei waehlen");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.addChoosableFileFilter(new XMLFilter());
        chooser.setAcceptAllFileFilterUsed(false);

        int returnval = chooser.showOpenDialog(null);

        if (returnval == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            XML parser = new XML(file.getAbsolutePath());
            RegelTreeModel model = new RegelTreeModel((Container) parser.buildTree());


            this.tree.setModel(model);
        }
    }
}
