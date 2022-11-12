package com.example.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.common.Constants
import com.example.countries.domain.model.CountriesModel
import com.example.countries.domain.model.FilterChildData
import com.example.countries.domain.model.FilterParentModel



class FilterAdapter(val list: MutableList<FilterParentModel>, private val onItemClicked: (FilterParentModel) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType== Constants.PARENT){
            val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.parent_row, parent,false)
            GroupViewHolder(rowView)
        } else {
            val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.child_row, parent,false)
            ChildViewHolder(rowView)
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val dataList = list[position]
        if (dataList.type == Constants.PARENT) {
            holder as GroupViewHolder
            holder.apply {
                parentTV?.text = dataList.parentTitle
                itemView.setOnClickListener{
                    expandOrCollapseParentItem(dataList,position)
                }
            }
        } else {
            holder as ChildViewHolder
            holder.apply {
                val singleService = dataList.subList.first()
                childTV?.text =singleService.childTitle
                itemView.setOnClickListener {
                    checkBox?.isChecked = true
                    onItemClicked(dataList)
                }
            }
        }
    }

    private fun expandOrCollapseParentItem(singleBoarding: FilterParentModel,position: Int) {
        if (singleBoarding.isExpanded) {
            collapseParentRow(position)
        } else {
            expandParentRow(position)
        }
    }

    private fun expandParentRow(position: Int){
        val currentBoardingRow = list[position]
        val services = currentBoardingRow.subList
        currentBoardingRow.isExpanded = true
        var nextPosition = position
        if(currentBoardingRow.type==Constants.PARENT){
            services.forEach { service ->
                val parentModel =  FilterParentModel()
                parentModel.type = Constants.CHILD
                val subList : ArrayList<FilterChildData> = ArrayList()
                subList.add(service)
                parentModel.subList=subList
                list.add(++nextPosition,parentModel)
            }
            notifyDataSetChanged()
        }
    }

    private fun collapseParentRow(position: Int){
        val currentBoardingRow = list[position]
        val services = currentBoardingRow.subList
        list[position].isExpanded = false
        if(list[position].type==Constants.PARENT){
            services.forEach { _ ->
                list.removeAt(position + 1)
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int = list[position].type

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }



    class GroupViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val parentTV = row.findViewById(R.id.parent_Title) as TextView?
    }

    class ChildViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val childTV = row.findViewById(R.id.child_Title) as TextView?
        val checkBox = row.findViewById(R.id.checkBox) as CheckBox?
    }
}