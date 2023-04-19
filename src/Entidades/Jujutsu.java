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
@Table(name = "jujutsu")
@NamedQueries({
    @NamedQuery(name = "Jujutsu.findAll", query = "SELECT j FROM Jujutsu j")})
public class Jujutsu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idjujutsu")
    private Integer idjujutsu;
    @Column(name = "jujutsu_name")
    private String jujutsuName;
    @Column(name = "jujutsu_desc")
    private String jujutsuDesc;
    @Column(name = "dano_base")
    private Integer danoBase;

    public Jujutsu() {
    }

    public Jujutsu(Integer idjujutsu) {
        this.idjujutsu = idjujutsu;
    }

    public Integer getIdjujutsu() {
        return idjujutsu;
    }

    public void setIdjujutsu(Integer idjujutsu) {
        this.idjujutsu = idjujutsu;
    }

    public String getJujutsuName() {
        return jujutsuName;
    }

    public void setJujutsuName(String jujutsuName) {
        this.jujutsuName = jujutsuName;
    }

    public String getJujutsuDesc() {
        return jujutsuDesc;
    }

    public void setJujutsuDesc(String jujutsuDesc) {
        this.jujutsuDesc = jujutsuDesc;
    }

    public Integer getDanoBase() {
        return danoBase;
    }

    public void setDanoBase(Integer danoBase) {
        this.danoBase = danoBase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idjujutsu != null ? idjujutsu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jujutsu)) {
            return false;
        }
        Jujutsu other = (Jujutsu) object;
        if ((this.idjujutsu == null && other.idjujutsu != null) || (this.idjujutsu != null && !this.idjujutsu.equals(other.idjujutsu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idjujutsu + ";" + jujutsuName + ";" + danoBase;
    }
    
}
