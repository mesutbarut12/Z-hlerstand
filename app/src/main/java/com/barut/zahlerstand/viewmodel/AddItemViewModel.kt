package com.barut.zahlerstand.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.room.DatabaseZaehlerstand
import kotlinx.coroutines.launch

class AddItemViewModel(application: Application) : BaseViewModel(application) {

    fun setDatasInSQLITE(model : ArrayList<MainFragmentModel>) {
        launch {
            val dao = DatabaseZaehlerstand(getApplication()).dao()
            dao.insertAll(*model.toTypedArray())
        }
    }

    fun checkAnfangValueNotBiggerEndeValue(anfang : String,ende : String) : Boolean{
        return anfang.toDouble() < ende.toDouble()
    }
}