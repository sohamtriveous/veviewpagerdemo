package cc.soham.newsapplicationvodafone.networking;

import cc.soham.newsapplicationvodafone.objects.NewsApiArticleResponse;
import cc.soham.newsapplicationvodafone.objects.NewsApiSourcesResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sohammondal on 05/08/16.
 */

public class NewsAPI {
    private static final String API_KEY = "31e057047a5d4ce0bb7c68cf3536d80b";
    private static final String BASE_URL = "https://newsapi.org/v1/";

    private static NewsAPIInterface newsAPIInterface;

    public static NewsAPIInterface getNewsAPI() {
        if(newsAPIInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsAPIInterface = retrofit.create(NewsAPIInterface.class);
        }
        return newsAPIInterface;
    }

    public interface NewsAPIInterface {
        @GET("articles?apiKey=" + API_KEY)
        Call<NewsApiArticleResponse> getArticles(@Query("source") String source, @Query("sortBy") String sortBy);

        @GET("sources")
        Call<NewsApiSourcesResponse> getSources();
    }
}
