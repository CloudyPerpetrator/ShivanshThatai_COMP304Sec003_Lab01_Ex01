package shivanshthatai_comp304sec003_lab01_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.shivanshthatai_comp304sec003_lab01_ex01.R

class CustomerDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fullNameEditText = findViewById<EditText>(R.id.fullNameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val addressEditText = findViewById<EditText>(R.id.addressEditText)
        val logInButton = findViewById<Button>(R.id.logInButton)

        logInButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val address = addressEditText.text.toString()

            val intent = Intent(this, OrderDetailsActivity::class.java)
            intent.putExtra("fullName", fullName)
            intent.putExtra("email", email)
            intent.putExtra("address", address)
            startActivity(intent)
        }
    }
}

class OrderDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val fullName = intent.getStringExtra("fullName")
        val email = intent.getStringExtra("email")
        val address = intent.getStringExtra("address")

        // Check if any of the Intent extras are null
        if (fullName == null || email == null || address == null) {
            // Handle the case where any of the extras are null
            Toast.makeText(this, "Error: Missing customer details", Toast.LENGTH_SHORT).show()
            finish() // Close the activity
            return
        }

        // Continue with your normal initialization
        val productNameEditText = findViewById<EditText>(R.id.productNameEditText)
        val pricePerUnitEditText = findViewById<EditText>(R.id.pricePerUnitEditText)
        val quantityEditText = findViewById<EditText>(R.id.quantityEditText)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val outputTextView = findViewById<TextView>(R.id.outputTextView)

        outputTextView.text = "Customer Name: $fullName\nEmail ID: $email\nAddress: $address"

        calculateButton.setOnClickListener {
            val productName = productNameEditText.text.toString()
            val pricePerUnit = pricePerUnitEditText.text.toString().toDouble()
            val quantity = quantityEditText.text.toString().toInt()

            if (quantity < 1 || quantity > 10) {
                Toast.makeText(this, "Invalid quantity. Please enter a value between 1 and 10.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val totalPrice = pricePerUnit * quantity

            outputTextView.append("\nProduct Name: $productName\nPrice Per Unit: $pricePerUnit\nQuantity: $quantity\nTotal Price: $totalPrice")
            Toast.makeText(this, "Total Price: $totalPrice", Toast.LENGTH_SHORT).show()
        }
    }
}
