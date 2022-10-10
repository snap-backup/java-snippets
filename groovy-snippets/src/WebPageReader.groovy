// MIT License or WTFPL (your choice)

class WebPageReader {

   static void main(String[] args) {
      String url = "https://dna-engine.org/api/books/1/"
      println url
      try {
         InputStream stream = new URL(url).openStream()
         BufferedReader reader = new BufferedReader(new InputStreamReader(stream))
         for (String line = reader.readLine(); line != null; line = reader.readLine())
            println line
         reader.close()
         stream.close()
         }
      catch (Exception e) {
         println e.toString()
         }
      }

   }
