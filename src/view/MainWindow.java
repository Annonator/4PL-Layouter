package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import template.RegelwerkTemplate;

import controller.AddNodeController;
import controller.DeleteNoteController;
import controller.InputPanelController;
import controller.OpenFileController;
import controller.SaveFileController;

import model.RegelTreeModel;
import model.Ruleset;

public class MainWindow extends JFrame {

    private JTree tree;
    private JScrollPane treeScroll;
    private InputPanel rightPanel;
    private RegelTreeModel model;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem open, save;
    private InputPanelController treeListener;
    private JPanel leftPanel;
    private JPanel bottPanel;
    private JButton add;
    private JButton delete;

    public MainWindow() {
        this(null);
    }

    public MainWindow(RegelTreeModel model) {

        super("4PL Regelwerkgenerator");
        this.setSize(500, 700);
        this.setLayout(new GridLayout(1, 1));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.model = model;

        // generate tree
        if (this.model != null) {
            this.tree = new JTree(this.model);
        } else {
            this.model = new RegelTreeModel(new Ruleset("name", "englishName", "text", "englishText", 0));
            this.tree = new JTree(this.model);
        }

        // Menu
        this.menuBar = new JMenuBar();

        this.file = new JMenu("Datei");
        this.file.getAccessibleContext().setAccessibleDescription("Optionen");
        this.menuBar.add(this.file);

        this.open = new JMenuItem("Oeffnen");
        this.open.setName("MenuOpen");
        this.open.getAccessibleContext().setAccessibleDescription(
                "Laedt eine neue Regelwerksdatei");
        this.open.addActionListener(new OpenFileController(this.tree));
        this.file.add(this.open);

        this.save = new JMenuItem("Speichern");
        this.save.setName("MenuSave");
        this.save.getAccessibleContext().setAccessibleDescription(
                "Speichert die offene Datei");
        this.save.addActionListener(new SaveFileController(this.tree));
        this.file.add(this.save);

        JMenu export = new JMenu("Export");
        this.menuBar.add(export);

        JMenuItem html = new JMenuItem("HTML");
        html.addActionListener(new RegelwerkTemplate(this.tree));
        export.add(html);

        this.setJMenuBar(this.menuBar);

        this.tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);



        // Add Tree to window
        this.treeScroll = new JScrollPane(this.tree);
        this.tree.setName("Regelwerk");

        this.leftPanel = new JPanel();
        this.leftPanel.setLayout(new BorderLayout());
        this.leftPanel.add(treeScroll, BorderLayout.CENTER);

        this.bottPanel = new JPanel();
        this.bottPanel.setLayout(new GridLayout(1, 0));

        this.add = new JButton("Add");
        this.add.addActionListener(new AddNodeController(this.tree));
        this.bottPanel.add(this.add, BorderLayout.SOUTH);
        this.add(this.leftPanel);

        this.delete = new JButton("Delete");
        this.delete.addActionListener(new DeleteNoteController(this.tree));
        this.bottPanel.add(this.delete, BorderLayout.SOUTH);

        this.leftPanel.add(this.bottPanel, BorderLayout.SOUTH);
        this.rightPanel = new InputPanel(this.tree);
        this.add(this.rightPanel);

        // Listener
        this.treeListener = new InputPanelController(this.model, this.rightPanel);
        this.tree.addTreeSelectionListener(this.treeListener);


        this.setVisible(true);
    }
}
