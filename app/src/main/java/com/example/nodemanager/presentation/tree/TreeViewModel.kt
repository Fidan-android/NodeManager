package com.example.nodemanager.presentation.tree

import androidx.lifecycle.ViewModel
import com.example.nodemanager.domain.model.NodeModel
import com.example.nodemanager.domain.structure.Manager
import com.example.nodemanager.help.generateNodeName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class TreeViewModel : ViewModel(), ITreeViewModel {
    private val rootNode = NodeModel(name = generateNodeName("root"))

    private var nodes = mutableMapOf(rootNode.name to rootNode)

    private val _currentNode = MutableStateFlow<NodeModel>(rootNode)
    val currentNode: StateFlow<NodeModel> = _currentNode

    private val nodeManager = Manager(nodes, rootNode.name)

    override fun onPush(node: NodeModel) {
        nodeManager.push(node.name)
        updateStateFromManager()
    }

    override fun onBack() {
        nodeManager.back()
        updateStateFromManager()
    }

    override fun onAddChild() {
        nodeManager.addChild()
        updateStateFromManager()
    }

    override fun onRemoveCurrent() {
        nodeManager.removeCurrent()
        updateStateFromManager()
    }

    private fun updateStateFromManager() {
        nodes = nodeManager.getAllNodes().toMutableMap()
        val newId = nodeManager.getCurrentString()
        _currentNode.value = nodes[newId]!!
    }
}