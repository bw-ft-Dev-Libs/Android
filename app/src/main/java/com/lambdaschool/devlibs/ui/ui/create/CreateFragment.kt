package com.lambdaschool.devlibs.ui.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.lambdaschool.devlibs.R

class CreateFragment : Fragment() {
/*
*
*
*   create fragment should
*
*   1. prompt users to select a catagory
*   2. be prompted for word by word input
*   3. attempt to save locally and remotely
*   4. either show user finished madlib
*                  or
*        redirect to a view fragment
*
*
*
*
*
* */
   lateinit var createViewModel:CreateViewModel
    lateinit var supportFragmentManager: FragmentManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
       // createViewModel = ViewModelProviders.of(this).get(CreateViewModel::class.java)
        supportFragmentManager=fragmentManager as FragmentManager
        val root = inflater.inflate(R.layout.fragment_create, container, false)
        val textView = root.findViewById<TextView>(R.id.text_notifications)
        createViewModel =activity?.run {
            ViewModelProviders.of(this,CreateVMFactory).get(CreateViewModel::class.java)
        } ?: throw Exception("Invalid Activity")




        createViewModel.text.observe(this, Observer { s -> textView.text = s })
        createViewModel.settext("orig")

        supportFragmentManager.beginTransaction()
                .add(R.id.frag_create_center_holder, CreateSubFragment()).commit()



        return root
    }
}