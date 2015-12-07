/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tania.anime.model;

import static com.tania.anime.model.Conexion.getConnection;
import static com.tania.anime.model.Conexion.testForColumn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chemasmas
 */
public class AnimeDAO implements CRUDDB<Anime> {
    /*
     private int id;
     private String nombre;
     private float costo;
     private Genero gen;
     private Autor aut;
     private String descripcion;
     private String formato;
     private int cantDisc;
     private int temporada;
     private String imagen;
     */

    Connection conn;
    //INSERT INTO `anime`(`id`, `nombre`, `costo`, `genero`, `autor`, `descripcion`, `formato`, `Ndiscos`, `temporadas`, `url`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7],[value-8],[value-9],[value-10])
    final static String insertS = "insert into anime(nombre,costo,genero,autor,descripcion,formato,Ndiscos,temporadas,url) values (?,?,?,?,?,?,?,?,?)";
    final static String insertR = "insert into anime(id,nombre,costo,genero,autor,descripcion,formato,Ndiscos,temporadas,url) values (?,?,?,?,?,?,?,?,?,?)";
    final static String updateS = "update anime set nombre = ?,costo=?,genero=?,autor=?,descripcion=?,formato=?,Ndiscos=?,temporadas=?,url=? where id = ?";
    final static String deleteS = "delete from anime where id=?";

    @Override
    public void insert(Anime dto) {
        try {
            conn = getConnection();
            PreparedStatement pstm;
            if (dto.getId() == 0) {
                pstm = conn.prepareStatement(insertS);
                pstm.setString(1, dto.getNombre());
                pstm.setFloat(2, dto.getCosto());
                pstm.setInt(3, dto.getGen().getId());
                pstm.setInt(4, dto.getAut().getId());
                pstm.setString(5, dto.getDescripcion());
                pstm.setString(6, dto.getFormato());
                pstm.setInt(7, dto.getCantDisc());
                pstm.setInt(8, dto.getTemporada());
                pstm.setString(9, dto.getImagen());

            } else {
                pstm = conn.prepareStatement(insertR);
                pstm.setInt(1, dto.getId());
                pstm.setString(2, dto.getNombre());
                pstm.setFloat(3, dto.getCosto());
                pstm.setInt(4, dto.getGen().getId());
                pstm.setInt(5, dto.getAut().getId());
                pstm.setString(6, dto.getDescripcion());
                pstm.setString(7, dto.getFormato());
                pstm.setInt(8, dto.getCantDisc());
                pstm.setInt(9, dto.getTemporada());
                pstm.setString(10, dto.getImagen());
            }

            pstm.execute();

        } catch (SQLException ex) {
            System.out.println("Error en el insert");
            ex.printStackTrace();
        }
    }

    @Override
    public Anime select(String campos, String condicion) {
        Anime res= new Anime();
        Genero g;
        Autor a;
        //Este DAO necesita recuperar de otras tablas
        GeneroDAO gd=new GeneroDAO();
        AutorDAO ad=new AutorDAO();
        
        try {
            conn = getConnection();
            
            Statement stm;
            ResultSet rs;
            
            stm = conn.createStatement();
            String temp = "Select "+campos+ "from anime where "+condicion+";";
            
            //stm.execute(temp);
            rs = stm.executeQuery(temp);
            
            if(rs.next())
            {
                //anime(id,nombre,costo,genero,autor,descripcion,formato,cantidadDisc,temporada,url)
                if(testForColumn(rs,"id"))
                    res.setId(rs.getInt("id"));
                if(testForColumn(rs,"nombre"))
                    res.setNombre(rs.getString("nombre"));
                if(testForColumn(rs,"costo"))
                    res.setCosto(rs.getFloat("costo"));
                //Al autor y el Genero tienen  truco
                if(testForColumn(rs,"genero"))
                {
                    //Recuperamos el genero correspondiente
                    g=gd.select("*", "id="+rs.getInt("genero"));
                    if(g!=null)
                        res.setGen(g);
                }
                if(testForColumn(rs,"autor"))
                {
                    //Recuperamos el autor correspondiente
                    a=ad.select("*", "id="+rs.getInt("autor"));
                    if(a!=null)
                        res.setAut(a);
                }
                 if(testForColumn(rs,"descripcion"))
                    res.setDescripcion(rs.getString("descripcion"));
                 if(testForColumn(rs,"formato"))
                    res.setFormato(rs.getString("formato"));
                 if(testForColumn(rs,"Ndiscos"))
                    res.setCantDisc(rs.getInt("Ndiscos"));
                 if(testForColumn(rs,"temporadas"))
                    res.setTemporada(rs.getInt("temporadas"));
                if(testForColumn(rs,"url"))
                    res.setImagen(rs.getString("url"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en el select");
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public List<Anime> selectAll(String campos, String condicion) {
        ArrayList<Anime> res=new ArrayList<Anime>();
        Anime curr= new Anime();
        Genero g;
        Autor a;
        //Este DAO necesita recuperar de otras tablas
        GeneroDAO gd=new GeneroDAO();
        AutorDAO ad=new AutorDAO();
        
        try {
            conn = getConnection();
            
            Statement stm;
            ResultSet rs;
            
            stm = conn.createStatement();
            String temp = "Select "+campos+ "from anime where "+condicion+";";
            
            //stm.execute(temp);
            rs = stm.executeQuery(temp);
            
            while(rs.next())
            {
                curr=new Anime();
                //anime(id,nombre,costo,genero,autor,descripcion,formato,cantidadDisc,temporada,url)
                if(testForColumn(rs,"id"))
                    curr.setId(rs.getInt("id"));
                if(testForColumn(rs,"nombre"))
                    curr.setNombre(rs.getString("nombre"));
                if(testForColumn(rs,"costo"))
                    curr.setCosto(rs.getFloat("costo"));
                //Al autor y el Genero tienen  truco
                if(testForColumn(rs,"genero"))
                {
                    //Recuperamos el genero correspondiente
                    g=gd.select("*", "id="+rs.getInt("genero"));
                    if(g!=null)
                        curr.setGen(g);
                }
                if(testForColumn(rs,"autor"))
                {
                    //Recuperamos el autor correspondiente
                    a=ad.select("*", "id="+rs.getInt("autor"));
                    if(a!=null)
                        curr.setAut(a);
                }
                 if(testForColumn(rs,"descripcion"))
                    curr.setDescripcion(rs.getString("descripcion"));
                 if(testForColumn(rs,"formato"))
                    curr.setFormato(rs.getString("formato"));
                 if(testForColumn(rs,"Ndiscos"))
                    curr.setCantDisc(rs.getInt("Ndiscos"));
                 if(testForColumn(rs,"temporadas"))
                    curr.setTemporada(rs.getInt("temporadas"));
                if(testForColumn(rs,"url"))
                    curr.setImagen(rs.getString("url"));
                
                res.add(curr);
            }
        } catch (SQLException ex) {
            System.out.println("Error en el select");
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public void update(Anime dto) {
        try {
            conn = getConnection();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(updateS);
            pstm.setString(1, dto.getNombre());
            pstm.setFloat(2, dto.getCosto());
            pstm.setInt(3, dto.getGen().getId());
            pstm.setInt(4, dto.getAut().getId());
            pstm.setString(5, dto.getDescripcion());
            pstm.setString(6, dto.getFormato());
            pstm.setInt(7, dto.getCantDisc());
            pstm.setInt(8, dto.getTemporada());
            pstm.setString(9, dto.getImagen());
            pstm.execute();

        } catch (SQLException ex) {
            System.out.println("Error en el insert");
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Anime dto) {
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

}
