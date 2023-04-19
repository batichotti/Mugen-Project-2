package DAOs;


import Entidades.Filiacao;
import java.util.ArrayList;
import java.util.List;


/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 15:53:13*/
public class DAOFiliacao extends DAOGenerico<Filiacao>{
private List<Filiacao> lista = new ArrayList<>();

    public DAOFiliacao() {
        super(Filiacao.class);
    }
public int autoIdFiliacao() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idfiliacao) FROM Filiacao e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Filiacao> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Filiacao e WHERE e.idfiliacao LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<Filiacao> listById(int id) {
        return em.createQuery("SELECT e FROM Filiacao e WHERE e.filiacao= :id").setParameter("id", id).getResultList();
    }

    public List<Filiacao> listInOrderNome() {
        return em.createQuery("SELECT e FROM Filiacao e ORDER BY e.idfiliacao").getResultList();
    }

    public List<Filiacao> listInOrderId() {
        return em.createQuery("SELECT e FROM Filiacao e ORDER BY e.filiacao").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Filiacao> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getfiliacao()+ "-" + lf.get(i).getidfiliacao());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOTrabalhador daoFiliacao = new DAOFiliacao();
        List<Filiacao> listaFiliacao = daoFiliacao.list();
        for (Filiacao filiacao : listaFiliacao) {
            System.out.println(filiacao.getidfiliacao()+ "-" + filiacao.getfiliacao());
        }
    }
}
