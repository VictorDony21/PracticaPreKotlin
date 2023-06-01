package com.example.practicaprekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    var txtNombre: EditText? = null
    var lblNombre: TextView? = null
    var btnEntrar: Button? = null
    var btnSalir: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtNombre = findViewById<EditText>(R.id.txtNombre)
        lblNombre = findViewById<TextView>(R.id.lblNombre)
        btnEntrar = findViewById<Button>(R.id.btnEntrar)
        btnSalir = findViewById<Button>(R.id.btnSalir)
        btnEntrar!!.setOnClickListener {
            val usuario = txtNombre!!.text.toString()
            if (usuario == getString(R.string.user)) {
                // Credenciales válidas, redireccionar a otra actividad
                val intent = Intent(this@MainActivity, ReciboNominaActivity::class.java)
                intent.putExtra("user", usuario) // Agregar el nombre de usuario al intent
                startActivity(intent)
            } else {
                // Credenciales inválidas, mostrar mensaje de error
                txtNombre!!.error = "Credenciales inválidas"
            }
        }

        //Boton para cerrar la app
        btnSalir!!.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Cerrar aplicación")
            builder.setMessage("¿Estás seguro de que quieres cerrar la aplicación?")
            builder.setPositiveButton(
                "Sí"
            ) { dialog, which ->
                finish() // Cerrar la actividad actual y salir de la aplicación
            }
            builder.setNegativeButton("No", null) // No hacer nada si se selecciona "No"
            val dialog = builder.create()
            dialog.show()
        }
    }
}
