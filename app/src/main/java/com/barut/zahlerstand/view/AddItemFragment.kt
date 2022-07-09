package com.barut.zahlerstand.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.barut.zahlerstand.DatePicker
import com.barut.zahlerstand.R
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.viewmodel.AddItemFragmentViewModel

class AddItemFragment : Fragment() {

    private var zahelerstandAnfang: EditText? = null
    private var zahelerstandEnde: EditText? = null
    private var price: EditText? = null
    private var date: EditText? = null
    private var type: EditText? = null
    private var buton: Button? = null
    private var basePrice: EditText? = null
    private var switch: Switch? = null
    private var zaehlerstandDatumStart: EditText? = null
    private var zaehlerstandDatumEnd: EditText? = null

    private var zaehlerstandTextAnfang: String? = null
    private var zaehlerstandTextEnde: String? = null
    private var priceText: String? = null
    private var dateText: String? = null
    private var typeText: String? = null
    private var basePriceText: String? = null

    private var viewModel: AddItemFragmentViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_item, container, false)
        initViews(view)
        startFragment()
        dateClickListener()
        popUpMenu()
        switchOptions()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context,"View Created",Toast.LENGTH_SHORT).show()
    }

    private fun initViews(view: View) {
        zahelerstandAnfang = view.findViewById(R.id.add_item_fragment_zaehlerstand_anfang)
        zahelerstandEnde = view.findViewById(R.id.add_item_fragment_zaehlerstand_ende)
        price = view.findViewById(R.id.add_item_fragment_price_kilo)
        date = view.findViewById(R.id.add_item_fragment_date)
        type = view.findViewById(R.id.add_item_fragment_type)
        buton = view.findViewById(R.id.add_item_fragment_save)
        basePrice = view.findViewById(R.id.add_item_fragment_basePrice)
        switch = view.findViewById(R.id.add_item_fragment_switch)
        zaehlerstandDatumStart = view.findViewById(R.id.add_item_fragment_zaehlerstand_anfang_date)
        zaehlerstandDatumEnd = view.findViewById(R.id.add_item_fragment_zaehlerstand_ende_date)
    }

    private fun checkInputIsCorrectly(): Boolean {
        zaehlerstandTextAnfang = zahelerstandAnfang?.text.toString()
        zaehlerstandTextEnde = zahelerstandEnde?.text.toString()
        priceText = price?.text.toString()
        dateText = date?.text.toString()
        typeText = type?.text.toString()
        basePriceText = basePrice?.text.toString()

        if (
            zaehlerstandTextAnfang!!.isNotEmpty() &&
            priceText!!.isNotEmpty() &&
            dateText!!.isNotEmpty() &&
            typeText!!.isNotEmpty() &&
            basePriceText!!.isNotEmpty()
        ) {
            return true
        } else {
            Toast.makeText(
                context, "Sei dir sicher das alle Felder ausgefüllt sind",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
    }

    fun startFragment() {
        viewModel = ViewModelProvider(this).get(AddItemFragmentViewModel::class.java)
        buton?.setOnClickListener {
            var checked = checkInputIsCorrectly()
            if (checked == true) {
                var checkValues = viewModel?.checkAnfangValueNotBiggerEndeValue(
                    zaehlerstandTextAnfang!!,
                    zaehlerstandTextEnde!!
                )
                if (checkValues == true) {

                    var getDataForSwitch = viewModel?.calculateSwitch(switch?.isChecked!!,
                    basePriceText!!)

                    var model = MainFragmentModel(
                        null,
                        zaehlerstandTextAnfang,
                        zaehlerstandTextEnde,
                        priceText, getDataForSwitch.toString(),
                        dateText, typeText,
                    )
                    viewModel!!.setDatasInSQLITE(arrayListOf(model))
                    val action = AddItemFragmentDirections.actionAddItemFragmentToMainFragment()
                    Navigation.findNavController(it).navigate(action)
                } else {
                    Toast.makeText(
                        context, "der Anfang wert muss kleiner als der entwert sein!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun dateClickListener() {
        var datepicker = DatePicker(context!!)
        zaehlerstandDatumStart?.setText(datepicker.getTodaysDate().toString())
        zaehlerstandDatumEnd?.setText(datepicker.getTodaysDate().toString())
        date?.setText(datepicker.getTodaysDate().toString())
        date?.setOnClickListener {
            datepicker.initDatePicker(date!!)
        }
        zaehlerstandDatumStart?.setOnClickListener {
            datepicker.initDatePicker(zaehlerstandDatumStart!!)
        }
        zaehlerstandDatumEnd?.setOnClickListener {
            datepicker.initDatePicker(zaehlerstandDatumEnd!!)
        }
    }

    fun popUpMenu() {
        type?.setOnClickListener {
            var popupMenu: PopupMenu = PopupMenu(context, it)
            popupMenu.inflate(R.menu.add_item_fragment_pop_up_menu)
            popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(p0: MenuItem?): Boolean {
                    return when (p0?.itemId) {
                        R.id.add_item_fragment_pop_up_menu_strom -> {
                            type?.setText("Strom")
                            true
                        }
                        R.id.add_item_fragment_pop_up_menu_gas -> {
                            type?.setText("Gas")
                            true
                        }
                        else -> false
                    }

                }
            })
            popupMenu.show()
        }
    }

    fun switchOptions() {
        switch?.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                Toast.makeText(context, "Monatlich", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Jährlich", Toast.LENGTH_SHORT).show()
            }
        }
    }


}