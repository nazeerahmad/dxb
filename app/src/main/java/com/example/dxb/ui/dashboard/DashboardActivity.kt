package com.example.dxb.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.dxb.R
import com.example.dxb.base.BaseActivity
import com.example.dxb.databinding.ActivityDashboardBinding
import com.example.dxb.ui.home.HomeFragment
import com.example.dxb.ui.myAccount.MyAccountFragment
import com.example.dxb.ui.newOrder.NewOrderFragment
import com.example.dxb.ui.notification.NotificationFragment
import com.example.dxb.ui.orders.OrdersFragment

class DashboardActivity : BaseActivity<ActivityDashboardBinding,DashboardViewModel>(),View.OnClickListener {

    override fun getLayoutId(): Int {
        return  R.layout.activity_dashboard
    }

    override fun getViewModel(): Class<DashboardViewModel> {
        return DashboardViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callfragemnt(HomeFragment())

        dataBinding.btnHome.setOnClickListener (this)
        dataBinding.btnOrder.setOnClickListener (this)
        dataBinding.btnNeworder.setOnClickListener (this)
        dataBinding.btnNotification.setOnClickListener (this)
        dataBinding.btnMyaccount.setOnClickListener (this)


    }

    fun addFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment,
        fragmentTag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment, fragmentTag)
            .disallowAddToBackStack()
            .commit()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_home->{
                callfragemnt(HomeFragment())
                dataBinding.imHome.setImageResource(R.drawable.home_dark)
                dataBinding.txtHome.setTextColor(resources.getColor(R.color.purple_500))
                dataBinding.imOrder.setImageResource(R.drawable.order)
                dataBinding.txtorder.setTextColor(resources.getColor(R.color.black))
                dataBinding.imnotification.setImageResource(R.drawable.notif)
                dataBinding.txtNotification.setTextColor(resources.getColor(R.color.black))
                dataBinding.imUser.setImageResource(R.drawable.user)
                dataBinding.textUser.setTextColor(resources.getColor(R.color.black))
            }
            R.id.btn_order->{
                callfragemnt(OrdersFragment())
                dataBinding.imOrder.setImageResource(R.drawable.order_dark)
                dataBinding.txtorder.setTextColor(resources.getColor(R.color.purple_500))
                dataBinding.imHome.setImageResource(R.drawable.home)
                dataBinding.txtHome.setTextColor(resources.getColor(R.color.black))
                dataBinding.imnotification.setImageResource(R.drawable.notif)
                dataBinding.txtNotification.setTextColor(resources.getColor(R.color.black))
                dataBinding.imUser.setImageResource(R.drawable.user)
                dataBinding.textUser.setTextColor(resources.getColor(R.color.black))
            }
            R.id.btn_notification->{
                callfragemnt(NotificationFragment())
                dataBinding.imnotification.setImageResource(R.drawable.notif_dark)
                dataBinding.txtNotification.setTextColor(resources.getColor(R.color.purple_500))
                dataBinding.imOrder.setImageResource(R.drawable.order)
                dataBinding.txtorder.setTextColor(resources.getColor(R.color.black))
                dataBinding.imHome.setImageResource(R.drawable.home)
                dataBinding.txtHome.setTextColor(resources.getColor(R.color.black))
                dataBinding.imUser.setImageResource(R.drawable.user)
                dataBinding.textUser.setTextColor(resources.getColor(R.color.black))
            }
            R.id.btn_myaccount->{
                callfragemnt(MyAccountFragment())
                dataBinding.imUser.setImageResource(R.drawable.user_dark)
                dataBinding.textUser.setTextColor(resources.getColor(R.color.purple_500))
                dataBinding.imnotification.setImageResource(R.drawable.notif)
                dataBinding.txtNotification.setTextColor(resources.getColor(R.color.black))
                dataBinding.imOrder.setImageResource(R.drawable.order)
                dataBinding.txtorder.setTextColor(resources.getColor(R.color.black))
                dataBinding.imHome.setImageResource(R.drawable.home)
                dataBinding.txtHome.setTextColor(resources.getColor(R.color.black))
            }
            R.id.btn_neworder->{
                callfragemnt(NewOrderFragment())
                dataBinding.imUser.setImageResource(R.drawable.user)
                dataBinding.imHome.setImageResource(R.drawable.home)
                dataBinding.imOrder.setImageResource(R.drawable.order)
                dataBinding.imnotification.setImageResource(R.drawable.notif)
            }
        }
    }

    fun callfragemnt(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.framecontainer, fragment)
            .commit()
    }


}