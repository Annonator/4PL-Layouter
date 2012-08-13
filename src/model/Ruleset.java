package model;

public class Ruleset extends Container {

    public Ruleset(String name, String text) {
        this(name, text, 0);
    }

    public Ruleset(String name, String text, int order) {
        this(name, text, (long) order);
    }

    public Ruleset(String name, String text, long order) {
        super(name, text);
        this.order = order;
    }

    public Ruleset(String name, String englishName, String text, String englishText, int order) {
        this(name, englishName, text, englishText, (long) order);
    }

    public Ruleset(String name, String englishName, String text, String englishText, long order) {
        super(name, englishName, text, englishText);
        this.order = order;
    }

    public void addRule(Rule rule) {
        super.addChild(rule);
    }

    public void addRule(Ruleset rule) {
        super.addChild(rule);
    }
}
