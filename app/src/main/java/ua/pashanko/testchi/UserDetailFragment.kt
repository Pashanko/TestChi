package ua.pashanko.testchi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class UserDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            val name = args.getString(ARG_NAME)
            val age = args.getInt(ARG_AGE)
            val dob = args.getInt(ARG_DOB)
            val isStudent = args.getBoolean(ARG_IS_STUDENT)

            val textViewName: TextView = view.findViewById(R.id.nameTextView)
            val textViewAge: TextView = view.findViewById(R.id.ageTextView)
            val textViewDob: TextView = view.findViewById(R.id.dobTextView)
            val textViewIsStudent: TextView = view.findViewById(R.id.studentTextView)

            textViewName.text = "Name: $name"
            textViewAge.text = "Age: $age"
            textViewDob.text = "DOB: $dob"
            textViewIsStudent.text = "Is Student: $isStudent"
        }
    }

    companion object {
        private const val ARG_NAME = "arg_name"
        private const val ARG_AGE = "arg_age"
        private const val ARG_DOB = "arg_dob"
        private const val ARG_IS_STUDENT = "arg_is_student"

        fun newInstance(
            name: String,
            age: Int,
            dob: String,
            isStudent: Boolean
        ): UserDetailFragment {
            val fragment = UserDetailFragment()
            val args = Bundle()
            args.putString(ARG_NAME, name)
            args.putInt(ARG_AGE, age)
            args.putString(ARG_DOB, dob)
            args.putBoolean(ARG_IS_STUDENT, isStudent)
            fragment.arguments = args
            return fragment
        }
    }
}