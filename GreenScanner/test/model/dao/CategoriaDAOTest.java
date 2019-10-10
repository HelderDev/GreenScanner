/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Categoria;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AmorimHe
 */
public class CategoriaDAOTest {
    
    public CategoriaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    @Test
    public void inserir() {
        Categoria cat = new Categoria("Roupas");
        CategoriaDAO dao = new CategoriaDAO();
       if(dao.save(cat)){
           System.out.println("Salvo com sucesso!");
       }
       else{
           fail("Erro ao salvar");
       }
    }
    
}
