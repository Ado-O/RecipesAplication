package com.example.adoo.recipesaplication.data.storage.converter;

import android.util.Log;

import com.example.adoo.recipesaplication.data.Directions;
import com.example.adoo.recipesaplication.data.Ingredients;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.RecipesTag;
import com.example.adoo.recipesaplication.data.Suggested;
import com.example.adoo.recipesaplication.data.Tag;
import com.example.adoo.recipesaplication.data.storage.remote.response.BaseResponse;
import com.example.adoo.recipesaplication.data.storage.remote.response.IngredientsResponse;
import com.example.adoo.recipesaplication.data.storage.remote.response.RecipeResponse;
import com.example.adoo.recipesaplication.data.storage.remote.response.TagsResponse;

import java.util.ArrayList;
import java.util.List;

public class RemoteToLocal {

    private static final String TAG = RemoteToLocal.class.getSimpleName();

    /**********
     * Recipes
     **********/
    public static List<Recipe> recipesConverter(List<RecipeResponse> recipesResponse) {
        List<Recipe> recipes = new ArrayList<>();

        for (RecipeResponse r : recipesResponse) {

            recipes.add(
                    new Recipe(
                            r.getId(),
                            r.getName(),
                            r.getImageUrl(),
                            r.getInfo(),
                            r.getTime(),
                            r.getCalorie(),
                            r.getCarbs(),
                            r.getFats(),
                            r.getProteins(),
                            r.getLevel(),
                            r.getServings()
                    )
            );
        }
        return recipes;
    }

    /***************
     * Ingredients
     **************/
    public static List<Ingredients> ingredientsConverter(List<RecipeResponse>recipeResponse,
                                                         List<IngredientsResponse> ingredientsResponses) {
        List<Ingredients> ingredients = new ArrayList<>();

        for (RecipeResponse mid : recipeResponse) {
            for (IngredientsResponse i : ingredientsResponses) {
                ingredients.add(new Ingredients(mid.getId(), i.getName(), i.getAmount()));
            }
        }
        return ingredients;
    }

    /*************
     * Directions
     ************/
    public static List<Directions> directionsConverter(List<RecipeResponse> recipeResponse,
                                                       List<String> description) {
        List<Directions> directions = new ArrayList<>();

        for (RecipeResponse mid : recipeResponse) {
            for (String des : description) {
                directions.add(new Directions(mid.getId(), des));
            }
        }
        return directions;
}

    /*************
     * RecipesTag
     ************/
    public static List<RecipesTag> recipesTagsConvertor(long recipesId, List<Integer> tags) {
        List<RecipesTag> recipesTags = new ArrayList<>();

        for (long tagId : tags) {
            recipesTags.add(
                    new RecipesTag(
                            recipesId,
                            tagId
                    )
            );
        }

        return recipesTags;
    }

    /*******
     * Tag
     *******/
    public static List<Tag> tagConverter(List<TagsResponse> tagsResponses) {
        List<Tag> tags = new ArrayList<>();

        for (TagsResponse t : tagsResponses) {
            tags.add(
                    new Tag(
                            t.getId(),
                            t.getName()
                    )
            );
        }
        return tags;
    }

    /************
     * Suggested
     ************/
    public static List<Suggested> suggestedConverter(BaseResponse baseResponse) {

        List<Suggested> suggesteds = new ArrayList<>();

        for (int recipeid : baseResponse.getData().getSuggested()) {
            suggesteds.add(new Suggested(recipeid));

        }
        return suggesteds;

    }

}
