package com.amit.easytapapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amit.easytapapp.adapters.EzeTapAdapter
import com.amit.easytapapp.callback.ItemActionCallBack
import com.amit.easytapapp.databinding.ActivityMainBinding
import com.amit.easytapapp.models.EzeTapModel
import com.amit.easytapapp.models.UIData
import com.amit.easytapapp.viewmodels.EzeTapViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: EzeTapViewModel
    private var mAdapter: EzeTapAdapter? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(EzeTapViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel.getNetworkResponse()
        setUpObserver()
    }

    fun setUpObserver() {

        mViewModel.getNetworkResponseLiveData().observe(this, Observer {
            Log.d("Act", "response " + it.headingText + "")
            binding?.headingTitle = it.headingText
            it?.uidata?.let { itemList ->
                setUpRecyclerView(itemList)
            }
        })

        mViewModel.getNetworkErrorLiveData().observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })
    }

    fun setUpRecyclerView(list: ArrayList<UIData>) {
        binding.rv.layoutManager = LinearLayoutManager(this)
        mAdapter = EzeTapAdapter()
        mAdapter?.updateList(list)
        mAdapter?.setItemCallBack(itemActionCallBack)
        binding.rv.adapter = mAdapter
    }

    private var itemActionCallBack: ItemActionCallBack = object : ItemActionCallBack {
        override fun onItemClick(position: Int?) {
            Log.d("Amit", "" + position)
            Log.d("Amit", "" + mAdapter?.getItemList())

            val itemList = mAdapter?.getItemList()
            val lastIndex = itemList?.size?.minus(1)
            itemList?.removeAt(lastIndex!!)
            val ezeTapModel = EzeTapModel()
            ezeTapModel.uidata = itemList
            moveToUserInfo(ezeTapModel)
        }
    }

    private fun moveToUserInfo(ezeTapModel: EzeTapModel) {
        val infoActivityIntent = Intent(this, UserInfoActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("user-info", ezeTapModel)
        infoActivityIntent.putExtras(bundle)
        startActivity(infoActivityIntent)
    }
}