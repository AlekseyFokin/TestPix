package com.example.pix.data.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PictureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pictures: List<PictureDbo>)

    @Query("delete from pictures")
    suspend fun clearAll()

    @Query("select * from pictures")
   // suspend fun getAll(): List<PictureDbo>
    fun getAll(): PagingSource<Int,PictureDbo>

    @Transaction
    suspend fun refresh(launches: List<PictureDbo>) {
        clearAll()
        insertAll(launches)
    }
}