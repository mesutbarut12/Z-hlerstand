package com.barut.zahlerstand.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.barut.zahlerstand.R
import com.barut.zahlerstand.model.MainFragmentModel
import com.barut.zahlerstand.view.MainFragmentDirections

class MainFragmentAdapter(var values : ArrayList<MainFragmentModel>)
    : RecyclerView.Adapter<MainFragmentAdapter.MainFragmentHolder>() {



    private lateinit var mListenerGlobal : GetRemovedPostion


    class MainFragmentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date : TextView = itemView.findViewById(R.id.main_fragment_row_date)
        val type : TextView = itemView.findViewById(R.id.main_fragment_row_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_fragment_recylerview_row,parent,false)
        return MainFragmentHolder(view)
    }

    override fun onBindViewHolder(holder: MainFragmentHolder, position: Int) {
        holder.date.setText(values.get(position).date)
        holder.type.setText(values.get(position).type)

        holder.itemView.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(
                values.get(position).zaehlerstandNach.toString(),
                values.get(position).id.toString(),
                values.get(position).price.toString(),
                values.get(position).date.toString(),
                values.get(position).zaehlerstandVor.toString(),
                values.get(position).type.toString(),
                values.get(position).zaehlerstandNach.toString(),
                values.get(position).basePrice.toString()
            )
            Navigation.findNavController(it).navigate(action)
        }

        longClickListener(holder)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    fun updateValues(datas : ArrayList<MainFragmentModel>) {
        values.clear()
        values = datas
        notifyDataSetChanged()
    }

    fun longClickListener(holder : MainFragmentHolder){
        holder.itemView.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                mListenerGlobal.pos(values.get(holder.adapterPosition).id!!.toLong())
                println("gelöscht")
                return false
            }
        })
    }
    interface GetRemovedPostion{
        fun pos(pos : Long)
    }
    fun getItemRemovesPos(mListener : GetRemovedPostion){
        this.mListenerGlobal = mListener
    }



}

