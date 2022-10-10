// MIT License or WTFPL (your choice)

package example;

public abstract class DesktopBrowser {

   public static void main(String[] args) {
      String url = "https://dna-engine.org/";
      try {
         java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
         }
      catch (java.io.IOException e) {
         System.out.println(e.getMessage());
         }
      }

   }
