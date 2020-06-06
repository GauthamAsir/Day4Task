package a.gautham.day4task

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.email_input
import kotlinx.android.synthetic.main.activity_login.password_input
import kotlinx.android.synthetic.main.activity_register.*
import java.text.SimpleDateFormat
import java.util.*

class Register : AppCompatActivity() {

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val dlg = ProgressDialog(this)
        dlg.setMessage("Registering you in")

        register_bt.setOnClickListener {

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

            if (getPno().isEmpty()){
                phone_input.error = "Phone number cant be empty"
                return@setOnClickListener
            }else
                phone_input.error = null

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

            if (getPno().length!=10){
                phone_input.error = "Enter a valid Indian phone number"
                return@setOnClickListener
            }else
                phone_input.error = null

            dlg.show()

            val auth : FirebaseAuth = FirebaseAuth.getInstance()

            auth.createUserWithEmailAndPassword(getEmail(), getPass()).addOnCompleteListener {
                if(it.isSuccessful){

                    val database: DatabaseReference = FirebaseDatabase.getInstance().reference
                    val currentDate =
                        SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

                    val currentUser : FirebaseUser? = auth.currentUser
                    val user = User(currentUser?.uid.toString(), getEmail(), getPass(), getPno(),
                         currentDate, getDeviceName())
                    database.child("user").child(currentUser?.uid.toString()).setValue(user)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                dlg.dismiss()
                                startActivity(Intent(applicationContext, MainActivity::class.java))
                                finish()
                            } else {
                                FirebaseAuth.getInstance().currentUser?.delete()
                                dlg.dismiss()
                                Toast.makeText(applicationContext,"Failed to login", Toast.LENGTH_LONG).show()
                            }
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

    private fun getPno(): String {
        return phone_input.editText?.text.toString()
    }

    private fun getPass(): String {
        return password_input.editText?.text.toString()
    }

}