// MIT License or WTFPL (your choice)

class TopMultiMap<K, V> extends TreeMap<Integer, V> {
   private final int maxElems
   private int minKey = Integer.MIN_VALUE + 1
   private static Comparator<Integer> topComp = new Comparator<Integer>() {
      int compare(Integer a, Integer b) { return a < b ? 1 : -1 } }
   TopMultiMap(int maxNumberElements) {
      super(topComp)
      maxElems = maxNumberElements
      }
   @Override
   V put(Integer key, V value) {
      // Runnable findNewMinKey = () -> {
      //    int elemNum = 0
      //    for (Map.Entry<Integer, V> entry : super.entrySet())
      //       if (elemNum++ < maxElems)
      //          minKey = entry.getKey()
      //    }
      // if (key >= minKey) {
      //    super.put(key, value)
      //    if (size() >= maxElems)
      //       findNewMinKey.run()
      //    }
      return value
      }
   @Override
   Set<Map.Entry<Integer, V>> entrySet() {
      return headMap(minKey - 1).entrySet()
      }

   static void main(String[] args) {
      TreeMap<Integer, String> islands = new TopMultiMap<Integer, String>(3)
      islands.put( 507000, "Baffin")
      islands.put( 726000, "Borneo")
      islands.put(2131000, "Greenland")
      islands.put( 578000, "Madagascar")
      islands.put( 800000, "New Guinea")
      println "Three Largest Islands:"
      for (Map.Entry<Integer, String> island : islands.entrySet())
         println island.getKey() + " sq km - " + island.getValue()
      }

   }
