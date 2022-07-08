package com.barut.zahlerstand.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.barut.zahlerstand.R
import com.barut.zahlerstand.databinding.FragmentDetailsBinding
import com.barut.zahlerstand.model.MainFragmentModel

class DetailsFragment : Fragment() {

    private lateinit var dataBinding : FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_details,
        container,false)

        getDataFromMainFragment()
        return dataBinding.root
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
            dataBinding.viewmodel

        }
    }




}