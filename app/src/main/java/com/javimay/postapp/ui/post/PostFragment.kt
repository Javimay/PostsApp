package com.javimay.postsapp.ui.post

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.javimay.postsapp.R
import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.data.model.User
import com.javimay.postsapp.databinding.FragmentPostBinding
import com.javimay.postsapp.ui.adapters.PostsRecyclerViewAdapter
import com.javimay.postsapp.ui.home.HomeFragmentDirections
import com.javimay.postsapp.utils.toParcelable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding

    private lateinit var navController: NavController
    private val postFragmentViewModel: PostFragmentViewModel by viewModels()
    private lateinit var usersList: MutableList<User>
    private lateinit var postsList: MutableList<Post>
    private var observer = Observer<Boolean>{postFragmentViewModel.loading}
    private lateinit var alertMessage: AlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            updatePosts()
        }
        binding = FragmentPostBinding.inflate(inflater)
        navController = this.findNavController()
        binding.rvPosts.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        alertMessage = AlertDialog.Builder(requireContext())
        binding.srlRefresh.setOnRefreshListener {
            downloadPosts()
        }
        postFragmentViewModel.loading.observe(viewLifecycleOwner){
            showLoading(it)
        }
        binding.fabDeleteAllPosts.setOnClickListener {
            showAlert()

        }
        createAlertMessage()
        downloadUsers()
        return binding.root
    }

    private fun createAlertMessage() {
        alertMessage.setTitle(resources.getString(R.string.txt_alert_title_delete))
            .setMessage(resources.getString(R.string.txt_alert_message_delete))
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
            .setPositiveButton("I'm sure", DialogInterface.OnClickListener { dialog, _ ->
                deleteAllPosts()
                showToast("Deleting post...")
                dialog.cancel()
            })
            .setCancelable(false)
    }

    private fun deleteAllPosts() {
        postFragmentViewModel.deleteAllPosts()
            binding.rvPosts.adapter.let { recyclerView ->
                postsList.clear()
                recyclerView?.notifyDataSetChanged()
                showRefreshInfo(true)
            }
            Log.e("posts","Postlist: ${postsList.size}")
    }

    private fun showRefreshInfo(show: Boolean) {
        if (show){
            binding.rvPosts.visibility = View.GONE
            binding.fabDeleteAllPosts.visibility = View.GONE
            binding.tvRefresInfo.visibility = View.VISIBLE
        }else{
            binding.rvPosts.visibility = View.VISIBLE
            binding.fabDeleteAllPosts.visibility = View.VISIBLE
            binding.tvRefresInfo.visibility = View.GONE
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun showAlert() {
        alertMessage.create().show()
    }

    override fun onResume() {

        super.onResume()
    }
    private fun showLoading(show: Boolean) {
        if (show){
            binding.pbLoading.visibility = View.VISIBLE
            binding.flPostContainer.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.black_transparent,null))
        }else{
            binding.pbLoading.visibility = View.GONE
            binding.flPostContainer.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun downloadUsers() {
        val responseGetUsers = postFragmentViewModel.getUsers()
        responseGetUsers.observe(viewLifecycleOwner){
            if (it != null)
            usersList = it.toMutableList()
            downloadPosts()
            downloadComments()
        }
    }

    private fun downloadComments() {
         postFragmentViewModel.getComments()
    }

    private fun downloadPosts() {
        val responseGetPosts = postFragmentViewModel.getPosts()
        responseGetPosts.observe(viewLifecycleOwner){
            if (it != null) {
                postsList = it.toMutableList()
                initRecycler()
            }
        }
    }

    private fun updatePosts() {
        val responseGetPosts = postFragmentViewModel.getPosts()
        responseGetPosts.observe(viewLifecycleOwner){
            if (it != null) {
                postsList = it.toMutableList()
                initRecycler()
            }
        }
    }

    private fun initRecycler() {
        val viewManager = LinearLayoutManager(context)
        val adapter = PostsRecyclerViewAdapter(postsList, context, false)
        adapter.onItemClick = {position ->
            goToContentPostFragment(postsList[position])
        }
        binding.rvPosts.adapter = adapter
        binding.rvPosts.layoutManager = viewManager
        binding.rvPosts.setItemViewCacheSize(50)
        showLoading(false)
        showRefreshInfo(false)
        binding.srlRefresh.isRefreshing = false
    }

    private fun goToContentPostFragment(post: Post) {
        val postSafeArgs = HomeFragmentDirections.actionHomeFragmentToContentPostFragment(
            toParcelable(post)
        )
        navController.navigate(postSafeArgs)
    }

    override fun onDestroy() {
        super.onPause()
        postFragmentViewModel.loading.removeObserver(observer)
        postFragmentViewModel.cancelJobs()
    }


}


