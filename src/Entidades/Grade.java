/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Mateus Cohuzer
 */
@Entity
@Table(name = "grade")
@NamedQueries({
    @NamedQuery(name = "Grade.findAll", query = "SELECT g FROM Grade g")})
public class Grade implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gradeIdgrade")
    private List<Personagem> personagemList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idgrade")
    private Integer idgrade;
    @Column(name = "nome_grade")
    private String nomeGrade;
    @Column(name = "descricao")
    private String descricao;

    public Grade() {
    }

    public Grade(Integer idgrade) {
        this.idgrade = idgrade;
    }

    public Integer getIdgrade() {
        return idgrade;
    }

    public void setIdgrade(Integer idgrade) {
        this.idgrade = idgrade;
    }

    public String getNomeGrade() {
        return nomeGrade;
    }

    public void setNomeGrade(String nomeGrade) {
        this.nomeGrade = nomeGrade;
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
        hash += (idgrade != null ? idgrade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grade)) {
            return false;
        }
        Grade other = (Grade) object;
        if ((this.idgrade == null && other.idgrade != null) || (this.idgrade != null && !this.idgrade.equals(other.idgrade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idgrade + ";" + nomeGrade + ";" + descricao;
    }

    public List<Personagem> getPersonagemList() {
        return personagemList;
    }

    public void setPersonagemList(List<Personagem> personagemList) {
        this.personagemList = personagemList;
    }
    
}
