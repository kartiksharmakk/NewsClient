package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.data.util.Resource
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.presentation.adapter.NewsAdapter
import com.example.newsapp.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
    private var viewModel: NewsViewModel? = null;
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter;
    private var country= "us"
    private var page= 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        // avoid unexpected error
        viewModel = (activity as MainActivity).viewModel
        newsAdapter= (activity as MainActivity).newsAdapter
        initRecyclerView()
         viewnewList()
    }
    private fun viewnewList() {
        viewModel?.getNewsHeadLines(country = country, page = page)
        viewModel?.newsHeadLines?.observe(viewLifecycleOwner){ resources ->
            when (resources) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
//                        resources?.data.let {
//                            newsAdapter.differ.submitList(it?.articles)
//                        }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(activity, resources.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun  initRecyclerView(){
        binding.rvNews.apply {
            adapter= newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        lifecycleScope.launch {
            viewModel?.flow?.collectLatest { pagingData ->
                newsAdapter.submitData(pagingData)
            }
        }

        newsAdapter.setOnItemClickListner { article->

            val bundle = Bundle().apply {
                putParcelable("selected_article",article)
            }

            findNavController().navigate(
                R.id.action_newsFragment_to_infoFragment,bundle
            )
        }

    }
    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        binding.progressBar.visibility = View.GONE
    }
}