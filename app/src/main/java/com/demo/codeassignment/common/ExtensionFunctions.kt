package com.demo.codeassignment.common

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity

fun <T : Any> AppCompatActivity.launchActivity(classType: Class<T>) {
    val intent = Intent(this, classType)
    startActivity(intent)
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.setVisibility(show: Boolean) {
    if (show) show() else hide()
}
