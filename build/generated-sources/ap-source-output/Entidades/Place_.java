package Entidades;

import Entidades.Cidade;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-25T12:21:24", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Place.class)
public class Place_ { 

    public static volatile SingularAttribute<Place, Integer> idplace;
    public static volatile SingularAttribute<Place, Short> temDedo;
    public static volatile SingularAttribute<Place, Cidade> cidadeIdcidade;
    public static volatile SingularAttribute<Place, String> nomePlace;
    public static volatile SingularAttribute<Place, String> descPlace;

}