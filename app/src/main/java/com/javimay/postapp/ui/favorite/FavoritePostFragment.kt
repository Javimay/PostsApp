package com.javimay.postsapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.databinding.FragmentFavoritePostBinding
import com.javimay.postsapp.ui.adapters.PostsRecyclerViewAdapter
import com.javimay.postsapp.ui.home.HomeFragmentDirections
import com.javimay.postsapp.utils.toParcelable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritePostFragment : Fragment() {

    private val favoritePostViewModel:FavoritePostViewModel by viewModels()
    private lateinit var binding: FragmentFavoritePostBinding
    private lateinit var postsList: MutableList<Post>
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = this.findNavController()
        binding = FragmentFavoritePostBinding.inflate(inflater)
        downloadPost()
        return binding.root
    }

    private fun downloadPost() {
       val responsePosts = favoritePostViewModel.downloadFavoritePost()
        responsePosts.observe(viewLifecycleOwner){
            if (!it.isNullOrEmpty()){
                postsList = it.toMutableList()
                initRecycler()
            }
        }
    }

    private fun initRecycler() {
        val viewManager = LinearLayoutManager(context)
        val adapter = PostsRecyclerViewAdapter(postsList, context, true)
        adapter.onItemClick = {position ->
            goToContentPostFragment(postsList[position])
        }
        binding.rvFavorites.adapter = adapter
        binding.rvFavorites.layoutManager = viewManager
        binding.rvFavorites.setItemViewCacheSize(50)
    }

    private fun goToContentPostFragment(post: Post) {
        val postSafeArgs = HomeFragmentDirections.actionHomeFragmentToContentPostFragment(
            toParcelable(post)
        )
        navController.navigate(postSafeArgs)
    }
}