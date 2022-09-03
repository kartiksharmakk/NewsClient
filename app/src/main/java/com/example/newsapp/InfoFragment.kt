package com.example.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapp.databinding.FragmentInfoBinding
import com.example.newsapp.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class InfoFragment : Fragment() {
    private lateinit var fragmentInfoBinding: FragmentInfoBinding
    private lateinit var viewModel : NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBinding = FragmentInfoBinding.bind(view)
        val args : InfoFragmentArgs by navArgs()
        val article = args.selectedArticle

        viewModel=(activity as MainActivity).viewModel

        fragmentInfoBinding.wvInfo.apply {
            var webViewClient = WebViewClient()
            if(article.url!=null) {
                loadUrl(article.url)
            }

        }
        fragmentInfoBinding.fabSave.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view,"Saved Successfully!",Snackbar.LENGTH_LONG).show()
        }
    }
}