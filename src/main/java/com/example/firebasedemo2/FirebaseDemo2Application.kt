package com.example.firebasedemo2

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import jakarta.annotation.PostConstruct
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.io.FileInputStream

@SpringBootApplication
open class FirebaseDemo2Application {
    @PostConstruct
    fun initialize() {
        try {
            val serviceAccount =
                FileInputStream("C:\\Users\\Pavel\\IdeaProjects\\firebaseDemo2\\src\\services.json")
            val options = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://rudn23-952a1.firebaseio.com/")
                .build()
            FirebaseApp.initializeApp(options)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(FirebaseDemo2Application::class.java, *args)
        }
    }
}
