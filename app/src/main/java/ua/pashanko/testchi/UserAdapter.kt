package ua.pashanko.testchi

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(internal var users: List<User>, private val sharedPreferences: SharedPreferences, private val fragmentManager: FragmentManager) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
        holder.switchIsStudent.setOnCheckedChangeListener(null) // Remove previous listener before reusing view
        holder.switchIsStudent.isChecked = user.isStudent
        holder.switchIsStudent.setOnCheckedChangeListener { _, isChecked ->
            user.isStudent = isChecked
            saveUserStatus(user)
        }

        holder.itemView.setOnClickListener {
            showUserDetailFragment(user)
        }
    }

    private fun showUserDetailFragment(user: User) {
        val fragment = UserDetailFragment.newInstance(user.name, user.age,user.birthDate ,user.isStudent)
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment, "user_detail")
            .addToBackStack(null)
            .commit()
    }
    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.nameTextView)
        private val textViewAge: TextView = itemView.findViewById(R.id.ageTextView)
        val switchIsStudent: Switch = itemView.findViewById(R.id.isStudentSwitch)

        fun bind(user: User) {
            textViewName.text = "Name: ${user.name}"
            textViewAge.text = "Age: ${user.age}"
            switchIsStudent.isChecked = user.isStudent
        }

    }
    private fun saveUserStatus(user: User) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(user.name, user.isStudent)
        editor.apply()
    }
}

