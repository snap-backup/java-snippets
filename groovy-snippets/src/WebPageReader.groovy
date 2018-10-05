// MIT License or WTFPL (your choice)

class WebPageReader {

   static void main(String[] args) {
      String url = "https://dnajs.org/rest/book/1/"
      System.out.println(url)
      try {
         InputStream stream = new URL(url).openStream()
         BufferedReader reader = new BufferedReader(new InputStreamReader(stream))
         for (String s = reader.readLine(); s != null; s = reader.readLine())
            println(s)
         reader.close()
         }
      catch (Exception e) {
         println(e.toString())
         }
      }

   }
