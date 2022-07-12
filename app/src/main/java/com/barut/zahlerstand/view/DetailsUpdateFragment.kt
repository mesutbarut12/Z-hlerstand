package com.barut.zahlerstand.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.barut.zahlerstand.databinding.FragmentDetailsUpdateBinding
import com.barut.zahlerstand.viewmodel.BaseViewModel
import com.barut.zahlerstand.viewmodel.DetailsUpdateFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class DetailsUpdateFragment : Fragment(), CoroutineScope {

    private var _binding: FragmentDetailsUpdateBinding? = null
    private val binding get() = _binding!!

    private var idFromParent: Int? = null

    private var kiloPrice: String = ""
    private var date: String = ""
    private var id: String = ""
    private var type: String = ""
    private var zaehlerstandAnfang: String = ""
    private var zaehlerstandEnde: String = ""

    @Inject
    lateinit var viewModel: DetailsUpdateFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        getIdFromParent()
        whenClickSave()
        return view
    }

    private fun getIdFromParent() {
        arguments?.let {
            idFromParent = DetailsUpdateFragmentArgs.fromBundle(it).id.toInt()
        }
    }

    fun getValues() {
        kiloPrice = binding.detailsFragmentUpdatePrice.text.toString()
        date = binding.detailsFragmentUpdateDate.text.toString()
        id = binding.detailsFragmentUpdateId.text.toString()
        type = binding.detailsFragmentUpdateType.text.toString()
        zaehlerstandAnfang = binding.detailsFragmentUpdateBegin.text.toString()
        zaehlerstandEnde = binding.detailsFragmentUpdateEnd.text.toString()
    }

    private fun whenClickSave() {
        binding.detailsFragmentUpdateSave.setOnClickListener {
            getValues()
            if (kiloPrice.isNotEmpty() ||
                date.isNotEmpty() ||
                type.isNotEmpty() ||
                zaehlerstandAnfang.isNotEmpty() ||
                zaehlerstandEnde.isNotEmpty()
            ) {
                if (kiloPrice.isNotEmpty())
                    viewModel.updateDataPrice(idFromParent!!.toLong(), kiloPrice)
                if (date.isNotEmpty())
                    viewModel.updateDataBaseDate(idFromParent!!.toLong(), date)
                if (type.isNotEmpty())
                    viewModel.updateDataBaseType(idFromParent!!.toLong(), type)
                if (zaehlerstandAnfang.isNotEmpty())
                    viewModel.updateDataZaehlerstandVor(idFromParent!!.toLong(), zaehlerstandAnfang)
                if (zaehlerstandEnde.isNotEmpty())
                    viewModel.updateDataZaehlerstandNach(idFromParent!!.toLong(), zaehlerstandEnde)
                launch {
                    var data = viewModel.getDetailsData(idFromParent!!.toLong())
                    val action = DetailsUpdateFragmentDirections.actionDetailsUpdateFragmentToDetailsFragment(
                        data?.id.toString(),
                        data?.price.toString(),
                        data?.date.toString(),
                        data?.zaehlerstandVor.toString(),
                        data?.type.toString(),
                        data?.zaehlerstandNach.toString(),
                        data?.basePrice.toString()
                    )
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    private fun updateData(string: String, type: String) {
        viewModel.updateDataZaehlerstandNach(idFromParent!!.toLong(), string)
    }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


}