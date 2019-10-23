package com.lambdaschool.devlibs.ui.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        createViewModel = ViewModelProviders.of(this).get(CreateViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_create, container, false)
        val textView = root.findViewById<TextView>(R.id.text_notifications)
        createViewModel.text.observe(this, Observer { s -> textView.text = s })



        return root
    }
}