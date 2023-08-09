package DAOs;

import Entidades.Personagem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateus Batichotti Silva | 19/04/2023 - 15:54:37
 */
public class DAOPersonagem extends DAOGenerico<Personagem> {

    private List<Personagem> lista = new ArrayList<>();

    public DAOPersonagem() {
        super(Personagem.class);
    }

    public int autoIdPersonagem() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idpersonagem) FROM Personagem e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Personagem> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Personagem e WHERE e.idpersonagem LIKE :nome")
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    public List<Personagem> listById(int id) {
        return em.createQuery("SELECT e FROM Personagem e WHERE e.nome_personagem= :id").setParameter("id", id).getResultList();
    }

    public List<Personagem> listInOrderNome() {
        return em.createQuery("SELECT e FROM Personagem e ORDER BY e.idpersonagem").getResultList();
    }

    public List<Personagem> listInOrderId() {
        return em.createQuery("SELECT e FROM Personagem e ORDER BY e.nome_personagem").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Personagem> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getNomePersonagem() + "-" + lf.get(i).getIdpersonagem());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPersonagem daoPersonagem = new DAOPersonagem();
        List<Personagem> listaPersonagem = daoPersonagem.list();
        for (Personagem cla : listaPersonagem) {
            System.out.println(cla.getIdpersonagem()+ "-" + cla.getNomePersonagem());
        }
    }
}
