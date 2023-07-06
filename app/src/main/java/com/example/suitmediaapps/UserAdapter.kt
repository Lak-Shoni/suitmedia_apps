package com.example.suitmediaapps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter( private val users: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(name: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
        val nama = user.first_name+" "+user.last_name

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(nama)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarImageView: ImageView = itemView.findViewById(R.id.avatar)
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_username)
        private val emailTextView: TextView = itemView.findViewById(R.id.tv_email)

        fun bind(user: User) {
            nameTextView.text = "${user.first_name} ${user.last_name}"
            emailTextView.text = user.email

            Glide.with(itemView)
                .load(user.avatar)
                .circleCrop()
                .into(avatarImageView)
        }
    }
}


