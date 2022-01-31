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
import com.javimay.postsapp.data.model.Comment
import com.javimay.postsapp.data.model.Post


class CommentsRecyclerViewAdapter(
    private val commentsList: MutableList<Comment>,
    context: Context?) :
    RecyclerView.Adapter<CommentsRecyclerViewAdapter.MyViewHolder>() {
    private var mInflater: LayoutInflater = LayoutInflater.from(context)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = mInflater.inflate(R.layout.recycler_view_comments_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initBindHolder(commentsList[position], position)
    }

    override fun getItemCount() = commentsList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun initBindHolder(comment: Comment, position: Int) {
            val tvComment = itemView.findViewById<TextView>(R.id.tvComment)
            tvComment.text = comment.body
        }
    }
}