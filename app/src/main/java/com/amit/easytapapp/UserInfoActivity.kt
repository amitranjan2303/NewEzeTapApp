package com.amit.easytapapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.amit.easytapapp.adapters.EzeTapAdapterUserInfo
import com.amit.easytapapp.databinding.ActivityUserInfoBinding
import com.amit.easytapapp.models.EzeTapModel
import com.amit.easytapapp.models.UIData
import java.util.*

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding
    private var mAdapter: EzeTapAdapterUserInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)

        val uiDataBundle: Bundle? = intent?.extras
        val model: EzeTapModel? =
            if (Build.VERSION.SDK_INT >= 33) {
                uiDataBundle?.getParcelable("user-info", EzeTapModel::class.java)
            } else {
                uiDataBundle?.getParcelable("user-info")
            }
        Log.d("Amit ", "UserInfo " + model)
        setUpRecyclerView(model?.uidata)
    }

    fun setUpRecyclerView(list: ArrayList<UIData>?) {
        binding.rv.layoutManager = LinearLayoutManager(this)
        mAdapter = EzeTapAdapterUserInfo()
        mAdapter?.updateList(list)
        binding.rv.adapter = mAdapter
    }

}