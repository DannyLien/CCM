package com.hank.ccm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface RecordedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recorded:Recorded)

    @Query("select * from Recorded")
    fun getAll():List<Recorded>

}