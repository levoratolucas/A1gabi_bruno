////package com.example.a1gabi_bruno
////
////import android.os.Bundle
////import androidx.activity.ComponentActivity
////import androidx.activity.compose.setContent
////import androidx.activity.enableEdgeToEdge
////import androidx.compose.foundation.layout.*
////import androidx.compose.foundation.lazy.LazyColumn
////import androidx.compose.foundation.lazy.items
////import androidx.compose.material3.*
////import androidx.compose.runtime.*
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.text.input.TextFieldValue
////import androidx.compose.ui.tooling.preview.Preview
////import androidx.compose.ui.unit.dp
////import androidx.lifecycle.lifecycleScope
////import androidx.navigation.compose.NavHost
////import androidx.navigation.compose.composable
////import androidx.navigation.compose.rememberNavController
////import com.example.a1gabi_bruno.ViewModel.BlueScreen
////import com.example.a1gabi_bruno.ViewModel.GreenScreen
////import com.example.a1gabi_bruno.ViewModel.RedScreen
////import com.example.a1gabi_bruno.database.DatabaseBuilder
////import com.example.a1gabi_bruno.model.Livro
////import com.example.a1gabi_bruno.ui.theme.A1gabi_brunoTheme
////import kotlinx.coroutines.launch
////
////class MainActivity : ComponentActivity() {
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        enableEdgeToEdge()
////
////        val database = DatabaseBuilder.getInstance(applicationContext)
////        val livroDao = database.livroDao()
////
////        setContent {
////            A1gabi_brunoTheme {
////                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
////                    var livros by remember { mutableStateOf(emptyList<Livro>()) }
////                    var livroNome by remember { mutableStateOf(TextFieldValue("")) }
////
////                    // Função para carregar livros do BD
////                    fun loadLivros() {
////                        lifecycleScope.launch {
////                            livros = livroDao.getAll() // Recupera todos os livros
////                        }
////                    }
////
////                    // Insere um livro e recarrega a lista
////                    fun addLivro(nome: String) {
////                        if (nome.isNotBlank()) { // Só adiciona se o nome não estiver vazio
////                            lifecycleScope.launch {
////                                val novoLivro = Livro(titulo = nome)
////                                livroDao.insert(novoLivro) // Insere no banco
////                                livroNome = TextFieldValue("") // Limpa o campo de texto
////                                loadLivros() // Recarrega os livros
////                            }
////                        }
////                    }
////
////                    // Inicializa os livros ao abrir
////                    LaunchedEffect(Unit) {
////                        loadLivros()
////                    }
////
////                    Column(
////                        modifier = Modifier
////                            .padding(innerPadding)
////                            .fillMaxSize()
////                    ) {
////                        // Campo para digitar o nome do livro
////                        OutlinedTextField(
////                            value = livroNome,
////                            onValueChange = { livroNome = it },
////                            label = { Text("Nome do Livro") },
////                            modifier = Modifier
////                                .fillMaxWidth()
////                                .padding(16.dp)
////                        )
////
////                        // Botão para adicionar livro
////                        Button(
////                            onClick = { addLivro(livroNome.text) },
////                            modifier = Modifier
////                                .fillMaxWidth()
////                                .padding(horizontal = 16.dp)
////                        ) {
////                            Text("Adicionar Livro")
////                        }
////
////                        // Exibe a lista de livros
////                        LazyColumn(
////                            modifier = Modifier.fillMaxSize()
////                        ) {
////                            items(livros) { livro ->
////                                Text(
////                                    text = livro.titulo,
////                                    modifier = Modifier.padding(16.dp)
////                                )
////                            }
////                        }
////                    }
////                }
////            }
////        }
////    }
////}
////
////@Composable
////fun MainScreen() {
////    val navController = rememberNavController()
////
////    // Define a navegação entre as telas
////    NavHost(navController = navController, startDestination = "red") {
////        composable("red") { RedScreen(navController) }
////        composable("blue") { BlueScreen(navController) }
////        composable("green") { GreenScreen(navController) }
////    }
////}]
//
////package com.example.a1gabi_bruno
////
////import android.os.Bundle
////import androidx.activity.ComponentActivity
////import androidx.activity.compose.setContent
////import androidx.compose.foundation.layout.*
////import androidx.compose.material3.*
////import androidx.compose.runtime.*
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.tooling.preview.Preview
////import androidx.compose.ui.unit.dp
////import androidx.navigation.compose.NavHost
////import androidx.navigation.compose.composable
////import androidx.navigation.compose.rememberNavController
////import com.example.a1gabi_bruno.ui.theme.A1gabi_brunoTheme
////
////class MainActivity : ComponentActivity() {
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContent {
////            A1gabi_brunoTheme {
////                MainScreen() // Chama a tela principal com navegação
////            }
////        }
////    }
////}
////
////@Composable
////fun MainScreen() {
////    val navController = rememberNavController()
////
////    // Define as rotas e telas
////    NavHost(navController = navController, startDestination = "red") {
////        composable("red") { RedScreen(navController) }
////        composable("blue") { BlueScreen(navController) }
////        composable("green") { GreenScreen(navController) }
////    }
////}
////
////@Composable
////fun RedScreen(navController: androidx.navigation.NavController) {
////    ScreenTemplate(
////        title = "Red Screen",
////        buttonLabel = "Go to Blue Screen",
////        onButtonClick = { navController.navigate("blue") }
////    )
////}
////
////@Composable
////fun BlueScreen(navController: androidx.navigation.NavController) {
////    ScreenTemplate(
////        title = "Blue Screen",
////        buttonLabel = "Go to Green Screen",
////        onButtonClick = { navController.navigate("green") }
////    )
////}
////
////@Composable
////fun GreenScreen(navController: androidx.navigation.NavController) {
////    ScreenTemplate(
////        title = "Green Screen",
////        buttonLabel = "Go to Red Screen",
////        onButtonClick = { navController.navigate("red") }
////    )
////}
////
////// Função de template para evitar repetição
////@Composable
////fun ScreenTemplate(title: String, buttonLabel: String, onButtonClick: () -> Unit) {
////    Column(
////        modifier = Modifier
////            .fillMaxSize()
////            .padding(16.dp),
////        verticalArrangement = Arrangement.Center
////    ) {
////        Text(
////            text = title,
////            style = MaterialTheme.typography.headlineMedium,
////            modifier = Modifier.padding(bottom = 16.dp)
////        )
////        Button(onClick = onButtonClick) {
////            Text(buttonLabel)
////        }
////    }
////}
////
////@Preview(showBackground = true)
////@Composable
////fun DefaultPreview() {
////    A1gabi_brunoTheme {
////        MainScreen()
////    }
////}
//
//package com.example.a1gabi_bruno
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.room.Room
//import com.example.a1gabi_bruno.ViewModel.BookScreen
//import com.example.a1gabi_bruno.database.AppDatabase
//import com.example.a1gabi_bruno.ui.theme.A1gabi_brunoTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Inicializa o banco de dados
//        val database = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java,
//            "app-database"
//        ).build()
//
//        val livroDao = database.livroDao()
//
//        setContent {
//            A1gabi_brunoTheme {
//                BookScreen(livroDao = livroDao)
//            }
//        }
//    }
//}
//

package com.example.a1gabi_bruno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.a1gabi_bruno.ViewModel.BookScreen
import com.example.a1gabi_bruno.ViewModel.BlueScreen
import com.example.a1gabi_bruno.ViewModel.GreenScreen
import com.example.a1gabi_bruno.ViewModel.RedScreen
import com.example.a1gabi_bruno.database.AppDatabase
import com.example.a1gabi_bruno.ui.theme.A1gabi_brunoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o banco de dados
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app-database"
        ).build()

        val livroDao = database.livroDao()

        setContent {
            A1gabi_brunoTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "blue"
                ) {
                    composable("blue") { BlueScreen(navController = navController) }
                    composable("green") { GreenScreen(livroDao = livroDao,navController = navController) }
                    composable("red") { RedScreen(navController = navController) }
                    composable("book") { BookScreen(livroDao = livroDao, navController = navController) }
                }
            }
        }
    }
}

