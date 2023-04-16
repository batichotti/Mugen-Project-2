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
@Table(name = "cla")
@NamedQueries({
    @NamedQuery(name = "Cla.findAll", query = "SELECT c FROM Cla c")})
public class Cla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcla")
    private Integer idcla;
    @Column(name = "nome_cla")
    private String nomeCla;
    @Column(name = "desc_cla")
    private String descCla;

    public Cla() {
    }

    public Cla(Integer idcla) {
        this.idcla = idcla;
    }

    public Integer getIdcla() {
        return idcla;
    }

    public void setIdcla(Integer idcla) {
        this.idcla = idcla;
    }

    public String getNomeCla() {
        return nomeCla;
    }

    public void setNomeCla(String nomeCla) {
        this.nomeCla = nomeCla;
    }

    public String getDescCla() {
        return descCla;
    }

    public void setDescCla(String descCla) {
        this.descCla = descCla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcla != null ? idcla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cla)) {
            return false;
        }
        Cla other = (Cla) object;
        if ((this.idcla == null && other.idcla != null) || (this.idcla != null && !this.idcla.equals(other.idcla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Cla[ idcla=" + idcla + " ]";
    }
    
}
