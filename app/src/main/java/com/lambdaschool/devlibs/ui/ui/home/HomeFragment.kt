package com.lambdaschool.devlibs.ui.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.devlibs.Prefs

import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_home_layout.*


class HomeFragment : Fragment() {
    /*
    *
    *
    *   Home fragment should
    *
    *   maybe uneccessary, but if not
    *   1 should show user data
    *   2 or perhaps the last madlib made
    *   3 or something
    *
    *
    *
    *
    * */
    private var homeViewModel: HomeViewModel? = null
    private var listener: OnHomeFragmentInteractionListener? = null
    var prefs: Prefs? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home_layout, container, false)
        val textView = root.findViewById<TextView>(R.id.text_home)
        homeViewModel!!.text.observe(this, Observer { s -> textView.text = s })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = Prefs(context!!)

        button_logout.setOnClickListener {
            prefs?.deleteLoginCredentials()
            listener?.onHomeFragmentInteractionListener(
                MainActivity.MainActivityFragmentActionKeys.LOG_OUT
            )
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnHomeFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnHomeFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnHomeFragmentInteractionListener {
        fun onHomeFragmentInteractionListener(enumKey: MainActivity.MainActivityFragmentActionKeys)
    }
}