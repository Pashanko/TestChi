package ua.pashanko.testchi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("Users", Context.MODE_PRIVATE)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(getUsersFromSharedPreferences(), sharedPreferences, supportFragmentManager)
        recyclerView.adapter = userAdapter
    }

    private fun getUsersFromSharedPreferences(): List<User> {
        val userListJson = sharedPreferences.getString("userList", null)
        val gson = Gson()
        return if (userListJson == null) {
            mutableListOf()
        } else {
            gson.fromJson(userListJson, object : TypeToken<List<User>>() {}.type)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAddUser -> openAddUserActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openAddUserActivity() {
        val intent = Intent(this, AddUserActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        userAdapter.users = getUsersFromSharedPreferences()
        userAdapter.notifyDataSetChanged()
    }
}
