package com.barut.zahlerstand.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.room.DatabaseZaehlerstand
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel @Inject
constructor(application: Application) : BaseViewModel(application) {

    var liveData: MutableLiveData<List<MainFragmentModel>>? = MutableLiveData()
    val dao = DatabaseZaehlerstand(getApplication()).dao()

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