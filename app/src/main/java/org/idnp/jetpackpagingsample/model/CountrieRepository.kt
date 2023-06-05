package org.idnp.jetpackpagingsample.model

import android.util.Log
import org.idnp.jetpackpagingsample.entities.Countrie

class CountrieRepository {
    fun getUsers(nextPageNumber: Int): List<Countrie> {

        Log.d("nextPageNumber:",nextPageNumber.toString())

        val users = arrayListOf<Countrie>()
        var user: Countrie
        val start: Int = 100 * nextPageNumber
        val end = start + 20

        for (i in start..end) {
            user = Countrie(cui = i, firstName = "FirstName " + i, lastName = "SecondName " + i);
            users.add(user)
        }

        return users
    }
}

//data class ResponseUser(val users: List<User>, val nextPageNumber: Int)