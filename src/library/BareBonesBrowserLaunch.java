//package com.centerkey.utils;
package barebonesbrowserlaunch;

import javax.swing.JOptionPane;
import java.util.Arrays;

/**
 * <b>Bare Bones Browser Launch for Java</b><br>
 * Utility class to open a web page from a Swing application
 * in the user's default browser.<br>
 * Supports: Mac OS X, GNU/Linux, Unix, Windows XP/Vista<br>
 * Example Usage:<code><br> &nbsp; &nbsp;
 *    String url = "https://dna-engine.org/";<br> &nbsp; &nbsp;
 *    BareBonesBrowserLaunch.openURL(url);<br></code>
 * Latest Version: <a href="https://centerkey.com/java/browser/">centerkey.com/java/browser</a><br>
 * Author: Dem Pilafian<br>
 * Public Domain Software -- Free to Use as You Like
 * @version 3.0, February 7, 2010
 */
public class BareBonesBrowserLaunch {

   static final String[] browsers = { "google-chrome", "firefox", "opera",
      "konqueror", "epiphany", "seamonkey", "galeon", "kazehakase", "mozilla" };
   static final String errMsg = "Error attempting to launch web browser";

   /**
    * Opens the specified web page in the user's default web browser
    * @param url A web address (URL) of a web page (ex: "https://dna-engine.org/")
    */
   public static void openURL(String url) {
      try {  //attempt to use Desktop library from JDK 1.6+ (even if on 1.5)
         Class<?> d = Class.forName("java.awt.Desktop");
         d.getDeclaredMethod("browse", new Class[] {java.net.URI.class}).invoke(
            d.getDeclaredMethod("getDesktop").invoke(null),
            new Object[] {java.net.URI.create(url)});
         // Above code mimicks:
         //    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
         }
      catch (Exception ignore) {  //library not available or failed
         String osName = System.getProperty("os.name");
         try {
            if (osName.startsWith("Mac OS")) {
               Class.forName("com.apple.eio.FileManager").getDeclaredMethod(
                  "openURL", new Class[] {String.class}).invoke(null,
                  new Object[] {url});
               }
            else if (osName.startsWith("Windows"))
               Runtime.getRuntime().exec(
                  "rundll32 url.dll,FileProtocolHandler " + url);
            else {  //assume Unix or Linux
               int b = 0;
               while (b < browsers.length && Runtime.getRuntime().exec(
                     new String[] {"which", browsers[b]}).waitFor() != 0)
                  b++;
               if (b < browsers.length)
                  Runtime.getRuntime().exec(new String[] {browsers[b], url});
               else
                  throw new Exception(Arrays.toString(browsers));
               /*
               boolean found = false;
               for (String browser : browsers)
                  if (!found) {
                     found = Runtime.getRuntime().exec(
                        new String[] {"which", browser}).waitFor() == 0;
                     if (found)
                        Runtime.getRuntime().exec(new String[] {browser, url});
                     }
               if (!found)
                  throw new Exception(Arrays.toString(browsers));
               */
               }
            }
         catch (Exception e) {
            JOptionPane.showMessageDialog(null, errMsg + "\n" + e.toString());
            }
         }
      }

   }
