package com.javimay.postsapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.javimay.postsapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.javimay.postsapp.ui.adapters.ViewPagerFragmentAdapter
import com.javimay.postsapp.utils.TAB_ALL
import com.javimay.postsapp.utils.TAB_FAVORITES
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val tabTitles = arrayOf(TAB_ALL, TAB_FAVORITES)

    private var postsChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            //TODO: onChangeCallback code
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.vpPosts.isUserInputEnabled = false
        initTabs()
        return binding.root
    }

    private fun initTabs() {
//        requireActivity().actionBar!!.elevation = 0f
        binding.vpPosts.adapter= ViewPagerFragmentAdapter(this, tabTitles)
        TabLayoutMediator(binding.tabLayout, binding.vpPosts){ tab: TabLayout.Tab, position: Int ->
            tab.text = tabTitles[position]
        }.attach()
    }
}