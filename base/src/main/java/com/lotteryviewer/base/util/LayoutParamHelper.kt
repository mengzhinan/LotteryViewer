package com.lotteryviewer.base.util

import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout

/**
 * Author: duke
 * DateTime: 2021-06-26 19-43
 * Description: 操作 view 的 layoutParam 辅助类
 *
 */
object LayoutParamHelper {

    const val TYPE_WIDTH = 1
    const val TYPE_HEIGHT = 2
    const val TYPE_MARGIN_LEFT = 3
    const val TYPE_MARGIN_TOP = 4
    const val TYPE_MARGIN_RIGHT = 5
    const val TYPE_MARGIN_BOTTOM = 6

    private fun getLayoutParam(view: View?): ViewGroup.LayoutParams? {
        return view?.layoutParams
    }

    fun get(view: View?, typeConst: Int): Int {
        val param = getLayoutParam(view) ?: return 0
        return if (typeConst == TYPE_WIDTH) {
            param.width
        } else if (typeConst == TYPE_HEIGHT) {
            param.height
        } else {
            if (param is ViewGroup.MarginLayoutParams) {
                when (typeConst) {
                    TYPE_MARGIN_LEFT -> {
                        param.leftMargin
                    }
                    TYPE_MARGIN_TOP -> {
                        param.topMargin
                    }
                    TYPE_MARGIN_RIGHT -> {
                        param.rightMargin
                    }
                    TYPE_MARGIN_BOTTOM -> {
                        param.bottomMargin
                    }
                    else -> {
                        0
                    }
                }
            } else {
                0
            }
        }
    }

    fun set(view: View?, valuePX: Int, typeConst: Int) {
        val param = getLayoutParam(view) ?: return
        if (typeConst == TYPE_WIDTH) {
            param.width = valuePX
        } else if (typeConst == TYPE_HEIGHT) {
            param.height = valuePX
        } else {
            if (param is ViewGroup.MarginLayoutParams) {
                when (typeConst) {
                    TYPE_MARGIN_LEFT -> {
                        param.leftMargin = valuePX
                    }
                    TYPE_MARGIN_TOP -> {
                        param.topMargin = valuePX
                    }
                    TYPE_MARGIN_RIGHT -> {
                        param.rightMargin = valuePX
                    }
                    TYPE_MARGIN_BOTTOM -> {
                        param.bottomMargin = valuePX
                    }
                    else -> {

                    }
                }
            }
        }
        view?.layoutParams = param
        view?.requestLayout()
    }

    fun getRelativeLayoutParam(view: View?): RelativeLayout.LayoutParams? {
        val param = getLayoutParam(view)
        if (param !is RelativeLayout.LayoutParams) {
            return null
        }
        return param
    }
}
