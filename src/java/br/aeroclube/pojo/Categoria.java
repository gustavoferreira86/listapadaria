package br.aeroclube.pojo;
// Generated 11/07/2016 08:40:05 by Hibernate Tools 4.3.1

import br.aeroclube.hibernate.SampleEntity;




/**
 * Categoria generated by hbm2java
 */
public class Categoria  implements java.io.Serializable, SampleEntity {


     private Integer idCategoria;
     private String nomeCategoria;

    public Categoria() {
    }

    public Categoria(String nomeCategoria) {
       this.nomeCategoria = nomeCategoria;
    }
   
    public Integer getIdCategoria() {
        return this.idCategoria;
    }
    
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
    public String getNomeCategoria() {
        return this.nomeCategoria;
    }
    
    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    @Override
    public int getId() {
        return idCategoria;
    }


}

