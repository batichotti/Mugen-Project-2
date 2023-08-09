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
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idcor_cabelo) FROM Cor_cabelo e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<CorCabelo> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Cor_cabelo e WHERE e.idcorCabelo LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<CorCabelo> listById(int id) {
        return em.createQuery("SELECT e FROM Cor_cabelo e WHERE e.cor= :id").setParameter("id", id).getResultList();
    }

    public List<CorCabelo> listInOrderNome() {
        return em.createQuery("SELECT e FROM Cor_cabelo e ORDER BY e.cor").getResultList();
    }

    public List<CorCabelo> listInOrderId() {
        return em.createQuery("SELECT e FROM Cor_cabelo e ORDER BY e.idcorCabelo").getResultList();
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
            ls.add(lf.get(i).getCor()+ "-" + lf.get(i).getIdcorCabelo());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOCorCabelo daoCorCabelo = new DAOCorCabelo();
        List<CorCabelo> listaCorCabelo = daoCorCabelo.list();
        for (CorCabelo corCabelo : listaCorCabelo) {
            System.out.println(corCabelo.getIdcorCabelo()+ "-" + corCabelo.getCor());
        }
    }
}
