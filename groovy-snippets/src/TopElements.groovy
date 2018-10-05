// MIT License or WTFPL (your choice)

class Pair<K, V> {
   final K key
   final V value
   Pair(K key, V value) {
      this.key = key
      this.value = value
      }
   }

class TopElements<K, V> {

   private final TreeMap<Integer, List<V>> elemListMap =
      new TreeMap<Integer, List<V>>(Collections.reverseOrder())
   private final int maxElems
   private int numElems = 0

   TopElements(int maxNumberElements) {
      maxElems = maxNumberElements
      }

   void put(Integer key, V value) {
      if (numElems < maxElems || key >= elemListMap.lastKey()) {
         if (!elemListMap.containsKey(key))
            elemListMap.put(key, new ArrayList<V>())
         elemListMap.get(key).add(value)
         numElems = numElems + 1
         }
      int lastBucketSize = elemListMap.get(elemListMap.lastKey()).size()
      if (numElems - lastBucketSize >= maxElems) {
         elemListMap.remove(elemListMap.lastKey())
         numElems = numElems - lastBucketSize
         }
      }

   List<Pair<Integer, V>> getList() {
      List<Pair<Integer, V>>elems = new ArrayList<Pair<Integer, V>>()
      for (Map.Entry<Integer, List<V>> elemList : elemListMap.entrySet())
         for (V value : elemList.getValue())
            elems.add(new Pair<Integer, V>(elemList.getKey(), value))
      return elems
      }

   static void main(String[] args) {
      TopElements<Integer, String> islands = new TopElements<Integer, String>(3)
      islands.put( 507000, "Baffin")
      islands.put( 726000, "Borneo")
      islands.put(2131000, "Greenland")
      islands.put( 578000, "Madagascar")
      islands.put( 800000, "New Guinea")
      println("Three Largest Islands:")
      for (Pair<Integer, String> island : islands.getList())
         println(island.key + " sq km - " + island.value)
      }

   }
