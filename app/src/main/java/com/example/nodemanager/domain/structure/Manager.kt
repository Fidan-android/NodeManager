package com.example.nodemanager.domain.structure

import com.example.nodemanager.domain.model.NodeModel
import java.util.UUID

class Manager(
    private var nodes: Map<String, NodeModel>,
    root: String
) : IManager {

    private var currentString: String = root

    override fun getAllNodes(): Map<String, NodeModel> = nodes
    override fun getCurrentString(): String = currentString

    private fun getCurrent(): NodeModel = nodes[currentString]!!

    override fun push(model: String) {
        if (nodes[currentString]?.children?.any { it.name == model } == true) {
            currentString = model
        }
    }

    override fun back() {
        nodes[currentString]?.parent?.let {
            currentString = it
        }
    }

    override fun addChild() {
        val newChild = NodeModel(name = UUID.randomUUID().toString(), parent = currentString)
        val current = getCurrent()
        val updatedCurrent = current.copy(children = current.children + newChild)

        nodes = nodes.toMutableMap().apply {
            put(updatedCurrent.name, updatedCurrent)
            put(newChild.name, newChild)
        }

        currentString = updatedCurrent.name
    }

    override fun removeCurrent() {
        val current = getCurrent()
        val parentString = current.parent ?: return

        val parent = nodes[parentString] ?: return
        val updatedParent = parent.copy(
            children = parent.children.filter { it.name != currentString }
        )

        nodes = nodes.toMutableMap().apply {
            put(updatedParent.name, updatedParent)
            remove(currentString)
        }

        currentString = updatedParent.name
    }
}
