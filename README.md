# NewsClient

Android News App with Search and Save functionality

https://user-images.githubusercontent.com/77577353/188262824-42ffea9d-b42f-415a-921a-bdadbabf316d.mp4

<br />

# MVVM Architecture
This app is based on Model–view–viewmodel (MVVM) Architecture

<br />

![MVVM](mvvm.png "MVVM Architecture")

<br />

# App Structure
![AppStructure](AppStructure.png "App Structure")

<br />

# Libraries used

* ViewModel
* NavController
* LiveData
* Dagger Hilt
* Room 
* Glide
* Kotlin Coroutines
* Logging Interceptors (OkHttp Lib)
* RetroFit 2
* Convertor-Gson (Retrofit Convertor Lib)

<br />

# Search Logic
NewsViewModel.kt
```kt
fun searchNews(
        country: String,
        searchQuery : String,
        page: Int
    ) = viewModelScope.launch {
        searchedNews.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val response = getSearchedNewsUseCase.execute(
                    country,
                    searchQuery,
                    page
                )
                searchedNews.postValue(response)
            } else {
                searchedNews.postValue(Resource.Error("No internet connection"))
            }
        }catch(e:Exception){
            searchedNews.postValue(Resource.Error(e.message.toString()))
        }
    }
```
Inside NewsAPIService.kt
```kt
@GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("country")
        country:String,
        @Query("q")
        searchQuery:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY
    ): Response<APIResponse>
```

# Save and Delete Logic
Inside NewsViewModel.kt
```kt
//local data
    fun saveArticle(article: Article) = viewModelScope.launch {
        saveNewsUseCase.execute(article)
    }

    fun getSavedNews() = liveData{
        getSavedNewsUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteArticle(article: Article) = viewModelScope.launch {
        deleteSavedNewsUseCase.execute(article)
    }

```
Inside ArticleDao.kt
```kt
@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}
```