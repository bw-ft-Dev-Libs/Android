package com.lambdaschool.devlibs.ui.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.model.DevLibLocal
import kotlinx.android.synthetic.main.fragment_list_layout.view.*

class ListFragment : Fragment() {
    /*
    *
    *
    *   list fragment should
    *
    *   1. display a list of madlibs to the user
    *   2. may also be able to sort list by category or user or similar
    *
    *
    *
    *
    *
    * */
    lateinit var listViewModel: ListViewModel
    lateinit var adapter:ReAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_list_layout, container, false)
        val textView = root.findViewById<TextView>(R.id.list_tv)
        val recycle_view = root.recycle_view

        var initialList= listOf<DevLibLocal>()
        if (listViewModel.list.value !=null) {
            initialList = listViewModel.list.value as List<DevLibLocal>
        }
        val manager = StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
         adapter = ReAdapter(initialList,this)
        recycle_view.layoutManager = manager
        recycle_view.adapter = adapter
       // listViewModel!!.list.observe(this, Observer { s -> textView.text = s.toString() })
        return root
    }
}