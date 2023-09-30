package Entidades;

import Entidades.Cidade;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-09T15:41:54", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pais.class)
public class Pais_ { 

    public static volatile ListAttribute<Pais, Cidade> cidadeList;
    public static volatile SingularAttribute<Pais, Integer> idpais;
    public static volatile SingularAttribute<Pais, String> nomePais;

}