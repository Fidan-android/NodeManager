package com.example.nodemanager.domain.structure

import com.example.nodemanager.domain.model.NodeModel
import java.util.UUID

class Manager(root: NodeModel) : IManager<NodeModel> {

    private var currentString: String = root.name
    private var nodes = mutableMapOf(root.name to root)

    override fun getAllNodes(): Map<String, NodeModel> = nodes

    override fun getCurrent(): NodeModel = nodes[currentString]!!

    override fun push(model: String): NodeModel {
        if (nodes[currentString]?.children?.any { it.name == model } == true) {
            currentString = model
        }

        return getCurrent()
    }

    override fun back(): NodeModel {
        nodes[currentString]?.parent?.let {
            currentString = it
        }

        return getCurrent()
    }

    override fun addChild(): NodeModel {
        val newChild = NodeModel(name = UUID.randomUUID().toString(), parent = currentString)
        val current = getCurrent()
        val updatedCurrent = current.copy(children = current.children + newChild)

        nodes = nodes.toMutableMap().apply {
            put(updatedCurrent.name, updatedCurrent)
            put(newChild.name, newChild)
        }

        currentString = updatedCurrent.name

        return getCurrent()
    }

    override fun removeCurrent(): NodeModel {
        val current = getCurrent()
        val parentString = current.parent ?: return getCurrent()

        val parent = nodes[parentString] ?: return getCurrent()
        val updatedParent = parent.copy(
            children = parent.children.filter { it.name != currentString }
        )

        nodes = nodes.toMutableMap().apply {
            put(updatedParent.name, updatedParent)
            remove(currentString)
        }
        currentString = updatedParent.name

        return getCurrent()
    }
}
