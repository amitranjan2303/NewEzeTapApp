package com.amit.easytapapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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

    private fun setUpObserver() {

        mViewModel.getNetworkResponseLiveData().observe(this, {
            Log.d("Act", "response " + it.headingText + "")
            binding.headingTitle = it.headingText
            it?.uiData?.let { itemList ->
                setUpRecyclerView(itemList)
            }
        })

        mViewModel.getResponseBitmapLiveData().observe(this, {
            it?.let {
                binding.imgHead.setImageBitmap(it)
            }
        })

        mViewModel.getNetworkErrorLiveData().observe(this, {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })
    }

    private fun setUpRecyclerView(list: ArrayList<UIData>) {
        binding.rv.layoutManager = LinearLayoutManager(this)
        mAdapter = EzeTapAdapter()
        mAdapter?.updateList(list)
        mAdapter?.setItemCallBack(itemActionCallBack)
        binding.rv.adapter = mAdapter
    }

    private var itemActionCallBack: ItemActionCallBack = object : ItemActionCallBack {
        override fun onItemClick(position: Int?) {
            val itemList = mAdapter?.getItemList()
            val lastIndex = itemList?.size?.minus(1)
            itemList?.removeAt(lastIndex!!)
            val ezeTapModel = EzeTapModel()
            ezeTapModel.uiData = itemList
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