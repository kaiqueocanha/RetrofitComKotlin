package com.ocanha.retrofitcomkotlin.views.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ocanha.retrofitcomkotlin.R
import com.ocanha.retrofitcomkotlin.databinding.LoadingViewBinding

class LoadingView (context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: LoadingViewBinding =
        LoadingViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        /*
        This ClickListener is added so the view doesn't pass the click event to
        the views on the screen that the component is used.
         */
        this.binding.root.setOnClickListener {}
        context.obtainStyledAttributes(attrs, R.styleable.LoadingView).apply {
            enabled(getBoolean(R.styleable.LoadingView_visible, true))
            recycle()
        }
    }

    fun show() {
        this.enabled(true)
    }

    fun dismiss() {
        this.enabled(false)
    }

    fun isShowing(): Boolean {
        return this.binding.root.visibility == View.VISIBLE
    }

    private fun enabled(isEnabled: Boolean) {
        this.binding.root.visibility =
            if (isEnabled) View.VISIBLE else View.GONE
    }

}