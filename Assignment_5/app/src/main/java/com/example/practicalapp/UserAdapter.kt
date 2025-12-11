package com.example.practicalapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class UserAdapter(private var items: MutableList<User>, private val listener: Listener) :
    RecyclerView.Adapter<UserAdapter.VH>() {

    interface Listener {
        fun onEdit(user: User)
        fun onDelete(user: User)
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val iv: ImageView = view.findViewById(R.id.ivUser)
        val btnEdit: Button = view.findViewById(R.id.btnEdit)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val u = items[position]
        holder.tvName.text = u.name
        if (u.imageUrl.isNotBlank()) holder.iv.load(u.imageUrl) else holder.iv.setImageResource(0)
        holder.btnEdit.setOnClickListener { listener.onEdit(u) }
        holder.btnDelete.setOnClickListener { listener.onDelete(u) }
    }

    override fun getItemCount(): Int = items.size

    fun update(newList: MutableList<User>) {
        items = newList
        notifyDataSetChanged()
    }
}
