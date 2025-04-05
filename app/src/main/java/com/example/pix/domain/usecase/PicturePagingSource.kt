package com.example.pix.domain.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.example.pix.data.flickr.FlickrRepository
import com.example.pix.domain.entity.Picture

class PicturePagingSource(val query:String?, val repository: FlickrRepository) : PagingSource<Int, Picture>() {

    override fun getRefreshKey(state: PagingState<Int, Picture>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Picture> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.search(text=query,page=page)
        }.fold(
            onSuccess = {
                Page(
                    data = it,
                    prevKey = null,//params.key?.let { it-1 },
                    nextKey = if (it.isEmpty() == true) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}