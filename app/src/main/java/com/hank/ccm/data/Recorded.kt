package com.hank.ccm.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Recorded(
    @NonNull
    @ColumnInfo(name = "nick")
    var nickname: String,
    var counter: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) {
}