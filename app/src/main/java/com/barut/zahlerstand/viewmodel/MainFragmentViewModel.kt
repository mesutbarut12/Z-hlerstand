package com.barut.zahlerstand.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barut.zahlerstand.model.MainFragmentModel

class MainFragmentViewModel : ViewModel() {

    var liveData : MutableLiveData<ArrayList<MainFragmentModel>>? = MutableLiveData()

    fun setData(){
        var model = MainFragmentModel(
            "1",
            "1000",
            "0.30",
            "123",
            "04.07.2022",
            "Strom")
        liveData?.value = arrayListOf<MainFragmentModel>(model)

    }


}