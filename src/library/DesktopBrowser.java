// MIT License or WTFPL (your choice)

public class DesktopBrowser {

   public static void main(String[] args) {
      String url = "http://www.google.com";
      try {
         java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
         }
      catch (java.io.IOException e) {
         System.out.println(e.getMessage());
         }
      }

   }
