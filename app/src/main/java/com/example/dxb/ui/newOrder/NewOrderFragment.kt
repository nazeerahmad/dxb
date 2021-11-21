package com.example.dxb.ui.newOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dxb.R
import com.example.dxb.base.BaseFragment
import com.example.dxb.databinding.FragmentNewOrderBinding


class NewOrderFragment : BaseFragment<FragmentNewOrderBinding,NewOrderViewModel>() {

    override fun getLayoutID(): Int {
        return R.layout.fragment_new_order
    }

    override fun getViewModel(): Class<NewOrderViewModel> {
        return NewOrderViewModel::class.java
    }
}