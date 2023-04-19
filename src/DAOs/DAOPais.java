package DAOs;


import Entidades.Pais;
import java.util.ArrayList;
import java.util.List;


/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 15:55:43*/
public class DAOPais extends DAOGenerico<Pais>{
private List<Pais> lista = new ArrayList<>();

    public DAOPais() {
        super(Pais.class);
    }
public int autoIdPais() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idpais) FROM Pais e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Pais> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Pais e WHERE e.idpais LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<Pais> listById(int id) {
        return em.createQuery("SELECT e FROM Pais e WHERE e.nome_pais= :id").setParameter("id", id).getResultList();
    }

    public List<Pais> listInOrderNome() {
        return em.createQuery("SELECT e FROM Pais e ORDER BY e.idpais").getResultList();
    }

    public List<Pais> listInOrderId() {
        return em.createQuery("SELECT e FROM Pais e ORDER BY e.nome_pais").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Pais> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getnome_pais()+ "-" + lf.get(i).getidpais());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOTrabalhador daoPais = new DAOPais();
        List<Pais> listaPais = daoPais.list();
        for (Pais pais : listaPais) {
            System.out.println(pais.getidpais()+ "-" + pais.getnome_pais());
        }
    }
}
