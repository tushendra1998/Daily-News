package com.example.android.dailynews.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.dailynews.Model.CategoryDataModel;
import com.example.android.dailynews.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategoryDataModel> categoryDataModels;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public CategoryAdapter(ArrayList<CategoryDataModel> categoryDataModels, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryDataModels = categoryDataModels;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_view_item,parent,false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryDataModel categoryAdapter = categoryDataModels.get(position);
        holder.categoryType.setText(categoryAdapter.getCategory());
        Picasso.get().load(categoryAdapter.getCategoryImageUrl()).into(holder.categoryImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryDataModels.size();
    }

    public interface CategoryClickInterface {
        void onCategoryClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryType;
        private ImageView categoryImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryType = itemView.findViewById(R.id.CategoryText);
            categoryImage = itemView.findViewById(R.id.CategoryImage);
        }
    }
}
