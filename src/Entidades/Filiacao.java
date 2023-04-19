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
@Table(name = "filiacao")
@NamedQueries({
    @NamedQuery(name = "Filiacao.findAll", query = "SELECT f FROM Filiacao f")})
public class Filiacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idfiliacao")
    private Integer idfiliacao;
    @Column(name = "filiacao")
    private String filiacao;
    @Column(name = "desc_filiacao")
    private String descFiliacao;

    public Filiacao() {
    }

    public Filiacao(Integer idfiliacao) {
        this.idfiliacao = idfiliacao;
    }

    public Integer getIdfiliacao() {
        return idfiliacao;
    }

    public void setIdfiliacao(Integer idfiliacao) {
        this.idfiliacao = idfiliacao;
    }

    public String getFiliacao() {
        return filiacao;
    }

    public void setFiliacao(String filiacao) {
        this.filiacao = filiacao;
    }

    public String getDescFiliacao() {
        return descFiliacao;
    }

    public void setDescFiliacao(String descFiliacao) {
        this.descFiliacao = descFiliacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfiliacao != null ? idfiliacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filiacao)) {
            return false;
        }
        Filiacao other = (Filiacao) object;
        if ((this.idfiliacao == null && other.idfiliacao != null) || (this.idfiliacao != null && !this.idfiliacao.equals(other.idfiliacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idfiliacao + ";" + filiacao + ";" + descFiliacao;
    }
    
}
