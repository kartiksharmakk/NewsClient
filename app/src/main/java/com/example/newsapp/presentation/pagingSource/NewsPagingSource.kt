package com.example.newsapp.presentation.pagingSource
import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource (
    private val getNewsHeadLineUseCase: GetNewsHeadlinesUseCase,
    private val country: String
):  PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,Article> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = getNewsHeadLineUseCase.execute(country, nextPageNumber);

            if (response.data != null) {
                val mArtical: List<Article> = response.data?.articles!!
                return LoadResult.Page(
                    data = mArtical,
                    prevKey = null, // Only paging forward.
                    nextKey = nextPageNumber+1
                )
            } else {
                return LoadResult.Error(
                    Throwable(
                        "Some error occured"
                    )
                )
            }
        }
        catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }

}