// Hao Zhong
// GPL - 202110
// CoroutineTask.kt
package com.fullsail.gpl.zhonghao_ce09

import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection

class CoroutineTask (private val mInterface: CoroutineInterface) {

    fun coBegin(_urlAddress: String) {
        onPreExecute()
        CoroutineScope(Dispatchers.Main).launch { onPostExecute(_urlAddress) }
    }

    private fun onPreExecute() {
        mInterface.onPre()
    }

    private suspend fun doInBackground(_urlAddress: String) = withContext(Dispatchers.IO) {
        var line: String? = ""
        var result = ""

        val url = URL(_urlAddress)
        val connection = url.openConnection() as HttpsURLConnection
        val inStream = connection.inputStream
        val bufferedReader = BufferedReader(InputStreamReader(inStream))

        while (line != null) {
            line = bufferedReader.readLine()
            if (line != null) result += line
        }

        return@withContext result
    }

    private suspend fun onPostExecute(_urlAddress: String) {
        val result = doInBackground(_urlAddress)
        mInterface.onFinish(result)
    }
}