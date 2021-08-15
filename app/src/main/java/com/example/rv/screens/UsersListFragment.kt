package com.example.rv.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rv.Navigation
import com.example.rv.UserActionListener
import com.example.rv.UsersAdapter
import com.example.rv.databinding.UsersListFragmentBinding
import com.example.rv.model.User

class UsersListFragment : Fragment() {

    private var _binding: UsersListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UsersListViewModel by viewModels { factory() }

    private lateinit var adapter: UsersAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.users.observe(viewLifecycleOwner, Observer {
            adapter.users = it
        })
        _binding = UsersListFragmentBinding.inflate(inflater, container, false)

        adapter = UsersAdapter(object : UserActionListener {

            override fun onUserMove(user: User, moveBy: Int) {
                viewModel.moveUser(user, moveBy)
            }

            override fun onUserDelete(user: User) {
                viewModel.deleteUser(user)

            }

            override fun onUserDetails(user: User) {
                navigator().showDetails(user)
            }

        })



        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}