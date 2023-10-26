package com.example.complexevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.Runnable

class MainActivity : AppCompatActivity() {
    private lateinit var edit_text: EditText
    private lateinit var check_box: CheckBox
    private lateinit var text_view: TextView
    private lateinit var progress_bar: ProgressBar


    private var progressHandler: Handler = Handler()
    private var progressRunnable: Runnable? = null
    private var progress: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_text = findViewById(R.id.edit_text)
        check_box = findViewById(R.id.check_box)
        text_view = findViewById(R.id.text_view)
        progress_bar = findViewById(R.id.progress_bar)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            if (check_box.isChecked) {
                val text = edit_text.text.toString()
                text_view.text = text
                startProgress()
            }
        }
    }

    private fun startProgress() {
        progress = 0
        progress_bar.max = 100 // Initialize progress bar with maximum value
        progress_bar.progress = progress
        progressRunnable = object : Runnable {
            override fun run() {
                progress += 10
                progress_bar.progress = progress // Update progress bar with current progress value
                if (progress >= 100) {
                    progressHandler.removeCallbacks(this)
                    progress = 0
                    progress_bar.progress = progress
                } else {
                    progressHandler.postDelayed(this, 200)
                }
            }
        }
        progressHandler.postDelayed(progressRunnable!!, 1000)
    }
}