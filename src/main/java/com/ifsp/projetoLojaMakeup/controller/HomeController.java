package com.ifsp.projetoLojaMakeup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ifsp.projetoLojaMakeup.model.Produto;
import com.ifsp.projetoLojaMakeup.service.ProdutoService;

@Controller
public class HomeController {
    @Autowired
    private ProdutoService produtoService;
    @GetMapping("/")
    public String abrirHome(Model model){
        List<Produto> listaProdutos = produtoService.listarTodos();
        model.addAttribute("lista", listaProdutos);
        return "home";
    }

    @GetMapping("/login")
    public String abrirLogin(){
        return "login";
    }

    @GetMapping("/cadastro")
    public String abrirCadastro(){
        return "cadastro";
    }
/*
    @GetMapping("/buscar")
    public String buscarProduto(String titulo, Model model){

        List<Produto> listaProdutos = produtoService.buscarPorTitulo(titulo);

        model.addAttribute("lista", listaProdutos);

        return "produtos";
    }
         */
}
