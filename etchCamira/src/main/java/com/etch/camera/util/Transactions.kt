package com.etch.camera.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etch.camera.R
import com.etch.camera.framework.Cameriza

fun Cameriza.initFragment(
    fragment: Fragment,
    fragmentTag: String?
) {
    supportFragmentManager
        .beginTransaction()
        .add(R.id.container, fragment, fragmentTag)
        .commit()
}

fun Cameriza.addFragmentWithoutBack(
    fragment: Fragment,
    bundle: Bundle?,
    fragmentTag: String?
) {
    fragment.arguments = bundle
    supportFragmentManager
        .beginTransaction()
//        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        .replace(R.id.container, fragment, fragmentTag)
        .commit()
}

fun Cameriza.addFragmentWithBack(
    fragment: Fragment,
    bundle: Bundle?,
    fragmentTag: String?
) {
    fragment.arguments = bundle
    supportFragmentManager
        .beginTransaction()
//        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        .add(R.id.container, fragment, fragmentTag)
        .addToBackStack(null)
        .commit()
}
