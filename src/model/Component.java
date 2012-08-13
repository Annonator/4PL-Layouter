package model;

public abstract class Component implements Comparable<Component> {

    protected Container pred;
    protected String name;
    protected String text;
    protected long order;
    protected String englishName;
    protected String englishDescription;

    public Component(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public Component(String name, String englishName, String text,
            String englishText) {
        this.name = name.replaceAll("/!/", "<");
        this.englishName = englishName.replaceAll("/!/", "<");
        this.text = text.replaceAll("/!/", "<");
        this.englishDescription = englishText.replaceAll("/!/", "<");
    }

    @Override
    public int compareTo(Component b) {

        if (this.order > b.getOrder()) {
            return 1;
        }
        if (this.order < b.getOrder()) {
            return -1;
        }

        return 0;
    }

    public Container getPredecessor() {
        return pred;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setOrder(int order) {
        this.setOrder((long) order);
    }

    public void setOrder(long order) {
        this.order = order;

        if (this.pred != null) {
            System.out.println("reoder");
            this.pred.sortContent();
        }
        System.out.println("no");
    }

    public long getOrder() {
        return this.order;
    }

    public String toString() {
        return this.name;
    }

    protected void print(int indentation) {
        for (int i = 0; i < indentation; i++) {
            System.out.print(" ");
        }
        System.out.println(this);
    }

    public void print() {
        print(0);
    }

    public void setEnglishName(String name) {
        this.englishName = name;
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public void setEnglishDescription(String text) {
        this.englishDescription = text;
    }

    public String getEnglishDescription() {
        return this.englishDescription;
    }
}
