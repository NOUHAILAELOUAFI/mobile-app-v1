package com.example.firedatabase_assis
import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.SQLException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.firedatabase_assis.databinding.ActivityLoginFormBinding
class login_form : AppCompatActivity() {
    private lateinit var bind : ActivityLoginFormBinding
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(bind.root)

        var dbhelp=DB_class(applicationContext)
        var db=dbhelp.readableDatabase

        bind.btnlogin.setOnClickListener {
            var email=bind.logtxt.text.toString();
            var password=bind.ed3.text.toString()

            val query="SELECT * FROM user WHERE email='"+email+"' AND pswd='"+password+"'"

            val rs=db.rawQuery(query,null)

            if(rs.moveToFirst()){

                val name=rs.getString(rs.getColumnIndex("email"))
                val accountType=rs.getString(rs.getColumnIndex("account_type"))

                rs.close()

                startActivity(
                    Intent(this,welcome_window::class.java).putExtra("email",email)
                        .putExtra("accountType",accountType)
                )
            }
            else{

                var ad = AlertDialog.Builder(this)
                ad.setTitle("Message")
                ad.setMessage("Username or password is incorrect!")
                ad.setPositiveButton("Ok", null)
                ad.show()
            }
        }
        bind.regisLink.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }



}