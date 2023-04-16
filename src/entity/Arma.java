/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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
@Table(name = "arma")
@NamedQueries({
    @NamedQuery(name = "Arma.findAll", query = "SELECT a FROM Arma a")})
public class Arma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idarma")
    private Integer idarma;
    @Column(name = "nome_arma")
    private String nomeArma;
    @Column(name = "desc_arma")
    private String descArma;
    @Column(name = "dano")
    private Integer dano;

    public Arma() {
    }

    public Arma(Integer idarma) {
        this.idarma = idarma;
    }

    public Integer getIdarma() {
        return idarma;
    }

    public void setIdarma(Integer idarma) {
        this.idarma = idarma;
    }

    public String getNomeArma() {
        return nomeArma;
    }

    public void setNomeArma(String nomeArma) {
        this.nomeArma = nomeArma;
    }

    public String getDescArma() {
        return descArma;
    }

    public void setDescArma(String descArma) {
        this.descArma = descArma;
    }

    public Integer getDano() {
        return dano;
    }

    public void setDano(Integer dano) {
        this.dano = dano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarma != null ? idarma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arma)) {
            return false;
        }
        Arma other = (Arma) object;
        if ((this.idarma == null && other.idarma != null) || (this.idarma != null && !this.idarma.equals(other.idarma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Arma[ idarma=" + idarma + " ]";
    }
    
}
