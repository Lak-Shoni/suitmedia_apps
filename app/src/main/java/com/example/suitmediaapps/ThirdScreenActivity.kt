package com.example.suitmediaapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var emptyState:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        toolbar = findViewById(R.id.toolbar3)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.rv_user)
        emptyState = findViewById(R.id.emptyState)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/") // Base URL API ReqRes
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.getUsers(1, 10)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val users = apiResponse?.data ?: emptyList()
                    userAdapter = UserAdapter(users)
                    userAdapter.setOnItemClickListener(object : UserAdapter.OnItemClickListener {
                        override fun onItemClick(name: String) {
                            val intent = Intent(this@ThirdScreenActivity, SecondScreenActivity::class.java)
                            intent.putExtra(SecondScreenActivity.ITEM_NAME_EXTRA, name)
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                    })
                    recyclerView.adapter = userAdapter
                } else {
                    emptyState.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}