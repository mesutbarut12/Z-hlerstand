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
import com.barut.zahlerstand.databinding.FragmentAddItemBinding
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.viewmodel.AddItemFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddItemFragment : Fragment() {

    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    private var zaehlerstandTextAnfang: String? = null
    private var zaehlerstandTextEnde: String? = null
    private var priceText: String? = null
    private var dateText: String? = null
    private var typeText: String? = null
    private var basePriceText: String? = null

    @Inject lateinit var viewModel: AddItemFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        val view = binding.root
        startFragment()
        dateClickListener()
        popUpMenu()
        switchOptions()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "View Created", Toast.LENGTH_SHORT).show()
    }

    private fun checkInputIsCorrectly(): Boolean {
        zaehlerstandTextAnfang = binding.addItemFragmentZaehlerstandAnfang.text.toString()
        zaehlerstandTextEnde = binding.addItemFragmentZaehlerstandEnde.text.toString()
        priceText = binding.addItemFragmentPriceKilo.text.toString()
        dateText = binding.addItemFragmentDate.text.toString()
        typeText = binding.addItemFragmentType.text.toString()
        basePriceText = binding.addItemFragmentBasePrice.text.toString()

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
        binding.addItemFragmentSave.setOnClickListener {
            var checked = checkInputIsCorrectly()
            if (checked == true) {
                var checkValues = viewModel?.checkAnfangValueNotBiggerEndeValue(
                    zaehlerstandTextAnfang!!,
                    zaehlerstandTextEnde!!
                )
                if (checkValues == true) {

                    var getDataForSwitch = viewModel?.calculateSwitch(
                        binding.addItemFragmentSwitch.isChecked!!,
                        basePriceText!!
                    )

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
        binding.addItemFragmentZaehlerstandAnfangDate.setText(datepicker.getTodaysDate().toString())
        binding.addItemFragmentZaehlerstandEndeDate.setText(datepicker.getTodaysDate().toString())
        binding.addItemFragmentDate.setText(datepicker.getTodaysDate().toString())
        binding.addItemFragmentDate.setOnClickListener {
            datepicker.initDatePicker(binding.addItemFragmentDate)
        }
        binding.addItemFragmentZaehlerstandAnfangDate.setOnClickListener {
            datepicker.initDatePicker(binding.addItemFragmentZaehlerstandAnfangDate)
        }
        binding.addItemFragmentZaehlerstandEndeDate.setOnClickListener {
            datepicker.initDatePicker(binding.addItemFragmentZaehlerstandEndeDate)
        }
    }

    fun popUpMenu() {
        binding.addItemFragmentType.setOnClickListener {
            var popupMenu: PopupMenu = PopupMenu(context, it)
            popupMenu.inflate(R.menu.add_item_fragment_pop_up_menu)
            popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(p0: MenuItem?): Boolean {
                    return when (p0?.itemId) {
                        R.id.add_item_fragment_pop_up_menu_strom -> {
                            binding.addItemFragmentType.setText("Strom")
                            true
                        }
                        R.id.add_item_fragment_pop_up_menu_gas -> {
                            binding.addItemFragmentType.setText("Gas")
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
        binding.addItemFragmentSwitch.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                Toast.makeText(context, "Monatlich", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Jährlich", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}