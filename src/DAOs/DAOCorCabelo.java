package DAOs;


import Entidades.Cor_cabelo;
import java.util.ArrayList;
import java.util.List;


/**
 *
* @author Mateus Batichotti Silva | 19/04/2023 - 15:47:14*/
public class DAOCorCabelo extends DAOGenerico<Cor_cabelo>{
private List<Cor_cabelo> lista = new ArrayList<>();

    public DAOCorCabelo() {
        super(Cor_cabelo.class);
    }
public int autoIdCorCabelo() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idcor_cabelo) FROM Cor_cabelo e ").getSingleResult(); 
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Cor_cabelo> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Cor_cabelo e WHERE e.idcorCabelo LIKE :nome")
         .setParameter("nome", "%" + nome + "%")
         .getResultList();
    }

    public List<Cor_cabelo> listById(int id) {
        return em.createQuery("SELECT e FROM Cor_cabelo e WHERE e.cor= :id").setParameter("id", id).getResultList();
    }

    public List<Cor_cabelo> listInOrderNome() {
        return em.createQuery("SELECT e FROM Cor_cabelo e ORDER BY e.cor").getResultList();
    }

    public List<Cor_cabelo> listInOrderId() {
        return em.createQuery("SELECT e FROM Cor_cabelo e ORDER BY e.idcorCabelo").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Cor_cabelo> lf;
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
        List<Cor_cabelo> listaCorCabelo = daoCorCabelo.list();
        for (Cor_cabelo corCabelo : listaCorCabelo) {
            System.out.println(corCabelo.getIdcorCabelo()+ "-" + corCabelo.getCor());
        }
    }
}
