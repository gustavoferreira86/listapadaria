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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author gusta
 */
@ManagedBean
@SessionScoped
public class ProdutosController {

    int startId;
    int endId;
    DataModel<Produtos> produtosList;
    ProdutosHelper helper;
    private int recordCount = 1000;
    private int pageSize = 10;    
    private Produtos current;
    private Produtos produto;
    private int selectedItemIndex;
    private List list;

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }
    
    /**
     * Creates a new instance of ProdutosController
     */
    
    
    public ProdutosController() {
        helper = new ProdutosHelper();
    }
    
    public Produtos getSelected() {
        if (current == null) {
            current = new Produtos();
            selectedItemIndex = -1;
        }
        return current;
    }

    public List getList() {
        return helper.getProdutosList();
    }

    public DataModel getProdutos() {
        produtosList = new ListDataModel(helper.getProdutosList());
        return produtosList;
    }

    void recreateModel() {
        produtosList = null;
    }
    
    
    public int getPageSize() {
        return pageSize;
    }

    public Produtos getCurrent() {
        return current;
    }

    public String setCurrent(int current) {
        ProdutosHelper helper = new ProdutosHelper();
        this.current = helper.getProdutosList(current);
        return this.current.getNomeProduto();
    }
    
    public String prepareView(int id){
        produto = (Produtos) helper.getProdutosList(id);
        list = getList();
        return "EditarProduto";
    }
    public String prepareList(){
        recreateModel();
        return "index";
    }
    
    public String preparaAdicionar(){
        produto  = new Produtos();
        return "AddProduto";
    }
    
    public String adicionar(int id) {
        if(produto==null)
            preparaAdicionar();
        helper.saveOrUpdate(produto);
        return "index";
    }
    
    public String update() {
        helper.update(produto);
        return "index";
    }
    
    public Fornecedores getNomeFornecedor(int id) {
        ProdutosHelper helper = new ProdutosHelper();
        if(produto!=null)
        this.produto.setFornecedores_idFornecedores(id);
        return helper.getFornecedores(id);
    }
    
    public Categoria getNomeCategoria(int id) {
        ProdutosHelper helper = new ProdutosHelper();
        if(produto!=null)
        this.produto.setCategoria_idCategoria(id);
        return helper.getCategoria(id);
    }
}
