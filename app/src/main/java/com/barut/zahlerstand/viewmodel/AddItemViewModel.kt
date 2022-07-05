package com.barut.zahlerstand.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.room.DatabaseZaehlerstand
import kotlinx.coroutines.launch

class AddItemViewModel(application: Application) : BaseViewModel(application) {

    var liveDataDatasUpdated: MutableLiveData<Boolean>? = MutableLiveData()

    suspend fun setDatasInSQLITE(list : List<MainFragmentModel>) {

        launch {
            val dao = DatabaseZaehlerstand(getApplication()).dao()
            dao.insertAll(*list.toTypedArray())
            liveDataDatasUpdated?.value = true
        }
    }
}