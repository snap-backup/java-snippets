// MIT License or WTFPL (your choice)

package javasnippets;

import java.io.*;
import java.net.*;

public class WebPageReader {

   public static void main(String[] args) {
      String url = "https://dnajs.org/rest/book/1/";
      System.out.println(url);
      try {
         InputStream stream = new URL(url).openStream();
         BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
         for (String line = reader.readLine(); line != null; line = reader.readLine())
            System.out.println(line);
         reader.close();
         }
      catch (Exception e) {
         System.out.println(e.toString());
         }
      }

   }
