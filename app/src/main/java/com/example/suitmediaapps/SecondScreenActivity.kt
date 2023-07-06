package com.example.suitmediaapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var name:TextView
    private lateinit var selectedName:TextView
    private lateinit var toolbar: Toolbar
    private lateinit var choose: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        name = findViewById(R.id.tv_name)
        selectedName = findViewById(R.id.tv_selected_name)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val getNama = intent.getStringExtra("NAMA")

        name.text = getNama

        val getSelectedName = intent.getStringExtra("selectedItem")

        selectedName.text = getSelectedName

        choose = findViewById(R.id.btn_choose)

        choose.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val itemName = data?.getStringExtra(ITEM_NAME_EXTRA)
            if (!itemName.isNullOrEmpty()) {
                selectedName.text = itemName
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val SECOND_ACTIVITY_REQUEST_CODE = 1
        const val ITEM_NAME_EXTRA = "item_name"
    }
}