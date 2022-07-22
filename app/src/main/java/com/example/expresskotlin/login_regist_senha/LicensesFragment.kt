package com.example.expresskotlin.login_regist_senha

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.expresskotlin.R


class LicensesFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogInflater: LayoutInflater? = activity?.layoutInflater
        val openSourceLicensesView : View? = dialogInflater?.inflate(R.layout.fragment_licenses, null)

        val dialogBuilder = activity?.let { AlertDialog.Builder(it) }
        dialogBuilder?.setView(openSourceLicensesView)
        dialogBuilder?.setTitle((getString(R.string.dialog_title_licenses)))
        dialogBuilder?.setPositiveButton(android.R.string.ok, null)

        return dialogBuilder!!.create()
    }


}