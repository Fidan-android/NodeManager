package com.example.nodemanager.domain.structure

import com.example.nodemanager.domain.model.NodeModel

interface IManager<T> {
    // getters
    fun getCurrent(): T
    fun getAllNodes(): Map<String, NodeModel>

    // setters
    fun push(model: String):T
    fun back():T
    fun addChild():T
    fun removeCurrent():T
}