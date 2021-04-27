package com.lotteryviewer.twocolorball.ui.model

/**
 * author: duke
 * version: 1.0
 * dateTime: 2021-04-27 23:43
 * description: 每一期开奖数据
 *
 */
data class Total(
    var red1: Int,
    var groupDateStr: String?,
    var groupRed1: String?,
    var groupRed2: String?,
    var groupRed3: String?,
    var groupRed4: String?,
    var groupRed5: String?,
    var groupRed6: String?,
    var groupBlue: String?
)
