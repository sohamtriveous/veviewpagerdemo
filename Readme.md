#things to do


- Remove NewsObjects
- Create CommonStuff.java and add a static List<Articles> there, create getters and setters
```java
  public class CommonStuff {
      public static List<Article> articles;

      public static List<Article> getArticles() {
          return articles;
      }

      public static void setArticles(List<Article> articles) {
          CommonStuff.articles = articles;
      }
  }
```
- Update Adapter to reflect the following:
  - update the list from NewsObjects to Article
  - update constructor
  - update all the places where we access the NewsObject
- When a response comes in MainActivity, do the following
  - store it in CommonStuff
  - create the adapter with the List<Article> we receive from the response like so:
```java
  NewsApiArticleResponse newsApiArticleResponse = response.body();
  CommonStuff.setArticles(newsApiArticleResponse.getArticles());
  NewsAdapter newsAdapter = new NewsAdapter(newsApiArticleResponse.getArticles());
```
  - load the recyclerview like before
  - show the progressbar before the loading begins
  - hide it once the response is received
- Update DetailsActivity like so:
  - get the List<Articles> instead of List<NewsObjects> from CommonStuff
  - update NewsObjects references to Article
