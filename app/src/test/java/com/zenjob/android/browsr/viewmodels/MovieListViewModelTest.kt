package com.zenjob.android.browsr.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.whenever
import com.zenjob.android.browsr.TestCoroutineRule
import com.zenjob.android.browsr.data.PaginatedListResponse
import com.zenjob.android.browsr.data.Show
import com.zenjob.android.browsr.network.Resource
import com.zenjob.android.browsr.repository.ShowsRepository
import com.zenjob.android.browsr.ui.movielist.MovieListViewModel
import com.zenjob.android.browsr.utils.ErrorViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.Date

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieListViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var showsRepository: ShowsRepository

    @Mock
    private lateinit var showsItemsResultObserver: Observer<List<Show>>

    @Mock
    private lateinit var errorViewStateObserver: Observer<ErrorViewState>

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when user load shows list successfully`() {
        val showsList = listOf(
            Show(id = 1, overview = "finding a host body in investigative reporter", title = "Venom", releaseDate = Date(System.currentTimeMillis()), voteAverage = 6.8f),
            Show(id = 2, overview = "Eternals are a team of ancient aliens", title = "Eternals", releaseDate = Date(System.currentTimeMillis()), voteAverage = 7.1f),
            Show(id = 3, overview = " diving in a remote French lake", title = "The Deep House", releaseDate = Date(System.currentTimeMillis()), voteAverage = 7.3f)
        )

        val successResponse = Resource.success(PaginatedListResponse(1, 3, 1, showsList))

        testCoroutineRule.runBlockingTest {
            whenever(showsRepository.getPopularTvShowsList()).thenReturn(successResponse)

            val viewModel = MovieListViewModel(showsRepository)
            viewModel.showsItemsResult.observeForever(showsItemsResultObserver)

            Mockito.verify(showsItemsResultObserver, Mockito.atLeast(1)).onChanged(showsList)

            viewModel.showsItemsResult.removeObserver(showsItemsResultObserver)
        }
    }

    @Test
    fun `when user load shows list return error`() {
        val errorResponse = Resource.error("error", null)

        testCoroutineRule.runBlockingTest {
            whenever(showsRepository.getPopularTvShowsList()).thenReturn(errorResponse)

            val viewModel = MovieListViewModel(showsRepository)
            viewModel.showsItemsResult.observeForever(showsItemsResultObserver)

            viewModel.errorViewState.observeForever(errorViewStateObserver)

            Mockito.verify(showsItemsResultObserver, never()).onChanged(null)
            Mockito.verify(errorViewStateObserver, Mockito.atLeast(1)).onChanged(viewModel.getErrorStateMessage())

            viewModel.showsItemsResult.removeObserver(showsItemsResultObserver)
            viewModel.errorViewState.removeObserver(errorViewStateObserver)
        }
    }
}
