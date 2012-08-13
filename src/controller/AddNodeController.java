package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTree;

import model.Component;
import model.Container;
import model.RegelTreeModel;
import model.Rule;
import model.Ruleset;

public class AddNodeController implements ActionListener {

    private JTree tree;
    private RegelTreeModel model;

    public AddNodeController(JTree tree) {
        this.tree = tree;
        this.model = (RegelTreeModel) tree.getModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model = (RegelTreeModel) tree.getModel();
        String answer = JOptionPane.showInputDialog("Name des neuen Knotens", JOptionPane.PLAIN_MESSAGE);

        if (answer != null && answer.length() > 0) {
            Component node = (Component) this.tree.getLastSelectedPathComponent();

            if (node instanceof Rule) {
                Ruleset newSet = new Ruleset(node.getName(), node.getEnglishName(), node.getText(), node.getEnglishDescription(), node.getOrder());
                node.getPredecessor().addChild(newSet);
                node.getPredecessor().removeChild(node);
                Rule comp = new Rule(answer, answer, "TEXT", "TEXT", 1);
                newSet.addChild(comp);
                this.model.fireTreeNodesChanged(this.tree.getSelectionPath());
            } else {
                int count;
                if (node.getPredecessor() == null) {
                    count = ((Ruleset) node).getNumberOfChildren() + 1;
                } else {
                    count = node.getPredecessor().getNumberOfChildren() + 1;
                }
                Rule newRule = new Rule(answer, answer, "TEXT", "TEXT", count);
                Container con = (Container) node;
                con.addChild(newRule);
                this.model.fireTreeNodesChanged(this.tree.getSelectionPath());
            }
        }
    }
}
