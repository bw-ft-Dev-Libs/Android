package com.lambdaschool.devlibs

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri


fun Context.showToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.openSoftKeyboard(view: View?) {
    view?.requestFocus()
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun Context.playBeepBoop() {
    val mp = MediaPlayer.create(this, R.raw.beepboop)
    mp.start()
}

fun Context.getTwitterIntent( shareText: String): Intent {
    val shareIntent: Intent

        val tweetUrl = "https://twitter.com/intent/tweet?text=$shareText"
        val uri = Uri.parse(tweetUrl)
        shareIntent = Intent(Intent.ACTION_VIEW, uri)
        return shareIntent

}