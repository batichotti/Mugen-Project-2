package DAOs;


import Entidades.Jujutsu;
import java.util.ArrayList;
import java.util.List;


/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 15:52:23*/
public class DAOJujutsu extends DAOGenerico<Jujutsu>{
private List<Jujutsu> lista = new ArrayList<>();

    public DAOJujutsu() {
        super(Jujutsu.class);
    }
public int autoIdJujutsu() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idjujutsu) FROM Jujutsu e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Jujutsu> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Jujutsu e WHERE e.idjujutsu LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<Jujutsu> listById(int id) {
        return em.createQuery("SELECT e FROM Jujutsu e WHERE e.jujutsu_name= :id").setParameter("id", id).getResultList();
    }

    public List<Jujutsu> listInOrderNome() {
        return em.createQuery("SELECT e FROM Jujutsu e ORDER BY e.idjujutsu").getResultList();
    }

    public List<Jujutsu> listInOrderId() {
        return em.createQuery("SELECT e FROM Jujutsu e ORDER BY e.jujutsu_name").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Jujutsu> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getJujutsuName()+ "-" + lf.get(i).getIdjujutsu());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOJujutsu daoJujutsu = new DAOJujutsu();
        List<Jujutsu> listaJujutsu = daoJujutsu.list();
        for (Jujutsu jujutsu : listaJujutsu) {
            System.out.println(jujutsu.getIdjujutsu()+ "-" + jujutsu.getJujutsuName());
        }
    }
}
