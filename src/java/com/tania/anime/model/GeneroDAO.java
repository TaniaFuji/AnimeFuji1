/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tania.anime.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static com.tania.anime.model.Conexion.getConnection;
import static com.tania.anime.model.Conexion.testForColumn;

/**
 *
 * @author Seb
 */
public class GeneroDAO implements CRUDDB<Genero> {

    
    Connection conn;
    
    final static String insertS ="insert into genero(nombre) values (?)";
    final static String insertR ="insert into genero(id,nombre) values (?,?)";
    //final static String selectS ="select ? from genero where ?";
    final static String updateS = "update genero set nombre = ? where id = ?";
    final static String deleteS = "delete from genero where id=?"; 
       
    @Override
    public void insert(Genero dto) {
        try {
            conn = getConnection();
            PreparedStatement pstm;
            if(dto.getId()==0)
            {
                pstm=conn.prepareStatement(insertS);
                pstm.setString(1, dto.getNombre());
            }
            else
            {
                pstm=conn.prepareStatement(insertR);
                pstm.setInt(1, dto.getId());
                pstm.setString(2, dto.getNombre());
            }
            
            pstm.execute();
            
        } catch (SQLException ex) {
            System.out.println("Error en el insert");
            ex.printStackTrace();
        }
    }

  
    @Override
    public void update(Genero dto) {
        try {
            conn = getConnection();
            PreparedStatement pstm;
            pstm=conn.prepareStatement(updateS);
            pstm.setString(1, dto.getNombre());
            pstm.setInt(2, dto.getId());
            
            pstm.execute();
            
        } catch (SQLException ex) {
            System.out.println("Error en el insert");
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Genero dto) {
        try {
            conn = getConnection();
            PreparedStatement pstm;
            pstm=conn.prepareStatement(deleteS);
            pstm.setInt(1, dto.getId());
            
            pstm.execute();
            
        } catch (SQLException ex) {
            System.out.println("Error en el insert");
            ex.printStackTrace();
        }
    }
    
    @Override
    public Genero select(String campos,String condicion) {
        Genero res= new Genero();
        try {
            conn = getConnection();
            
            Statement stm;
            ResultSet rs;
            
            
            stm=conn.createStatement();
                       
            String temp= "Select "+campos+" from genero where "+condicion+";";
            
            stm.execute(temp);
            rs=stm.executeQuery(temp);
            
            if(rs.next())
            {

                if(testForColumn(rs,"id"))
                    res.setId(rs.getInt("id"));
                if(testForColumn(rs,"nombre"))
                    res.setNombre(rs.getString("nombre"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en el select");
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public List<Genero> selectAll(String campos, String condicion) {
        ArrayList<Genero> res=new ArrayList<Genero>();
        Genero curr;
        try {
            conn = getConnection();
            
            Statement stm;
            ResultSet rs;
            
            stm=conn.createStatement();
                       
            String temp= "Select "+campos+" from genero where "+condicion+";";
            
            stm.execute(temp);
            rs=stm.executeQuery(temp);
            
            while(rs.next())
            {
                curr=new Genero();
                if(testForColumn(rs,"id"))
                    curr.setId(rs.getInt("id"));
                if(testForColumn(rs,"nombre"))
                    curr.setNombre(rs.getString("nombre"));
                
                res.add(curr);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en el select");
            ex.printStackTrace();
        }
        return res;
    }
        
    public static void main(String[] args) {
        GeneroDAO gd=new GeneroDAO();
        Genero g=new Genero();
        g.setId(3);
        g.setNombre("Shojo");
        
        List<Genero> lista;
        //g.setNombre("Shojo");
        //gd.insert(g);
        //g=gd.select("*","","id=1");
        //g=gd.select("nombre","nombre='comedia'");
        //lista=gd.selectAll("*","1=1");
        
        //for (Genero ge : lista) {
          //  System.out.println(ge.getId());
          //  System.out.println(ge.getNombre());
        //}
        //gd.update(g);
        //gd.delete(g);
        //gd.insert(g);
        
    }

    
    
}
