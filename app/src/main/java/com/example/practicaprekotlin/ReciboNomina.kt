package com.example.practicaprekotlin

class ReciboNomina(private var horasNormales: Int, private var horasExtras: Int, private var puesto: Int) {


    fun calcularSubtotal(): Double {
        val PAGO_BASE = 200.0
        var incrementoPago = 0.0
        if (puesto == 1) {
            incrementoPago = PAGO_BASE * 1.2
        } else if (puesto == 2) {
            incrementoPago = PAGO_BASE * 1.5
        } else if (puesto == 3) {
            incrementoPago = PAGO_BASE * 2.0
        }
        return (incrementoPago * horasNormales) + (incrementoPago * horasExtras * 2)
    }

    fun calcularImpuesto(): Double {
        val subtotal = calcularSubtotal();
        val impuesto = subtotal * 0.16
        return impuesto
    }

    fun calcularTotal(): Double {
        val subtotal = calcularSubtotal();
        val impuesto = calcularImpuesto();

        val total = subtotal - impuesto
        return total
    }

    // Métodos getter y setter para los atributos

    fun getHorasNormales(): Int {
        return horasNormales
    }

    fun setHorasTrabNormal(horasNormales: Int) {
        this.horasNormales = horasNormales
    }

    fun getHorasTrabExtras(): Int {
        return horasExtras
    }

    fun setHorasExtras(horasExtras: Int) {
        this.horasExtras = horasExtras
    }

    fun getPuesto(): Int {
        return puesto
    }

    fun setPuesto(puesto: Int) {
        this.puesto = puesto
    }

}

