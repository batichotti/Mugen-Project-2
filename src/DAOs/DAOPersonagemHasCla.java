package DAOs;

import Entidades.PersonagemHasCla;
import Entidades.PersonagemHasClaPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mateus Batichotti Silva | 19/04/2023 - 15:54:37
 */
public class DAOPersonagemHasCla extends DAOGenerico<PersonagemHasCla> {

    private List<PersonagemHasCla> lista = new ArrayList<>();

    public DAOPersonagemHasCla() {
        super(PersonagemHasCla.class);
    }

    //busca com PK composta
    public PersonagemHasCla obter(PersonagemHasClaPK phcpk) {
        return em.find(PersonagemHasCla.class, phcpk);
    }

    public List<PersonagemHasCla> listInOrderNome() {
        TypedQuery<PersonagemHasCla> query = em.createQuery("SELECT e FROM PersonagemHasCla e ORDER BY e.escopo", PersonagemHasCla.class);
        List<PersonagemHasCla> resultList = query.getResultList();
        return resultList;
    }

    public List<String> listInOrderNomeStrings() {
        List<PersonagemHasCla> lf = listInOrderNome();
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPersonagemHasClaPK().toString());
        }
        return ls;
    }
}
