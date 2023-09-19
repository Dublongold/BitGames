package com.bibitstarzrz.csnoslo.tsapp.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun smallerPartInflater(id: Int, inflater: LayoutInflater, container: ViewGroup?): View? = inflater.inflate(id, container, false)