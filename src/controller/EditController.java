package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import model.Component;
import model.RegelTreeModel;

import view.InputPanel;

public class EditController implements ActionListener {

    private InputPanel panel;
    private JTree theTree;
    private RegelTreeModel model;

    public EditController(InputPanel panel, JTree tree) {
        this.model = (RegelTreeModel) tree.getModel();
        this.panel = panel;
        this.theTree = tree;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // �nder inhalt
        this.model = (RegelTreeModel) theTree.getModel();
        ((Component) this.theTree.getLastSelectedPathComponent()).setOrder(this.panel.getOrder());
        ((Component) this.theTree.getLastSelectedPathComponent()).setName(this.panel.getName());
        ((Component) this.theTree.getLastSelectedPathComponent()).setEnglishName(this.panel.getEnglishName());
        ((Component) this.theTree.getLastSelectedPathComponent()).setEnglishDescription(this.panel.getEnglishDescription());
        ((Component) this.theTree.getLastSelectedPathComponent()).setText(this.panel.getDescription());

        ((Component) this.theTree.getLastSelectedPathComponent()).getPredecessor().sortContent();

        TreePath path = this.theTree.getSelectionPath().getParentPath();
        this.theTree.setSelectionPath(this.theTree.getSelectionPath().getParentPath());
        this.model.fireTreeNodesChanged(path);
    }
}
