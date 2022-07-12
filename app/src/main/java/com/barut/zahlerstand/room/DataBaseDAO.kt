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
    suspend fun getData(id1 : Long) : MainFragmentModel

    @Query("DELETE FROM MainFragmentModel")
    suspend fun deleteAll()

    @Query("DELETE FROM MainFragmentModel WHERE id= :id1")
    suspend fun deleteItem(id1 : Long)

    @Query("UPDATE MainFragmentModel SET zaehlerstandVor = :string WHERE ID= :id1")
    suspend fun updateDataZaehlerstandVor(id1 : Long, string : String)

    @Query("UPDATE MainFragmentModel SET zaehlerstandNach = :string WHERE ID= :id1")
    suspend fun updateDataZaehlerstandNach(id1 : Long, string : String)

    @Query("UPDATE MainFragmentModel SET price = :string WHERE ID= :id1")
    suspend fun updateDataPrice(id1 : Long, string : String)

    @Query("UPDATE MainFragmentModel SET basePrice = :string WHERE ID= :id1")
    suspend fun updateDataBasePrice(id1 : Long, string : String)

    @Query("UPDATE MainFragmentModel SET type = :string WHERE ID= :id1")
    suspend fun updateDataBaseType(id1 : Long, string : String)

    @Query("UPDATE MainFragmentModel SET date = :string WHERE ID= :id1")
    suspend fun updateDataBaseDate(id1 : Long, string : String)



}