package org.idnp.jetpackpagingsample

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.idnp.jetpackpagingsample.database.CountrieDatabase
import org.idnp.jetpackpagingsample.entities.Countrie
import org.idnp.jetpackpagingsample.model.CountrieRepository
import org.idnp.jetpackpagingsample.ui.theme.JetpackPagingSampleTheme
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackPagingSampleTheme {
                // A surface container using the 'background' color from the theme
                val db = Room.databaseBuilder(
                    applicationContext,
                    CountrieDatabase::class.java, "database-name"
                ).build()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val repository =
                        CountrieRepository(CountrieDatabase.getInstance(context.applicationContext))

                    Greeting(repository)
                }
            }
        }
    }
}

@Composable
fun Greeting(db: CountrieRepository, modifier: Modifier = Modifier) {

    val TAG: String = "RoomDatabase"
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val fillDataOnClick = {
            fillWithStartingNotes(context, db,scope)
        }

        val studentsOnClick: () -> Unit = {
            scope.launch {
                db.clearAll()
            }
        }

        Button(onClick = fillDataOnClick) {
            Text(text = "Fill student & book tables")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = studentsOnClick) {
            Text(text = "Show students")
        }
    }
}

private fun loadJSONFromAsset(context: Context): String {
    val json: String?
    try {
        val inputStream = context.resources.openRawResource(R.raw.countries)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        val charset: Charset = Charsets.UTF_8
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, charset)
    }
    catch (ex: IOException) {
        Log.d("ERROR LOAD",ex.toString())
        return ""
    }
    return json
}

fun fillWithStartingNotes(context: Context, repository: CountrieRepository, scope: CoroutineScope) {
    try {

        val obj = JSONObject(loadJSONFromAsset(context))
        val userArray = obj.getJSONArray("countries")
        for (i in 0 until userArray.length()) {
            val userDetail = userArray.getJSONObject(i)

            val name_en = userDetail.getString("name_en")
            val name_es = userDetail.getString("name_es")
            val continent_en = userDetail.getString("continent_en")
            val continent_es = userDetail.getString("continent_es")
            val capital_en = userDetail.getString("capital_en")
            val capital_es = userDetail.getString("capital_es")
            val dial_code = userDetail.getString("dial_code")
            val code_2 = userDetail.getString("code_2")
            val code_3 = userDetail.getString("code_3")
            val tld = userDetail.getString("tld")
            val km2 = userDetail.getInt("km2")

            val noteEntity = Countrie(
                countrieId = 0,
                name_en = name_en,
                name_es = name_es,
                continent_en = continent_en,
                continent_es = continent_es,
                capital_en = capital_en,
                capital_es = capital_es,
                dial_code = dial_code,
                code_2 = code_2,
                code_3 = code_3,
                tld = tld,
                km2 = km2
            )


            Log.d("DETALLE",noteEntity.toString())

            scope.launch {
                repository.insertCountrie(noteEntity)
            }
        }
    }
    catch (e: JSONException) {
        Log.d("ERROR",e.toString())
    }
}

