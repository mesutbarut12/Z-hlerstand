package com.barut.zahlerstand.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barut.zahlerstand.model.MainFragmentModel

class MainFragmentViewModel : ViewModel() {

    var liveData : MutableLiveData<ArrayList<MainFragmentModel>>? = null

    fun setData(){

    }


}