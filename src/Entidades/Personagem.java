/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mateus Cohuzer
 */
@Entity
@Table(name = "personagem")
@NamedQueries({
    @NamedQuery(name = "Personagem.findAll", query = "SELECT p FROM Personagem p"),
    @NamedQuery(name = "Personagem.findByIdpersonagem", query = "SELECT p FROM Personagem p WHERE p.idpersonagem = :idpersonagem"),
    @NamedQuery(name = "Personagem.findByNomePersonagem", query = "SELECT p FROM Personagem p WHERE p.nomePersonagem = :nomePersonagem"),
    @NamedQuery(name = "Personagem.findByIdade", query = "SELECT p FROM Personagem p WHERE p.idade = :idade"),
    @NamedQuery(name = "Personagem.findByAltura", query = "SELECT p FROM Personagem p WHERE p.altura = :altura"),
    @NamedQuery(name = "Personagem.findByPeso", query = "SELECT p FROM Personagem p WHERE p.peso = :peso"),
    @NamedQuery(name = "Personagem.findByNascimento", query = "SELECT p FROM Personagem p WHERE p.nascimento = :nascimento")})
public class Personagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idpersonagem")
    private Integer idpersonagem;
    @Column(name = "nome_personagem")
    private String nomePersonagem;
    @Column(name = "idade")
    private Integer idade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "altura")
    private Double altura;
    @Column(name = "peso")
    private Integer peso;
    @Column(name = "nascimento")
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @JoinColumn(name = "arma_idarma", referencedColumnName = "idarma")
    @ManyToOne(optional = false)
    private Arma armaIdarma;
    @JoinColumn(name = "filiacao_idfiliacao", referencedColumnName = "idfiliacao")
    @ManyToOne(optional = false)
    private Filiacao filiacaoIdfiliacao;
    @JoinColumn(name = "grade_idgrade", referencedColumnName = "idgrade")
    @ManyToOne(optional = false)
    private Grade gradeIdgrade;
    @JoinColumn(name = "jujutsu_idjujutsu", referencedColumnName = "idjujutsu")
    @ManyToOne(optional = false)
    private Jujutsu jujutsuIdjujutsu;
    @JoinColumn(name = "raca_idraca", referencedColumnName = "idraca")
    @ManyToOne(optional = false)
    private Raca racaIdraca;

    public Personagem() {
    }

    public Personagem(Integer idpersonagem) {
        this.idpersonagem = idpersonagem;
    }

    public Integer getIdpersonagem() {
        return idpersonagem;
    }

    public void setIdpersonagem(Integer idpersonagem) {
        this.idpersonagem = idpersonagem;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Arma getArmaIdarma() {
        return armaIdarma;
    }

    public void setArmaIdarma(Arma armaIdarma) {
        this.armaIdarma = armaIdarma;
    }

    public Filiacao getFiliacaoIdfiliacao() {
        return filiacaoIdfiliacao;
    }

    public void setFiliacaoIdfiliacao(Filiacao filiacaoIdfiliacao) {
        this.filiacaoIdfiliacao = filiacaoIdfiliacao;
    }

    public Grade getGradeIdgrade() {
        return gradeIdgrade;
    }

    public void setGradeIdgrade(Grade gradeIdgrade) {
        this.gradeIdgrade = gradeIdgrade;
    }

    public Jujutsu getJujutsuIdjujutsu() {
        return jujutsuIdjujutsu;
    }

    public void setJujutsuIdjujutsu(Jujutsu jujutsuIdjujutsu) {
        this.jujutsuIdjujutsu = jujutsuIdjujutsu;
    }

    public Raca getRacaIdraca() {
        return racaIdraca;
    }

    public void setRacaIdraca(Raca racaIdraca) {
        this.racaIdraca = racaIdraca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersonagem != null ? idpersonagem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personagem)) {
            return false;
        }
        Personagem other = (Personagem) object;
        if ((this.idpersonagem == null && other.idpersonagem != null) || (this.idpersonagem != null && !this.idpersonagem.equals(other.idpersonagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idpersonagem + ";" + nomePersonagem + ";" + idade + ";" + altura + ";" + peso + ";" + nascimento + ";" + racaIdraca + ";" + filiacaoIdfiliacao + ";" + jujutsuIdjujutsu + ";" + gradeIdgrade + ";" + armaIdarma;
    }
    
}
