package model;

import java.util.ArrayList;

import javax.swing.event.*;
import javax.swing.tree.*;

public class RegelTreeModel implements TreeModel {

    private Container root;
    private ArrayList<TreeModelListener> listener;

    public RegelTreeModel(Container root) {
        this.root = root;
        this.listener = new ArrayList<TreeModelListener>();
    }

    @Override
    public void addTreeModelListener(TreeModelListener arg0) {
        this.listener.add(arg0);
    }

    @Override
    public Object getChild(Object parent, int index) {

        Container childs = (Container) parent;
        return childs.getChild(index);
    }

    @Override
    public int getChildCount(Object parent) {
        Container childs = (Container) parent;
        return childs.getNumberOfChildren();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        Container childs = (Container) parent;
        Component element = (Component) child;

        return childs.getIndexOfChild(element);
    }

    @Override
    public Object getRoot() {
        return this.root;
    }

    @Override
    public boolean isLeaf(Object arg0) {
        return arg0 instanceof Rule;
    }

    @Override
    public void removeTreeModelListener(TreeModelListener arg0) {
        if (this.listener.contains(arg0)) {
            this.listener.remove(arg0);
        }

    }

    @Override
    public void valueForPathChanged(TreePath arg0, Object arg1) {
        // TODO Auto-generated method stub
    }

    public void fireTreeNodesChanged(TreePath changed) {
        TreeModelEvent ev = new TreeModelEvent(this, changed);
        for (int i = 0; i < this.listener.size(); i++) {

            this.listener.get(i).treeStructureChanged(ev);
        }
    }
}
