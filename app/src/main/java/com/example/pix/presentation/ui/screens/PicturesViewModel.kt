package com.example.pix.presentation.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pix.data.flickr.FlickrRepository
import com.example.pix.domain.entity.Picture
import com.example.pix.domain.usecase.PicturePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.retry

@HiltViewModel
class PicturesViewModel @Inject constructor(repository: FlickrRepository): ViewModel() {

//    private val _searchString: MutableStateFlow<String> = MutableStateFlow("")
//    val searchString: StateFlow<String> = _searchString
var searchString:String?=null

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
  val pagesPictures: Flow<PagingData<Picture>> =
        Pager( // постраничная загрузка фотографий
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { PicturePagingSource(searchString, repository) }
        ).flow.cachedIn(viewModelScope)
   // val picturesData = pagesPictures.collectAsLazyPagingItems()


//   fun pastSearchString(newSearchString:String){
//        searchString=newSearchString
//    }

    @OptIn(FlowPreview::class)
    fun startRequest(query:String){
        searchString=query
    }
}