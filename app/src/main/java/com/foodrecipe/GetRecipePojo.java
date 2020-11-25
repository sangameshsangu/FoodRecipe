package com.foodrecipe;

import com.google.gson.annotations.SerializedName;

public class GetRecipePojo {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GetRecipePojo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @SerializedName("description")
    private String description;

    public GetRecipePojo()
    {

    }
}



