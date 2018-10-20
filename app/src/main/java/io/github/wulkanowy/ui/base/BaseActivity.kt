package io.github.wulkanowy.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), BaseView {

    protected lateinit var messageContainer: View

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun showMessage(text: String) {
        Snackbar.make(messageContainer, text, LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        invalidateOptionsMenu()
    }
}
