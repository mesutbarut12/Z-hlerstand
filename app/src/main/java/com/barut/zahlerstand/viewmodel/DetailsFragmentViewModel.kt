package com.barut.zahlerstand.viewmodel

import android.app.Application


class DetailsFragmentViewModel(application: Application) : BaseViewModel(application) {


    fun getConsum(zaehlerstandAnfang: String, zaehlerstandEnde: String): String {
        return (zaehlerstandEnde?.toDouble()!! - zaehlerstandAnfang?.toDouble()!!).toString()
    }


    fun getKiloPrice(consum: String, price: String): String {
        return (consum.toDouble() * price.toDouble()).toString()
    }

    fun getResult(kiloPrice: Double, basePrice: Double): String = (kiloPrice + basePrice).toString()


}