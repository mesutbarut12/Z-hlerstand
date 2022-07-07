package com.barut.zahlerstand.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.room.DataBaseDAO
import com.barut.zahlerstand.room.DatabaseZaehlerstand
import kotlinx.coroutines.launch

class MainFragmentViewModel(application: Application) : BaseViewModel(application) {

    var liveData: MutableLiveData<List<MainFragmentModel>>? = MutableLiveData()
    private val dao = DatabaseZaehlerstand(getApplication()).dao()

    init {
        getAllDatasFromSQLite()
    }

    fun getAllDatasFromSQLite(){
        launch {
            showDatas(dao.getAllDatas())
            Toast.makeText(getApplication(),"Daten erfolgreich geladen",Toast.LENGTH_SHORT)
                .show()
        }
    }
    fun deleteAllValuesInSQLite(){
        launch {
            dao.deleteAll()
            showDatas(dao.getAllDatas())
            Toast.makeText(getApplication(),"Alle Daten wurden gelöscht",
            Toast.LENGTH_SHORT).show()
        }
    }
    fun deleteSpeciallyItem(id : Long) {
        launch {
            dao.deleteItem(id)
            showDatas(dao.getAllDatas())
            Toast.makeText(getApplication(),"Item mit der id $id gelöscht!",
            Toast.LENGTH_SHORT).show()
        }
    }

    fun showDatas(list : List<MainFragmentModel>){
        liveData?.value = list
    }

}