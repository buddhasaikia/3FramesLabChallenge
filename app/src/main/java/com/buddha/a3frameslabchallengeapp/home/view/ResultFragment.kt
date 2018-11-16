package com.buddha.a3frameslabchallengeapp.home.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buddha.a3frameslabchallengeapp.config.Config

import com.buddha.a3frameslabchallengeapp.R
import com.buddha.a3frameslabchallengeapp.data.Word

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        val adapterWordList =
            AdapterWordList(arguments?.getParcelableArrayList(Config.Extras.DATALIST))
        recyclerView.adapter = adapterWordList
        return view
    }

    companion object {
        fun newInstance(list: ArrayList<Word>?): ResultFragment? {
            val fragment = ResultFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(Config.Extras.DATALIST, list)
            fragment.arguments = bundle
            return fragment
        }
    }
}
