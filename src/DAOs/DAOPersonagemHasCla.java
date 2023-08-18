package DAOs;

import Entidades.PersonagemHasCla;
import Entidades.PersonagemHasClaPK;
import java.util.ArrayList;
import java.util.List;

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
    public PersonagemHasCla obter(PersonagemHasClaPK personagemHasClaPk){
        return em.find(PersonagemHasCla.class, personagemHasClaPk);
    }
    
    public List<PersonagemHasCla> listInOrderNome(){
        return em.createQuery("SELECT e FROM PersonagemHasCla e ORDER BY e.personagemHasCla").getResultList();
    }

    public List<String> listInOrderNomeStrings(){
        List<PersonagemHasCla> lf = listInOrderNome();
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPersonagemHasClaPK().toString());
        }
        return ls;
    }
}
