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

    var viewmodel: MainFragmentViewModel? = null
    var recyclerView: RecyclerView? = null
    var adapter = MainFragmentAdapter(ArrayList())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        init(view)
        observeData()
        createMenu()
        getItemRemodesPos()
        return view
    }


    private fun init(view: View) {
        viewmodel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        recyclerView = view.findViewById(R.id.fragment_main_recylcerview)
    }

    private fun observeData() {
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context!!)

        viewmodel?.liveData?.observe(viewLifecycleOwner, Observer {
            var arrayList = ArrayList(it)
            adapter.updateValues(arrayList)
        })
    }

    private fun getItemRemodesPos() {
        adapter.getItemRemovedPos(object : MainFragmentAdapter.GetRemovedPostion {
            override fun pos(pos: Long) {
                viewmodel?.deleteSpeciallyItem(pos)
            }
        })
    }

    private fun createMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.main_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.main_fragment_menu_add -> {
                        val action = MainFragmentDirections.actionMainFragmentToAddItemFragment()
                        val navigation = Navigation.findNavController(view!!)
                        navigation.navigate(action)
                        true
                    }
                    R.id.main_fragment_menu_delete_all -> {
                        //viewmodel?.deleteAllValuesInSQLite()
                        viewmodel?.deleteAllValuesInSQLite()
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }


}