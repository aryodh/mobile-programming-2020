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
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var wifiManager: WifiManager
    private lateinit var listView: ListView
    private lateinit var wifiListAdapter: WifiListAdapter
    private var wifiNameList: List<String> = emptyList()

    private fun scanSuccess() {
        val results = wifiManager.scanResults
        Log.d("scanSuccess", "Called")
        Log.d("listName", generateWifiNameList(results).toString())
        generateList(generateWifiNameList(results))
        Log.d("result: ", results.toString())
    }

    private fun scanFailure() {
        val results = wifiManager.scanResults
        generateList(generateWifiNameList(results))
        Log.d("result: ", results.toString())
    }

    private fun generateWifiNameList(results: List<ScanResult>): List<String> {
        var tempList: List<String> = listOf()
        Log.d("generateWifiNameList", results.toString())
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
            Log.d("scanBtn", "Clicked!")
            if (!checkIfAlreadyHavePermission()) {
                requestForSpecificPermission();
            } else{
                startWifiScanning()
            }
        }
    }

    private fun checkIfAlreadyHavePermission(): Boolean {
        val result =
            applicationContext?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) }
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
        Log.d("Success? -->", success.toString())
        if (!success) {
            // scan failure handling
            scanFailure()
        } else {
            scanSuccess()
        }
    }

    private fun generateList(wifiList: List<String>) {
        Log.d("generateList", "Called")
        Log.d("wifiList", wifiList.toString())

        wifiListAdapter = WifiListAdapter(wifiList)
        wifi_recycler_view.adapter = wifiListAdapter
    }

}