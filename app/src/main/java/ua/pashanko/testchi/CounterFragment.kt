package ua.pashanko.testchi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class CounterFragment : Fragment() {

    private var fragmentCounter = 0
    private lateinit var fragmentCounterTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_counter, container, false)
        fragmentCounterTextView = rootView.findViewById(R.id.fragmentCounterTextView)
        val incrementFragmentCounterButton: Button = rootView.findViewById(R.id.incrementFragmentCounterButton)
        val backButton: Button = rootView.findViewById(R.id.backButton)

        incrementFragmentCounterButton.setOnClickListener {
            incrementFragmentCounter()
        }

        backButton.setOnClickListener {
            goBackToActivity()
        }

        if (savedInstanceState != null) {
            fragmentCounter = savedInstanceState.getInt("fragmentCounter", 0)
            updateFragmentCounter()
        }

        return rootView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("fragmentCounter", fragmentCounter)
    }

    private fun incrementFragmentCounter() {
        fragmentCounter++
        updateFragmentCounter()
    }

    private fun updateFragmentCounter() {
        fragmentCounterTextView.text = "Fragment Counter: $fragmentCounter"
    }

    private fun goBackToActivity() {
        (requireActivity() as MainActivity).transferFragmentDataToActivityCounter(fragmentCounter)
        requireActivity().onBackPressed()
    }
}