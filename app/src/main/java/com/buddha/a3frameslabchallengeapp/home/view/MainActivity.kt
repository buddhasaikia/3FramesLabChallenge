package com.buddha.a3frameslabchallengeapp.home.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.buddha.a3frameslabchallengeapp.R
import com.buddha.a3frameslabchallengeapp.data.Word
import com.buddha.a3frameslabchallengeapp.utils.ActivityUtils

class MainActivity : AppCompatActivity(), HomeFragment.ActionListener {
    override fun onActionComplete(list: ArrayList<Word>?) {
        val fragment = ResultFragment.newInstance(list)
        ActivityUtils.addFragmentToActivityKeepHistory(supportFragmentManager, fragment ?: return, R.id.container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = HomeFragment()
        ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.container)
    }

}
