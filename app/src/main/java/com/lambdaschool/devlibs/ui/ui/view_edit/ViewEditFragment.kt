package com.lambdaschool.devlibs.ui.ui.view_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lambdaschool.devlibs.Prefs
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.model.DevLibLocal
import com.lambdaschool.devlibs.ui.ui.listview.HomeFragment
import com.lambdaschool.devlibs.ui.ui.listview.HomeViewModel


class ViewEditFragment () : Fragment() {



        /*
        }
        *
        *
        *   ViewEdit fragment should
        *
        *
        *   1 figure out from args how to build the display
        *   2 Allow for editing if userId=madlib's creator's ID
        *   3 provide the opportunity to submit changes or delete the madlib locally and remotely
        *
        *
        *
        *
        * */


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_view_edit_layout, container, false)
    //    val textView = root.findViewById<TextView>(R.id.text_home)
       // homeViewModel!!.text.observe(this, Observer { s -> textView.text = s })

        val recieved:DevLibLocal = arguments?.getSerializable("const") as DevLibLocal
    /*  if (recieved.lib != "") {
          Toast.makeText(view!!.context,recieved.lib,Toast.LENGTH_SHORT ).show()
      }*/

        Toast.makeText(root.context,recieved.lib,Toast.LENGTH_SHORT).show()



        return root
    }
}