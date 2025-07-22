package com.example.nodemanager.presentation.tree

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nodemanager.domain.model.NodeModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreeScreen(viewModel: TreeViewModel = viewModel()) {
    val currentNode by viewModel.currentNode.collectAsState()

    currentNode.let { current ->
        Scaffold(
            topBar = {
                CustomTopBar(
                    model = current,
                    onBack = { viewModel.onBack() },
                    onDelete = { viewModel.onRemoveCurrent() }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { viewModel.onAddChild() }
                ) { Icon(Icons.Filled.Add, "Добавить") }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                if (current.children.isEmpty()) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text("У родителя нет детей", fontSize = 18.sp)
                    }
                } else {
                    Text("Дети:", fontWeight = FontWeight.Medium)
                    Spacer(Modifier.height(8.dp))
                    current.children.forEach { child ->
                        Text(
                            text = child.name,
                            modifier = Modifier
                                .clickable { viewModel.onPush(child) }
                                .padding(8.dp)
                                .fillMaxWidth(),
                            fontSize = 16.sp
                        )
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(model: NodeModel, onBack: () -> Unit, onDelete: () -> Unit) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.width(180.dp), contentAlignment = Alignment.Center) {
                Text(
                    model.name,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        },
        navigationIcon = {
            if (model.parent != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Назад"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.DarkGray,
            titleContentColor = Color.LightGray,
            navigationIconContentColor = Color.LightGray,
            actionIconContentColor = Color.LightGray
        ),
        actions = {
            if (model.parent != null) {
                IconButton({ onDelete() }) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Удалить"
                    )
                }
            }
        }
    )
}