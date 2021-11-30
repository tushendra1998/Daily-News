package com.example.android.dailynews.Model;

public class CategoryDataModel {
    private String category;
    private String CategoryImageUrl;

    public CategoryDataModel(String category, String categoryImageUrl) {
        this.category = category;
        CategoryImageUrl = categoryImageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryImageUrl() {
        return CategoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        CategoryImageUrl = categoryImageUrl;
    }
}
