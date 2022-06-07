package com.lexmasterteam.databaseapp1.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lexmasterteam.databaseapp1.databinding.CustomRowBinding
import com.lexmasterteam.databaseapp1.model.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()

    inner class MyViewHolder(val binding: CustomRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CustomRowBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.binding.tvId.text = currentItem.id.toString()
        holder.binding.tvFirstName.text = currentItem.firstName
        holder.binding.tvLastName.text = currentItem.lastName
        holder.binding.tvAge.text = currentItem.age.toString()

        holder.binding.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged() // aktualizacja adaptera
    }
}