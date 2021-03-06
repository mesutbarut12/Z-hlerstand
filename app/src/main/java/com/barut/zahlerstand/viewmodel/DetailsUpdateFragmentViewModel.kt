package com.barut.zahlerstand.viewmodel

import android.app.Application
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.room.DatabaseZaehlerstand
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class DetailsUpdateFragmentViewModel
@Inject constructor(application: Application) : BaseViewModel(application) {


    private val dao = DatabaseZaehlerstand(getApplication()).dao()


    fun updateDataZaehlerstandVor(id1: Long, updateString: String) {
        launch {
            dao.updateDataZaehlerstandVor(id1, updateString)
        }
    }

    fun updateDataZaehlerstandNach(id1: Long, updateString: String) {
        launch {
            dao.updateDataZaehlerstandNach(id1, updateString)
        }
    }

    fun updateDataPrice(id1: Long, updateString: String) {
        launch {
            dao.updateDataPrice(id1, updateString)
        }
    }

    fun updateDataBasePrice(id1: Long, updateString: String) {
        launch {
            dao.updateDataBasePrice(id1, updateString)
        }
    }

    fun updateDataBaseType(id1: Long, updateString: String) {
        launch {
            dao.updateDataBaseType(id1, updateString)
        }
    }

    fun updateDataBaseDate(id1: Long, updateString: String) {
        launch {
            dao.updateDataBaseDate(id1, updateString)
        }
    }
    fun updateDataBaseBasePrice(id1: Long, updateString: String) {
        launch {
            dao.updateDataBaseDate(id1, updateString)
        }
    }


     suspend fun getDetailsData(id1: Long): MainFragmentModel? {
         return dao.getData(id1)

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