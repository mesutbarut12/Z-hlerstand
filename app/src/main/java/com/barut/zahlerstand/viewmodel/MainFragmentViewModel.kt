package com.barut.zahlerstand.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.room.DataBaseDAO
import com.barut.zahlerstand.room.DatabaseZaehlerstand
import kotlinx.coroutines.launch

class MainFragmentViewModel(application: Application) : BaseViewModel(application) {

    var liveData: MutableLiveData<ArrayList<MainFragmentModel>>? = MutableLiveData()

    fun setData() {
        var model = MainFragmentModel(
            "1",
            "1000",
            "0.30",
            "123",
            "04.07.2022",
            "Strom"
        )
        liveData?.value = arrayListOf<MainFragmentModel>(model)

    }

    fun storeInSQLite(model : ArrayList<MainFragmentModel>) {
        launch {
            val dao = DatabaseZaehlerstand(getApplication()).dao()
            val listLong = dao.insertAll(*model.toTypedArray())
            var i = 0
            while (i < model.size){
                model[i].id = listLong[i].toString()
                i += 1
            }
        }
    }


}