package com.example.nodemanager.presentation.tree

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TreeScreen(viewModel: TreeViewModel = viewModel()) {
    val currentNode by viewModel.currentNode.collectAsState()

    currentNode?.let { current ->
        Scaffold { innerPadding ->
            Column(modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = 40.dp, horizontal = 20.dp)
            ) {

                if (current.parent != null) {
                    IconButton(onClick = { viewModel.onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }

                Text("Текущий узел: ${current.name}", fontWeight = FontWeight.Bold)

                Spacer(Modifier.height(16.dp))

                Row {
                    Button(onClick = { viewModel.onAddChild() }) {
                        Text("Добавить потомка")
                    }
                    Spacer(Modifier.width(8.dp))


                    Spacer(Modifier.width(8.dp))
                    Button(onClick = { viewModel.onRemoveCurrent() }, enabled = current.parent != null) {
                        Text("Удалить")
                    }
                }

                Spacer(Modifier.height(24.dp))
                Text("Потомки:", fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(8.dp))
                current.children.forEach { child ->
                    Text(
                        text = child.name,
                        modifier = Modifier
                            .clickable { viewModel.onPush(child) }
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                    Spacer(Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun BackIconButton(onBackClick: () -> Unit) {

}