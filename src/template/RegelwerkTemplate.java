package template;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;

import model.Component;
import model.Container;
import model.Ruleset;

public class RegelwerkTemplate implements ActionListener {

    private String name;
    private String infoLink;
    private JTree tree;
    private Container root;
    // test zumr workaround, container umbruch
    private ArrayList<String> containerBreak;
    // 0 = Deutsch 1 = Englisch
    private int language;
    private String stopMark;
    private String date;

    public RegelwerkTemplate(JTree tree) {
        this.tree = tree;
        this.language = 0;
        this.stopMark = "";
        this.name = "";
        this.date = "";
        this.containerBreak = new ArrayList<String>();
    }

    private String header(String name) {

        String erg = "<div>\n"
                + "<div style=\"background-color: #303030; border: 2px solid #4B4B4B; padding: 8px\">\n"
                + "<div style=\"background-color: #232323; border: 4px solid #232323;\">\n"
                + "<strong>" + name + "</strong>\n";

        erg += this.addSpacer();

        erg += "<a target=\"_blank\" href=\"" + this.infoLink + "\">\n"
                + "<strong>Info &amp; Details</strong>\n" + "</a>\n";

        erg += this.addSpacer();

        if (this.language == 0) {
            erg += "<a target=\"_blank\" href=\"/index.php/de/News:regelwerk/178/4/Vollstaendiges_Regelwerk.html\">\n"
                    + "<strong>Globales Regelwerk</strong>\n" + "</a>\n";
        } else {
            erg += "<a target=\"_blank\" href=\"/index.php/de/News:regelwerk/178/4/Vollstaendiges_Regelwerk.html\">\n"
                    + "<strong>Global Rules</strong>\n" + "</a>\n";
        }

        erg += "<div style=\"float:right\">\n" + "Stand: " + this.date + "\n"
                + "</div>\n" + "</div>\n";

        erg += this.addLine();

        if (this.language == 0) {
            erg += "<p>Die hier aufgef&uuml;hrten Regeln sind f&uuml;r alle Teilnehmer der 4Players Liga verbindlich. Wer sich bei der 4Players Liga anmeldet erkl&auml;rt damit, dass er diese Regeln gelesen und verstanden hat und diese nach bestem Wissen und Gewissen einh&auml;lt."
                    + "<br><br>"
                    + "Die 4Players Liga Regelwerke werden immer vollst&auml;ndig und aktuell gehalten. Bitte informiert euch zus�tzlich im Bereich Info &amp; Details der jeweiligen Ligen, Ladders und Turniere sowie in den News zu den Spielen, um &uuml;ber m&ouml;gliche &auml;nderungen und Ausnahmen informiert zu sein. Solltet ihr Fragen und Anregungen zu den Regeln haben, k&ouml;nnt ihr jederzeit das Supportsystem nutzen und ein Ticket erstellen. </p>\n";
        } else {
            erg += "ENGLISH TEXT MISSING";
        }

        erg += "</div>\n";

        erg += this.tableOfContent();

        // TODO: check !!!!
        erg += "<div style=\"border: 2px solid #4b4b4b; background-color: #303030; padding: 8px; margin-top: 5px; float: right; width: 380px;\">\n";
        erg += this.getFirstContentList("", this.root);
        erg += "</div>\n";

        erg += "<div style=\"border: 2px solid #4b4b4b; background-color: #303030; padding: 8px; margin: 5px 10px 0px 0px; width: 210px; float:left; clear: left\">\n";
        erg += "<div style=\"background-color: #232323; border: 4px solid #232323;\">\n";
        erg += "<span style=\"color: #fabf37;\">\n";
        erg += "<strong>Help & Support</strong>\n";
        erg += "</span>\n";
        erg += "</div>\n";
        erg += "<hr style=\"color: #fabf37; border: 1px solid #fabf37;\">\n";
        erg += "<p>\n";
        erg += "<a target=\"_blank\" href=\"/index.php/de/News:bericht/1258/4Players_Liga_How_to_register.html\">\n";
        erg += "<img border=\"0\" alt=\"\" src=\"http://static2.4pl.4pcdn.de/images/editorial/ee/b5/44736.jpg\" style=\"border: 0; margin-top: 4px; margin-bottom: 4px;\">\n";
        erg += "</a>\n";
        erg += "<a target=\"_blank\" href=\"/index.php/de/News:bericht/1261/Verification_out_of_Germany.html\">\n";
        erg += "<img border=\"0\" alt=\"\" src=\"http://static1.4pl.4pcdn.de/images/editorial/70/0c/44739.jpg\" style=\"border: 0; margin-top: 4px; margin-bottom: 4px;\">\n";
        erg += "</a>\n";
        erg += "<a target=\"_blank\" href=\"/index.php/de/News:bericht/1260/4Players_Liga_How_to_Team.html\">\n";
        erg += "<img border=\"0\" alt=\"\" src=\"http://static1.4pl.4pcdn.de/images/editorial/a2/a4/44737.jpg\" style=\"border: 0; margin-top: 4px; margin-bottom: 4px;\">\n";
        erg += "</a>\n";
        erg += "<a target=\"_blank\" href=\"/index.php/de/News:bericht/721/4PL_Teamspeak_Live-Support.html\">\n";
        erg += "<img border=\"0\" alt=\"\" src=\"http://static2.4pl.4pcdn.de/images/editorial/3a/8e/44738.jpg\" style=\"border: 0; margin-top: 4px; margin-bottom: 4px;\">\n";
        erg += "</a>\n";
        erg += "<a target=\"_blank\" href=\"/index.php/de/Support:new_ticket/new_ticket/Support.html\">\n";
        erg += "<img border=\"0\" alt=\"\" src=\"http://static1.4pl.4pcdn.de/images/editorial/bf/97/44412.jpg\" style=\"border: 0; margin-top: 4px; margin-bottom: 4px;\">\n";
        erg += "</a>\n";
        erg += "<a target=\"_blank\" href=\"/index.php/News%3Aregelwerk/49/Anti-Cheat_System.html\">\n";
        erg += "<img border=\"0\" alt=\"\" src=\"http://static1.4pl.4pcdn.de/images/editorial/21/d6/44413.jpg\" style=\"border: 0; margin-top: 4px; margin-bottom: 4px;\">\n";
        erg += "</a>\n";
        erg += "<a target=\"_blank\" href=\"/index.php/News:show/834/How_to_Gameaccount.html\">\n";
        erg += "<img border=\"0\" alt=\"\" src=\"http://static3.4pl.4pcdn.de/images/editorial/e9/a1/44410.jpg\" style=\"border: 0; margin-top: 4px; margin-bottom: 4px;\">\n";
        erg += "</a>\n";
        erg += "<a target=\"_blank\" href=\"http://forum.4pl.4players.de/\">\n";
        erg += "<img border=\"0\" alt=\"\" src=\"http://static1.4pl.4pcdn.de/images/editorial/1a/25/44411.jpg\" style=\"border: 0; margin-top: 4px; margin-bottom: 4px;\">\n";
        erg += "</a>\n";
        erg += "</p>\n";
        erg += "</div>\n";

        return erg;
    }

    public String tableOfContent() {
        String erg = "<div style=\"border: 2px solid #4b4b4b; background-color: #303030; padding: 8px; margin-top: 5px; width: 210px; float:left\">\n"
                + "<div style=\"background-color: #232323; border: 4px solid #232323;\">\n"
                + "<span style=\"color: #fabf37;\">\n";
        if (this.language == 0) {
            erg += "<strong>Inhaltsverzeichnis</strong>\n";
        } else {
            erg += "<strong>Table of Contents</strong>\n";
        }

        erg += "</span>\n" + "</div>\n";

        erg += this.addLine();

        for (int i = 1; i <= this.root.getNumberOfChildren(); i++) {
            Component tmp = this.root.getChild(i - 1);
            String number = i + ".";

            erg += "<ul style=\"list-style:none; margin: 0px; padding: 0px; font-size: 8pt;\">\n";
            String name = "";

            if (this.language == 0) {
                name += tmp.getName();
            } else {
                name += tmp.getEnglishName();
            }

            erg += "<li style=\"color: #fabf37; list-style-type:none\">"
                    + "<strong>"
                    + "<p style=\"padding: 0px; margin: 0px; float: left; width: 40px;\">"
                    + number + "</p>" + "<a href=\"#" + name + "\">" + name
                    + "</a>" + "</strong>" + "</li>\n";

            if (tmp instanceof Ruleset) {
                Container node = (Container) tmp;

                for (int j = 0; j < node.getNumberOfChildren(); j++) {
                    erg += this.getListElement(number + (j + 1) + ".",
                            node.getChild(j));
                }

            }
            erg += "</ul>\n";

            erg += this.addLine(2);
        }

        erg += "</div>\n";

        return erg;
    }

    private String getListElement(String number, Component node) {
        String name = "";

        if (this.language == 0) {
            name += node.getName();
        } else {
            name += node.getEnglishName();
        }

        String erg = "<li style=\"color: #fabf37; list-style-type:none\">"
                + "<p style=\"padding: 0px; margin: 0px; float: left; width: 40px;\">"
                + number + "</p>" + "<a href=\"#" + name + "\">" + name
                + "</a>" + "</li>\n";

        if (node instanceof Ruleset) {
            Ruleset tmpNode = (Ruleset) node;
            String list;

            for (int i = 0; i < tmpNode.getNumberOfChildren(); i++) {
                // gehe ebene Tiefer
                list = this.getListElement(number + (i + 1) + ".",
                        tmpNode.getChild(i));
                erg += list;
            }
        }

        return erg;
    }

    private String content() {
        String erg = "";
        erg += "<div style=\"clear:both; color: #3c3c3c; \">_</div>";
        erg += "<div style=\"border: 2px solid #4b4b4b; background-color: #303030; padding: 8px; margin-top: 5px;\">\n";
        erg += this.getContentList("", this.root);
        erg += "</div>\n";

        return erg;
    }

    private String getFirstContentList(String number, Component node) {
        String name = "";
        String description = "";

        if (this.language == 0) {
            name += node.getName();
            description += node.getText();

        } else {
            name += node.getEnglishName();
            description += node.getEnglishDescription();
        }

        if (description.equals("rules")) {
            description = "";
        }

        String erg = "";

        if (!number.equals("")) {
            if (number.length() > 2) {
                erg = "<div style=\"background-color: #4b4b4b; color: #fabf37\">\n"
                        + "<strong>"
                        + number
                        + " "
                        + name
                        + "</strong>\n"
                        + "<a href=\"#Inhaltsverzeichnis\" style=\"float:right\">^</a>"
                        + "</div>\n";
            } else {

                erg = "<div style=\"background-color: #232323; border: 4px solid #232323; color: #fabf37\">\n"
                        + "<strong>"
                        + number
                        + " "
                        + name
                        + "</strong>\n"
                        + "<a href=\"#Inhaltsverzeichnis\" style=\"float:right\">^</a>"
                        + "</div>\n"
                        + "<hr style=\"color: #4b4b4b; border: 1px solid #fabf37;\">\n";
            }

        }

        erg += "<p>" + description + "</p>";

        if (!this.stopMark.equals("xxx")) {
            if (number.equals(this.stopMark)) {
                this.stopMark = "xxx";
                return "";
            } else {
                this.containerBreak.add(number);
            }

            if (node instanceof Ruleset) {
                Ruleset tmpNode = (Ruleset) node;
                String list;

                for (int i = 0; i < tmpNode.getNumberOfChildren(); i++) {
                	if (number.length() >= 4) {
                        erg += "<div style=\"margin-left: 8%;\">\n";
                    }
                    // gehe ebene Tiefer
                    list = this.getFirstContentList(number + (i + 1) + ".",
                            tmpNode.getChild(i));
                    erg += list;
                    if (number.length() >= 4) {
                        erg += "</div>\n";
                    }
                }
            }

            return erg;
        }
        return "";

    }

    private String getContentList(String number, Component node) {
        String name = "";
        String description = "";

        if (this.language == 0) {
            name += node.getName();
            description += node.getText();
        } else {
            name += node.getEnglishName();
            description += node.getEnglishDescription();
        }

        String erg = "<a name=\"" + name + "\"> </br> </a>\n";

        if (!number.equals("")) {
            if (number.length() > 2) {
                erg += "<div style=\"background-color: #4b4b4b; color: #fabf37\">\n"
                        + "<strong>"
                        + number
                        + " "
                        + name
                        + "</strong>\n"
                        + "<a href=\"#Inhaltsverzeichnis\" style=\"float:right\">^</a>"
                        + "</div>\n";
            } else {
                erg += "<div style=\"background-color: #232323; border: 4px solid #232323; color: #fabf37\">\n"
                        + "<strong>"
                        + number
                        + " "
                        + name
                        + "</strong>\n"
                        + "<a href=\"#Inhaltsverzeichnis\" style=\"float:right\">^</a>"
                        + "</div>\n"
                        + "<hr style=\"color: #4b4b4b; border: 1px solid #fabf37;\">\n";
            }

        }

        erg += "<p>" + description + "</p>";

        // alle schon eingetragenen rules und bricht ab wenn hier eine
        // aufgerufen wird
        for (String num : this.containerBreak) {
            if (num.equals(number)) {
                erg = "";
                break;
            }
        }

        if (node instanceof Ruleset) {
            Ruleset tmpNode = (Ruleset) node;
            String list;

            for (int i = 0; i < tmpNode.getNumberOfChildren(); i++) {
                if (number.length() >= 4) {
                    erg += "<div style=\"margin-left: 8%;\">\n";
                }
                // gehe ebene Tiefer
                list = this.getContentList(number + (i + 1) + ".",
                        tmpNode.getChild(i));
                erg += list;
                if (number.length() >= 4) {
                    erg += "</div>\n";
                }
            }
        }

        return erg;
    }

    private String footer() {
        return "</div>";
    }

    private String addSpacer() {
        return this.addSpacer(6);
    }

    private String addSpacer(int count) {
        String val = "";

        for (int i = 0; i < count; i++) {
            val += "<img width=\"8\" height=\"8\" border=\"0\" alt=\"spacer\" src=\"http://static2.4pl.4players.de/images/editorial/d0/b4/9596.png\" style=\"border: 0pt none;\">\n";
        }

        return val;
    }

    private String addLine() {
        return this.addLine(1);
    }

    private String addLine(int color) {
        String yellow = "#fabf37";
        String grey = "#4b4b4b";

        String col = yellow;

        if (color == 2) {
            col = grey;
        }

        return "<hr style=\"color: " + col
                + "#fabf37; border: 1px solid" + col + ";\">\n";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String answer = JOptionPane.showInputDialog(
                "Umbruch bei regel (zb 1.2.1.)", JOptionPane.PLAIN_MESSAGE);
        
        String datum = JOptionPane.showInputDialog("Datum", JOptionPane.PLAIN_MESSAGE);

        if (answer != null && datum != null && datum.length() > 0 && answer.length() > 0) {
        	this.date = datum;
            this.stopMark = answer;
            this.root = (Container) this.tree.getModel().getRoot();

            String nameAnswer = JOptionPane.showInputDialog(
                    "Name des Regelwerks", JOptionPane.PLAIN_MESSAGE);

            if (nameAnswer != null && nameAnswer.length() > 0) {
                this.language = 0;
                this.stopMark = answer;
                String outString = this.header(nameAnswer) + this.content()
                        + this.footer();

                this.language = 1;
                this.stopMark = answer;
                String outEnglish = this.header(nameAnswer) + this.content()
                        + this.footer();

                JFrame output = new JFrame("html Code");
                output.setLayout(new GridLayout(0, 1));
                output.setSize(800, 600);

                JTextArea area = new JTextArea();
                area.setText(outString);
                JScrollPane scroll = new JScrollPane(area);

                JTextArea areaEnglish = new JTextArea();
                areaEnglish.setText(outEnglish);
                JScrollPane scrollEnglish = new JScrollPane(areaEnglish);

                output.add(scroll);
                output.add(scrollEnglish);
                output.setVisible(true);
            }

        }

    }
}