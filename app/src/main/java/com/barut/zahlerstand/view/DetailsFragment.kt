package com.barut.zahlerstand.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.barut.zahlerstand.R
import com.barut.zahlerstand.databinding.FragmentDetailsBinding
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.viewmodel.DetailsFragmentViewModel

class DetailsFragment : Fragment() {

    private lateinit var dataBinding : FragmentDetailsBinding

    private var typeView : TextView? = null
    private var idView : TextView? = null
    private var priceView : TextView? = null
    private var dateView : TextView? = null
    private var zaehlerstandAnfangView : TextView? = null
    private var zaehlerstandEndeView : TextView? = null
    private var zaehlerstandVerbrauchView : TextView? = null
    private var kiloPriceView : TextView? = null
    private var basePriceView : TextView? = null
    private var resultView : TextView? = null

    private var type : String? = ""
    private var id : String? = ""
    private var kiloPrice : String? = ""
    private var date : String? = ""
    private var zaehlerstandAnfang : String? = ""
    private var zaehlerstandEnde : String? = ""
    private var uuid : String? =  ""
    private var basePrice : String? = ""
    private var result : String? = ""

    private var viewmodel : DetailsFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_details,
        container,false)
        init(dataBinding.root)
        getDataFromMainFragment()
        return dataBinding.root
    }
    private fun init(view : View){
        typeView = view.findViewById(R.id.details_fragment_type)
        idView = view.findViewById(R.id.details_fragment_id)
        priceView = view.findViewById(R.id.details_fragment_price)
        dateView = view.findViewById(R.id.details_fragment_date)
        zaehlerstandAnfangView = view.findViewById(R.id.details_fragment_zaehlerstand_anfang)
        zaehlerstandEndeView = view.findViewById(R.id.details_fragment_zaehlerstand_ende)
        zaehlerstandVerbrauchView = view.findViewById(R.id.details_fragment_zaehlerstand_verbrauch)
        kiloPriceView = view.findViewById(R.id.details_fragment_kilo_price)
        basePriceView = view.findViewById(R.id.details_fragment_basePrice)
        resultView = view.findViewById(R.id.details_fragment_result)

        viewmodel = ViewModelProvider(this).get(DetailsFragmentViewModel::class.java)
    }
    private fun getDataFromMainFragment(){
        arguments?.let {
            var model = MainFragmentModel(
                DetailsFragmentArgs.fromBundle(it).id.toLong(),
                DetailsFragmentArgs.fromBundle(it).zaehlerstandAnfang,
                DetailsFragmentArgs.fromBundle(it).zaehlerstandEnde,
                DetailsFragmentArgs.fromBundle(it).price,
                DetailsFragmentArgs.fromBundle(it).basePrice,
                DetailsFragmentArgs.fromBundle(it).date,
                DetailsFragmentArgs.fromBundle(it).type
            )
            dataBinding.data = model

            /*var consum = viewmodel?.getConsum(zaehlerstandAnfang!!, zaehlerstandEnde!!)
            var kiloPrice = viewmodel?.getKiloPrice(consum!!, kiloPrice!!)
            var result = viewmodel?.getResult(kiloPrice!!,basePrice!!)*/

           /* typeView?.text = type
            idView?.text = id
            priceView?.text = this.kiloPrice + "€"
            dateView?.text = date
            zaehlerstandAnfangView?.text = zaehlerstandAnfang + " kWh"
            zaehlerstandEndeView?.text = zaehlerstandEnde + " kWh"
            zaehlerstandVerbrauchView?.text = consum.toString() + " kWh"
            kiloPriceView?.text = kiloPrice + "€"
            basePriceView?.text = basePrice + "€"
            resultView?.text = result + "€"*/
        }
    }




}