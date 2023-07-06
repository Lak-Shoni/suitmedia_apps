package com.example.suitmediaapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var inputText: EditText
    private lateinit var nextPage: Button
    private lateinit var check: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.ev_name)
        inputText = findViewById(R.id.ev_polindrome)
        nextPage = findViewById(R.id.btn_nextPage)
        check = findViewById(R.id.btn_result)


        check.setOnClickListener {
            val input = inputText.text.toString()
            if (input.isNotEmpty()){
                val result = if (isPalindrome(input)) "it’s palindrome" else "it’s not palindrome"
                showResultDialog(result)
            }else{
                val message = "isi terlebih dahulu"
                showResultDialog(message)
            }
        }

        nextPage.setOnClickListener {
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra("NAMA",name.text.toString())
            startActivity(intent)
        }
    }
    private fun isPalindrome(input: String): Boolean {
        val cleanInput = input.replace(Regex("[^A-Za-z0-9]"), "").lowercase(Locale.getDefault())
        return cleanInput == cleanInput.reversed()
    }

    private fun showResultDialog(resultMessage: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Result")
        builder.setMessage(resultMessage)
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }
}