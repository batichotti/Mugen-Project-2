package DAOs;


import Entidades.Raca;
import java.util.ArrayList;
import java.util.List;


/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 15:48:00*/
public class DAORaca extends DAOGenerico<Raca>{
private List<Raca> lista = new ArrayList<>();

    public DAORaca() {
        super(Raca.class);
    }
public int autoIdRaca() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idraca) FROM Raca e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Raca> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Raca e WHERE e.idraca LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<Raca> listById(int id) {
        return em.createQuery("SELECT e FROM Raca e WHERE e.nome_raca= :id").setParameter("id", id).getResultList();
    }

    public List<Raca> listInOrderNome() {
        return em.createQuery("SELECT e FROM Raca e ORDER BY e.idraca").getResultList();
    }

    public List<Raca> listInOrderId() {
        return em.createQuery("SELECT e FROM Raca e ORDER BY e.nome_raca").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Raca> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getNomeRaca()+ "-" + lf.get(i).getIdraca());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAORaca daoRaca = new DAORaca();
        List<Raca> listaRaca = daoRaca.list();
        for (Raca raca : listaRaca) {
            System.out.println(raca.getIdraca()+ "-" + raca.getNomeRaca());
        }
    }
}
