package com.lambdaschool.devlibs.ui.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.lambdaschool.devlibs.R

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
    private var listViewModel: ListViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_list_layout, container, false)
        val textView = root.findViewById<TextView>(R.id.text_dashboard)
        listViewModel!!.text.observe(this, Observer { s -> textView.text = s })
        return root
    }
}