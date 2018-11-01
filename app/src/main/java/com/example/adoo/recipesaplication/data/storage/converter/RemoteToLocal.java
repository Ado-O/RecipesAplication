package com.example.adoo.recipesaplication.data.storage.converter;

import com.example.adoo.recipesaplication.data.Directions;
import com.example.adoo.recipesaplication.data.Ingredients;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.RecipesTag;
import com.example.adoo.recipesaplication.data.SubRecipe;
import com.example.adoo.recipesaplication.data.Tag;
import com.example.adoo.recipesaplication.data.storage.remote.response.DirectionsResponse;
import com.example.adoo.recipesaplication.data.storage.remote.response.IngredientsResponse;
import com.example.adoo.recipesaplication.data.storage.remote.response.RecipeResponse;
import com.example.adoo.recipesaplication.data.storage.remote.response.TagsResponse;
import com.example.adoo.recipesaplication.main.MainActivity;

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
                            r.getTime(),
                            r.getServings(),
                            r.getLevel(),
                            r.getCalorie(),
                            r.getCarbs(),
                            r.getProteins(),
                            r.getFats()
                    )
            );
            //if sub get only 5 recipes
            if (MainActivity.IS_SUB) {
                if (r.getId() == 5) {
                    break;
                }
            }
        }

        return recipes;
    }

    /**********
     * SubRecipes
     **********/
    public static List<SubRecipe> subRecipesConverter(List<RecipeResponse> recipesResponse) {
        List<SubRecipe> subRecipes = new ArrayList<>();

        for (RecipeResponse r : recipesResponse) {
            if (r.getId() == 1 || r.getId() == 2 || r.getId() == 3 ||
                    r.getId() == 4 || r.getId() == 5) {
               continue;
            } else {
                subRecipes.add(
                        new SubRecipe(
                                r.getId(),
                                r.getName(),
                                r.getImageUrl()
                        )
                );
            }
        }

        return subRecipes;
    }

    /***************
     * Ingredients
     **************/
    public static List<Ingredients> ingredientsConverter
    (List<RecipeResponse> recipeResponse) {
        List<Ingredients> ingredients = new ArrayList<>();

        for (RecipeResponse recipe : recipeResponse) {
            for (IngredientsResponse i : recipe.getIngredients()) {
                ingredients.add(new Ingredients(recipe.getId(), i.getName(), i.getAmount()));
            }
        }
        return ingredients;
    }

    /*************
     * Directions
     ************/
    public static List<Directions> directionsConverter(List<RecipeResponse> recipeResponse) {
        List<Directions> directions = new ArrayList<>();

        for (RecipeResponse recipe : recipeResponse) {
            for (DirectionsResponse des : recipe.getDirections()) {
                directions.add(new Directions(recipe.getId(), des.getNumber(), des.getDirections()));
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

}
