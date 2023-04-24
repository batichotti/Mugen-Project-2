package DAOs;


import Entidades.Arma;
import java.util.ArrayList;
import java.util.List;


/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 15:49:35*/
public class DAOArma extends DAOGenerico<Arma>{
private List<Arma> lista = new ArrayList<>();

    public DAOArma() {
        super(Arma.class);
    }
public int autoIdArma() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idraca) FROM arma e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Arma> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Arma e WHERE e.idarma LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<Arma> listById(int id) {
        return em.createQuery("SELECT e FROM Arma e WHERE e.nome_arma= :id").setParameter("id", id).getResultList();
    }

    public List<Arma> listInOrderNome() {
        return em.createQuery("SELECT e FROM Arma e ORDER BY e.idarma").getResultList();
    }

    public List<Arma> listInOrderId() {
        return em.createQuery("SELECT e FROM Arma e ORDER BY e.nome_arma").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Arma> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getNomeArma()+ "-" + lf.get(i).getIdarma());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOArma daoArma = new DAOArma();
        List<Arma> listaArma = daoArma.list();
        for (Arma arma : listaArma) {
            System.out.println(arma.getIdarma()+ "-" + arma.getNomeArma());
        }
    }
}
