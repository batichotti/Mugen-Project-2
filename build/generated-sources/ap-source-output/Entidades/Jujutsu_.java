package Entidades;

import Entidades.Personagem;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-04T22:54:51", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Jujutsu.class)
public class Jujutsu_ { 

    public static volatile SingularAttribute<Jujutsu, String> jujutsuDesc;
    public static volatile ListAttribute<Jujutsu, Personagem> personagemList;
    public static volatile SingularAttribute<Jujutsu, String> jujutsuName;
    public static volatile SingularAttribute<Jujutsu, Integer> idjujutsu;
    public static volatile SingularAttribute<Jujutsu, Integer> danoBase;

}