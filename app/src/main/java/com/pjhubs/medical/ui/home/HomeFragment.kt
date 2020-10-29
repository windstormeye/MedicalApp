package com.pjhubs.medical.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pjhubs.medical.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val items = ArrayList<HomeItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        val adapter = HomeAdapter(items)
        recyclerView.adapter = adapter
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        initItems()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun initItems() {
        items.add(HomeItem("饮食", R.drawable.ic_food))
        items.add(HomeItem("运动", R.drawable.ic_sport))
        items.add(HomeItem("用药", R.drawable.ic_pills))
        items.add(HomeItem("综合", R.drawable.ic_general))
    }
}