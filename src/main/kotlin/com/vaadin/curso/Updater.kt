package com.vaadin.curso

open class Updater {

    private val onUpdate by lazy { mutableListOf<() -> Unit>() }

    fun addOnUpdate(block: () -> Unit) {
        onUpdate.add(block)
    }

    fun update() = onUpdate.forEach { it() }

}