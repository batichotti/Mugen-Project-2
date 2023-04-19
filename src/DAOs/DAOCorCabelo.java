package DAOs;


import Entidades.CorCabelo;
import java.util.ArrayList;
import java.util.List;


/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 15:47:14*/
public class DAOCorCabelo extends DAOGenerico<CorCabelo>{
private List<CorCabelo> lista = new ArrayList<>();

    public DAOCorCabelo() {
        super(CorCabelo.class);
    }
public int autoIdCorCabelo() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idcor_cabelo) FROM CorCabelo e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<CorCabelo> listByNome(String nome) {
        return em.createQuery("SELECT e FROM CorCabelo e WHERE e.idcor_cabelo LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<CorCabelo> listById(int id) {
        return em.createQuery("SELECT e FROM CorCabelo e WHERE e.cor= :id").setParameter("id", id).getResultList();
    }

    public List<CorCabelo> listInOrderNome() {
        return em.createQuery("SELECT e FROM CorCabelo e ORDER BY e.idcor_cabelo").getResultList();
    }

    public List<CorCabelo> listInOrderId() {
        return em.createQuery("SELECT e FROM CorCabelo e ORDER BY e.cor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<CorCabelo> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getcor()+ "-" + lf.get(i).getidcor_cabelo());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOTrabalhador daoCorCabelo = new DAOCorCabelo();
        List<CorCabelo> listaCorCabelo = daoCorCabelo.list();
        for (CorCabelo corCabelo : listaCorCabelo) {
            System.out.println(corCabelo.getidcor_cabelo()+ "-" + corCabelo.getcor());
        }
    }
}
