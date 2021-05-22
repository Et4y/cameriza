package com.etch.camera.framework

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.etch.camera.util.addFragmentWithoutBack


private val PERMISSIONS_REQUIRED =
    arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )


/**
 * The sole purpose of this fragment is to request permissions and, once granted, display the
 * camera fragment to the user.
 */
class PermissionsFragment : Fragment() {

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                if (it.value) {
                    (activity as Cameriza).addFragmentWithoutBack(GalleryFragment(), null, "")
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (!hasPermissions(requireContext())) {
            // Request camera-related permissions
            requestMultiplePermissions.launch(PERMISSIONS_REQUIRED)
        } else {
            // If permissions have already been granted, proceed
            (activity as Cameriza).addFragmentWithoutBack(GalleryFragment(), null, "")
        }
    }


    /** Convenience method used to check if all permissions required by this app are granted */
    private fun hasPermissions(context: Context) = PERMISSIONS_REQUIRED.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

}
