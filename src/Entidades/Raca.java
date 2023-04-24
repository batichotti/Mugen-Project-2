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
@Table(name = "raca")
@NamedQueries({
    @NamedQuery(name = "Raca.findAll", query = "SELECT r FROM Raca r")})
public class Raca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idraca")
    private Integer idraca;
    @Column(name = "nome_raca")
    private String nomeRaca;
    @Column(name = "desc_raca")
    private String descricao;

    public Raca() {
    }

    public Raca(Integer idraca) {
        this.idraca = idraca;
    }

    public Integer getIdraca() {
        return idraca;
    }

    public void setIdraca(Integer idraca) {
        this.idraca = idraca;
    }

    public String getNomeRaca() {
        return nomeRaca;
    }

    public void setNomeRaca(String nomeRaca) {
        this.nomeRaca = nomeRaca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idraca != null ? idraca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Raca)) {
            return false;
        }
        Raca other = (Raca) object;
        if ((this.idraca == null && other.idraca != null) || (this.idraca != null && !this.idraca.equals(other.idraca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idraca + ";" + nomeRaca + ";" + descricao;
    }
    
}
