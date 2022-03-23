package com.vaadin.service

import com.vaadin.curso.Pessoa

object PessoaService {

    val listaPessoas: MutableList<Pessoa> = mutableListOf()

    fun save(pessoa:Pessoa) {
        listaPessoas.add(pessoa)
    }

}