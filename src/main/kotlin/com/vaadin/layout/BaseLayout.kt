package com.vaadin.layout

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.html.Header
import com.vaadin.flow.component.html.Nav
import com.vaadin.flow.component.html.Section
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.router.PageTitle

abstract class BaseLayout : AppLayout() {

    private lateinit var viewTitle: Span

    private val currentPageTitle: String
        get() {
            return content?.javaClass?.getAnnotation(PageTitle::class.java)?.value ?: ""
        }

    init {
        primarySection = Section.DRAWER
        addToNavbar(true, createHeaderContent())
        addToDrawer(createDrawerContent())
    }

    private fun createHeaderContent() =
        Header().apply {
            className = "view-header"
            drawerToggle {
                className = "view-toggle"
                addThemeVariants(ButtonVariant.LUMO_CONTRAST)
                element.setAttribute("aria-label", "Menu toggle")
            }
            viewTitle = span { className = "view-title" }
            add(viewTitle)
        }

    private fun createDrawerContent() =
        Section().apply {
            className = "drawer-section"
            h2("App VoK") { className = "app-name" }
            nav { menu() }
            footer { className = "footer" }
        }

    abstract fun Nav.menu()

    override fun afterNavigation() {
        super.afterNavigation()
        viewTitle.text = currentPageTitle
    }

}