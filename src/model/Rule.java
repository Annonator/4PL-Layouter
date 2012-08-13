package model;

public class Rule extends Component {

    public Rule(String name, String text) {
        this(name, text, 0);
    }

    public Rule(String name, String text, int order) {
        this(name, text, (long) order);
    }

    public Rule(String name, String text, long order) {
        super(name, text);
        this.order = order;
    }

    public Rule(String name, String englishName, String text, String englishText, long order) {
        super(name, englishName, text, englishText);
        this.order = order;
    }
}
