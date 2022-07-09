package com.barut.zahlerstand.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.room.DatabaseZaehlerstand
import kotlinx.coroutines.launch

class AddItemFragmentViewModel(application: Application) : BaseViewModel(application) {

    fun setDatasInSQLITE(model: ArrayList<MainFragmentModel>) {
        launch {
            val dao = DatabaseZaehlerstand(getApplication()).dao()
            dao.insertAll(*model.toTypedArray())
        }
    }

    fun checkAnfangValueNotBiggerEndeValue(anfang: String, ende: String?): Boolean {
        if (ende == "") {
           return true
        } else {
            return anfang.toDouble() < ende!!.toDouble()
        }
    }

    fun calculateSwitch(b: Boolean, value: String): Double {
        var result = 0.0
        if (b == false) {
            result = value.toDouble() / 12.0
            return result
        } else {
            return value.toDouble()
        }
    }
}