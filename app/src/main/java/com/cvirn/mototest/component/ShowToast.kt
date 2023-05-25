package com.cvirn.mototest.component

import android.content.Context
import android.widget.Toast

fun ShowToast(context: Context) {
    Toast.makeText(
        context,
        "Under construction. Will be available in the next release",
        Toast.LENGTH_SHORT,
    ).show()
}
