package com.ifsp.projetoLojaMakeup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ifsp.projetoLojaMakeup.model.Usuario;
import com.ifsp.projetoLojaMakeup.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    String msg;
    //abre form cadastro
    @GetMapping("/cadastro")
    public String cadastroFormulario(){
        return "cadaastro";
    }

    //formulario
    @PostMapping("/cadastro")
    public String cadastrar(Model model,Usuario usuario){
        if (usuarioService.buscarPorEmail(usuario.getEmail())!=null) {
            msg = "Email já cadastrado";
            model.addAttribute("msgErro", msg);
            return "cadastro";
        }

        usuario.setPerfil("CLIENTE");
        usuarioService.salvar(usuario);

        return "redirect:/login";
    }

    //parte de login
    @PostMapping("/login")
    public String login(@RequestParam String senha,@RequestParam String email,HttpSession sessao, Model model){
        Usuario usuarioBanco = usuarioService.buscarPorEmail(email);
        if (usuarioBanco==null) {
            msg = "Usuário não cadastrado";
            model.addAttribute("msgErro", msg);
            return "login";
        }

        if (!usuarioBanco.getSenha().equals(senha)) {
            msg = "Senha incorreta";
            model.addAttribute("msgErro", msg);
            return "login";
        }
        sessao.setAttribute("usuario", usuarioBanco);

        if (usuarioBanco.getPerfil().equals("ADMIN")) {
            return "redirect:/admin/pr";
        }
        return "redirect:/";
    }
}
