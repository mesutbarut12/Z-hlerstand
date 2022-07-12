package com.barut.zahlerstand.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.room.DatabaseZaehlerstand
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class AddItemFragmentViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

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
            return roundDouble(result)
        } else {
            result = value.toDouble()
            return roundDouble(result)
        }
    }
    fun roundDouble(number : Double) : Double{
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return (df.format(number).toDouble())
    }
}