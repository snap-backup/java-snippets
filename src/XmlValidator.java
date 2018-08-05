// MIT License or WTFPL (your choice)

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.transform.stream.StreamSource;

public class XmlValidator {

   public static void main(String[] args) {
      try {
         StreamSource xsdInput = new StreamSource(new
            XmlValidator().getClass().getResourceAsStream("countries.xsd"));
         Schema schema = SchemaFactory.newInstance(
            XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(xsdInput);
         File xmlFile = new File("../data/countries.xml");
         schema.newValidator().validate(new StreamSource(xmlFile));
         System.out.println(xmlFile.getPath());
         System.out.println(new File("countries.xsd").getPath());
         System.out.println("XML file sucessfully validated against XSD.");
         }
      catch (Exception e) {
         System.out.println(e.getMessage());
         }
      }

   }
