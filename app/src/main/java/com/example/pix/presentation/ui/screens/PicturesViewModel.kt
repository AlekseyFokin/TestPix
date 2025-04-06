package com.example.pix.presentation.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pix.data.flickr.FlickrRepository
import com.example.pix.domain.entity.Picture
import com.example.pix.domain.usecase.PicturePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(repository: FlickrRepository): ViewModel() {

var searchString:String?=null

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
  val pagesPictures: Flow<PagingData<Picture>> =
        Pager( // постраничная загрузка фотографий
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { PicturePagingSource(searchString, repository) }
        ).flow.cachedIn(viewModelScope)


    @OptIn(FlowPreview::class)
    fun startRequest(query:String){
        searchString=query
    }
}