package com.javimay.postsapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.javimay.postsapp.R
import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.ui.callbacks.ItemTouchHelperAdapter
import java.util.*


class PostsRecyclerViewAdapter(
    private var postsList: MutableList<Post>,
    private val context: Context?,
    private val favorites: Boolean
) :RecyclerView.Adapter<PostsRecyclerViewAdapter.MyViewHolder>(), ItemTouchHelperAdapter {
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

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
       //No implementation needed
    }

    override fun onItemDismiss(position: Int) {
        postsList.removeAt(position)
        notifyItemRemoved(position)
        Toast.makeText(context, "item deleted", Toast.LENGTH_LONG).show()
    }
}