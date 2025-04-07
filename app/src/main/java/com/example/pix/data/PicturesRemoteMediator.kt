package com.example.pix.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pix.data.flickr.FlickrApi
import com.example.pix.data.flickr.mapper.toPictureDbo
import com.example.pix.data.room.PictureDao
import com.example.pix.data.room.PictureDbo
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalPagingApi::class)
class PicturesRemoteMediator @AssistedInject constructor(
    private val pictureDao: PictureDao,
    private val flickrApi: FlickrApi,
    @Assisted
    private val queryString: MutableStateFlow<String>
) : RemoteMediator<Int, PictureDbo>() {

    private var pageIndex = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PictureDbo>
    ): MediatorResult {
        pageIndex =
            getPageIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)

        val limit = state.config.pageSize
        val offset = pageIndex * limit

        return try {
            val picturesDbo = fetchPictures(limit, offset)
            if (loadType == LoadType.REFRESH) {
                pictureDao.refresh(picturesDbo)
            } else {
                pictureDao.insertAll(picturesDbo)
            }
            MediatorResult.Success(endOfPaginationReached = picturesDbo.size < limit)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.APPEND -> ++pageIndex
            LoadType.PREPEND -> return null
        }
        return pageIndex
    }

    private suspend fun fetchPictures(limit: Int, offset: Int): List<PictureDbo> {
        val listPictureDbo = mutableListOf<PictureDbo>()
        val response = flickrApi.searchPhotos(queryString.value, pageIndex).photos?.photo
        if (response != null) {
            response.map { photoDto ->
                listPictureDbo.add(photoDto.toPictureDbo())
            }
        }
        return listPictureDbo
    }

    @AssistedFactory
    interface Factory {
        fun create(queryString: MutableStateFlow<String>): PicturesRemoteMediator
    }
}