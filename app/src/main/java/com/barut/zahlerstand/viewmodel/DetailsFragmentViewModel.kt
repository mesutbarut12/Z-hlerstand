package com.barut.zahlerstand.viewmodel

import android.app.Application
import com.barut.zahlerstand.room.DatabaseZaehlerstand
import kotlinx.coroutines.launch

class DetailsFragmentViewModel(application: Application) : BaseViewModel(application) {

    fun getConsum(zaehlerstandAnfang: String, zaehlerstandEnde: String?): String {
        if (zaehlerstandEnde == "") {
            return "Zählerstand Ende fehlt"
        } else {
            return (zaehlerstandEnde?.toDouble()!! - zaehlerstandAnfang?.toDouble()!!).toString()
        }
    }

    fun getKiloPrice(consum: String, price: String): String {

        return (consum.toDouble() * price.toDouble()).toString()
    }

    fun getResult(kiloPrice: String, basePrice: String): String {
        return (kiloPrice.toDouble() + basePrice.toDouble()).toString()
    }

    fun updateDataInSQLite(id: Long, value: String) {
        launch {
            val dao = DatabaseZaehlerstand(getApplication()).dao()
            dao.updateData(id, value)
        }
    }


}