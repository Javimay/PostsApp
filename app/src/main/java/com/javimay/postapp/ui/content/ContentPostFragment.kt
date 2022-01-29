package com.javimay.postsapp.ui.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.javimay.postsapp.R
import com.javimay.postsapp.data.model.Comment
import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.data.model.parcels.PostParcel
import com.javimay.postsapp.databinding.FragmentContentPostBinding
import com.javimay.postsapp.ui.adapters.CommentsRecyclerViewAdapter
import com.javimay.postsapp.utils.toPost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentPostFragment : Fragment() {

    private lateinit var binding: FragmentContentPostBinding
    private lateinit var selectedPost: Post
    private val contentPostViewModel:ContentPostViewModel by viewModels()
    private lateinit var commentsList: MutableList<Comment>
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = this.findNavController()
        binding = FragmentContentPostBinding.inflate(inflater)
        val postParcel = arguments?.getParcelable<PostParcel>("Post")
        selectedPost = postParcel?.let { toPost(it) }!!
        binding.tvBody.text = selectedPost.body
        binding.tvBody.text = selectedPost.body
        downloadComments(selectedPost.id)
        getUser(selectedPost.id)
        selectedPost.read = false
        updatePost(selectedPost)
        binding.ibPostFavorite.setOnClickListener {
            selectedPost.favorite = true
            updatePost(selectedPost)
            Toast.makeText(context, getString(R.string.txt_save_post_as_favorite),Toast.LENGTH_LONG).show()
        }
        binding.ibBack.setOnClickListener {
            navController.popBackStack()
        }
        return binding.root
    }

    private fun updatePost(post: Post) {
        contentPostViewModel.updatePost(post)
    }

    private fun downloadComments(postId: Int) {
        val commentsResponse = contentPostViewModel.getCommentsByPost(postId)
        commentsResponse.observe(viewLifecycleOwner){
            if (!it.isNullOrEmpty()){
                commentsList = it.toMutableList()
                initRecycler()
            }
        }
    }

    private fun getUser(userId: Int) {
        val responseUser = contentPostViewModel.getUserById(userId)
        responseUser.observe(viewLifecycleOwner){
            if (it != null){
                binding.tvUser.text = "Name: ${it.name}" +
                        "\nEmail: ${it.email}" +
                        "\nPhone: ${it.phone}" +
                        "\nWebsite: ${it.website}"

            }
        }
    }

    private fun initRecycler() {
        val viewManager = LinearLayoutManager(context)
        val adapter = CommentsRecyclerViewAdapter(commentsList, context)

        binding.rvComments.adapter = adapter
        binding.rvComments.layoutManager = viewManager
        binding.rvComments.setItemViewCacheSize(5)
    }


}