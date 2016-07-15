/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aeroclube.hibernate;

import br.aeroclube.pojo.Categoria;
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
public class CategoriaController {

    int startId;
    int endId;
    DataModel<Categoria> produtosList;
    ProdutosHelper helper;
    private int recordCount = 1000;
    private int pageSize = 10;
    List<Categoria> list;
    private Categoria current;
    private int selectedItemIndex;
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    /**
     * Creates a new instance of ProdutosController
     */
    
    
    public CategoriaController() {
        helper = new ProdutosHelper();
    }
    
    public Categoria getSelected() {
        if (current == null) {
            current = new Categoria();
            selectedItemIndex = -1;
        }
        return current;
    }


    public DataModel getCategorias() {
        if (produtosList == null) {
            produtosList = new ListDataModel(helper.getCategoriaList());
        }
        return produtosList;
    }

    public List getList() {
        return helper.getCategoriaList();
    }
    
    void recreateModel() {
        produtosList = null;
    }
    
    public int getPageSize() {
        return pageSize;
    }

    public String prepareView(){
        current = (Categoria) getCategorias().getRowData();
        list = getList();
        return "browse";
    }
    public String prepareList(){
        recreateModel();
        return "index";
    }

    public String preparaAdicionarCategoria(){
        categoria  = new Categoria();
        return "AddCategoria";
    }
    
    public String addCategoria() {
        helper.saveOrUpdateCategoria(categoria);
        return "AddProduto";
    }
    
}
