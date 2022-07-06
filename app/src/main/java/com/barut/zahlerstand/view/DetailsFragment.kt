package com.barut.zahlerstand.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.barut.zahlerstand.R

class DetailsFragment : Fragment() {

    private var typeView : TextView? = null
    private var idView : TextView? = null
    private var priceView : TextView? = null
    private var dateView : TextView? = null
    private var zaehlerstandAnfangView : TextView? = null
    private var zaehlerstandEndeView : TextView? = null
    private var zaehlerstandVerbrauchView : TextView? = null

    private var type : String? = ""
    private var id : String? = ""
    private var price : String? = ""
    private var date : String? = ""
    private var zaehlerstandAnfang : String? = ""
    private var zaehlerstandEnde : String? = ""
    private var zaehlerstandVerbrauch : String? = ""
    private var uuid : String? =  ""

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

            var verbrauch = zaehlerstandEnde?.toDouble()!! - zaehlerstandAnfang?.toDouble()!!

            typeView?.text = type
            idView?.text = id
            priceView?.text = price + "€"
            dateView?.text = date
            zaehlerstandAnfangView?.text = zaehlerstandAnfang + " kWh"
            zaehlerstandEndeView?.text = zaehlerstandEnde + " kWh"
            zaehlerstandVerbrauchView?.text = verbrauch.toString() + " kWh"
        }
    }




}