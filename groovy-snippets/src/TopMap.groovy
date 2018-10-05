// MIT License or WTFPL (your choice)

class TopMap<K, V> extends TreeMap<Integer, V> {

   private final int maxElems
   TopMap(int maxNumberElements) {
      super(Collections.reverseOrder())
      maxElems = maxNumberElements
      }
   @Override
   V put(Integer key, V value) {
      //while (containsKey(key)) key = key + 1  //optional hack
      if (size() < maxElems || key > lastKey())
         super.put(key, value)
      if (size() > maxElems)
         remove(lastKey())
      return value
      }

   static void main(String[] args) {
      TreeMap<Integer, String> islands = new TopMap<Integer, String>(3)
      islands.put( 507000, "Baffin")
      islands.put( 726000, "Borneo")
      islands.put(2131000, "Greenland")
      islands.put( 578000, "Madagascar")
      islands.put( 800000, "New Guinea")
      println("Three Largest Islands:")
      for (Map.Entry<Integer, String> island : islands.entrySet())
         println(island.getKey() + " sq km - " + island.getValue())
      }

   }
