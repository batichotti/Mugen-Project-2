package Entidades;

import Entidades.PersonagemHasCla;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-07-26T17:15:35", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Personagem.class)
public class Personagem_ { 

    public static volatile SingularAttribute<Personagem, Integer> idade;
    public static volatile ListAttribute<Personagem, PersonagemHasCla> personagemHasClaList;
    public static volatile SingularAttribute<Personagem, Integer> idpersonagem;
    public static volatile SingularAttribute<Personagem, Date> nascimento;
    public static volatile SingularAttribute<Personagem, Double> altura;
    public static volatile SingularAttribute<Personagem, Integer> peso;
    public static volatile SingularAttribute<Personagem, String> nomePersonagem;

}