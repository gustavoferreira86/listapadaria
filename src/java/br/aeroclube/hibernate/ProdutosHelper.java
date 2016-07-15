/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aeroclube.hibernate;

import br.aeroclube.pojo.Categoria;
import br.aeroclube.pojo.Fornecedores;
import br.aeroclube.pojo.Produtos;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 *
 * @author gusta
 */
public class ProdutosHelper {
    
    Session session = null;
    private Transaction tx;

    public ProdutosHelper() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        if(sessionFactory.isClosed())
            this.session = sessionFactory.openSession();
        else 
            this.session = sessionFactory.getCurrentSession();
    }
    
    public List getProdutosList() {
        List<Produtos> produtosList = null;
        try {
            verificaSession();
            Query q = session.createQuery ("from Produtos");
            produtosList = (List<Produtos>) q.list();
            tx.commit();
            if(session.isOpen())
             session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return produtosList;
    }
    
    public List getProdutosList(int id) {
        List<Produtos> produtosList = null;
        try {
            verificaSession();
            Query q = session.createQuery ("from Produtos where idProdutos="+id);
            produtosList = (List<Produtos>) q.list();
            tx.commit();
            if(session.isOpen())
             session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return produtosList;
    }
    
    public void saveOrUpdateFornecedores(Fornecedores fornecedores) {
        verificaSession();
        session.save(fornecedores);
        tx.commit();
         if(session.isOpen())
             session.close();
    }
    
    public Fornecedores getFornecedores(int fornecedores) {
        verificaSession();
        Query q = session.createQuery ("from Fornecedores where idFornecedores="+fornecedores);
        Fornecedores f =  (Fornecedores) q.list().get(0);
        tx.commit();
         if(session.isOpen())
             session.close();
         return f;
    }
    
    public Categoria getCategoria(int categoria) {
        verificaSession();
        Query q = session.createQuery ("from Categoria where idCategoria="+categoria);
        Categoria f =  (Categoria) q.list().get(0);
        tx.commit();
         if(session.isOpen())
             session.close();
         return f;
    }
    
    public List getFornecedoresList() {
        List<Fornecedores> fornecedoresList = null;
        try {
            verificaSession();
            Query q = session.createQuery ("from Fornecedores");
            fornecedoresList = (List<Fornecedores>) q.list();
            tx.commit();
             if(session.isOpen())
             session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    return fornecedoresList;
    }
    
    public List getCategoriaList() {
        List<Categoria> categoriaList = null;
        try {
            verificaSession();
            Query q = session.createQuery ("from Categoria");
            categoriaList = (List<Categoria>) q.list();
            tx.commit();
             if(session.isOpen())
             session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return categoriaList;
    }

    void saveOrUpdateCategoria(Categoria categoria) {
        verificaSession();
        session.save(categoria);
        tx.commit();
         if(session.isOpen())
             session.close();
    }
    
    void saveOrUpdate(Produtos produtos) {
        verificaSession();
        session.save(produtos);
        tx.commit();
         if(session.isOpen())
             session.close();
    }

    void update(Produtos produto) {
        
        try {
            verificaSession();
            session.update(produto);
            tx.commit();
            if(session.isOpen())
             session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void verificaSession() {
        if(!session.isOpen()) {
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                this.session = sessionFactory.openSession();
            }
        if(tx==null)
            tx = session.beginTransaction();
        else if(!tx.isActive())
            tx = session.beginTransaction();
    }
    
    
}
