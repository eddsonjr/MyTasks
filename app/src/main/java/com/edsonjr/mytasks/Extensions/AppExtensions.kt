package com.edsonjr.mytasks.Extensions

import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*



private val locale = Locale("pt","BR") //considera a localizacao brasileira para a
//data


//extensions de data
fun Date.format(): String {
    return SimpleDateFormat("dd/MM/yyyy", locale).format(this)
}


//extension para o TextInputLayout
var TextInputLayout.text : String
    get() = editText?.text.toString() ?: ""
    set(value) {
        editText?.setText(value)
    }