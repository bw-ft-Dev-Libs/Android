package com.lambdaschool.devlibs.ui.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.tempWordNeeds
import kotlinx.android.synthetic.*

class CreateEntryFragment(list: MutableList<String> = mutableListOf<String>()) :Fragment() {


    /*
  *
  *
  *   create sub fragment should
  *
  *   1. if created without arguments, should prompt user to select a devlibs category
  *   2. then pass on to requesting words based on catergory chosen,
  *   3.  then just keep doing this til we've got a full list
  *    4. finally redirect to view fragment
  *
  *
  *
  *
  *
  * */
    lateinit var createViewModel:CreateViewModel
    lateinit var supportFragmentManager:FragmentManager
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        createViewModel =activity?.run {
            ViewModelProviders.of(this,CreateVMFactory).get(CreateViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        supportFragmentManager=fragmentManager as FragmentManager
        val root = inflater.inflate(R.layout.fragment_create_sub, container, false)
        val textView = root.findViewById<EditText>(R.id.create_sub_frag_ev)
        createViewModel.createString.observe(this, Observer { s -> textView.setText(s) })
        val personNames = arrayOf("Rahul", "Jack", "Rajeev", "Aryan", "Rashmi", "Jaspreet", "Akbar")
        val spinner = root.findViewById<Spinner>(R.id.create_sub_spinner)
        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(root.context, R.layout.support_simple_spinner_dropdown_item ,personNames)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(view.context, position.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }

        return root
    }
}