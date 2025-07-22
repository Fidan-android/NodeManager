package com.example.nodemanager.presentation.tree

import androidx.lifecycle.ViewModel
import com.example.nodemanager.domain.model.NodeModel
import com.example.nodemanager.domain.structure.Manager
import com.example.nodemanager.help.generateNodeName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class TreeViewModel : ViewModel(), ITreeViewModel {
    private val rootNode = NodeModel(name = generateNodeName("root"))

    private val _currentNode = MutableStateFlow<NodeModel>(rootNode)
    val currentNode: StateFlow<NodeModel> = _currentNode

    private val nodeManager = Manager(rootNode)

    override fun onPush(node: NodeModel) {
        _currentNode.value = nodeManager.push(node.name)
    }

    override fun onBack() {
        _currentNode.value = nodeManager.back()
    }

    override fun onAddChild() {
        _currentNode.value = nodeManager.addChild()
    }

    override fun onRemoveCurrent() {
        _currentNode.value = nodeManager.removeCurrent()
    }
}