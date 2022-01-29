// MIT License or WTFPL (your choice)

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.*
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import org.w3c.dom.*

class XmlWriter {

   static void main(String[] args) {
      try {

         // Sample Data (countries and capitals)
         HashMap<String, String> data = new HashMap<String, String>()
         data.put("Egypt",   "Cairo")
         data.put("Finland", "Helsinki")
         data.put("Japan",   "Tokyo")

         // Create DOM (with top-level node)
         DocumentBuilder xmlBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
         Document xmlDoc = xmlBuilder.newDocument()
         Node node = xmlDoc.createElement("countries")
         xmlDoc.appendChild(node)  //top-level node

         // Add Data to DOM
         for (String key : data.keySet()) {
            Element elem = xmlDoc.createElement("country")
            elem.setAttribute("capital", data.get(key))   //attrib
            elem.appendChild(xmlDoc.createTextNode(key))  //content
            node.appendChild(elem)
            }

         // Write DOM to XML file
         Source source = new DOMSource(xmlDoc)
         Result result = new StreamResult(new File("output-countries.xml"))
         Transformer xformer = TransformerFactory.newInstance().newTransformer()
         xformer.setOutputProperty(OutputKeys.INDENT, "yes")
         xformer.transform(source, result)

         //Print out XML file contents
         File xmlFile = new File("output-countries.xml")
         Scanner input = new Scanner(xmlFile)
         System.out.println(xmlFile.getPath())
         while (input.hasNextLine())
            System.out.println(input.nextLine())
         input.close()

         }
      catch (Exception e) {
         System.out.println(e.getMessage())
         }
      }

   }
