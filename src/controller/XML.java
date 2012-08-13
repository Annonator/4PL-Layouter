package controller;

import java.util.Iterator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Component;
import model.Container;
import model.Rule;
import model.Ruleset;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/*import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;*/

public class XML {

	private SAXBuilder builder;
	private File xmlFile;
	private Document document;
	private Element rootNode;

	public XML() {
		this("src/regelwerk.xml");
	}

	public XML(String xmlpath) {
		try {

			this.builder = new SAXBuilder();
			this.xmlFile = new File(xmlpath);
			if (!this.xmlFile.exists()) {
				throw new NullPointerException();
			}

			this.document = (Document) this.builder.build(this.xmlFile);
			this.rootNode = document.getRootElement();
		} catch (NullPointerException exc) {
			System.out.println("File nicht gefunden !");
		} catch (IOException exc) {
			System.out.println(exc.getMessage());
		} catch (JDOMException exc) {
			System.out.println(exc.getMessage());
		}

	}

	public Component buildTree() {
		return this.buildTree(this.rootNode);
	}

	public Component buildTree(Element activeNode) {
		Component activeElement = null;
		Component res;

		// System.out.println(activeNode.getTextNormalize());
		// System.out.println("---");

		if (activeNode.getName().equals("ruleset")) {
			activeElement = new Ruleset(activeNode.getAttributeValue("name"),
					activeNode.getAttributeValue("english"),
					activeNode.getTextNormalize(),
					activeNode.getChildTextNormalize("english"),
					Integer.parseInt(activeNode.getAttributeValue("order")));
		} else if (activeNode.getName().equals("rule")) {
			activeElement = new Rule(activeNode.getAttributeValue("name"),
					activeNode.getAttributeValue("english"),
					activeNode.getTextNormalize(),
					activeNode.getChildTextNormalize("english"),
					Integer.parseInt(activeNode.getAttributeValue("order")));

		} else if (activeNode.getName().equals("rules")) {
			activeElement = new Ruleset("rules", "rules");
		}

		Iterator<?> it = activeNode.getChildren().iterator();
		while (it.hasNext()) {
			res = this.buildTree((Element) it.next());

			if (activeElement instanceof Ruleset && res != null) {
				((Ruleset) activeElement).addChild(res);
			}
		}

		return activeElement;
	}

	public static void buildFromModel(Container root, BufferedWriter writer) {
		for (int i = root.getNumberOfChildren() - 1; i >= 0; i--) {
			try {
				writeEntity(root.getChild(i), writer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void writeEntity(Component node, BufferedWriter writer)
			throws IOException {
		writer.write(System.getProperty("line.separator"));

		String type = "rule";

		if (node instanceof Ruleset) {
			type = "ruleset";
		}

		writer.write("<" + type + " name=\"" + node.getName() + "\" english=\""
				+ node.getEnglishName() + "\" order=\"" + node.getOrder()
				+ "\">");
		writer.write(System.getProperty("line.separator"));
		String text = node.getText();
		// workaroudn für < lese fehler beim laden

		String tmp = text.replaceAll("<", "/!/");

		writer.write(tmp);
		writer.write(System.getProperty("line.separator"));
		writer.write("<english>");
		writer.write(System.getProperty("line.separator"));
		if (node.getEnglishDescription() != null) {
			writer.write(node.getEnglishDescription());
		}
		writer.write(System.getProperty("line.separator"));
		writer.write("</english>");

		if (node instanceof Ruleset) {
			Ruleset set = (Ruleset) node;

			for (int i = set.getNumberOfChildren() - 1; i >= 0; i--) {
				try {
					writeEntity(set.getChild(i), writer);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		writer.write(System.getProperty("line.separator"));
		writer.write("</" + type + ">");

	}
}
