#things to do

- Create a new Activity, call it SourcesActivity (Use New>Activity>Empty Activity)
- Also give change its intent-filter to Main and Launcher, so that it launches when the app is opened
- Write the retrofit API definition for the sources endpoint, which includes
  - getting a sample json response from newsapi.org
  - validating it in jsoneditoronline
  - creating the object (described before) in jsonshcematopojo
  - import the objects in our app
  - write a new method in our NewsAPI interface like so:
```java
      @GET("sources")
      Call<NewsApiSourcesResponse> getSources();
```
- Add support for storing and retrieving the Sources in CommonStuff
```java
    public static List<Source> sources;
    public static List<Source> getSources() {
        return sources;
    }
    public static void setSources(List<Source> sources) {
        CommonStuff.sources = sources;
    }
```
- Create "SourceAdapter" for our SourceActivity
  - create a view for our sources (image and name)
  - create the corresponding viewholders
  - create the List<Source> in the class along with the constructor
  - implement all the methods (onBindViewHolder, onCreateViewHolder(ViewGroup parent, int viewType), getItemCount()) like before
  - once you click on the linearlayout (the root view for every item), launch MainActivity, make sure to pass the position of the item clicked
- Create a layout for MainActivity which will have a progressbar and recyclerview like mainactivity
- MainActivity will make a retrofit network call for all the sources
  - once you receive the response, create the adapter and populate the recyclerview
  - also store the source list in CommonStuff
- In MainActivity
  - retrieve the position passed from the SourceAdapter
  - retrieve the Source at that position from CommonStuff
  - update the original NewsAPI article call with the name and sortBy information from that source like so:
```java
  Call<NewsApiArticleResponse> responseCall = NewsAPI.getNewsAPI().getArticles(source.getId(), source.getSortBysAvailable().get(0));
```
  - This is because each source only supports a certain type of sortBy functionality and we just choose the first one