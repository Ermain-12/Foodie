package com.ermain.foodie.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ermain.foodie.model.Recipe

class RecipeAdapter(private val context: Context,
                    private val recipes: List<Recipe>
                    ) : RecyclerView.Adapter<ViewHolder>(), View.OnClickListener {

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}