package com.vaadin.curso

import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.span
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.layout.MyAppLayout

@Route(value = "", layout = MyAppLayout::class)
@PageTitle("Pagina Inicial")
class HomeView : KComposite() {

    private val root = ui {
        verticalLayout {

            span("Meu primeiro componente vaadin") {  }

        }
    }



}