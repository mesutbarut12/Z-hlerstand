package com.barut.zahlerstand.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.barut.zahlerstand.model.MainFragmentModel
@Dao
interface DataBaseDAO {

    @Insert
    suspend fun insertAll(vararg model : MainFragmentModel) : List<Long>

    @Query("SELECT * FROM MainFragmentModel")
    suspend fun getAllDatas() : List<MainFragmentModel>

    @Query("SELECT * FROM MainFragmentModel WHERE id= :id1")
    suspend fun getData(id1 : String) : MainFragmentModel

    @Query("DELETE FROM MainFragmentModel")
    suspend fun deleteAll()

    @Query("DELETE FROM MainFragmentModel WHERE id= :id1")
    suspend fun deleteItem(id1 : Long)

    @Query("UPDATE MainFragmentModel SET zaehlerstandNach= :string WHERE ID= :id1")
    suspend fun updateData(id1 : Long, string : String)

}