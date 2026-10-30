package jp.co.human.localnote

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun LoginScreen(){
    val loginId = rememberSaveable { mutableStateOf("")}
    val password = rememberSaveable { mutableStateOf("") }
    val message = rememberSaveable { mutableStateOf("") }
ScreenFrame{
    Text("ログイン")
    }
}