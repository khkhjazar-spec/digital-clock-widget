package com.example.clockwidget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class ConfigActivity : Activity() {
    override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        val id = intent.extras?.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
            ?: AppWidgetManager.INVALID_APPWIDGET_ID
        setResult(RESULT_CANCELED, Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id))
        val d = resources.displayMetrics.density
        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; gravity = Gravity.CENTER; setPadding((16 * d).toInt(), (16 * d).toInt(), (16 * d).toInt(), (16 * d).toInt()) }
        root.addView(TextView(this).apply { text = "اختر ستايل الساعة"; textSize = 20f; gravity = Gravity.CENTER; setPadding(0, 0, 0, (16 * d).toInt()) })
        for (r in 0..1) {
            val row = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER }
            for (c in 0..2) {
                val i = r * 3 + c
                row.addView(View(this).apply {
                    setBackgroundResource(Styles.bg[i])
                    layoutParams = LinearLayout.LayoutParams((84 * d).toInt(), (84 * d).toInt()).apply { setMargins((8 * d).toInt(), (8 * d).toInt(), (8 * d).toInt(), (8 * d).toInt()) }
                    setOnClickListener {
                        getSharedPreferences("clock", MODE_PRIVATE).edit().putInt("s$id", i).apply()
                        Styles.update(this@ConfigActivity, AppWidgetManager.getInstance(this@ConfigActivity), id)
                        setResult(RESULT_OK, Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id))
                        finish()
                    }
                })
            }
            root.addView(row)
        }
        setContentView(root)
    }
}
