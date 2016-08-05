package cc.soham.newsapplicationvodafone;

import java.util.List;

import cc.soham.newsapplicationvodafone.objects.Article;
import cc.soham.newsapplicationvodafone.objects.Source;

/**
 * Created by sohammondal on 05/08/16.
 */

public class CommonStuff {
    public static List<Article> articles;
    public static List<Source> sources;

    public static List<Article> getArticles() {
        return articles;
    }

    public static void setArticles(List<Article> articles) {
        CommonStuff.articles = articles;
    }

    public static List<Source> getSources() {
        return sources;
    }

    public static void setSources(List<Source> sources) {
        CommonStuff.sources = sources;
    }
}
