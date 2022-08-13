package com.example.newsapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.model.Article
import com.example.newsapp.databinding.NewsListItemBinding

class NewsAdapter : PagingDataAdapter<Article, NewsAdapter.newViewHolder>(callBacks)  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newViewHolder {
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.context),  parent,false)
        return newViewHolder(binding);
    }
    override fun onBindViewHolder(holder: newViewHolder, position: Int) {
        val article = getItem(position)
        if(article!=null) {
            article.also {
                holder.bind(it);
            }
        }
    }
    inner class newViewHolder(val binding: NewsListItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article)
        {
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source?.name
            Glide.with(binding.ivArticleImage.context)
                .load(article.urlToImage)
                .into(binding.ivArticleImage)

            binding.root.setOnClickListener {
                onItemClickListener.let { it->
                    if (it != null) {
                        it(article)
                    }
                }
            }
        }
    }
    companion object {
        private val callBacks = object : DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url  == newItem.url
            }
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem  == newItem
            }
        }
    }


    private var onItemClickListener: ((Article)->Unit)?=null;

    fun setOnItemClickListner (listener : (Article)->Unit){
        onItemClickListener=listener
    }
}