package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import util.DialogFilter;

public class MenuController implements ActionListener {

    private JFileChooser fileDialog;
    private JFrame frame;
    private DialogFilter filter;

    public MenuController(JFrame frame) {
        this.fileDialog = new JFileChooser();
        this.filter = new DialogFilter();
        this.fileDialog.setFileFilter(this.filter);
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((((JMenuItem) e.getSource()).getName()).equals("MenuOpen")) {
            System.out.println("MenuOpen");

            int res = this.fileDialog.showOpenDialog(this.frame);

            if (res == JFileChooser.APPROVE_OPTION) {
                File file = this.fileDialog.getSelectedFile();

                System.out.println(file.getAbsolutePath());
            }

        } else if ((((JMenuItem) e.getSource()).getName()).equals("MenuSave")) {
            System.out.println("MenueSave");
        }
    }
}
