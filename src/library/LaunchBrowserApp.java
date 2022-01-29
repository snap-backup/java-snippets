// MIT License or WTFPL (your choice)

package example;

import barebonesbrowserlaunch.BareBonesBrowserLaunch;

public abstract class LaunchBrowserApp {

   public static void main(String[] args) {
      String url = "https://dnajs.org/";
      System.out.println("Site: " + url);
      BareBonesBrowserLaunch.openURL(url);
      }

   }
