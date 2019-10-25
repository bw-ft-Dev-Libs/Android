package com.lambdaschool.devlibs.ui.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.SEND_DEV_LIB
import com.lambdaschool.devlibs.model.DevLibBackend
import com.lambdaschool.devlibs.model.DevLibLocal
import kotlinx.android.synthetic.main.devlib_list_layout.view.*


class ReAdapter(val parentFragment: Fragment) :
        androidx.recyclerview.widget.ListAdapter<DevLibBackend,
                ReAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback< DevLibBackend>() {
            override fun areItemsTheSame(oldItem: DevLibBackend, newItem:  DevLibBackend): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem:  DevLibBackend, newItem:  DevLibBackend): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.lib == newItem.lib
            }
        }
    }
    var list: List<DevLibBackend> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.devlib_list_layout, parent, false)
        return ViewHolder(viewGroup)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //get the current lib
        val currentSelection = list[position]

        //set the text to the current lib
        holder.firstTV.text= currentSelection.lib

        //
        holder.parentView.setOnClickListener{

            var bundle = bundleOf( SEND_DEV_LIB to currentSelection)
            NavHostFragment.findNavController(parentFragment).navigate(R.id.action_navigation_create_to_navigation_view_edit, bundle)
        }




    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       val parentView=view.devlib_list_parent
        val firstTV: TextView = view.devlib_list_tv_one
        val secondTV: TextView = view.devlib_List_tv_two

   // ya'all much be pretty wealthy to be able to afford two tvs


    }


}
