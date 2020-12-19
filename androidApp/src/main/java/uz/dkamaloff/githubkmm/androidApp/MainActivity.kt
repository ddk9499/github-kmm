package uz.dkamaloff.githubkmm.androidApp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import uz.dkamaloff.githubkmm.githubSdk.GithubSDK
import uz.dkamaloff.githubkmm.githubSdk.create

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sdk = GithubSDK.create()
        Log.d("TAG", "onCreate: ${sdk.oAuthParams}")
    }
}
