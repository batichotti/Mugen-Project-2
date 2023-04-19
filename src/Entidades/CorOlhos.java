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
@Table(name = "cor_olhos")
@NamedQueries({
    @NamedQuery(name = "CorOlhos.findAll", query = "SELECT c FROM CorOlhos c")})
public class CorOlhos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcor_olhos")
    private Integer idcorOlhos;
    @Column(name = "cor")
    private String cor;
    @Column(name = "heterocromia")
    private Short heterocromia;

    public CorOlhos() {
    }

    public CorOlhos(Integer idcorOlhos) {
        this.idcorOlhos = idcorOlhos;
    }

    public Integer getIdcorOlhos() {
        return idcorOlhos;
    }

    public void setIdcorOlhos(Integer idcorOlhos) {
        this.idcorOlhos = idcorOlhos;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Short getHeterocromia() {
        return heterocromia;
    }

    public void setHeterocromia(Short heterocromia) {
        this.heterocromia = heterocromia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcorOlhos != null ? idcorOlhos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorOlhos)) {
            return false;
        }
        CorOlhos other = (CorOlhos) object;
        if ((this.idcorOlhos == null && other.idcorOlhos != null) || (this.idcorOlhos != null && !this.idcorOlhos.equals(other.idcorOlhos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idcorOlhos + ";" + cor + ";" + (heterocromia==1?"Sim":"Não");
    }
    
}
