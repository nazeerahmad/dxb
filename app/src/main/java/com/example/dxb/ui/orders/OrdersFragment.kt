package com.example.dxb.ui.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dxb.R
import com.example.dxb.base.BaseFragment
import com.example.dxb.databinding.FragmentOrdersBinding


class OrdersFragment : BaseFragment<FragmentOrdersBinding,OrdersViewModel>() {


    override fun getLayoutID(): Int {
        return R.layout.fragment_orders
    }

    override fun getViewModel(): Class<OrdersViewModel> {
       return OrdersViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}