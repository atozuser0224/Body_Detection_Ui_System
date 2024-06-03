package com.example.test1design

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.example.test1design.ui.theme.Test1DesignTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val py = Python.getInstance()
        val module = py.getModule("test")
        enableEdgeToEdge()
        setContent {
            Test1DesignTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    val testText = remember {
                        mutableIntStateOf(0)
                    }
                    Column {
                        Text("${testText.intValue}")

                        Button(onClick = {
                            testText.intValue = ( module.callAttr("test", testText.intValue).toInt())
                        }) {
                            Text(text = "클릭")
                        }
                    }

                }
            }
        }
    }
}
