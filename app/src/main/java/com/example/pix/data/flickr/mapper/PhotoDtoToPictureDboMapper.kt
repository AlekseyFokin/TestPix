package com.example.pix.data.flickr.mapper

import com.example.pix.data.flickr.FlickrRepository.Companion.PICTURE_URL_END
import com.example.pix.data.flickr.FlickrRepository.Companion.PICTURE_URL_START
import com.example.pix.data.flickr.dto.PhotoDto
import com.example.pix.data.room.PictureDbo

    fun PhotoDto.toPictureDbo(): PictureDbo = PictureDbo(
        title = title,
        url =    "$PICTURE_URL_START/${server}/${id}_${secret}_$PICTURE_URL_END",
        id=id,
        label = ""
    )
