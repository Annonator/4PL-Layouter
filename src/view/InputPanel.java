package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;

import controller.EditController;

public class InputPanel extends JPanel {

    private static final long serialVersionUID = 3563430709011321510L;
    private JLabel nameLabel, englishNameLabel, descriptionLabel, englishDescriptionLabel, orderLabel;
    private JTextField nameField, englishNameField, orderField;
    private JTextArea desciptionArea, englishDescriptionArea;
    private JScrollPane scroller, englishScroller;
    private JButton save, delete;
    private JPanel top, bot, mid;
    private EditController controller;

    public InputPanel(JTree controller) {
        this.controller = new EditController(this, controller);
        this.setLayout(new BorderLayout());
        this.top = new JPanel();

        this.nameLabel = new JLabel("Name:");
        this.englishNameLabel = new JLabel("E-Name:");
        this.descriptionLabel = new JLabel("Regeltext:");
        this.englishDescriptionLabel = new JLabel("E-Text");
        this.orderLabel = new JLabel("Sortierung:");

        this.nameField = new JTextField();
        this.englishNameField = new JTextField();
        this.orderField = new JTextField();
        this.desciptionArea = new JTextArea();
        this.englishDescriptionArea = new JTextArea();

        this.scroller = new JScrollPane(this.desciptionArea);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.englishScroller = new JScrollPane(this.englishDescriptionArea);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.desciptionArea.setLineWrap(true);
        this.englishDescriptionArea.setLineWrap(true);

        this.top.setLayout(new GridLayout(0, 2));
        this.top.add(this.orderLabel);
        this.top.add(this.orderField);

        this.top.add(this.nameLabel);
        this.top.add(this.nameField);

        this.top.add(this.englishNameLabel);
        this.top.add(this.englishNameField);

        this.top.add(this.descriptionLabel);

        this.bot = new JPanel();
        this.bot.setLayout(new GridLayout(1, 2));
        this.save = new JButton("Save");
        this.save.addActionListener(this.controller);
        this.delete = new JButton("Delete");
        this.delete.setEnabled(false);
        this.bot.add(this.save);
        this.bot.add(this.delete);


        //center zusammenbauen

        this.mid = new JPanel();
        this.mid.setLayout(new GridLayout(0, 1));

        this.mid.add(this.scroller);
        this.mid.add(this.englishScroller);

        this.add(this.top, BorderLayout.NORTH);
        this.add(this.mid, BorderLayout.CENTER);
        this.add(this.bot, BorderLayout.SOUTH);
    }

    public void setOrder(int order) {
        this.orderField.setText(String.valueOf(order));
    }

    public void setOrder(long order) {
        this.orderField.setText(String.valueOf(order));
    }

    public long getOrder() {
        return Long.valueOf(this.orderField.getText());
    }
    
    @Override
    public void setName(String name) {
        this.nameField.setText(name);
    }

    @Override
    public String getName() {
        return this.nameField.getText();
    }

    public void setEnglishName(String name) {
        this.englishNameField.setText(name);
    }

    public String getEnglishName() {
        return this.englishNameField.getText();
    }

    public void setdescription(String text) {
        this.desciptionArea.setText(text);
    }

    public String getDescription() {
        return this.desciptionArea.getText();
    }

    public void setEnglishDescription(String text) {
        this.englishDescriptionArea.setText(text);
    }

    public String getEnglishDescription() {
        return this.englishDescriptionArea.getText();
    }
}
