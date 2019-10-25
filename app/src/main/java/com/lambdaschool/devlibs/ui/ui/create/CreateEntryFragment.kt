package com.lambdaschool.devlibs.ui.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.lambdaschool.devlibs.*
import com.lambdaschool.devlibs.database.DatabaseRepo
import com.lambdaschool.devlibs.model.CallBackState
import com.lambdaschool.devlibs.model.DevLibCreate
import com.lambdaschool.devlibs.model.DevLibLocal
import com.lambdaschool.devlibs.viewmodel.CreateVMFactory
import com.lambdaschool.devlibs.viewmodel.CreateViewModel
import com.lambdaschool.devlibs.viewmodel.CreateViewModel.Companion.arrayOfNeeded
import com.lambdaschool.devlibs.viewmodel.CreateViewModel.Companion.arrayOfProvided
import com.lambdaschool.devlibs.viewmodel.CreateViewModel.Companion.template
import com.lambdaschool.devlibs.viewmodel.CreateViewModel.Companion.text
import com.lambdaschool.devlibs.viewmodel.CreateViewModel.Companion.vmCategory
import com.lambdaschool.devlibs.viewmodel.CreateViewModel.Companion.vmPosition
import kotlinx.android.synthetic.main.fragment_create_sub_layout.view.*

class CreateEntryFragment() :Fragment() {

    /*
  *
  *
  *   create sub fragment should
  *
  *   1. if created without arguments, should prompt user to select a devlibs category
  *   2. then pass on to requesting words based on catergory chosen,
  *   3.  then just keep doing this til we've got a full list
  *    4. finally redirect to view fragment using onfinish()
  *
  *
  *
  *
  *
  * */
    lateinit var createViewModel: CreateViewModel
    lateinit var supportFragmentManager:FragmentManager
    var init =false


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        createViewModel =activity?.run {
            ViewModelProviders.of(this, CreateVMFactory).get(CreateViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        supportFragmentManager=fragmentManager as FragmentManager
        val root = inflater.inflate(R.layout.fragment_create_sub_layout, container, false)
        val editView = root.findViewById<EditText>(R.id.create_sub_frag_et)
        val textView = root.findViewById<TextView>(R.id.create_sub_frag_tv)
        val button = root.findViewById<Button>(R.id.create_sub_frag_btn)
   //     val repo = DatabaseRepo(root.context)

        editView.visibility=View.INVISIBLE
        button.visibility=View.INVISIBLE


        //handle the spinner
        val spinner:Spinner = root.create_sub_spinner

        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(root.context, R.layout.support_simple_spinner_dropdown_item ,CATEGORIES)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (!init && position == 0) {
                        init = true
                    }
                    //make sure they're not choosing "choose a category"
                    else if (position != 0) {
                        // set the position in the viewmodel
                        vmPosition = 0

                        //set the category
                        vmCategory = position - 1

                        // sett the words needed in the viewmodel
                        arrayOfNeeded = tempWordNeeds[position - 1]
                        //set array of provided to array of needed simply for sizing
                        arrayOfProvided = arrayOfNeeded
                        //set the template to the appropriate template
                        template = tempTemplatesToInject[position-1]
                        // hide the spinner and reveal the edit text
                        spinner.visibility = View.GONE
                        editView.visibility = View.VISIBLE
                        button.visibility = View.VISIBLE
                        //set text/edit view text appropiately
                        textView.text = "Enter a:"
                        editView.hint = arrayOfNeeded[0]
                        root.context.showToast("now enter a " + arrayOfNeeded[vmPosition])
                    }
                }


                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }

        //handle the words

        button.setOnClickListener {
            val input = editView.text.toString()

            // if input is a word without spaces or weird chars
            if (input.matches("[\\p{L}\\p{No}]+".toRegex())) {
                addAWord(input)
                // if we have enough words in the list, wrap up
                if (arrayOfProvided.size == vmPosition) {
                    // we have all the words now
                    // exit
                    finish()
                    Toast.makeText(view!!.context, "complete", Toast.LENGTH_SHORT).show()
                } else {
                    //set the hint to the appropriate word
                    editView.hint = arrayOfNeeded[vmPosition]
                    //empty the text
                    editView.setText("")
                    root.context.showToast("now enter a " + arrayOfNeeded[vmPosition])
                }
            } else {
                root.context.showToast("Please enter a word without spaces or special characters")
                editView.setText("")
                editView.hint = arrayOfNeeded[vmPosition]
            }
        }






        return root
    }

    fun finish() {
        val token:String = prefs.getLoginCredentials()!!.token
        //make a mad lib object or at least pass the the completed lib to where ever it needs to go
        if (arrayOfProvided.size == template.size -1) {
            //make the string out of it's pieces
            text = ""
            for (i in 0 until arrayOfProvided.size) {
                text = text + template[i] + arrayOfProvided[i]
            }
            //make the madlib obj itself
            var finalObj = DevLibLocal(text,
                    prefs.getLoginCredentials()!!.userId, // there never should be a time where userID hasn't been saved after login
                    vmCategory)
            val userId = prefs.getLoginCredentials()
            val finalobjcreate = DevLibCreate(text,userId!!.userId, vmCategory

            )
            var something = hashMapOf<String,String>()
            something.put ("authorization",token)

   //   TODO: FIND OUT WHY THIS IS CRASHING SOMETIMES
     repo.createDevLib(finalobjcreate, token)



            //reset the views


        //and whatever else needs to get done



        //send it on to appropriate calls to database,retro or a fragment to view it in

        //todo 1: IMPLEMENT REDIRECT
        var bundle = bundleOf( SEND_DEV_LIB to finalObj)

        //findNavController(this).navigate(R.id.action_navigation_create_to_navigation_view_edit, bundle)



    }
        //this triggers if somehow we have two few words in provided
        else {

    }

    }
    fun addAWord(word:String){
        arrayOfProvided[vmPosition]=word
        vmPosition++

    }
}
//   input.replace("\\s".toRegex(), "")
//replace non characters with blank
/*  val regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]")

  if (regex.matcher(input).find()){
      input.replace("[$&+,:;=\\\\?@#|/'<>.^*()%!-].","")
  }
  if (input.matches("[\\p{L}\\p{No}\\p{Space}]+".toRegex())) {
println(true)
}
  input.replace("\\W+".toRegex(), "")*/