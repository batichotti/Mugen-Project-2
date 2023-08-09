/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Mateus Cohuzer
 */
@Embeddable
public class PersonagemHasClaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "personagem_idpersonagem")
    private int personagemIdpersonagem;
    @Basic(optional = false)
    @Column(name = "cla_idcla")
    private int claIdcla;

    public PersonagemHasClaPK() {
    }

    public PersonagemHasClaPK(int personagemIdpersonagem, int claIdcla) {
        this.personagemIdpersonagem = personagemIdpersonagem;
        this.claIdcla = claIdcla;
    }

    public int getPersonagemIdpersonagem() {
        return personagemIdpersonagem;
    }

    public void setPersonagemIdpersonagem(int personagemIdpersonagem) {
        this.personagemIdpersonagem = personagemIdpersonagem;
    }

    public int getClaIdcla() {
        return claIdcla;
    }

    public void setClaIdcla(int claIdcla) {
        this.claIdcla = claIdcla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) personagemIdpersonagem;
        hash += (int) claIdcla;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonagemHasClaPK)) {
            return false;
        }
        PersonagemHasClaPK other = (PersonagemHasClaPK) object;
        if (this.personagemIdpersonagem != other.personagemIdpersonagem) {
            return false;
        }
        if (this.claIdcla != other.claIdcla) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return personagemIdpersonagem + ";" + claIdcla;
    }
    
}
