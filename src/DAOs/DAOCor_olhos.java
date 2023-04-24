package DAOs;


import Entidades.CorOlhos;
import java.util.ArrayList;
import java.util.List;


/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 14:53:41*/
public class DAOCor_olhos extends DAOGenerico<CorOlhos>{
private List<CorOlhos> lista = new ArrayList<>();

    public DAOCor_olhos() {
        super(CorOlhos.class);
    }
public int autoIdCor_olhos() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idcor_olhos) FROM Cor_olhos e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<CorOlhos> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Cor_olhos e WHERE e.idcor_olhos LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<CorOlhos> listById(int id) {
        return em.createQuery("SELECT e FROM Cor_olhos e WHERE e.cor= :id").setParameter("id", id).getResultList();
    }

    public List<CorOlhos> listInOrderNome() {
        return em.createQuery("SELECT e FROM Cor_olhos e ORDER BY e.idcor_olhos").getResultList();
    }

    public List<CorOlhos> listInOrderId() {
        return em.createQuery("SELECT e FROM Cor_olhos e ORDER BY e.cor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<CorOlhos> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getCor()+ "-" + lf.get(i).getIdcorOlhos());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOCor_olhos daoCor_olhos = new DAOCor_olhos();
        List<CorOlhos> listaCor_olhos = daoCor_olhos.list();
        for (CorOlhos cor_olhos : listaCor_olhos) {
            System.out.println(cor_olhos.getIdcorOlhos()+ "-" + cor_olhos.getCor());
        }
    }
}
