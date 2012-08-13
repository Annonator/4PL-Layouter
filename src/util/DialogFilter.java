package util;

import java.io.File;

public class DialogFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File pathname) {
        String name = pathname.getName();

        if (pathname.isDirectory()) {
            return true;
        }

        if (name.endsWith(".xml")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "Filter .xml extensions";
    }
}
