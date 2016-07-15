/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aeroclube.hibernate;

import br.aeroclube.pojo.Fornecedores;
import br.aeroclube.pojo.Produtos;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author gusta
 */
@ManagedBean
@SessionScoped
public class FornecedorController {

    int startId;
    int endId;
    DataModel<Fornecedores> produtosList;
    ProdutosHelper helper;
    private int recordCount = 1000;
    private int pageSize = 10;
    List<Fornecedores> list;
    private Fornecedores current;
    private int selectedItemIndex;
    private Fornecedores fornecedor;
    
    /**
     * Creates a new instance of ProdutosController
     */
    public FornecedorController() {
        helper = new ProdutosHelper();
        fornecedor = new Fornecedores();
    }
    
    public Fornecedores getSelected() {
        if (current == null) {
            current = new Fornecedores();
            selectedItemIndex = -1;
        }
        return current;
    }


    public DataModel getFornecedores() {
        if (produtosList == null) {
            produtosList = new ListDataModel(helper.getFornecedoresList());
        }
        return produtosList;
    }

    public List getList() {
        return helper.getFornecedoresList();
    }
    
    void recreateModel() {
        produtosList = null;
    }
    
    public int getPageSize() {
        return pageSize;
    }

    public String prepareView(){
        current = (Fornecedores) getFornecedores().getRowData();
        list = getList();
        return "AddFornecedor";
    }
    public String prepareList(){
        recreateModel();
        return "index";
    }
    
    public String preparaAdicionarFornecedor(){
        fornecedor  = new Fornecedores();
        return "index";
    }
    
    public String addFornecedor() {
        helper.saveOrUpdateFornecedores(fornecedor);
        return "AddProduto";
    }

    public Fornecedores getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedores fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    
    
}
