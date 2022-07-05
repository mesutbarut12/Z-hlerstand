package com.barut.zahlerstand.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.zahlerstand.R
import com.barut.zahlerstand.adapter.MainFragmentAdapter
import com.barut.zahlerstand.viewmodel.MainFragmentViewModel


class MainFragment : Fragment() {

    var viewmodel : MainFragmentViewModel? = null
    var recyclerView : RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        init(view)
        observeData()
        return view
    }

    private fun init(view : View){
        viewmodel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)

        recyclerView = view.findViewById(R.id.fragment_main_recylcerview)
    }
    private fun observeData(){
        viewmodel?.liveData?.observe(viewLifecycleOwner,Observer{
           recyclerView?.adapter = MainFragmentAdapter(it)
           recyclerView?.layoutManager = LinearLayoutManager(context!!)
        })
    }


}