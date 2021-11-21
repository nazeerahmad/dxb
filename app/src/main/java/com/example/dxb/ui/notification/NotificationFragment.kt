package com.example.dxb.ui.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dxb.R
import com.example.dxb.base.BaseFragment
import com.example.dxb.databinding.FragmentNotificationBinding


class NotificationFragment : BaseFragment<FragmentNotificationBinding,NotificationViewModel>() {



    override fun getLayoutID(): Int {
       return R.layout.fragment_notification
    }

    override fun getViewModel(): Class<NotificationViewModel> {
        return  NotificationViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}