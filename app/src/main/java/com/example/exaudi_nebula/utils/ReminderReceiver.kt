package com.example.exaudi_nebula.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.exaudi_nebula.BaseActivity

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val title           = intent.getStringExtra("title") ?: "Pengingat"
        val message         = intent.getStringExtra("message") ?: "Waktunya melakukan sesuatu"
        val targetClassName = intent.getStringExtra("target_activity")

        val targetIntent = if (!targetClassName.isNullOrEmpty()) {
            try {
                val clazz = Class.forName(targetClassName)
                Intent(context, clazz).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            } catch (e: Exception) {
                Intent(context, BaseActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
        } else {
            Intent(context, BaseActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }

        NotificationHelper.showNotification(
            context = context,
            title = title,
            message = message,
            intent = targetIntent
        )
    }
}
