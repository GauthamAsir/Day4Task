package a.gautham.day4task

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import java.text.SimpleDateFormat
import java.util.*

class Login : AppCompatActivity() {

    private var auth : FirebaseAuth = FirebaseAuth.getInstance()

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val dlg = ProgressDialog(this)
        dlg.setMessage("Logging you in")

        register_here.setOnClickListener {
            startActivity(Intent(applicationContext, Register::class.java))
        }

        login_bt.setOnClickListener {

            if (getEmail().isEmpty()){
                email_input.error = "E-mail cant be empty"
                return@setOnClickListener
            }else
                email_input.error = null

            if (getPass().isEmpty()){
                password_input.error = "Password cant be empty"
                return@setOnClickListener
            }else
                password_input.error = null

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()){
                email_input.error = "Enter valid e-mail"
                return@setOnClickListener
            }else
                email_input.error = null

            if (getPass().length<=5){
                password_input.error = "Password should contain more than 5 characters"
                return@setOnClickListener
            }else
                password_input.error = null

            dlg.show()

            auth.signInWithEmailAndPassword(getEmail(), getPass()).addOnCompleteListener {
                if(it.isSuccessful){
                    if (it.isSuccessful) {
                        dlg.dismiss()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(applicationContext,"Failed to login", Toast.LENGTH_LONG).show()
                        dlg.dismiss()
                    }
                }else{
                    dlg.dismiss()
                    Toast.makeText(applicationContext,"Failed: "+ (it.exception?.message ?: "Unknown error"), Toast.LENGTH_LONG).show()
                }
            }

        }

    }

    @ExperimentalStdlibApi
    fun getDeviceName(): String? {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            model.capitalize(Locale.getDefault())
        } else manufacturer.capitalize(Locale.ROOT) + " " + model
    }

    private fun getEmail(): String {
        return email_input.editText?.text.toString()
    }

    private fun getPass(): String {
        return password_input.editText?.text.toString()
    }
}