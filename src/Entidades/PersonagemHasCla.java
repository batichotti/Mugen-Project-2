/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "personagem_has_cla")
@NamedQueries({
    @NamedQuery(name = "PersonagemHasCla.findAll", query = "SELECT p FROM PersonagemHasCla p"),
    @NamedQuery(name = "PersonagemHasCla.findByPersonagemIdpersonagem", query = "SELECT p FROM PersonagemHasCla p WHERE p.personagemHasClaPK.personagemIdpersonagem = :personagemIdpersonagem"),
    @NamedQuery(name = "PersonagemHasCla.findByClaIdcla", query = "SELECT p FROM PersonagemHasCla p WHERE p.personagemHasClaPK.claIdcla = :claIdcla"),
    @NamedQuery(name = "PersonagemHasCla.findByEscopo", query = "SELECT p FROM PersonagemHasCla p WHERE p.escopo = :escopo")})
public class PersonagemHasCla implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonagemHasClaPK personagemHasClaPK;
    @Column(name = "escopo")
    private String escopo;
    @JoinColumn(name = "personagem_idpersonagem", referencedColumnName = "idpersonagem", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personagem personagem;

    public PersonagemHasCla() {
    }

    public PersonagemHasCla(PersonagemHasClaPK personagemHasClaPK) {
        this.personagemHasClaPK = personagemHasClaPK;
    }

    public PersonagemHasCla(int personagemIdpersonagem, int claIdcla) {
        this.personagemHasClaPK = new PersonagemHasClaPK(personagemIdpersonagem, claIdcla);
    }

    public PersonagemHasClaPK getPersonagemHasClaPK() {
        return personagemHasClaPK;
    }

    public void setPersonagemHasClaPK(PersonagemHasClaPK personagemHasClaPK) {
        this.personagemHasClaPK = personagemHasClaPK;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personagemHasClaPK != null ? personagemHasClaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonagemHasCla)) {
            return false;
        }
        PersonagemHasCla other = (PersonagemHasCla) object;
        if ((this.personagemHasClaPK == null && other.personagemHasClaPK != null) || (this.personagemHasClaPK != null && !this.personagemHasClaPK.equals(other.personagemHasClaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PersonagemHasCla[ personagemHasClaPK=" + personagemHasClaPK + " ]";
    }
    
}
