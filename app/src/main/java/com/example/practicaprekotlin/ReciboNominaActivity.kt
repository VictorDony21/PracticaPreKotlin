package com.example.practicaprekotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Random


class ReciboNominaActivity : AppCompatActivity() {
    private lateinit var txtNombre: EditText
    private lateinit var txtHorasT: EditText
    private lateinit var txtHorasE: EditText
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnRegresar: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioAuxiliar: RadioButton
    private lateinit var radioAlbañil: RadioButton
    private lateinit var radioIngObra: RadioButton
    private lateinit var txtNumRecibo: TextView
    private lateinit var lblUsuario: TextView
    private lateinit var txtSubtotal: TextView
    private lateinit var txtImpuesto: TextView
    private lateinit var txtTotal: TextView
    private lateinit var Recibo :ReciboNomina

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recibo_nomina)

        // Asignar las referencias a los elementos de la interfaz
        txtNumRecibo = findViewById<TextView>(R.id.txtNumRecibo)
        txtNombre = findViewById(R.id.txtNombre)
        txtHorasT = findViewById<EditText>(R.id.txtHorasT)
        txtHorasE = findViewById<EditText>(R.id.txtHorasE)
        btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnRegresar = findViewById<Button>(R.id.btnRegresar)
        radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioAuxiliar = findViewById<RadioButton>(R.id.radioAuxiliar)
        radioAlbañil = findViewById<RadioButton>(R.id.radioAlbañil)
        radioIngObra = findViewById<RadioButton>(R.id.radioIngObra)
        lblUsuario = findViewById<TextView>(R.id.lblUsuario)
        txtSubtotal = findViewById<TextView>(R.id.txtSubtotal)
        txtImpuesto = findViewById<TextView>(R.id.txtImpuesto)
        txtTotal = findViewById<TextView>(R.id.txtTotal)

        // Obtener el usuario de la actividad anterior
        val bundle = intent.extras
        if (bundle != null) {
            val usuario = bundle.getString("user")
            lblUsuario.setText("Bienvenido $usuario")
        }


        // Botón para calcular
        btnCalcular.setOnClickListener(View.OnClickListener {
            if (validarCampos()) {
                numRandom()
                calcularNomina()
            } else {
                Toast.makeText(
                    this@ReciboNominaActivity,
                    "Por favor, completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        // Botón de limpiar
        btnLimpiar.setOnClickListener(View.OnClickListener { limpiarCampos() })

        // Botón para salir al login
        btnRegresar.setOnClickListener(View.OnClickListener { confirmarSalida() })
    }

    private fun validarCampos(): Boolean {
        var camposCompletos = true
        if (txtNombre!!.text.toString().isEmpty()) {
            txtNombre!!.error = "Campo obligatorio"
            camposCompletos = false
        }
        if (txtHorasT!!.text.toString().isEmpty()) {
            txtHorasT!!.error = "Campo obligatorio"
            camposCompletos = false
        }
        if (txtHorasE!!.text.toString().isEmpty()) {
            txtHorasE!!.error = "Campo obligatorio"
            camposCompletos = false
        }
        if (radioGroup!!.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Por favor, selecciona una opción", Toast.LENGTH_SHORT).show()
            camposCompletos = false
        }
        return camposCompletos
    }

    private fun calcularNomina() {
        val horasNormales = Integer.parseInt(txtHorasT.text.toString())
        val horasExtras = Integer.parseInt(txtHorasE.text.toString())
        val puesto = obtenerPuestoSeleccionado()

        val recibo = ReciboNomina(horasNormales, horasExtras, puesto)
        val subtotal = recibo.calcularSubtotal()
        val impuesto = recibo.calcularImpuesto()
        val total = recibo.calcularTotal()

        txtSubtotal.text = subtotal.toString()
        txtImpuesto.text = impuesto.toString()
        txtTotal.text = total.toString()
    }


    fun numRandom() {
        // Crear una instancia de la clase Random
        val random = Random()

        // Generar un número aleatorio entre 1 y 99999 (5 dígitos)
        val numeroAleatorio = random.nextInt(99999) + 1
        txtNumRecibo!!.text = "" + numeroAleatorio
    }

    private fun obtenerPuestoSeleccionado(): Int {
        var puestoSeleccionado = 0
        if (radioAuxiliar!!.isChecked) {
            puestoSeleccionado = 1
        } else if (radioAlbañil!!.isChecked) {
            puestoSeleccionado = 2
        } else if (radioIngObra!!.isChecked) {
            puestoSeleccionado = 3
        }
        return puestoSeleccionado
    }

    private fun limpiarCampos() {
        txtNumRecibo!!.text = ""
        txtNombre!!.setText("")
        txtHorasT!!.setText("")
        txtHorasE!!.setText("")
        txtSubtotal!!.text = ""
        txtImpuesto!!.text = ""
        txtTotal!!.text = ""
        radioGroup!!.clearCheck()
    }

    private fun confirmarSalida() {
        val builder = AlertDialog.Builder(this@ReciboNominaActivity)
        builder.setTitle("Regresar")
        builder.setMessage("¿Estás seguro de que quieres regresar?")
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