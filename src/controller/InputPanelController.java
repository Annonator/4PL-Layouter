package controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import view.InputPanel;

import model.Component;
import model.RegelTreeModel;

public class InputPanelController implements TreeSelectionListener {

    private RegelTreeModel model;
    private InputPanel panel;

    public InputPanelController(RegelTreeModel model, InputPanel panel) {
        this.model = model;
        this.panel = panel;
    }

    @Override
    public void valueChanged(TreeSelectionEvent arg0) {
        JTree trees = (JTree) arg0.getSource();
        // System.out.println(tree.getSelectionPath().getParentPath().getLastPathComponent().toString());
        // System.out.println(((Rule)tree.getLastSelectedPathComponent()).getText());
        Component test = (Component) trees.getLastSelectedPathComponent();
        if (test != null) {
            this.panel.setName(test.getName());
            this.panel.setEnglishName(test.getEnglishName());
            this.panel.setOrder(test.getOrder());
            this.panel.setdescription(test.getText());
            this.panel.setEnglishDescription(test.getEnglishDescription());
        }
    }
}
