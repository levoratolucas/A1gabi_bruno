//package com.example.a1gabi_bruno.ViewModel
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//
//@Composable
//fun GreenScreen(navController: NavController) {
//    Surface(modifier = Modifier.fillMaxSize(), color = Color.Green) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "Tela Verde",
//                color = Color.White,
//                style = MaterialTheme.typography.headlineMedium
//            )
//            Spacer(modifier = Modifier.height(20.dp))
//            Button(onClick = { navController.navigate("blue") }) {
//                Text(text = "Ir para a Tela Azul")
//            }
//            Spacer(modifier = Modifier.height(10.dp))
//            Button(onClick = { navController.navigate("red") }) {
//                Text(text = "Ir para a Tela Vermelha")
//            }
//            Spacer(modifier = Modifier.height(10.dp))
//            Button(onClick = { navController.navigate("book") }) {
//                Text(text = "Ir para Gerenciador de Livros")
//            }
//        }
//    }
//}
//
//
//
package com.example.a1gabi_bruno.ViewModel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a1gabi_bruno.dao.LivroDao
import com.example.a1gabi_bruno.model.Livro
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreenScreen(
    livroDao: LivroDao,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var livros by remember { mutableStateOf(emptyList<Livro>()) }
    var livroNome by remember { mutableStateOf(TextFieldValue("")) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        livros = livroDao.getAll()
    }

    fun addLivro(nome: String) {
        if (nome.isNotBlank()) {
            coroutineScope.launch {
                val novoLivro = Livro(titulo = nome)
                livroDao.insert(novoLivro)
                livroNome = TextFieldValue("")
                livros = livroDao.getAll()
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Gerenciador de Livros") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Campo de texto para adicionar o nome do livro
//            item {
//                OutlinedTextField(
//                    value = livroNome,
//                    onValueChange = { livroNome = it },
//                    label = { Text("Nome do Livro") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                )
//            }
//
//            // Botão para adicionar livro
//            item {
//                Button(
//                    onClick = { addLivro(livroNome.text) },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp)
//                ) {
//                    Text("Adicionar Livro")
//                }
//            }

            // Lista de livros
            items(livros) { livro ->
                Text(
                    text = livro.titulo,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Espaçamento entre a lista de livros e os botões de navegação
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Botões de navegação para outras telas
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { navController.navigate("blue") }) {
                        Text("Tela Azul")
                    }
                    Button(onClick = { navController.navigate("red") }) {
                        Text("Tela Vermelha")
                    }
                    Button(onClick = { navController.navigate("book") }) {
                        Text("Novo Livro")
                    }
                }
            }
        }
    }
}
