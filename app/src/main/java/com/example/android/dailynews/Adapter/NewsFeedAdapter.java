package com.example.android.dailynews.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.dailynews.Activity.NewsDetailsActivity;
import com.example.android.dailynews.Model.Articles;
import com.example.android.dailynews.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.ViewHolder> {
    private ArrayList<Articles> articlesArrayList;
    private Context context;

    public NewsFeedAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsFeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_view_item,parent,false);
        return new NewsFeedAdapter.ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedAdapter.ViewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.SubNewTitle.setText(articles.getDescription());
        holder.NewsTitle.setText(articles.getTitle());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NewsDetailsActivity.class);
                i.putExtra("title",articles.getTitle());
                i.putExtra("content",articles.getContent());
                i.putExtra("desc",articles.getDescription());
                i.putExtra("image",articles.getUrlToImage());
                i.putExtra("url",articles.getUrl());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView NewsTitle;
        private TextView SubNewTitle;
        private ImageView newsImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NewsTitle = itemView.findViewById(R.id.newsFeedHeadingText);
            SubNewTitle = itemView.findViewById(R.id.newsFeedBriefIntro);
            newsImage = itemView.findViewById(R.id.newsFeedImageView);
        }
    }
}
