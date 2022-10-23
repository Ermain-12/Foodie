package com.ermain.foodie.model

import android.os.Parcelable
import org.json.JSONArray


data class Recipe(
    val mealName: String,

    val mealCategory: String,

    val mealArea: String,

    val mealInstructions: String,

    val mealImage: String
) {
    companion object {
        fun fromJsonArray(recipeJsonArray: JSONArray): List<Recipe> {
            val recipes: MutableList<Recipe> = mutableListOf()

            for (i in 0 until recipeJsonArray.length()) {
                val recipeJson = recipeJsonArray.getJSONObject(i)
                recipes.add(
                    Recipe(
                        recipeJson.getString("strMeal"),
                        recipeJson.getString("strCategory"),
                        recipeJson.getString("strArea"),
                        recipeJson.getString("strInstructions"),
                        recipeJson.getString("strMealThumb")
                    )
                )
            }
            return recipes
        }
    }
}