package com.barut.zahlerstand.view

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.barut.zahlerstand.R
import com.barut.zahlerstand.adapter.MainFragmentAdapter
import com.barut.zahlerstand.databinding.FragmentMainBinding
import com.barut.zahlerstand.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var viewmodel: MainFragmentViewModel
    private  var adapter : MainFragmentAdapter = MainFragmentAdapter(ArrayList())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        //init()
        observeData()
        createMenu()
        getItemRemodesPos()
        return view

    }

    private fun observeData() {
        binding.fragmentMainRecylcerview.adapter = adapter
        binding.fragmentMainRecylcerview.layoutManager = LinearLayoutManager(context!!)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}