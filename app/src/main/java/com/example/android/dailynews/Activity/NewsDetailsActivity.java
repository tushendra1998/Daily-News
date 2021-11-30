package com.example.android.dailynews.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.dailynews.R;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class NewsDetailsActivity extends AppCompatActivity {
    String title,desc,content,imageUrl,url;
    private TextView titleView,subDescView,contentView;
    private ImageView newsImageView;
    private Button readNewsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        imageUrl = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");
        titleView = findViewById(R.id.idTVTitle);
        subDescView = findViewById(R.id.idSubDesc);
        contentView = findViewById(R.id.idContent);
        newsImageView = findViewById(R.id.idIVNews);
        readNewsBtn = findViewById(R.id.readButton);
        titleView.setText(title);
        subDescView.setText(desc);
        contentView.setText(content);
        Picasso.get().load(imageUrl).into(newsImageView);
        readNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}