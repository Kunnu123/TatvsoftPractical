package com.example.tatvsoftpractical.view

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var mEndWithAuto = false
    private var isHashmore = false
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

        bindingMainBinding?.swipe?.setOnRefreshListener {
            intoffset = 0
            usersdata.clear()
            isHashmore = false
            getUserData(intoffset.toString())
        }

        bindingMainBinding?.rvList?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val mLayoutManager = bindingMainBinding?.rvList?.layoutManager
                val visibleItemCount = mLayoutManager!!.childCount
                val totalItemCount = mLayoutManager.itemCount

                var firstVisibleItemPosition = 0
                if (mLayoutManager is LinearLayoutManager) {
                    firstVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
                }

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                    if (!mEndWithAuto) {
                        mEndWithAuto = true

                        if (totalItemCount > 2) {
                            intoffset = intoffset + 10
                            if(isHashmore){
                                getUserData((intoffset).toString())
                            }
                        }
                    }
                } else {
                    mEndWithAuto = false
                }

            }
        })

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
                isHashmore = it.data?.has_more!!
                usersdata.addAll(it.data.usersdata!!)
                mEndWithAuto = false
                customAdapter?.notifyDataSetChanged()
                bindingMainBinding?.swipe!!.isRefreshing = false
            } else {
                //No Data Logic
            }

        })

    }
}