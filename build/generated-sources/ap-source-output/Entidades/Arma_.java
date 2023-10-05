package Entidades;

import Entidades.Personagem;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-04T22:54:51", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Arma.class)
public class Arma_ { 

    public static volatile SingularAttribute<Arma, Integer> dano;
    public static volatile ListAttribute<Arma, Personagem> personagemList;
    public static volatile SingularAttribute<Arma, Integer> idarma;
    public static volatile SingularAttribute<Arma, String> descArma;
    public static volatile SingularAttribute<Arma, String> nomeArma;

}