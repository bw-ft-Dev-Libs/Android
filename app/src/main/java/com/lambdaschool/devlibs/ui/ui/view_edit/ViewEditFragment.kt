package com.lambdaschool.devlibs.ui.ui.view_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.model.DevLibLocal
import com.lambdaschool.devlibs.tempTemplatesToInject
import com.lambdaschool.devlibs.ui.ui.create.CreateVMFactory
import com.lambdaschool.devlibs.ui.ui.create.CreateViewModel
import com.lambdaschool.devlibs.ui.ui.create.CreateViewModel.Companion.arrayOfProvided
import okio.utf8Size


class ViewEditFragment () : Fragment() {
    lateinit var createViewModel: CreateViewModel
     var fieldLength:Int = 0


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
        createViewModel =activity?.run {
            ViewModelProviders.of(this, CreateVMFactory).get(CreateViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        val recieved:DevLibLocal = arguments?.getSerializable("const") as DevLibLocal


        Toast.makeText(root.context,arrayOfProvided[0],Toast.LENGTH_SHORT).show()

        //set the required number of fields via the category id, if multiple libs were implemented,
        // with varying number of fields, this could be changed to a DevLibID either in the model or
        // by checking the begining word or similar
        tempTemplatesToInject[0].size
        fieldLength = tempTemplatesToInject[recieved.categoryId].size


        return root
    }
}