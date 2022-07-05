package com.barut.zahlerstand.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MainFragmentModel(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    @ColumnInfo(name = "zaehlerstand")
    var zaehlerstand : String?,
    @ColumnInfo(name = "price")
    var price : String?,
    @ColumnInfo(name = "uuid")
    var uuid : String?,
    @ColumnInfo(name = "date")
    var date : String?,
    @ColumnInfo(name = "type")
    var type : String?
)