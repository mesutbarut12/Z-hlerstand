package com.barut.zahlerstand.view

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.zahlerstand.R
import com.barut.zahlerstand.adapter.MainFragmentAdapter
import com.barut.zahlerstand.viewmodel.MainFragmentViewModel


class MainFragment : Fragment() {

    var viewmodel : MainFragmentViewModel? = null
    var recyclerView : RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        init(view)
        observeData()
        createMenu()
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

    private fun createMenu(){
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_fragment_menu,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.main_fragment_menu_add -> {
                        val action = MainFragmentDirections.actionMainFragmentToAddItemFragment()
                        Navigation.findNavController(view!!).navigate(action)
                        true
                    }
                    else -> false
                }
            }

        },viewLifecycleOwner,Lifecycle.State.RESUMED)

    }


}