package com.lambdaschool.devlibs.ui.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.model.DevLibBackend
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
    lateinit var adapter: ReAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_list_layout, container, false)
        val textView = root.findViewById<TextView>(R.id.list_tv)
        recyclerView = root.recycle_view


        val manager = LinearLayoutManager(context)
        setupRecyclerView()

        return root

    }

    fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        adapter = ReAdapter(this)

        recyclerView.adapter = adapter


        listViewModel.getDevLibs().observe(this, Observer<List<DevLibBackend>> {
            updateRecyclerView(adapter, it as MutableList<DevLibBackend>)
        })


    }
    fun updateRecyclerView(adapter: ReAdapter, devLibBackendlist: MutableList<DevLibBackend>) {
        adapter.submitList(devLibBackendlist as List<DevLibBackend>)
        adapter.notifyDataSetChanged()
    }
}