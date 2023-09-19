package com.bibitstarzrz.csnoslo.tsapp.applicationParts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bibitstarzrz.csnoslo.tsapp.R
import com.bibitstarzrz.csnoslo.tsapp.applicationParts.smallerParts.web.WebSmallerPart

class WebApplicationPart: AppCompatActivity() {
    private lateinit var smallerPart: WebSmallerPart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.application_part_web)
        smallerPart = WebSmallerPart(intent?.getStringExtra(LINK)!!)
        supportFragmentManager.beginTransaction()
            .add(R.id.web_fragmentContainerView, smallerPart)
            .commit()
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        smallerPart.currentWebView?.restoreState(savedInstanceState)
    }

    companion object {
        const val LINK = "link"
    }
}