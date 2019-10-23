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
import kotlinx.android.synthetic.main.fragment_create.*

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
   lateinit var notificationsViewModel:NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_create, container, false)
        val textView = root.findViewById<TextView>(R.id.text_notifications)
        notificationsViewModel.text.observe(this, Observer { s -> textView.text = s })
  notificationsViewModel.settext("blah")



        return root
    }

    override fun onStart() {

        super.onStart()
    }
}