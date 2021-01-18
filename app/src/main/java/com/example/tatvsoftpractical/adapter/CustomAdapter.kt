package com.example.tatvsoftpractical.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tatvsoftpractical.R
import com.example.tatvsoftpractical.model.data
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by Belal on 6/19/2017.
 */

class CustomAdapter(val mContext: Context, val userList: List<data.users>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_row_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(mContext, userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(
            mContext: Context,
            users: data.users
        ) {
            val ivUserImage = itemView.findViewById(R.id.ivUserImage) as CircleImageView
            val txtUserName = itemView.findViewById(R.id.txtUserName) as AppCompatTextView
            val llOddLayout = itemView.findViewById(R.id.llOddLayout) as LinearLayout
            val llthree = itemView.findViewById(R.id.llthree) as LinearLayout
            val llFive = itemView.findViewById(R.id.llFive) as LinearLayout
            val iv1_odd = itemView.findViewById(R.id.iv1_odd) as AppCompatImageView
            val iv2_odd = itemView.findViewById(R.id.iv2_odd) as AppCompatImageView
            val iv3_odd = itemView.findViewById(R.id.iv3_odd) as AppCompatImageView
            val iv4_odd = itemView.findViewById(R.id.iv4_odd) as AppCompatImageView
            val iv5_odd = itemView.findViewById(R.id.iv5_odd) as AppCompatImageView


            val llEvenLayout = itemView.findViewById(R.id.llEvenLayout) as LinearLayout
            val llTwo_even = itemView.findViewById(R.id.llTwo_even) as LinearLayout
            val llFour = itemView.findViewById(R.id.llFour) as LinearLayout
            val iv1_even = itemView.findViewById(R.id.iv1_even) as AppCompatImageView
            val iv2_even = itemView.findViewById(R.id.iv2_even) as AppCompatImageView
            val iv3_even = itemView.findViewById(R.id.iv3_even) as AppCompatImageView
            val iv4_even = itemView.findViewById(R.id.iv4_even) as AppCompatImageView

            txtUserName.text = users.name
            Glide.with(mContext)
                .load(users.image)
                .into(ivUserImage)

            if (users.items!!.size % 2 == 0){
                llEvenLayout.visibility = View.VISIBLE
                if (users.items.size > 2){
                    llFour.visibility = View.VISIBLE
                    llTwo_even.visibility  = View.VISIBLE

                    Glide.with(mContext)
                        .load(users.items.get(0))
                        .error(R.drawable.ic_launcher_background)
                        .into(iv1_even)

                    Glide.with(mContext)
                        .load(users.items.get(1))
                        .into(iv2_even)

                    if (users.items.get(2) != null){
                        iv3_even.visibility = View.VISIBLE
                        Glide.with(mContext)
                            .load(users.items.get(2))
                            .into(iv3_even)
                    }else{
                        iv3_even.visibility = View.GONE
                    }


                    if (users.items.get(3) != null){
                        iv4_even.visibility = View.VISIBLE
                        Glide.with(mContext)
                            .load(users.items.get(3))
                            .into(iv4_even)
                    }else{
                        iv4_even.visibility = View.GONE
                    }

                }else{
                    llFour.visibility = View.GONE
                    llTwo_even.visibility = View.VISIBLE

                    if (users.items.get(0) != null){
                        iv1_even.visibility = View.VISIBLE
                        Glide.with(mContext)
                            .load(users.items.get(0))
                            .into(iv1_even)
                    }else{
                        iv1_even.visibility = View.GONE
                    }

                    if (users.items.get(1) != null){
                        iv2_even.visibility = View.VISIBLE
                        Glide.with(mContext)
                            .load(users.items.get(1))
                            .into(iv2_even)
                    }else{
                        iv2_even.visibility = View.GONE
                    }
                }
            }else{
                if (users.items.size == 1){
                    llthree.visibility = View.GONE
                    llFive.visibility = View.GONE
                    iv1_odd.visibility = View.VISIBLE

                    Glide.with(mContext)
                        .load(users.items.get(0))
                        .into(iv1_odd)
                }else if (users.items.size <= 3){
                    llthree.visibility = View.VISIBLE
                    llFive.visibility = View.GONE
                    iv1_odd.visibility = View.VISIBLE
                    iv2_odd.visibility = View.VISIBLE

                    Glide.with(mContext)
                        .load(users.items.get(0))
                        .into(iv1_odd)

                    Glide.with(mContext)
                        .load(users.items.get(1))
                        .into(iv2_odd)

                    if (users.items.get(2) != null){
                        iv3_odd.visibility = View.VISIBLE
                        Glide.with(mContext)
                            .load(users.items.get(2))
                            .into(iv3_odd)
                    }else{
                        iv3_odd.visibility = View.GONE
                    }
                }else{
                    llthree.visibility = View.VISIBLE
                    llFive.visibility = View.VISIBLE
                    iv1_odd.visibility = View.VISIBLE
                    iv2_odd.visibility = View.VISIBLE
                    iv3_odd.visibility = View.VISIBLE

                    Glide.with(mContext)
                        .load(users.items.get(0))
                        .into(iv1_odd)

                    Glide.with(mContext)
                        .load(users.items.get(1))
                        .into(iv2_odd)
                    Glide.with(mContext)
                        .load(users.items.get(2))
                        .into(iv3_odd)



                    if (users.items.get(3) != null){
                        iv4_odd.visibility = View.VISIBLE
                        Glide.with(mContext)
                            .load(users.items.get(3))
                            .into(iv4_odd)
                    }else{
                        iv4_odd.visibility = View.GONE
                    }

                    if (users.items.get(4) != null){
                        iv5_odd.visibility = View.VISIBLE
                        Glide.with(mContext)
                            .load(users.items.get(4))
                            .into(iv5_odd)
                    }else{
                        iv5_odd.visibility = View.GONE
                    }
                }
            }
        }
    }
}