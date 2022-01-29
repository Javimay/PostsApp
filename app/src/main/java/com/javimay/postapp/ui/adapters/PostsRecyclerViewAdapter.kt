package com.javimay.postsapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.javimay.postsapp.R
import com.javimay.postsapp.data.model.Post


class PostsRecyclerViewAdapter(
    private var postsList: MutableList<Post>,
    private val context: Context?,
    private val favorites: Boolean
) :
    RecyclerView.Adapter<PostsRecyclerViewAdapter.MyViewHolder>() {
    var onItemClick: ((position:Int) -> Unit)? = null
    private var mInflater: LayoutInflater = LayoutInflater.from(context)

init {
    if (favorites){
        val (favoriteList,_) = postsList.partition { it.favorite }
        postsList = favoriteList.toMutableList()
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = mInflater.inflate(R.layout.recycler_view_posts_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initBindHolder(postsList[position], position)
    }

    override fun getItemCount() = postsList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun initBindHolder(post: Post, position: Int) {
            val tvPostMessage = itemView.findViewById<TextView>(R.id.tvPostMessage)
            tvPostMessage.text = post.title
            val ibFavorite = itemView.findViewById<ImageButton>(R.id.ibFavorite)
            val ivDotNotification = itemView.findViewById<ImageView>(R.id.ivDotNotification)
            if (post.read){
                ivDotNotification.visibility = View.VISIBLE
            } else{
                ivDotNotification.visibility = View.GONE
            }
            if (post.favorite){
                ibFavorite.visibility = View.VISIBLE
            }else{
                ibFavorite.visibility = View.GONE
            }
            itemView.setOnClickListener {
                ivDotNotification.visibility = View.GONE
                post.read = false
                onItemClick?.invoke(position)
            }
        }
    }
}