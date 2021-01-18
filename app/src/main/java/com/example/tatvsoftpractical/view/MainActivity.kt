package com.example.tatvsoftpractical.view

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tatvsoftpractical.R
import com.example.tatvsoftpractical.adapter.CustomAdapter
import com.example.tatvsoftpractical.databinding.ActivityMainBinding
import com.example.tatvsoftpractical.model.data
import com.example.tatvsoftpractical.viewmodel.UserdataViewModel

class MainActivity : AppCompatActivity() {
    var bindingMainBinding: ActivityMainBinding? = null

    var userdataViewModel: UserdataViewModel? = null
    var customAdapter: CustomAdapter? = null
    var intoffset: Int = 0
    val usersdata = arrayListOf<data.users>()
    var progressDialog : ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog?.setTitle("Fetching Data")
        progressDialog?.setMessage("Loading...")


        userdataViewModel = UserdataViewModel(this.application)
        setData(usersdata)
        getUserData(intoffset.toString())

        bindingMainBinding?.txtMore?.setOnClickListener {
            intoffset = intoffset + 10
            getUserData((intoffset).toString())
        }

    }

    fun setData(usersData: List<data.users>?) {
        customAdapter = CustomAdapter(this@MainActivity, usersData!!)
        bindingMainBinding?.rvList?.adapter = customAdapter
    }

    private fun getUserData(offset: String?) {
        progressDialog?.show()
        userdataViewModel?.getUserLiveData(offset)?.observe(this, androidx.lifecycle.Observer {
            progressDialog?.dismiss()
            if (it.status!!) {
                if (it.data?.has_more!!) {
                    bindingMainBinding?.txtMore?.visibility  = View.VISIBLE
                } else {
                    bindingMainBinding?.txtMore?.visibility  = View.GONE
                }
                usersdata?.addAll(it.data.usersdata!!)
                customAdapter?.notifyDataSetChanged()
            } else {
                //No Data Logic
            }

        })

    }
}