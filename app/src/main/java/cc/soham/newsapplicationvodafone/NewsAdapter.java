package cc.soham.newsapplicationvodafone;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import cc.soham.newsapplicationvodafone.objects.Article;

/**
 * Created by sohammondal on 04/08/16.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    List<Article> newsObjectsList;

    public NewsAdapter(List<Article> newsObjectsList) {
        this.newsObjectsList = newsObjectsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        if (newsObjectsList == null)
            return;
        Article currentArticle = newsObjectsList.get(position);
        holder.title.setText(currentArticle.getTitle());
        if (currentArticle.getPublishedAt() == null || currentArticle.getPublishedAt().equals(""))
            holder.date.setVisibility(View.GONE);
        else {
            holder.date.setVisibility(View.VISIBLE);
            holder.date.setText(currentArticle.getPublishedAt());
        }
        holder.description.setText(currentArticle.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Clicked position " + position, Toast.LENGTH_SHORT).show();
                DetailsActivity.start(view.getContext(), position);
            }
        });
        Glide.with(holder.newsImage.getContext()).load(currentArticle.getUrlToImage()).into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        if (newsObjectsList == null)
            return 0;
        return newsObjectsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        public ImageView newsImage;
        public TextView title;
        public TextView date;
        public TextView description;
        public CardView cardView;

        public NewsViewHolder(View itemView) {
            super(itemView);

            newsImage = (ImageView) itemView.findViewById(R.id.item_name_image);
            title = (TextView) itemView.findViewById(R.id.item_name_title);
            date = (TextView) itemView.findViewById(R.id.item_name_date);
            description = (TextView) itemView.findViewById(R.id.item_name_description);
            cardView = (CardView) itemView.findViewById(R.id.item_name_card);
        }
    }
}
