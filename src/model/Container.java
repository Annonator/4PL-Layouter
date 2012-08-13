package model;

import java.util.*;

public class Container extends Component {

    protected ArrayList<Component> children;

    public Container(String name, String text) {
        super(name, text);
        children = new ArrayList<Component>();
    }

    public Container(String name, String englishName, String text, String englishText) {
        super(name, englishName, text, englishText);
        children = new ArrayList<Component>();
    }

    public void addChild(Component comp) {
        comp.pred = this;
        children.add(comp);
        Collections.sort(this.children);
    }

    public void removeChild(Component comp) {
        comp.pred = null;
        children.remove(comp);
    }

    public int getNumberOfChildren() {
        return children.size();
    }

    public Component getChild(int index) {
        return children.get(index);
    }

    public int getIndexOfChild(Component child) {
        return children.indexOf(child);
    }

    public boolean hasChild(Component comp) {
        return children.contains(comp);
    }

    protected void print(int indentation) {
        super.print(indentation);
        for (int i = 0; i < getNumberOfChildren(); i++) {
            Component child = getChild(i);
            child.print(indentation + 2);
        }
    }

    public String getText() {
        String result = this.text;
        for (int i = 0; i < getNumberOfChildren(); i++) {
            Component child = getChild(i);
            result += child.getText();
        }
        return this.text;
    }

    public void sortContent() {
        Collections.sort(this.children);
        System.out.println("ddd");
    }
}
