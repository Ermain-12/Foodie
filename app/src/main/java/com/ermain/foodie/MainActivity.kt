package com.ermain.foodie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.ermain.foodie.adapter.RecipeAdapter
import com.ermain.foodie.model.Recipe
import okhttp3.Headers
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    private val LogTag = "MainActivity/."
    private lateinit var recipeValue: EditText
    private lateinit var searchButton: Button
    private val recipes = mutableListOf<Recipe>()
    private lateinit var recyclerViewRecipes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recipeValue = findViewById(R.id.editTextRecipeName)
        searchButton = findViewById(R.id.buttonSearchRecipe)

        val recipeAdapter = RecipeAdapter(this, recipes)
        recyclerViewRecipes.adapter = recipeAdapter
        recyclerViewRecipes.layoutManager = LinearLayoutManager(this)

        val client = AsyncHttpClient()

        searchButton.setOnClickListener {
            client.get("https://www.themealdb.com/api/json/v1/1/search.php?s=${recipeValue.text}",
                object : JsonHttpResponseHandler() {
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String?,
                        throwable: Throwable?
                    ) {
                        Log.i(LogTag, "onFailure(): Error fetching API data: $statusCode")
                    }

                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                        Log.i(LogTag, "onSuccess(): Successfully fetched data")
                        try {
                            Log.i(LogTag, "${json?.jsonObject?.getJSONArray("meals")}")
                            val recipeJSONArray = json?.jsonObject?.getJSONArray("meals")
                            if (recipeJSONArray != null) {
                                recipes.addAll(Recipe.fromJsonArray(recipeJSONArray))
                            }
                        } catch (e: JSONException) {
                            Log.e(LogTag, "Error fetching resource")
                        }
                    }
                })
        }
    }
}