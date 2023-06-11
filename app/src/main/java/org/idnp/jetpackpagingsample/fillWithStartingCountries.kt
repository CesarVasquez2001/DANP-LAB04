package org.idnp.jetpackpagingsample

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.idnp.jetpackpagingsample.database.CountryDatabase
import org.idnp.jetpackpagingsample.entities.Country
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class fillWithStartingCountries(private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        GlobalScope.launch(Dispatchers.IO) {
            fillWithStarting(context)
        }
    }

    private fun loadJsonArray(context: Context): JSONArray? {
        val builder = StringBuilder()
        val inputStream: InputStream = context.resources.openRawResource(R.raw.countries)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?

        try {
            while (reader.readLine().also { line = it } != null) {
                builder.append(line)
            }
            val json = JSONObject(builder.toString())
            return json.getJSONArray("countries")
        } catch (exception: IOException) {
            exception.printStackTrace()
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
        return null
    }

    suspend fun fillWithStarting(context: Context) {
        val countryDatabase = CountryDatabase.getInstance(context)
        val countries: JSONArray? = loadJsonArray(context)

        try {
            var i = 0
            while (i < countries?.length() ?: 0) {

                val country: JSONObject? = countries?.getJSONObject(i)

                var name_en: String = country?.getString("name_en") ?: ""
                var name_es: String = country?.getString("name_es") ?: ""
                var continent_en: String = country?.getString("continent_en") ?: ""
                var continent_es: String = country?.getString("continent_es") ?: ""
                var capital_en: String = country?.getString("capital_en") ?: ""
                var capital_es: String = country?.getString("capital_es") ?: ""
                var dial_code: String = country?.getString("dial_code") ?: ""
                var code_2: String = country?.getString("code_2") ?: ""
                var code_3: String = country?.getString("code_3") ?: ""
                var tld: String = country?.getString("tld") ?: ""
                var km2: Int = country?.getInt("km2") ?: 0

                Log.d(i.toString(), name_en)

                countryDatabase.countrieDao().insertCountrie(
                    Country(
                        0,
                        name_en,
                        name_es,
                        continent_en,
                        continent_es,
                        capital_en,
                        capital_es,
                        dial_code,
                        code_2,
                        code_3,
                        tld,
                        km2
                    )
                )
                i++
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}

