package com.example.dxb.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dxb.R
import com.example.dxb.base.BaseFragment
import com.example.dxb.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>() {


    override fun getLayoutID(): Int {
        return R.layout.fragment_home
    }

    override fun getViewModel(): Class<HomeViewModel> {
       return HomeViewModel::class.java
    }


}