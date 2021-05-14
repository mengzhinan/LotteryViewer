package com.lotteryviewer.twocolorball.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.lotteryviewer.twocolorball.R

/**
 * author: duke
 * version: 1.0
 * dateTime: 2021-05-14 16:27
 * description: label text layout
 */
class LabelTextLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var root: View = LayoutInflater.from(context).inflate(
        R.layout.layout_label_layout,
        this,
        false
    )
    private var tvLabel: TextView
    private var tvContent: TextView

    init {
        removeAllViews()
        addView(root)
        tvLabel = root.findViewById(R.id.item_tv_label)
        tvContent = root.findViewById(R.id.item_tv_content)

    }

    fun setLabel(labelStr: String?) {
        tvLabel.text = labelStr
    }

    fun setContent(contentStr: String?) {
        tvContent.text = contentStr
    }

    fun setTextSize(spF: Float) {
        tvLabel.textSize = spF
        tvContent.textSize = spF
    }

}
