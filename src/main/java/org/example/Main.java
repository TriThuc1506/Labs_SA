package org.example;

import jdepend.framework.PackageFilter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("results.xml"),true))
        {

            jdepend.xmlui.JDepend xml = new jdepend.xmlui.JDepend(out);
            xml.addDirectory("D:\\SA\\Library-Assistant-master");

            PackageFilter f = PackageFilter.all();
//            f.including("vn.edu.iuh");
            f.accept("vn.edu");
            f.excluding("org");
            xml.setFilter(f);

            xml.analyze();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
