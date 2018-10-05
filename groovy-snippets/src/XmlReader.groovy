// MIT License or WTFPL (your choice)

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class XmlReader {

   static void processNode(Node node) {
      String msg = "node --> " + node.getNodeName();
      String nodeContent = node.getFirstChild().getNodeValue().trim();
      if (!nodeContent.isEmpty())
         msg = msg + " | value: " + nodeContent; //add text content
      if (node.hasAttributes())  //add first attribute if it exists
         msg = msg + " | attribute: " +
            node.getAttributes().item(0).getNodeName() + "=" +
            node.getAttributes().item(0).getNodeValue();
      System.out.println(msg);  //display node information
      for (Node subNode = node.getFirstChild(); subNode != null;
            subNode = subNode.getNextSibling())
         if (subNode.getNodeType() == Node.ELEMENT_NODE)
            processNode(subNode);
      }

   public static void main(String[] args) {
      File xmlFile = new File("../data/countries.xml");
      System.out.println(xmlFile.getPath());
      try {
         DocumentBuilder xmlBuilder =
            DocumentBuilderFactory.newInstance().newDocumentBuilder();
         Document xmlDoc = xmlBuilder.parse(xmlFile);
         processNode(xmlDoc.getDocumentElement());
         }
      catch (Exception e) {
         System.out.println(e.getMessage());
         }
      }

   }
