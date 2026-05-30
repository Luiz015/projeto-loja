package com.ifsp.projetoLojaMakeup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ifsp.projetoLojaMakeup.model.Produto;
import com.ifsp.projetoLojaMakeup.service.ProdutoService;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produtos")
    public String listarProdutos(Model model){
        
        List<Produto> listaProdutos = produtoService.listarTodos();
        model.addAttribute("lista", listaProdutos);
        return "produtos";
    }
}
