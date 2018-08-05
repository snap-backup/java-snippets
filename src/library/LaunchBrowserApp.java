// MIT License or WTFPL (your choice)

package barebonesbrowserlaunch;

public class LaunchBrowserApp {

   public static void main(String[] args) {
      String url = "http://www.google.com/";
      System.out.println("Site: " + url);
      BareBonesBrowserLaunch.openURL(url);
      }

   }
