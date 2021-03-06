package com.barut.zahlerstand.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.barut.zahlerstand.R
import com.barut.zahlerstand.databinding.FragmentDetailsBinding
import com.barut.zahlerstand.viewmodel.DetailsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    private var kiloPrice: String? = ""
    private var date: String? = ""
    private var id: String? = ""
    private var type: String? = ""
    private var zaehlerstandAnfang: String? = ""
    private var zaehlerstandEnde: String? = ""
    private var uuid: String? = ""
    private var basePrice: String? = ""

    @Inject lateinit var viewmodel: DetailsFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromMainFragment()
        openAlertDialogWhenClick()
    }

    private fun getDataFromMainFragment() {
        arguments?.let {
            type = DetailsFragmentArgs.fromBundle(it).type
            id = DetailsFragmentArgs.fromBundle(it).id
            kiloPrice = DetailsFragmentArgs.fromBundle(it).price
            date = DetailsFragmentArgs.fromBundle(it).date
            zaehlerstandAnfang = DetailsFragmentArgs.fromBundle(it).zaehlerstandAnfang
            zaehlerstandEnde = DetailsFragmentArgs.fromBundle(it).zaehlerstandEnde
            basePrice = DetailsFragmentArgs.fromBundle(it).basePrice

            var consum = viewmodel?.getConsum(zaehlerstandAnfang!!, zaehlerstandEnde!!)
            if (consum != "Z??hlerstand Ende fehlt") {
                var kiloPrice = viewmodel?.getKiloPrice(consum!!, kiloPrice!!)
                var result = viewmodel?.getResult(kiloPrice!!, basePrice!!)

                binding.detailsFragmentZaehlerstandVerbrauch.text = consum.toString() + " kWh"
                binding.detailsFragmentKiloPrice.text = kiloPrice + "???"
                binding.detailsFragmentResult.text = result + "???"
                binding.detailsFragmentZaehlerstandEnde.text = zaehlerstandEnde + " kWh"

            } else {
                binding.detailsFragmentZaehlerstandVerbrauch?.text = ""
                binding.detailsFragmentKiloPrice.text = ""
                binding.detailsFragmentResult.text = ""
                binding.detailsFragmentZaehlerstandEnde.text = ""

            }
            binding.detailsFragmentType.text = type
            binding.detailsFragmentId.text = id
            binding.detailsFragmentPrice.text = this.kiloPrice + "???"
            binding.detailsFragmentDate.text = date
            binding.detailsFragmentZaehlerstandAnfang.text = zaehlerstandAnfang + " kWh"
            binding.detailsFragmentBasePrice.text = basePrice + "???"


        }
    }

    private fun openAlertDialogWhenClick() {
        binding.detailsFragmentFloatingbutton?.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToDetailsUpdateFragment(id!!)
            Navigation.findNavController(it).navigate(action)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}