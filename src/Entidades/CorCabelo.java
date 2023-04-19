/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Mateus Cohuzer
 */
@Entity
@Table(name = "cor_cabelo")
@NamedQueries({
    @NamedQuery(name = "CorCabelo.findAll", query = "SELECT c FROM CorCabelo c")})
public class CorCabelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcor_cabelo")
    private Integer idcorCabelo;
    @Column(name = "cor")
    private String cor;

    public CorCabelo() {
    }

    public CorCabelo(Integer idcorCabelo) {
        this.idcorCabelo = idcorCabelo;
    }

    public Integer getIdcorCabelo() {
        return idcorCabelo;
    }

    public void setIdcorCabelo(Integer idcorCabelo) {
        this.idcorCabelo = idcorCabelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcorCabelo != null ? idcorCabelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorCabelo)) {
            return false;
        }
        CorCabelo other = (CorCabelo) object;
        if ((this.idcorCabelo == null && other.idcorCabelo != null) || (this.idcorCabelo != null && !this.idcorCabelo.equals(other.idcorCabelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idcorCabelo + ";" + cor;
    }
    
}
