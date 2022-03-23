package com.vaadin.layout

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.Composite
import com.vaadin.flow.component.HasComponents
import com.vaadin.flow.dom.Element
import java.util.stream.Stream

abstract class BaseComposite : Composite<Component>() {

    abstract override fun initContent(): Component?

    final override fun getContent(): Component = super.getContent()
    final override fun getElement(): Element = super.getElement()
    final override fun getChildren(): Stream<Component> = super.getChildren()
}

fun <T : Component> ui(block: HasComponents.() -> T): T = buildComponent(block)

fun <T : Component> buildComponent(block: HasComponents.() -> T): T = object : HasComponents {
    override fun getElement(): Element = throw UnsupportedOperationException("Not expected to be called")
    override fun add(vararg components: Component) {
        require(components.size < 2) {
            "Too many components to add - composite can only host one! ${components.toList()}"
        }
    }
}.block()

