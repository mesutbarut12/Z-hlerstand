package com.barut.zahlerstand.viewmodel

import android.app.Application
import com.barut.zahlerstand.room.DatabaseZaehlerstand
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class DetailsFragmentViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    fun getConsum(zaehlerstandAnfang: String, zaehlerstandEnde: String?): String {
        if (zaehlerstandEnde == "") {
            return "Zählerstand Ende fehlt"
        } else {
            var result = (zaehlerstandEnde?.toDouble()!! - zaehlerstandAnfang.toDouble())
            return roundDouble(result)
        }
    }

    fun getKiloPrice(consum: String, price: String): String {

        val result = (consum.toDouble() * price.toDouble())
        return roundDouble(result)
    }

    fun getResult(kiloPrice: String, basePrice: String): String {
        val result = (kiloPrice.toDouble() + basePrice.toDouble())
        return roundDouble(result)
    }

    fun roundDouble(number : Double) : String{
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return (df.format(number).toDouble()).toString()
    }


}