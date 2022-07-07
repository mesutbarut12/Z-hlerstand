package com.barut.zahlerstand.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.barut.zahlerstand.R
import com.barut.zahlerstand.viewmodel.DetailsFragmentViewModel

class DetailsFragment : Fragment() {

    private var typeView : TextView? = null
    private var idView : TextView? = null
    private var priceView : TextView? = null
    private var dateView : TextView? = null
    private var zaehlerstandAnfangView : TextView? = null
    private var zaehlerstandEndeView : TextView? = null
    private var zaehlerstandVerbrauchView : TextView? = null
    private var resultView : TextView? = null

    private var type : String? = ""
    private var id : String? = ""
    private var price : String? = ""
    private var date : String? = ""
    private var zaehlerstandAnfang : String? = ""
    private var zaehlerstandEnde : String? = ""
    private var zaehlerstandVerbrauch : String? = ""
    private var uuid : String? =  ""
    private var result : String? = ""

    private var viewmodel : DetailsFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)
        init(view)
        getDataFromMainFragment()
        return view
    }
    private fun init(view : View){
        typeView = view.findViewById(R.id.details_fragment_type)
        idView = view.findViewById(R.id.details_fragment_id)
        priceView = view.findViewById(R.id.details_fragment_price)
        dateView = view.findViewById(R.id.details_fragment_date)
        zaehlerstandAnfangView = view.findViewById(R.id.details_fragment_zaehlerstand_anfang)
        zaehlerstandEndeView = view.findViewById(R.id.details_fragment_zaehlerstand_ende)
        zaehlerstandVerbrauchView = view.findViewById(R.id.details_fragment_zaehlerstand_verbrauch)
        resultView = view.findViewById(R.id.details_fragment_result)

        viewmodel = ViewModelProvider(this).get(DetailsFragmentViewModel::class.java)
    }
    private fun getDataFromMainFragment(){
        arguments?.let {
            type = DetailsFragmentArgs.fromBundle(it).type
            id = DetailsFragmentArgs.fromBundle(it).id
            price = DetailsFragmentArgs.fromBundle(it).price
            date = DetailsFragmentArgs.fromBundle(it).date
            zaehlerstandAnfang = DetailsFragmentArgs.fromBundle(it).zaehlerstandAnfang
            zaehlerstandEnde = DetailsFragmentArgs.fromBundle(it).zaehlerstandEnde
            uuid = DetailsFragmentArgs.fromBundle(it).uuid

            var consum = viewmodel?.getConsum(zaehlerstandAnfang!!, zaehlerstandEnde!!)
            var result = viewmodel?.getResult(consum!!,price!!)

            typeView?.text = type
            idView?.text = id
            priceView?.text = price + "€"
            dateView?.text = date
            zaehlerstandAnfangView?.text = zaehlerstandAnfang + " kWh"
            zaehlerstandEndeView?.text = zaehlerstandEnde + " kWh"
            zaehlerstandVerbrauchView?.text = consum.toString() + " kWh"
            resultView?.text = result + "€"
        }
    }




}