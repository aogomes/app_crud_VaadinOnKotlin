package com.vaadin.layout

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.HasComponents
import com.vaadin.flow.component.HasElement
import com.vaadin.flow.component.html.ListItem
import com.vaadin.flow.component.html.Nav
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.component.html.UnorderedList
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.router.RouterLink
import javax.xml.bind.Element


@VaadinDsl
fun (@VaadinDsl HasComponents).nav(block: (@VaadinDsl Nav).() -> Unit = {}): Nav =
    init(Nav().apply {
        className = "menu-item-container"
        element.setAttribute("aria-labelledby", "views")
    }, block)


@VaadinDsl
fun HasComponents.addMenu(block: HasComponents.() -> Unit = {}) =
    init(UnorderedList().apply {
        className = "navigation-list"
        block()
    })

@VaadinDsl
inline fun <reified T : Component> HasComponents.addItemMenu(menuTitle: String?, icon: VaadinIcon) =
    init(ListItem().apply {
        routerLink<T>(menuTitle, icon)
    })

@VaadinDsl
inline fun <reified T : Component> HasComponents.routerLink(menuTitle: String?, icon: VaadinIcon) =
    init(RouterLink().apply {
        className = "menu-item-link"
        setRoute(T::class.java)
        button(menuTitle, icon(icon)) {
            className = "menu-item-text"
        }
    })
