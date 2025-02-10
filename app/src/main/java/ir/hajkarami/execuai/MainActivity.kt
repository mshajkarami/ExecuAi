package ir.hajkarami.execuai

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import ir.hajkarami.execuai.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSend.setOnClickListener {
            val inputText = binding.inputCatTextField.text.toString()
            if (inputText.isNotEmpty()) {
                performAction(inputText)
            } else {
                Toast.makeText(this, "pleas enter e message!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performAction(inputText: String) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://www.google.com"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                binding.resultTV.text = "Response is: ${response.substring(0, 500)}"
            },
            {   binding.resultTV.text = "That didn't work!" })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

}
