package com.androiddevs.runningappyt.other

import android.content.Context
import android.os.DropBoxManager
import com.androiddevs.runningappyt.db.Run
import com.androiddevs.runningappyt.utilities.TrackingUtility
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.data.Entry
import kotlinx.android.synthetic.main.item_run.view.*
import java.text.SimpleDateFormat
import java.util.*

class CustomMarkerView(
    val runs: List<Run>,
    c: Context,
    layoutId: Int
) : MarkerView(c, layoutId) {

    override fun getOffset(): MPPointF {
        return MPPointF(-width / 2f, -height.toFloat())
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        if (e == null) {
            return
        }

        val curRunId = e.x.toInt()
        val run = runs[curRunId]

        val calendar = Calendar.getInstance().apply {
            timeInMillis = run.timestamp
        }
        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        tvDate?.text = dateFormat.format(calendar.time)

        val avgSpeed = "${run.avgSpeedInKHM}km/h"
        tvAvgSpeed?.text = avgSpeed

        val distanceInKm = "${run.distanceInMeters / 1000f}km"
        tvDistance?.text = distanceInKm

        tvTime?.text = TrackingUtility.getFormattedStopwatchTime(run.timeInMillis)

        val caloriesBurned = "${run.caloriesBurned}k cal"
        tvCalories?.text = caloriesBurned
    }
}