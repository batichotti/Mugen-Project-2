package Entidades;

import Entidades.Personagem;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-09T15:41:54", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Raca.class)
public class Raca_ { 

    public static volatile ListAttribute<Raca, Personagem> personagemList;
    public static volatile SingularAttribute<Raca, Integer> idraca;
    public static volatile SingularAttribute<Raca, String> nomeRaca;
    public static volatile SingularAttribute<Raca, String> descricao;

}