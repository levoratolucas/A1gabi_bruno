package com.example.a1gabi_bruno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.a1gabi_bruno.database.DatabaseBuilder
import com.example.a1gabi_bruno.model.Livro
import com.example.a1gabi_bruno.ui.theme.A1gabi_brunoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = DatabaseBuilder.getInstance(applicationContext)
        val livroDao = database.livroDao()

        setContent {
            A1gabi_brunoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var livros by remember { mutableStateOf(emptyList<Livro>()) }
                    var livroNome by remember { mutableStateOf(TextFieldValue("")) }

                    // Função para carregar livros do BD
                    fun loadLivros() {
                        lifecycleScope.launch {
                            livros = livroDao.getAll() // Recupera todos os livros
                        }
                    }

                    // Insere um livro e recarrega a lista
                    fun addLivro(nome: String) {
                        if (nome.isNotBlank()) { // Só adiciona se o nome não estiver vazio
                            lifecycleScope.launch {
                                val novoLivro = Livro(titulo = nome)
                                livroDao.insert(novoLivro) // Insere no banco
                                livroNome = TextFieldValue("") // Limpa o campo de texto
                                loadLivros() // Recarrega os livros
                            }
                        }
                    }

                    // Inicializa os livros ao abrir
                    LaunchedEffect(Unit) {
                        loadLivros()
                    }

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        // Campo para digitar o nome do livro
                        OutlinedTextField(
                            value = livroNome,
                            onValueChange = { livroNome = it },
                            label = { Text("Nome do Livro") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )

                        // Botão para adicionar livro
                        Button(
                            onClick = { addLivro(livroNome.text) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Text("Adicionar Livro")
                        }

                        // Exibe a lista de livros
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(livros) { livro ->
                                Text(
                                    text = livro.titulo,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
