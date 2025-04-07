package com.example.pix.presentation.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pix.data.PicturesRemoteMediator
import com.example.pix.data.flickr.mapper.toPicture
import com.example.pix.data.room.PictureDao
import com.example.pix.domain.entity.Picture
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    private val pictureDao: PictureDao,
    private val picturesRemoteMediatorFactory:PicturesRemoteMediator.Factory
) : ViewModel() {


    val searchString = MutableStateFlow("cat")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class)
    val pagesPictures: Flow<PagingData<Picture>> =
        Pager( // постраничная загрузка фотографий
            config = PagingConfig(pageSize = 20),
            remoteMediator = picturesRemoteMediatorFactory.create(searchString),
            pagingSourceFactory = { pictureDao.getAll() }
        ).flow.map { pagingData->pagingData.map { p->p.toPicture() } }

            .cachedIn(viewModelScope)


    @OptIn(FlowPreview::class)
    fun startRequest(query: String) {
        viewModelScope.launch { searchString.emit(query) }

    }
}
