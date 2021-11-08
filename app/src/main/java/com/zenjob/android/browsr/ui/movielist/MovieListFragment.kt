package com.zenjob.android.browsr.ui.movielist

import android.content.Intent
import android.view.View
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.base.BaseBindingFragment
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.databinding.FragmentMovieListBinding
import com.zenjob.android.browsr.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : BaseBindingFragment<FragmentMovieListBinding>() {
    override val viewModel: MovieListViewModel by viewModel()
    override fun getLayoutResId(): Int = R.layout.fragment_movie_list

    private val movieListAdapter by lazy {
        MovieListAdapter(object : MovieListAdapterListener {
            override fun onMovieItemClick(itemView: View, position: Int, movie: Movie) {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra("movie", movie)
                startActivity(intent)
            }
        })
    }

    override fun onViewBound(binding: FragmentMovieListBinding) {
        setHasOptionsMenu(true)
        binding.list.adapter = movieListAdapter
        viewModel.showsItemsResult.observe(
            viewLifecycleOwner,
            {
                movieListAdapter.submitList(it)
            }
        )
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_list)
//        val list: RecyclerView = findViewById(R.id.list)
//
//        list.adapter = mAdapter
//
//        fetchMovies()
//
//        val refresh = findViewById<View>(R.id.refresh)
//        refresh.setOnClickListener {
//            fetchMovies()
//        }
//    }
//
//    override fun onMovieItemClick(
//        itemView: View,
//        position: Int,
//        movie: Movie
//    ) {
//        val intent = Intent(this, DetailActivity::class.java)
//        intent.putExtra("movie", movie)
//        startActivity(intent)
//    }

    fun fetchMovies() {

//        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .add(DateJsonAdapter())
//            .build()
//
//        val tmdbApiInterceptor = Interceptor { chain ->
//
//            val original = chain.request()
//            val originalHttpUrl = original.url()
//
//            val url = originalHttpUrl.newBuilder()
//                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
//                .build()
//
//            val reqBuilder = original.newBuilder()
//                .url(url)
//            chain.proceed(reqBuilder.build())
//        }
//
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(tmdbApiInterceptor)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.themoviedb.org/3/")
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .client(okHttpClient)
//            .build()
//
//        retrofit.create(TMDBApi::class.java).getPopularTvShows()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { paginatedList ->
//                mAdapter.submitList(paginatedList.results)
//            }
    }
}
