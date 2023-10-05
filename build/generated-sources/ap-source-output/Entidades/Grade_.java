package Entidades;

import Entidades.Personagem;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-04T22:54:51", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Grade.class)
public class Grade_ { 

    public static volatile ListAttribute<Grade, Personagem> personagemList;
    public static volatile SingularAttribute<Grade, String> nomeGrade;
    public static volatile SingularAttribute<Grade, Integer> idgrade;
    public static volatile SingularAttribute<Grade, String> descricao;

}