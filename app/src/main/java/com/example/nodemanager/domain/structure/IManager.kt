package com.example.nodemanager.domain.structure

import com.example.nodemanager.domain.model.NodeModel

interface IManager {
    // getters
    fun getCurrentString(): String
    fun getAllNodes(): Map<String, NodeModel>

    // setters
    fun push(model: String)
    fun back()
    fun addChild()
    fun removeCurrent()
}