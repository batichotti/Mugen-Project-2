package DAOs;import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import tools.StringTools;

/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 15:55:43*/
public class DAOGenerico<T> {

    public static EntityManager em = Persistence.createEntityManagerFactory("UP").createEntityManager();
    private Class clazz;
    StringTools stringTools = new StringTools();

    public DAOGenerico(Class clazz) {
        this.clazz = clazz;
    }

    public void inserir(T e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public void atualizar(T e) {
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
    }

    public void remover(T e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public T obter(Long id) {
        Object ent = em.find(clazz, id);
        if (ent == null) {
            return (T) ent;
        } else {
            em.refresh(ent);
            return (T) ent;
        }
    }

    public T obter(Integer id) {
        Object ent = em.find(clazz, id);
        if (ent == null) {
            return (T) ent;
        } else {
            em.refresh(ent);
            return (T) ent;
        }
    }

    public T obter(String id) {
        Object ent = em.find(clazz, id);
        if (ent == null) {
            return (T) ent;
        } else {
            em.refresh(ent);
            return (T) ent;
        }
    }

    public List<T> list() {
        return em.createQuery("SELECT e FROM " + (clazz.getSimpleName()) + " e").getResultList();
    }
}
