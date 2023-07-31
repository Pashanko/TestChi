package ua.pashanko.testchi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var activityCounter = 0
    private lateinit var activityCounterTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityCounterTextView = findViewById(R.id.activityCounterTextView)
        val openFragmentButton: Button = findViewById(R.id.openFragmentButton)

        openFragmentButton.setOnClickListener {
            openFragment()
        }

        if (savedInstanceState != null) {
            activityCounter = savedInstanceState.getInt("activityCounter", 0)
            updateActivityCounter()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("activityCounter", activityCounter)
    }

    private fun openFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(android.R.id.content, CounterFragment())
            addToBackStack(null)
            commit()
        }
    }

    fun transferFragmentDataToActivityCounter(fragmentCounter: Int) {
        activityCounter += fragmentCounter
        updateActivityCounter()
    }

    private fun updateActivityCounter() {
        activityCounterTextView.text = "Activity Counter: $activityCounter"
    }
}