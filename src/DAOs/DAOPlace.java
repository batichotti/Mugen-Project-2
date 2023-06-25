package DAOs;


import Entidades.Place;
import java.util.ArrayList;
import java.util.List;


/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 15:48:00*/
public class DAOPlace extends DAOGenerico<Place>{
private List<Place> lista = new ArrayList<>();

    public DAOPlace() {
        super(Place.class);
    }
public int autoIdPlace() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idplace) FROM Place e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Place> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Place e WHERE e.idplace LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<Place> listById(int id) {
        return em.createQuery("SELECT e FROM Place e WHERE e.nome_place= :id").setParameter("id", id).getResultList();
    }

    public List<Place> listInOrderNome() {
        return em.createQuery("SELECT e FROM Place e ORDER BY e.idplace").getResultList();
    }

    public List<Place> listInOrderId() {
        return em.createQuery("SELECT e FROM Place e ORDER BY e.nome_place").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Place> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getNomePlace()+ "-" + lf.get(i).getIdplace());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPlace daoPlace = new DAOPlace();
        List<Place> listaPlace = daoPlace.list();
        for (Place place : listaPlace) {
            System.out.println(place.getIdplace()+ "-" + place.getNomePlace());
        }
    }
}
