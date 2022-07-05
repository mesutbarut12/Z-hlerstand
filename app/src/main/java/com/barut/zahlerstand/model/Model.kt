package com.barut.zahlerstand.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MainFragmentModel(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    @ColumnInfo(name = "zaehlerstandVor")
    var zaehlerstandVor : String?,
    @ColumnInfo(name = "price")
    var price : String?,
    @ColumnInfo(name = "zaehlerstandNach")
    var zaehlerstandNach : String?,
    @ColumnInfo(name = "date")
    var date : String?,
    @ColumnInfo(name = "type")
    var type : String?
)