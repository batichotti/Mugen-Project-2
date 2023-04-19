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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Mateus Cohuzer
 */
@Entity
@Table(name = "place")
@NamedQueries({
    @NamedQuery(name = "Place.findAll", query = "SELECT p FROM Place p")})
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idplace")
    private Integer idplace;
    @Column(name = "nome_place")
    private String nomePlace;
    @Column(name = "desc_place")
    private String descPlace;
    @Column(name = "tem_dedo")
    private Short temDedo;
    @JoinColumn(name = "cidade_idcidade", referencedColumnName = "idcidade")
    @ManyToOne(optional = false)
    private Cidade cidadeIdcidade;

    public Place() {
    }

    public Place(Integer idplace) {
        this.idplace = idplace;
    }

    public Integer getIdplace() {
        return idplace;
    }

    public void setIdplace(Integer idplace) {
        this.idplace = idplace;
    }

    public String getNomePlace() {
        return nomePlace;
    }

    public void setNomePlace(String nomePlace) {
        this.nomePlace = nomePlace;
    }

    public String getDescPlace() {
        return descPlace;
    }

    public void setDescPlace(String descPlace) {
        this.descPlace = descPlace;
    }

    public Short getTemDedo() {
        return temDedo;
    }

    public void setTemDedo(Short temDedo) {
        this.temDedo = temDedo;
    }

    public Cidade getCidadeIdcidade() {
        return cidadeIdcidade;
    }

    public void setCidadeIdcidade(Cidade cidadeIdcidade) {
        this.cidadeIdcidade = cidadeIdcidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplace != null ? idplace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Place)) {
            return false;
        }
        Place other = (Place) object;
        if ((this.idplace == null && other.idplace != null) || (this.idplace != null && !this.idplace.equals(other.idplace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idplace + ";" + nomePlace + ";" + descPlace + ";" + (temDedo==1?"Sim":"Não") + ";" + cidadeIdcidade;
    }
    
}
