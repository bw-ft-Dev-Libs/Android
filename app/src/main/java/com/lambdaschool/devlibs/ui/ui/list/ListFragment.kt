package com.lambdaschool.devlibs.ui.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.model.DevLibBackend
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

        val root = inflater.inflate(R.layout.fragment_list_layout, container, false)
        // val textView = root.findViewById<TextView>(R.id.list_tv)
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        recyclerView = root.recycle_view

        setupRecyclerView()

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        adapter = ReAdapter(this)

        recyclerView.adapter = adapter
       /*  val test =listOf<DevLibBackend>(DevLibBackend(1,"asdf",1,3),DevLibBackend(1,"asdf",1,3),DevLibBackend(1,"asdf",1,3),DevLibBackend(1,"asdf",1,3))
        adapter.submitList(test)
        adapter.notifyDataSetChanged()*/
        listViewModel.list.observe(this, Observer<List<DevLibBackend>> {
            updateRecyclerView(adapter, it)
        })


    }
    fun updateRecyclerView(adapter: ReAdapter, devLibBackendlist: List<DevLibBackend>) {
        adapter.submitList(devLibBackendlist)
        adapter.notifyDataSetChanged()
    }
}