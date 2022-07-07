package com.barut.zahlerstand.viewmodel

import android.app.Application

class DetailsFragmentViewModel(application: Application) : BaseViewModel(application) {

    fun getConsum(zaehlerstandAnfang : String, zaehlerstandEnde: String) : String {
        return (zaehlerstandEnde?.toDouble()!! - zaehlerstandAnfang?.toDouble()!!).toString()
    }
    fun getResult(consum : String, price : String) : String{
        return (consum.toDouble() * price.toDouble()).toString()
    }


}