package DAOs;

import Entidades.Grade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateus Batichotti Silva | 19/04/2023 - 15:53:55
 */
public class DAOGrade extends DAOGenerico<Grade> {

    private List<Grade> lista = new ArrayList<>();

    public DAOGrade() {
        super(Grade.class);
    }

    public int autoIdGrade() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idgrade) FROM Grade e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Grade> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Grade e WHERE e.idgrade LIKE :nome")
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    public List<Grade> listById(int id) {
        return em.createQuery("SELECT e FROM Grade e WHERE e.nome_grade= :id").setParameter("id", id).getResultList();
    }

    public List<Grade> listInOrderNome() {
        return em.createQuery("SELECT e FROM Grade e ORDER BY e.idgrade").getResultList();
    }

    public List<Grade> listInOrderId() {
        return em.createQuery("SELECT e FROM Grade e ORDER BY e.nome_grade").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Grade> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getNomeGrade() + "-" + lf.get(i).getIdgrade());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOGrade daoGrade = new DAOGrade();
        List<Grade> listaGrade = daoGrade.list();
        for (Grade grade : listaGrade) {
            System.out.println(grade.getIdgrade() + "-" + grade.getNomeGrade());
        }
    }
}
