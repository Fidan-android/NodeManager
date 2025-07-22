package com.example.nodemanager.presentation.tree

import com.example.nodemanager.domain.model.NodeModel

interface ITreeViewModel {
    fun onPush(node: NodeModel)
    fun onBack()
    fun onAddChild()
    fun onRemoveCurrent()
}