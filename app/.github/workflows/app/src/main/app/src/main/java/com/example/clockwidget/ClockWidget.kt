package com.example.clockwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews

object Styles {
    val bg = intArrayOf(R.drawable.bg_0, R.drawable.bg_1, R.drawable.bg_2, R.drawable.bg_3, R.drawable.bg_4, R.drawable.bg_5)
    val fg = intArrayOf(0xFFEEF2FF.toInt(), 0xFFFFFFFF.toInt(), 0xFF10131C.toInt(), 0xFFFFFFFF.toInt(), 0xFFF5D27A.toInt(), 0xFF1F2A14.toInt())

    fun update(ctx: Context, mgr: AppWidgetManager, id: Int) {
        val st = ctx.getSharedPreferences("clock", Context.MODE_PRIVATE).getInt("s$id", 0)
        val rv = RemoteViews(ctx.packageName, R.layout.widget_clock)
        rv.setInt(R.id.root, "setBackgroundResource", bg[st])
        rv.setTextColor(R.id.time, fg[st])
        rv.setTextColor(R.id.date, fg[st])
        mgr.updateAppWidget(id, rv)
    }
}

class ClockWidget : AppWidgetProvider() {
    override fun onUpdate(ctx: Context, mgr: AppWidgetManager, ids: IntArray) {
        ids.forEach { Styles.update(ctx, mgr, it) }
    }
}
