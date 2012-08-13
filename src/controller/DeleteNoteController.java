package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTree;

import model.Component;
import model.Container;
import model.RegelTreeModel;
import model.Rule;

public class DeleteNoteController implements ActionListener {

    private JTree tree;
    private RegelTreeModel model;

    public DeleteNoteController(JTree tree) {
        this.tree = tree;
        this.model = (RegelTreeModel) tree.getModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model = (RegelTreeModel) tree.getModel();
        Component node = (Component) this.tree.getLastSelectedPathComponent();

        Container con = node.getPredecessor();
        con.removeChild(node);

        if (con.getNumberOfChildren() == 0) {
            Rule neu = new Rule(con.getName(), con.getEnglishName(), con.getText(), con.getEnglishDescription(), con.getOrder());
            Container par = con.getPredecessor();

            if (par != null) {
                par.removeChild(con);
                par.addChild(neu);
            }
        }

        this.model.fireTreeNodesChanged(this.tree.getSelectionPath().getParentPath());
    }
}
