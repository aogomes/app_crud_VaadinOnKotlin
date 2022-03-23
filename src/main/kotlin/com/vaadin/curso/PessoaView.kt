package com.vaadin.curso

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.layout.BaseComposite
import com.vaadin.layout.MyAppLayout
import com.vaadin.layout.buildComponent
import com.vaadin.layout.ui
import com.vaadin.service.PessoaService

@Route(layout = MyAppLayout::class)
@PageTitle("Tela Pessoa")
class PessoaView : BaseComposite() {

    private val updater = Updater()

    override fun initContent() = ui {
        verticalLayout {

            grid<Pessoa> {
                updater.addOnUpdate {
                    setItems(PessoaService.listaPessoas)
                }
                addColumn(Pessoa::nome).setHeader("Nome")
                addColumn(Pessoa::telefone).setHeader("Telefone")
                addComponentColumn { pes ->
                    buildComponent {
                        button("Edit") {
                            onLeftClick { EditPessoaDialog(pessoa = pes) { updater.update() }.open() }
                        }
                    }
                }

            }

            button("Adicionar Pessoa").apply {
                alignSelf = FlexComponent.Alignment.END
                onLeftClick {
                    EditPessoaDialog(Pessoa("", "")) { updater.update() }.open()
                }
            }

        }.apply { updater.update() }
    }
}

class Pessoa(var nome: String, var telefone: String)
