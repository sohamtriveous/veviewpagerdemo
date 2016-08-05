package cc.soham.newsapplicationvodafone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import cc.soham.newsapplicationvodafone.networking.NewsAPI;
import cc.soham.newsapplicationvodafone.objects.NewsApiArticleResponse;
import cc.soham.newsapplicationvodafone.objects.Source;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;

    int position = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(KEY_POSITION, DEFAULT_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.activity_main_newsitems);
        progressBar = (ProgressBar) view.findViewById(R.id.activity_main_progress);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Source source = CommonStuff.getSources().get(position);
        progressBar.setVisibility(View.VISIBLE);

        Call<NewsApiArticleResponse> responseCall = NewsAPI.getNewsAPI().getArticles(source.getId(), source.getSortBysAvailable().get(0));
        responseCall.enqueue(new Callback<NewsApiArticleResponse>() {
            @Override
            public void onResponse(Call<NewsApiArticleResponse> call, Response<NewsApiArticleResponse> response) {
                progressBar.setVisibility(View.GONE);
                NewsApiArticleResponse newsApiArticleResponse = response.body();
                CommonStuff.setArticles(newsApiArticleResponse.getArticles());
                NewsAdapter newsAdapter = new NewsAdapter(newsApiArticleResponse.getArticles());
                recyclerView.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(Call<NewsApiArticleResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private static final String KEY_POSITION = "position";
    private static final int DEFAULT_POSITION = 0;

    public static NewsFragment start(int position) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_POSITION, position);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }
}
