package com.barut.zahlerstand.util

import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.barut.zahlerstand.viewmodel.DetailsFragmentViewModel

@BindingAdapter(value =  ["android:getResult"])
fun getResult(view : TextView,kiloPrice : String){
    view.text = (kiloPrice.toDouble() + 10.0).toString()
}
@BindingAdapter(value =  ["android:getKiloPrice"])
fun getKiloPrice(view : TextView,consum: String, price: String){
    view.text =  (consum.toDouble() * price.toDouble()).toString()
}