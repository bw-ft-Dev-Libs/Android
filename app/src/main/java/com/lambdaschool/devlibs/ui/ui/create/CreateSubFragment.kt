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

class CreateSubFragment(list: MutableList<String>?) :Fragment() {


    /*
  *
  *
  *   create sub fragment should
  *
  *   1. if created without arguments, should prompt user to select a devlibs category
  *   2. then pass to a new instance of itself the category
  *   3.  then just keep doing this til we've got a full list
  *    4. finally redirect to view fragment
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
        val root = inflater.inflate(R.layout.fragment_create_sub, container, false)
        val textView = root.findViewById<TextView>(R.id.text_notifications)
        createViewModel.text.observe(this, Observer { s -> textView.text = s })



        return root
    }
}