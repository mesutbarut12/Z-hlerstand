package com.barut.zahlerstand.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.barut.zahlerstand.R
import com.barut.zahlerstand.viewmodel.DetailsFragmentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class DetailsFragment : Fragment() {

    private var typeView: TextView? = null
    private var idView: TextView? = null
    private var priceView: TextView? = null
    private var dateView: TextView? = null
    private var zaehlerstandAnfangView: TextView? = null
    private var zaehlerstandEndeView: TextView? = null
    private var zaehlerstandVerbrauchView: TextView? = null
    private var kiloPriceView: TextView? = null
    private var basePriceView: TextView? = null
    private var resultView: TextView? = null
    private var floatingButton : FloatingActionButton? = null

    private var type: String? = ""
    private var id: String? = ""
    private var kiloPrice: String? = ""
    private var date: String? = ""
    private var zaehlerstandAnfang: String? = ""
    private var zaehlerstandEnde: String? = ""
    private var uuid: String? = ""
    private var basePrice: String? = ""
    private var result: String? = ""

    private var viewmodel: DetailsFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)
        init(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromMainFragment()
        openAlertDialogWhenClick()
    }

    private fun init(view: View) {
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
        floatingButton = view.findViewById(R.id.details_fragment_floatingbutton)

        viewmodel = ViewModelProvider(this).get(DetailsFragmentViewModel::class.java)
    }

    private fun getDataFromMainFragment() {
        arguments?.let {
            type = DetailsFragmentArgs.fromBundle(it).type
            id = DetailsFragmentArgs.fromBundle(it).id
            kiloPrice = DetailsFragmentArgs.fromBundle(it).price
            date = DetailsFragmentArgs.fromBundle(it).date
            zaehlerstandAnfang = DetailsFragmentArgs.fromBundle(it).zaehlerstandAnfang
            zaehlerstandEnde = DetailsFragmentArgs.fromBundle(it).zaehlerstandEnde
            uuid = DetailsFragmentArgs.fromBundle(it).uuid
            basePrice = DetailsFragmentArgs.fromBundle(it).basePrice

            var consum = viewmodel?.getConsum(zaehlerstandAnfang!!, zaehlerstandEnde!!)
            if (consum != "Zählerstand Ende fehlt") {
                var kiloPrice = viewmodel?.getKiloPrice(consum!!, kiloPrice!!)
                var result = viewmodel?.getResult(kiloPrice!!, basePrice!!)

                zaehlerstandVerbrauchView?.text = consum.toString() + " kWh"
                kiloPriceView?.text = kiloPrice + "€"
                resultView?.text = result + "€"
                zaehlerstandEndeView?.text = zaehlerstandEnde + " kWh"

            } else {
                zaehlerstandVerbrauchView?.text = ""
                kiloPriceView?.text = ""
                resultView?.text = ""
                zaehlerstandEndeView?.text = ""

            }
            typeView?.text = type
            idView?.text = id
            priceView?.text = this.kiloPrice + "€"
            dateView?.text = date
            zaehlerstandAnfangView?.text = zaehlerstandAnfang + " kWh"
            basePriceView?.text = basePrice + "€"


        }
    }

    private fun openAlertDialogWhenClick() {
        floatingButton?.setOnClickListener {
            createAlertDialogForMenu()
        }
    }

    fun createAlertDialogForMenu() {
        if (activity != null) {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Zählerstand ende hinzufügen")
            val editText = EditText(requireContext())
            editText.setHint("Zählerstand eingeben")
            alertDialog.setView(editText)
            alertDialog.setPositiveButton("Speichern", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    if (editText.text.toString() > zaehlerstandAnfang!!) {
                        viewmodel?.updateDataInSQLite(id!!.toLong(), editText.text.toString())
                        val action = DetailsFragmentDirections.actionDetailsFragmentToMainFragment()
                        Navigation.findNavController(view!!).navigate(action)
                    } else {
                        Toast.makeText(
                            requireContext(), "Ende zählerstand muss größer als der anfang sein",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }).setNegativeButton("Abbrechen", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(requireContext(), "abgeborchen", Toast.LENGTH_SHORT).show()

                }

            }).setOnCancelListener {
                it.cancel()
                Toast.makeText(requireContext(), "Gecancelt", Toast.LENGTH_SHORT).show()
            }
            val create = alertDialog.create()
            create.show()
        } else {

        }
    }


}