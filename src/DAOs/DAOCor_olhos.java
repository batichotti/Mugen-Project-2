package DAOs;


import Entidades.Cor_olhos;
import java.util.ArrayList;
import java.util.List;


/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 14:53:41*/
public class DAOCor_olhos extends DAOGenerico<Cor_olhos>{
private List<Cor_olhos> lista = new ArrayList<>();

    public DAOCor_olhos() {
        super(Cor_olhos.class);
    }
public int autoIdCor_olhos() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idcor_olhos) FROM Cor_olhos e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Cor_olhos> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Cor_olhos e WHERE e.idcor_olhos LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<Cor_olhos> listById(int id) {
        return em.createQuery("SELECT e FROM Cor_olhos e WHERE e.cor= :id").setParameter("id", id).getResultList();
    }

    public List<Cor_olhos> listInOrderNome() {
        return em.createQuery("SELECT e FROM Cor_olhos e ORDER BY e.idcor_olhos").getResultList();
    }

    public List<Cor_olhos> listInOrderId() {
        return em.createQuery("SELECT e FROM Cor_olhos e ORDER BY e.cor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Cor_olhos> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getcor()+ "-" + lf.get(i).getidcor_olhos());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOTrabalhador daoCor_olhos = new DAOCor_olhos();
        List<Cor_olhos> listaCor_olhos = daoCor_olhos.list();
        for (Cor_olhos cor_olhos : listaCor_olhos) {
            System.out.println(cor_olhos.getidcor_olhos()+ "-" + cor_olhos.getcor());
        }
    }
}
