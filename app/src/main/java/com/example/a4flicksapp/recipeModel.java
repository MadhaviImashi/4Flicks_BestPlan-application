package com.example.a4flicksapp;

public class recipeModel {

    //declaring variables to hold details of the recipe temporarily
    private int id;
    private String Recipe_Name;
    private String Recipe_Ingredients;
    private String Recipe_Directions;
    private long started;
    private long finished;

    //Empty Constructor
    public recipeModel(){
    }

    //Constructor with id
    public recipeModel(int id, String recipe_Name, String recipe_Ingredients, String recipe_Directions, long started, long finished) {
        this.id = id;
        Recipe_Name = recipe_Name;
        Recipe_Ingredients = recipe_Ingredients;
        Recipe_Directions = recipe_Directions;
        this.started = started;
        this.finished = finished;
    }

    //Constructor without id
    public recipeModel(String recipe_Name, String recipe_Ingredients, String recipe_Directions, long started, long finished) {
        Recipe_Name = recipe_Name;
        Recipe_Ingredients = recipe_Ingredients;
        Recipe_Directions = recipe_Directions;
        this.started = started;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipe_Name() {
        return Recipe_Name;
    }

    public void setRecipe_Name(String recipe_Name) {
        Recipe_Name = recipe_Name;
    }

    public String getRecipe_Ingredients() {
        return Recipe_Ingredients;
    }

    public void setRecipe_Ingredients(String recipe_Ingredients) {
        Recipe_Ingredients = recipe_Ingredients;
    }

    public String getRecipe_Directions() {
        return Recipe_Directions;
    }

    public void setRecipe_Directions(String recipe_Directions) {
        Recipe_Directions = recipe_Directions;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
    
}
