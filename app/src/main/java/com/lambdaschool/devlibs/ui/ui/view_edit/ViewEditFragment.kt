package com.lambdaschool.devlibs.ui.ui.view_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.lambdaschool.devlibs.App.Companion.prefs
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.model.DevLibLocal
import com.lambdaschool.devlibs.showToast
import com.lambdaschool.devlibs.tempTemplatesToInject
import com.lambdaschool.devlibs.ui.ui.create.CreateVMFactory
import com.lambdaschool.devlibs.ui.ui.create.CreateViewModel
import kotlinx.android.synthetic.main.fragment_view_edit_layout.view.*


class ViewEditFragment () : Fragment() {
    lateinit var createViewModel: CreateViewModel
     var fieldLength:Int = 0
    var isUsers=false
    var listOfTemplateText = listOf<String>()
    var listOfInjectableText = listOf<String>()
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
        val linearLayout = root.view_edit_linear_layout
        createViewModel =activity?.run {
            ViewModelProviders.of(this, CreateVMFactory).get(CreateViewModel::class.java)
        } ?: throw Exception("Invalid Activity")




        //get the lib sent to this fragment
        val recieved:DevLibLocal = arguments?.getSerializable("const") as DevLibLocal
        //if lib is blank or null, redirect as something went wrong
        if (recieved.lib.isNullOrEmpty()) {
            root.context.showToast("something went wrong, please try again")
            NavHostFragment.findNavController(this).navigate(R.id.navigation_home)
        }
        //get the users Id
        val userID = prefs!!.getLoginCredentials()!!.userId  //should never be null if user has gotten this far
        // if userID from prefs matches the lib sent here, makes isUsers? true
        if (recieved.userId==userID) {isUsers = true}
        //set the required number of fields via the category id, if multiple libs were implemented,
        // with varying number of fields, this could be changed to a DevLibID either in the model or
        // by checking the begining word or similar
        fieldLength = tempTemplatesToInject[recieved.categoryId].size
        // grab the correct text list template based on category id
        listOfTemplateText=  tempTemplatesToInject[recieved.categoryId]
        //build a list of user vars based on the template text

        //make an alias for context for ease of my typing
        val contxt=root.context



        //start building the display
        //if it belongs to the user we can deconstruct it based on the template we have

        val regex = """\W+""".toRegex()
        val beautiful = "Roses are red, Violets are blue"

        // W+ is non word

        val pattern = "\\W+".toRegex()

        if (isUsers){
            //define the toBeChopped
            var toBeChoppedBegin  = listOfTemplateText[0].length
            val toBeChopped2 = listOfTemplateText[1]
        for (i in 0 until fieldLength) {

            //grab the appropriate bit of text from the template
            val textField = TextView(context)
            textField.setText(listOfTemplateText[i])
            linearLayout.addView(textField)

            //if we're not on the last iteration of the loop, get the user's word as well
            if (i != fieldLength) {


            }



                val editField = EditText(contxt)
              //  editField.setText()
            }
        }
        //else it's not and we can't reasonably deconstruct it as we can not be sure of the template
        else {
            val textField = TextView(context)
            textField.setText(recieved.lib)
            linearLayout.addView(textField)
        }








        return root
    }
}