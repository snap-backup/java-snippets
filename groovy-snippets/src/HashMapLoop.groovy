// MIT License or WTFPL (your choice)

class HashMapLoop {

   static void main(String[] args) {
      HashMap<String, String> data = new HashMap<String, String>()
      data.put("Color", "Green")
      data.put("Size",  "Medium")
      data.put("Speed", "Fast")
      for (String key : data.keySet())
         println key + " --> " + data.get(key)
      }

   }
