package com.example.android.dailynews.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.dailynews.Model.Articles;
import com.example.android.dailynews.Adapter.CategoryAdapter;
import com.example.android.dailynews.Model.CategoryDataModel;
import com.example.android.dailynews.Adapter.NewsFeedAdapter;
import com.example.android.dailynews.Model.NewsModel;
import com.example.android.dailynews.R;
import com.example.android.dailynews.RetrofitApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategoryClickInterface {
    //  9da6b3edeceb4d6f8e808d1aaeb5ba4b

    private RecyclerView newsFeedView,CategoryView;
    private ProgressBar loadProgressBar;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryDataModel> categoryDataModelArrayList;
    private CategoryAdapter categoryAdapter;
    private NewsFeedAdapter newsFeedAdapter;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsFeedView = findViewById(R.id.NewsFeedView);
        CategoryView = findViewById(R.id.CategoryView);
        loadProgressBar = findViewById(R.id.feedProgressBar);
        articlesArrayList = new ArrayList<>();
        categoryDataModelArrayList = new ArrayList<>();
        newsFeedAdapter = new NewsFeedAdapter(articlesArrayList,this);
        categoryAdapter = new CategoryAdapter(categoryDataModelArrayList,this,this::onCategoryClick);
        newsFeedView.setLayoutManager(new LinearLayoutManager(this));
        newsFeedView.setAdapter(newsFeedAdapter);
        CategoryView.setAdapter(categoryAdapter);
        getCategory();
        getNews("All");
        newsFeedAdapter.notifyDataSetChanged();

    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCategory()
    {
        categoryDataModelArrayList.add(new CategoryDataModel("All","https://images.unsplash.com/photo-1523461811963-7f1023caeddd?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2065&q=80"));
        categoryDataModelArrayList.add(new CategoryDataModel("Technology","https://images.unsplash.com/photo-1555949963-ff9fe0c870eb?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=870&q=80"));
        categoryDataModelArrayList.add(new CategoryDataModel("Science","https://images.unsplash.com/photo-1447433865958-f402f562b843?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=872&q=80"));
//        categoryDataModelArrayList.add(new CategoryDataModel("Sport","https://images.unsplash.com/photo-1575361204480-aadea25e6e68?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=871&q=80"));
        categoryDataModelArrayList.add(new CategoryDataModel("General","https://images.unsplash.com/photo-1562170142-04f38fb2827d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1031&q=80"));
        categoryDataModelArrayList.add(new CategoryDataModel("Business","https://images.unsplash.com/photo-1590283603385-17ffb3a7f29f?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80"));
        categoryDataModelArrayList.add(new CategoryDataModel("Entertainment","https://images.unsplash.com/photo-1574375927938-d5a98e8ffe85?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1169&q=80"));
        categoryDataModelArrayList.add(new CategoryDataModel("Health","https://images.unsplash.com/photo-1618498082410-b4aa22193b38?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80"));
        categoryAdapter.notifyDataSetChanged();
    }

    private void getNews(String category)
    {
        loadProgressBar.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryUrl = "https://newsapi.org/v2/top-headlines?country=in&category="+ category +"&apiKey=9da6b3edeceb4d6f8e808d1aaeb5ba4b";
        String Url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=9da6b3edeceb4d6f8e808d1aaeb5ba4b";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<NewsModel> call;
        if (category.equals("All"))
        {
            call = retrofitApi.getAllNews(Url);
        } else {
            call = retrofitApi.getNewsByCategory(categoryUrl);
        }

        call.enqueue(new Callback<NewsModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                loadProgressBar.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModel.getArticles();
                for (int i = 0; i <articles.size(); i++) {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(), articles.get(i).getContent()));
                }
                newsFeedAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Fail to get News",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category = categoryDataModelArrayList.get(position).getCategory();
        getNews(category);
    }
}