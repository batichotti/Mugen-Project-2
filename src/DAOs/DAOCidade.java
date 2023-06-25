package DAOs;


import Entidades.Cidade;
import java.util.ArrayList;
import java.util.List;


/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 15:54:37*/
public class DAOCidade extends DAOGenerico<Cidade>{
private List<Cidade> lista = new ArrayList<>();

    public DAOCidade() {
        super(Cidade.class);
    }
public int autoIdCidade() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idcidade) FROM Cidade e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Cidade> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Cidade e WHERE e.idcidade LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<Cidade> listById(int id) {
        return em.createQuery("SELECT e FROM Cidade e WHERE e.nome_cidade= :id").setParameter("id", id).getResultList();
    }

    public List<Cidade> listInOrderNome() {
        return em.createQuery("SELECT e FROM Cidade e ORDER BY e.idcidade").getResultList();
    }

    public List<Cidade> listInOrderId() {
        return em.createQuery("SELECT e FROM Cidade e ORDER BY e.nome_cidade").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Cidade> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getNomeCidade()+ "-" + lf.get(i).getIdcidade());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOCidade daoCidade = new DAOCidade();
        List<Cidade> listaCidade = daoCidade.list();
        for (Cidade cla : listaCidade) {
            System.out.println(cla.getIdcidade()+ "-" + cla.getNomeCidade());
        }
    }
}
