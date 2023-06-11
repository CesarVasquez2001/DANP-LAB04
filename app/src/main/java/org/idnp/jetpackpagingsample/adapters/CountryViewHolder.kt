package org.idnp.jetpackpagingsample.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.idnp.jetpackpagingsample.R
import org.idnp.jetpackpagingsample.entities.Country

class CountryViewHolder(view: View) : RecyclerView. ViewHolder(view) {

    private val cuiText = view.findViewById<TextView>(R.id.textViewCui) as TextView
    private val firstNameText = view.findViewById<TextView>(R.id.textViewFirstName) as TextView
    private val secondNameText = view.findViewById<TextView>(R.id.textViewSecondName) as TextView

    private val continent1 = view.findViewById<TextView>(R.id.textViewContinent1) as TextView
    private val continent2 = view.findViewById<TextView>(R.id.textViewContinent2) as TextView
    private val capital1 = view.findViewById<TextView>(R.id.textCapital1) as TextView
    private val capital2 = view.findViewById<TextView>(R.id.textCapital2) as TextView
    private val dial = view.findViewById<TextView>(R.id.textDial) as TextView
    private val code2 = view.findViewById<TextView>(R.id.textCode2) as TextView
    private val code3 = view.findViewById<TextView>(R.id.textCode3) as TextView
    private val tld1 = view.findViewById<TextView>(R.id.textTld) as TextView
    private val km21 = view.findViewById<TextView>(R.id.textKm2) as TextView

    fun bind(user: Country) {
        with(user) {
            cuiText.text = countrieId.toString()
            firstNameText.text = name_en.toString()
            secondNameText.text = name_es.toString()
            continent1.text=continent_en.toString()
            continent2.text=continent_es.toString()
            capital1.text=capital_en.toString()
            capital2.text=capital_es.toString()
            dial.text=dial_code.toString()
            code2.text=code_2.toString()
            code3.text=code_3.toString()
            tld1.text= tld.toString()
            km21.text=km2.toString()
        }
    }
}