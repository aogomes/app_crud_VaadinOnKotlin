package com.vaadin.layout

import com.github.mvysny.karibudsl.v10.button
import com.github.mvysny.karibudsl.v10.onLeftClick
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.HasComponents
import com.vaadin.flow.component.HasValue
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.data.binder.BeanValidationBinder
import com.vaadin.flow.data.binder.Binder
import kotlin.reflect.KClass

abstract class BaseDialog<T : Any>(var entityClass: KClass<T>, var entity: T) : Dialog(), Binding<T> {

    abstract fun HasComponents.initContent(): Component

    override fun open() {
        initContent()
        super.open().also { binder.readBean(entity) }
    }

    fun HasComponents.addButtonFechar() = button("Fechar").onLeftClick { close() }

    override val binder: BeanValidationBinder<T> by lazy { BeanValidationBinder(entityClass.java) }

}

interface Binding<T> {
    val binder: BeanValidationBinder<T>

    fun <VALUE> HasValue<*, VALUE>.bind(propertyName: String): Binder.Binding<T, VALUE> =
        binder.bind(this, propertyName)

    val <VALUE> HasValue<*, VALUE>.forField get(): Binder.BindingBuilder<T, VALUE> = binder.forField(this)
}
