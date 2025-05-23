package com.example.pix.data.flickr

import com.example.pix.data.flickr.dto.PhotoDto
import com.example.pix.data.flickr.mapper.toEntity
import com.example.pix.domain.entity.Picture
import javax.inject.Inject

class FlickrRepository @Inject constructor(
    private val flickrApi: FlickrApi
) {
    suspend fun search(
        text: String?,
        page: Int = 1,
        count: Int = 20
    ): List<Picture> {
     //   delay(5000)// чтобы показать, что лоадер работает
        val result = flickrApi.searchPhotos(text ?: DEFAULT_SEARCH_STRING, page, count)
        if (result.stat!="ok") {throw Exception("${result.code}: ${result.message}") }
        val listPhotoDto = result.photos?.photo ?: emptyList<PhotoDto>()
        val listOfPicture = mutableListOf<Picture>()
        if (listPhotoDto.isNotEmpty()) {
            listPhotoDto.map { photo ->
                listOfPicture.add(photo.toEntity())
            }
        }
        return listOfPicture
    }


    companion object {
        const val PICTURE_URL_START = "https://live.staticflickr.com/"
        const val PICTURE_URL_END = "b.jpg"
        const val DEFAULT_SEARCH_STRING = "cats"
    }
}