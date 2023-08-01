package ua.pashanko.testchi

import android.content.SharedPreferences
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Locale

class AddUserActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var buttonAddUser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        datePicker = findViewById(R.id.datePicker)
        buttonAddUser = findViewById(R.id.buttonAddUser)

        buttonAddUser.setOnClickListener {
            addUser()
        }
    }

    private fun addUser() {
        val name = editTextName.text.toString().trim()
        val age = editTextAge.text.toString().trim().toInt()
        val dob = formatDateOfBirth(datePicker)


        val sharedPreferences = getSharedPreferences("Users", MODE_PRIVATE)
        val user = User(name, age, dob, false)
        saveUser(sharedPreferences, user)

        finish()
    }

    private fun formatDateOfBirth(datePicker: DatePicker): String {
        val day = datePicker.dayOfMonth
        val month = datePicker.month
        val year = datePicker.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    private fun saveUser(sharedPreferences: SharedPreferences, user: User) {
        val gson = Gson()
        val userListJson = sharedPreferences.getString("userList", null)
        val userList: MutableList<User> =
            if (userListJson == null) mutableListOf() else gson.fromJson(
                userListJson,
                object : TypeToken<List<User>>() {}.type
            )
        userList.add(user)
        val editor = sharedPreferences.edit()
        editor.putString("userList", gson.toJson(userList))
        editor.apply()
    }
}