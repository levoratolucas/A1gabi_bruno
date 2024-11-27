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
//fun RedScreen(navController: NavController) {
//    Surface(modifier = Modifier.fillMaxSize(), color = Color.Red) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "Tela Vermelha",
//                color = Color.White,
//                style = MaterialTheme.typography.headlineMedium
//            )
//            Spacer(modifier = Modifier.height(20.dp))
//            Button(onClick = { navController.navigate("blue") }) {
//                Text(text = "Ir para a Tela Azul")
//            }
//            Spacer(modifier = Modifier.height(10.dp))
//            Button(onClick = { navController.navigate("green") }) {
//                Text(text = "Ir para a Tela Verde")
//            }
//            Spacer(modifier = Modifier.height(10.dp))
//            Button(onClick = { navController.navigate("book") }) {
//                Text(text = "Ir para Gerenciador de Livros")
//            }
//        }
//    }
//}
package com.example.a1gabi_bruno.ViewModel

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RedScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Red) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween // Organiza verticalmente
        ) {
            Text(
                text = "Tela Vermelha",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f)) // Para centralizar visualmente

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { navController.navigate("blue") }) {
                    Text("Tela Azul")
                }
                Button(onClick = { navController.navigate("green") }) {
                    Text("Tela Verde")
                }
                Button(onClick = { navController.navigate("book") }) {
                    Text("Novo Livro")
                }
            }
        }
    }
}
