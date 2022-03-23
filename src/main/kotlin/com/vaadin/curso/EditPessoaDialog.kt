package com.vaadin.curso

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.HasComponents
import com.vaadin.layout.BaseDialog
import com.vaadin.service.PessoaService

class EditPessoaDialog(var pessoa: Pessoa, val block: () -> Unit = {}) : BaseDialog<Pessoa>(Pessoa::class, pessoa) {

    override fun HasComponents.initContent() =

        verticalLayout {

            textField("Nome") {
                binder.forField(this).bind(Pessoa::nome)
            }

            textField("Telefone") {
                binder.forField(this)
                    .asRequired("Obrigatório")
                    .withValidator({ it.length >= 9 }, "O campo deve ter no minimo 9 digitos")
                    .bind(Pessoa::telefone)
            }

            datePicker("Data") { }

            button("Salvar").onLeftClick {
                binder.writeBean(pessoa)
                PessoaService.save(pessoa)
                block()
                close()
            }

            addButtonFechar()
        }
}