package com.nurika.opaku.view.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurika.opaku.data.repository.local.entity.UserEntity
import com.nurika.opaku.databinding.ActivityUserBinding
import com.nurika.opaku.utils.EnumStatus
import com.nurika.opaku.viewmodel.ViewModelFactory

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var viewModel: UserViewModel
    private val userAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, ViewModelFactory.getInstance(this))[UserViewModel::class.java]

        initAdapter(userAdapter)
        observeApi()
    }

    private fun observeApi() {
        viewModel.userList().observe(this, Observer {

            when (it.Status) {
                EnumStatus.SUCCESS -> {
                    updateAdapterData(it.data, userAdapter)
                    binding.pbLoading.visibility = View.GONE
                }
                EnumStatus.LOADING -> binding.pbLoading.visibility = View.VISIBLE
                EnumStatus.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    binding.pbLoading.visibility = View.GONE
                }
            }

        })
    }

    private fun updateAdapterData(newData: PagedList<UserEntity>?, userAdapter: UserAdapter) {
        newData?.let { datas ->
            userAdapter.submitList(datas)
            userAdapter.notifyDataSetChanged()
        }
    }

    private fun initAdapter(userAdapter: UserAdapter) {
        binding.rvListUser.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@UserActivity)
            setHasFixedSize(true)
        }
    }
}