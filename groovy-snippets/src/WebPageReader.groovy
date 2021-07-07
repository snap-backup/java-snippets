// MIT License or WTFPL (your choice)

class WebPageReader {

   static void main(String[] args) {
      String url = "https://dnajs.org/api/books/1/"
      System.out.println(url)
      try {
         InputStream stream = new URL(url).openStream()
         BufferedReader reader = new BufferedReader(new InputStreamReader(stream))
         for (String line = reader.readLine(); line != null; line = reader.readLine())
            println(line)
         reader.close()
         }
      catch (Exception e) {
         println(e.toString())
         }
      }

   }
