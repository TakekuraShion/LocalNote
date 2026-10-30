package jp.co.human.localnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.co.human.localnote.ui.theme.LocalNoteTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LocalNoteTheme {
                Scaffold { innerPadding ->
                    Column(
                        modifier = Modifier.padding( innerPadding)
                    ){
                        LoginScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    val message = remember {
        mutableStateOf("こんにちは")
    }
    Column{
        Text(message.value)

        Button(onClick = {
            message.value = "押されました"
    })  {
            Text("押す")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    MainScreen()
}

@Composable
fun ScreenFrame(content: @Composable ColumnScope.() -> Unit){
    Column(
        Modifier.fillMaxSize()
            .safeDrawingPadding()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        content()
    }
}

fun loginError(id:String,password: String): String{
    if(id.isBlank() || password.isBlank()){
        return "IDとパスワードを入力してください"
    }
    if(id != "student" || password != "practice123"){
        return "IDまたはパスワードが違います"
    }
    return ""
}

@Composable
fun App(){
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "login"){
        composable("login"){
            LoginScreen(onLogin = {nav.navigate("home")})
        }
        composable("home"){
            HomeScreen(onlogout = { nav.popBackStack()})
        }
    }
}
