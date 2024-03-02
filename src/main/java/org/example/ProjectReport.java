package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import jdepend.framework.JDepend;
import jdepend.framework.JavaPackage;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ProjectReport {

  public void reportProject(String path) {
    JDepend jDepend = new JDepend();
    try {
      jDepend.addDirectory(path);
      jDepend.analyze();
      Collection<JavaPackage> packages = jDepend.getPackages();

      Document document = new Document();
      Element rootElement = new Element("projectReport");
      document.setRootElement(rootElement);

      packages.forEach(javaPackage -> {
        Element element = new Element("package");
        element.setAttribute("name", javaPackage.getName());
        javaPackage.getEfferents().forEach(depen -> {
          Element dependency = new Element("dependency");
          dependency.setText(depen.getName());
          element.addContent(dependency);
        });
        rootElement.addContent(element);
      });

      XMLOutputter xmlOutputter = new XMLOutputter();
      xmlOutputter.setFormat(Format.getPrettyFormat());
      xmlOutputter.output(document, new FileWriter("project_report.xml"));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
