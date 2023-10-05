package Entidades;

import Entidades.Arma;
import Entidades.Filiacao;
import Entidades.Grade;
import Entidades.Jujutsu;
import Entidades.Raca;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-04T22:54:51", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Personagem.class)
public class Personagem_ { 

    public static volatile SingularAttribute<Personagem, Integer> idade;
    public static volatile SingularAttribute<Personagem, Integer> idpersonagem;
    public static volatile SingularAttribute<Personagem, Date> nascimento;
    public static volatile SingularAttribute<Personagem, Double> altura;
    public static volatile SingularAttribute<Personagem, Integer> peso;
    public static volatile SingularAttribute<Personagem, Grade> gradeIdgrade;
    public static volatile SingularAttribute<Personagem, Jujutsu> jujutsuIdjujutsu;
    public static volatile SingularAttribute<Personagem, String> nomePersonagem;
    public static volatile SingularAttribute<Personagem, Filiacao> filiacaoIdfiliacao;
    public static volatile SingularAttribute<Personagem, Raca> racaIdraca;
    public static volatile SingularAttribute<Personagem, Arma> armaIdarma;

}