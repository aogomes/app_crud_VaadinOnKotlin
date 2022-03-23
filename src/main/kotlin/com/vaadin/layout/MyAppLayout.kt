package com.vaadin.layout


import com.vaadin.curso.HomeView
import com.vaadin.curso.PessoaView
import com.vaadin.flow.component.dependency.CssImport
import com.vaadin.flow.component.html.Nav
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.page.BodySize
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.lumo.Lumo


@BodySize(width = "100vw", height = "100vh")
@CssImport("frontend://styles/shared-styles.css")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Theme(Lumo::class)
class MyAppLayout : BaseLayout() {

    override fun Nav.menu() {

        addMenu {

            addItemMenu<HomeView>("Home", VaadinIcon.HOME)

            addItemMenu<PessoaView>("Pessoa", VaadinIcon.USERS)

        }
    }

}




