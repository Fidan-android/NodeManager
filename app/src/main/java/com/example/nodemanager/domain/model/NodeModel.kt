package com.example.nodemanager.domain.model

import java.util.UUID

data class NodeModel(
    val name: String,
    var parent: String? = null,
    val children: List<NodeModel> = listOf()
) {
    override fun toString(): String {
        return "$name ${parent.toString()}, children: ${children.size}"
    }
}