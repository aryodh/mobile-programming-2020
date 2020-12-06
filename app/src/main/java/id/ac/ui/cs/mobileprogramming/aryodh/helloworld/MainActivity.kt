package id.ac.ui.cs.mobileprogramming.aryodh.helloworld

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    private lateinit var wifiManager: WifiManager
    private lateinit var listView: ListView
    private lateinit var wifiListAdapter: WifiListAdapter
    private var wifiNameList: List<String> = emptyList()

    private fun scanSuccess() {
        val results = wifiManager.scanResults
        generateList(generateWifiNameList(results))
    }

    private fun scanFailure() {
        val results = wifiManager.scanResults
    }

    private fun generateWifiNameList(results: List<ScanResult>): List<String> {
        var tempList: List<String> = listOf()
        for (item in results) {
            tempList += item.SSID
        }
        return tempList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wifi_recycler_view.layoutManager = LinearLayoutManager(this)
        wifiListAdapter = WifiListAdapter(emptyList())
        wifi_recycler_view.adapter = wifiListAdapter

        scanBtn.setOnClickListener{
            wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            if (!checkIfAlreadyHavePermission()) {
                requestForSpecificPermission();
            } else{
                startWifiScanning()
            }
        }
    }

    private fun checkIfAlreadyHavePermission(): Boolean {
        val result =
            applicationContext?.let { ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) }
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestForSpecificPermission() {
        let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_WIFI_STATE
                ),
                101
            )
        }
    }

    private fun startWifiScanning(){
        val wifiScanReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                if (success) {
                    scanSuccess()
                } else {
                    scanFailure()
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        applicationContext.registerReceiver(wifiScanReceiver, intentFilter)

        val success = wifiManager.startScan()
        if (!success) {
            scanFailure()
        }
    }

    private fun generateList(wifiList: List<String>) {

        wifiListAdapter = WifiListAdapter(wifiList)
        wifi_recycler_view.adapter = wifiListAdapter

        val thread = Thread {
            try {
                sendPostRequest(wifiList.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        thread.start()
    }

    private fun sendPostRequest(content: String) {

        val url = URL("https://38ff7f70d4e19d2c210e879cb27c9bf7.m.pipedream.net")
        val con = url.openConnection() as HttpURLConnection
        con.requestMethod = "POST"
        con.setRequestProperty("Content-Type", "application/json; utf-8")
        con.setRequestProperty("Accept", "application/json")
        con.doOutput = true
        val jsonInputString: String = "{\"data\":$content}"

        con.outputStream.use { os ->
            val input = jsonInputString.toByteArray(charset("utf-8"))
            os.write(input, 0, input.size)
        }

        BufferedReader(
            InputStreamReader(con.inputStream, "utf-8")
        ).use { br ->
            val response = StringBuilder()
            var responseLine: String? = null
            while (br.readLine().also { responseLine = it } != null) {
                response.append(responseLine!!.trim { it <= ' ' })
            }
            println(response.toString())
        }
        runOnUiThread {
            Toast.makeText(this@MainActivity, "POST request success!", Toast.LENGTH_SHORT).show()
        }



    }
}