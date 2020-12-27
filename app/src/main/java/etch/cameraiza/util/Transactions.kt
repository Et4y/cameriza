package etch.cameraiza

import android.os.Bundle
import androidx.fragment.app.Fragment

fun MainActivity.initFragment(
    fragment: Fragment,
    fragmentTag: String?
) {
    supportFragmentManager
        .beginTransaction()
        .add(R.id.container, fragment, fragmentTag)
        .commit()
}

fun MainActivity.addFragmentWithoutBack(
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
