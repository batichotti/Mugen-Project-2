package DAOs;


import Entidades.Cla;
import java.util.ArrayList;
import java.util.List;


/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 15:54:37*/
public class DAOCla extends DAOGenerico<Cla>{
private List<Cla> lista = new ArrayList<>();

    public DAOCla() {
        super(Cla.class);
    }
public int autoIdCla() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idcla) FROM Cla e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Cla> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Cla e WHERE e.idcla LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<Cla> listById(int id) {
        return em.createQuery("SELECT e FROM Cla e WHERE e.nome_cla= :id").setParameter("id", id).getResultList();
    }

    public List<Cla> listInOrderNome() {
        return em.createQuery("SELECT e FROM Cla e ORDER BY e.idcla").getResultList();
    }

    public List<Cla> listInOrderId() {
        return em.createQuery("SELECT e FROM Cla e ORDER BY e.nome_cla").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Cla> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getNomeCla()+ "-" + lf.get(i).getIdcla());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOCla daoCla = new DAOCla();
        List<Cla> listaCla = daoCla.list();
        for (Cla cla : listaCla) {
            System.out.println(cla.getIdcla()+ "-" + cla.getNomeCla());
        }
    }
}
