package cc.soham.newsapplicationvodafone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import cc.soham.newsapplicationvodafone.networking.NewsAPI;
import cc.soham.newsapplicationvodafone.objects.NewsApiSourcesResponse;
import cc.soham.newsapplicationvodafone.objects.Source;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourcesActivity extends AppCompatActivity {
    ViewPager viewPager;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);

        viewPager = (ViewPager) findViewById(R.id.activity_sources_viewpager);
        progressBar = (ProgressBar) findViewById(R.id.activity_source_progress);

        progressBar.setVisibility(View.VISIBLE);

        Call<NewsApiSourcesResponse> responseCall = NewsAPI.getNewsAPI().getSources();
        responseCall.enqueue(new Callback<NewsApiSourcesResponse>() {
            @Override
            public void onResponse(Call<NewsApiSourcesResponse> call, Response<NewsApiSourcesResponse> response) {
                progressBar.setVisibility(View.GONE);
                List<Source> source = response.body().getSources();
                CommonStuff.setSources(source);
                MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(), source);
                viewPager.setAdapter(myAdapter);
                Toast.makeText(SourcesActivity.this, "response received", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewsApiSourcesResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SourcesActivity.this, "error received", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static class MyAdapter extends FragmentStatePagerAdapter {
        List<Source> sources;

        public MyAdapter(FragmentManager fm, List<Source> sources) {
            super(fm);
            this.sources = sources;
        }

        @Override
        public Fragment getItem(int position) {
            return NewsFragment.start(position);
        }

        @Override
        public int getCount() {
            return sources.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sources.get(position).getName();
        }
    }
}
