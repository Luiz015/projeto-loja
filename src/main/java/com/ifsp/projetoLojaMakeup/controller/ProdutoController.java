package com.ifsp.projetoLojaMakeup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/produto/{id}")
    public String detalhesProduto(Model model, @PathVariable Long id){
        Produto produto = produtoService.buscarPorId(id);
        model.addAttribute("produto", produto);

        return "produto";

    }

    @GetMapping("/admin/produto/novo")
    public String cadastrarProduto(){
        return "cadastrarProduto";
    }

    @PostMapping("/admin/produto/salvar")
    public String salvarProduto(Produto produto){
        produtoService.salvarProduto(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/admin/produto/editar/{id}")
    public String editarProduto(Model model, @PathVariable Long id){
        Produto produto = produtoService.buscarPorId(id);

        model.addAttribute("produto", produto);

        return "editarProduto";
    }

    @PostMapping("/admin/produto/atualizar")
    public String atualizarProduto(Produto produto){
        produtoService.atualizar(produto);

        return "redirect:/produtos";
    }

    @GetMapping("/admin/produto/excluir/{id}")
    public String excluitProduto(@PathVariable Long id){
        produtoService.deletar(id);

        return "redirect:/produtos";
    }

    @GetMapping("/buscar")
    public String buscarProduto(String titulo, Model model){

        List<Produto> listaProdutos = produtoService.buscarPorTitulo(titulo);

        model.addAttribute("lista", listaProdutos);

        return "produtos";
    }

}
