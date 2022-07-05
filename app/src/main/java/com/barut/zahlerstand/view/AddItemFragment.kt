package com.barut.zahlerstand.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.barut.zahlerstand.R

class AddItemFragment : Fragment() {

    private var zahelerstand: EditText? = null
    private var price: EditText? = null
    private var date: EditText? = null

    private var zaehlerstandText: String? = null
    private var priceText: String? = null
    private var dateText: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_item, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        zahelerstand = view.findViewById(R.id.add_item_fragment_zaehlerstand)
        price = view.findViewById(R.id.add_item_fragment_price)
        date = view.findViewById(R.id.add_item_fragment_date)

        zaehlerstandText = zahelerstand?.text.toString()
        priceText = price?.text.toString()
        dateText = date?.text.toString()
    }

    private fun checkInputIsCorrectly() {
        if (
            zaehlerstandText!!.isNotEmpty() &&
            priceText!!.isNotEmpty() &&
            dateText!!.isNotEmpty()
        ){



        } else {
            Toast.makeText(context,"Sei dir sicher das alle Felder ausgefüllt sind",
            Toast.LENGTH_SHORT).show()
        }
    }


}