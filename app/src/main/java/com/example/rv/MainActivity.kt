package com.example.rv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rv.databinding.ActivityMainBinding
import com.example.rv.model.User
import com.example.rv.model.UserDetails
import com.example.rv.model.UsersListener
import com.example.rv.model.UsersService
import com.example.rv.screens.UserDetailsFragment
import com.example.rv.screens.UsersListFragment
import java.util.function.LongFunction

class MainActivity : AppCompatActivity(), Navigation {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, UsersListFragment()).commit()
        }
    }

    override fun showDetails(user: User) {
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.fragmentContainer, UserDetailsFragment.newInstance(user.id)).commit()
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun toast(messageRest: Int) {
        Toast.makeText(this, messageRest, Toast.LENGTH_SHORT).show()
    }
}