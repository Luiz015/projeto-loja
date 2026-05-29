package com.ifsp.projetoLojaMakeup.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ifsp.projetoLojaMakeup.model.Produto;
import com.ifsp.projetoLojaMakeup.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class UsuarioRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Usuario usuario){
        String sql = "INSERT INTO usuarios (nome, email, telefone, senha) VALUES (:nome, :email, :telefone, :senha)";
        Query query = em.createNativeQuery(sql);
        query.setParameter("nome", usuario.getNome());
        query.setParameter("email", usuario.getEmail());
        query.setParameter("telefone", usuario.getTelefone());
        query.setParameter("senha", usuario.getSenha());
        query.executeUpdate();
    }

    @Transactional
    public List<Usuario> findAll() {
    String sql = "SELECT * FROM usuarios";
    Query query = em.createNativeQuery(sql, Produto.class);
    List<Usuario> usuarios = query.getResultList();
    return usuarios;
    }
    
    @Transactional
    public Produto findById(Long id){
        String sql = "SELECT * FROM produtos WHERE id = :id";
        Query query = em.createNativeQuery(sql, Produto.class);
        query.setParameter("id", id);
        Produto produto = (Produto) query.getSingleResult();
        return produto;
    }

    public void atualizar(Usuario usuario){

        String sql = "UPDATE usuarios SET nome = :nome, email = :email, telefone = :telefone, senha = :senha WHERE id = :id";
        Query query = em.createNativeQuery(sql);

        query.setParameter("id", usuario.getId());
        query.setParameter("nome", usuario.getNome());
        query.setParameter("email", usuario.getEmail());
        query.setParameter("telefone", usuario.getTelefone());
        query.setParameter("senha", usuario.getSenha());

        query.executeUpdate();
    }

    @Transactional
    public void deletarUsuario(Long id){

        String sql = "DELETE FROM usuarios WHERE id = :id";

        Query query = em.createNativeQuery(sql);
        query.setParameter("id", id);

        query.executeUpdate();
    }
}
