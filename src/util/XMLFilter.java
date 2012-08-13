package util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class XMLFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }

        if (f.isDirectory()) {
            return true;
        }

        if (ext.equals("xml")) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String getDescription() {
        return "Es k�nnen nur XML Daten geladen werden";
    }
}
