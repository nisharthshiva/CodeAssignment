package com.demo.codeassignment.base

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.demo.codeassignment.R
import com.demo.codeassignment.common.Utility

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var progressBar: ProgressBar? = null
    lateinit var binding: VB

    abstract fun getInjectedViewBinding(): VB

    abstract fun initUI()
    open fun getProgressBar(): ProgressBar? {
        return null
    }

    open fun setUpViewModelObserver() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getInjectedViewBinding()
        setContentView(binding.root)
        progressBar = getProgressBar()

        initBaseUI()
        initUI()
    }

    private fun initBaseUI() {
        progressBar = binding.root.findViewById(R.id.progressDialog)
    }

    protected fun setActionBarTitle(headerTitle: String) {
    }

    fun showProgressLoader() {
        progressBar?.let {
            it.visibility = View.VISIBLE
        }
    }

    fun hideProgressLoader() {
        progressBar?.let {
            it.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    fun showGenericErrorAlert(context: Context, message: String, downloadUrl: String) {

        val builder = AlertDialog.Builder(this,R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.dialog_item,null)
        builder.setView(view)
        val  tv_name = view.findViewById<TextView>(R.id.tv_name)
        val  img_photos = view.findViewById<ImageView>(R.id.img_photos)
        val  tv_des = view.findViewById<TextView>(R.id.tv_des)
        tv_name.text = message
        tv_des.text = message
        Utility.setImageUrl(img_photos, downloadUrl!!)
        builder.setCancelable(true)
        builder.show()
    }
}