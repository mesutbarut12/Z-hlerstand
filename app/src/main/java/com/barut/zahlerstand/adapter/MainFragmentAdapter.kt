package com.barut.zahlerstand.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.barut.zahlerstand.R
import com.barut.zahlerstand.databinding.MainFragmentRecylerviewRowBinding
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.view.MainFragmentDirections
import kotlinx.android.synthetic.main.main_fragment_recylerview_row.view.*

class MainFragmentAdapter(var values: ArrayList<MainFragmentModel>) :
    RecyclerView.Adapter<MainFragmentAdapter.MainFragmentHolder>(), ItemClickListener {


    private lateinit var mListenerGlobal: GetRemovedPostion


    class MainFragmentHolder(itemView: MainFragmentRecylerviewRowBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var item = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MainFragmentRecylerviewRowBinding>(
            inflater,
            R.layout.main_fragment_recylerview_row,
            parent,
            false
        )
        return MainFragmentHolder(view)
    }

    override fun onBindViewHolder(holder: MainFragmentHolder, position: Int) {
        holder.item.model = values[position]
        holder.item.listener = this

        longClickListener(holder)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    fun updateValues(datas: ArrayList<MainFragmentModel>) {
        values.clear()
        values = datas
        notifyDataSetChanged()
    }

    fun longClickListener(holder: MainFragmentHolder) {
        holder.itemView.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {
                mListenerGlobal.pos(values.get(holder.adapterPosition).id!!.toLong())
                println("gelöscht")
                return false
            }
        })
    }

    interface GetRemovedPostion {
        fun pos(pos: Long)
    }

    fun getItemRemovesPos(mListener: GetRemovedPostion) {
        this.mListenerGlobal = mListener
    }

    override fun itemClick(v: View) {
        val id = v.main_fragment_id.text.toString().toLong()
        values.forEach {
            if (id == it.id) {
                val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(
                    "0",
                    it.id.toString(),
                    it.price.toString(),
                    it.date.toString(),
                    it.zaehlerstandVor.toString(),
                    it.type.toString(),
                    it.zaehlerstandNach.toString(),
                    it.basePrice.toString()
                )
                Navigation.findNavController(v).navigate(action)
            }
        }
    }

}

