package com.barut.zahlerstand.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.zahlerstand.R
import com.barut.zahlerstand.model.MainFragmentModel

class MainFragmentAdapter(val values : ArrayList<MainFragmentModel>)
    : RecyclerView.Adapter<MainFragmentAdapter.MainFragmentHolder>() {

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
    }

    override fun getItemCount(): Int {
        return values.size
    }
}