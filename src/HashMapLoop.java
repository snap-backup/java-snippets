// MIT License or WTFPL (your choice)

package javasnippets;

import java.util.HashMap;

public class HashMapLoop {

   public static void main(String[] args) {
      HashMap<String, String> data = new HashMap<String, String>();
      data.put("Color", "Green");
      data.put("Size",  "Medium");
      data.put("Speed", "Fast");
      for (String key : data.keySet())
         System.out.println(key + " --> " + data.get(key));
      }

   }
