package com.barut.zahlerstand.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.room.DataBaseDAO
import com.barut.zahlerstand.room.DatabaseZaehlerstand
import kotlinx.coroutines.launch

class MainFragmentViewModel(application: Application) : BaseViewModel(application) {

    var liveData: MutableLiveData<List<MainFragmentModel>>? = MutableLiveData()

    init {
        getAllDatasFromSQLite()
    }



    fun storeInSQLite(model : ArrayList<MainFragmentModel>) {
        launch {
            val dao = DatabaseZaehlerstand(getApplication()).dao()
            val listLong = dao.insertAll(*model.toTypedArray())

        }
    }

    fun getAllDatasFromSQLite(){
        launch {
            val dao = DatabaseZaehlerstand(getApplication()).dao().getAllDatas()
            showDatas(dao)
            Toast.makeText(getApplication(),"Daten erfolgreich geladen",Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun showDatas(list : List<MainFragmentModel>){
        liveData?.value = list
    }

}