package uz.dkamaloff.githubkmm.androidApp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import uz.dkamaloff.githubkmm.githubSdk.SDK

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sdk = SDK()
        Log.d("TAG", "onCreate: ${sdk.oAuthParams}")
    }
}
