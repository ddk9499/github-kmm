package uz.dkamaloff.githubkmm.androidApp

import androidx.appcompat.app.AppCompatActivity
import uz.dkamaloff.githubkmm.githubSdk.Greeting

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity()
