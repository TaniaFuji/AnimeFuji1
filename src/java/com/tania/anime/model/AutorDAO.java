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
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutorDAO implements CRUDDB<Autor> {

    Connection conn;
    //query parametrizada
    final static String insertS = "insert into autor(nombre, pais) VALUES (?,?)";
    final static String insertR = "insert into autor(id, nombre, pais) VALUES (?,?,?)";
    final static String updateS = "update autor set id = ?,nombre = ?,pais = ? where id = ?";
    final static String deleteS = "delete from autor where id = ?";

    @Override
    public void insert(Autor dto) {
        conn = getConnection();
        PreparedStatement pstm;
        try {
            if (dto.getId() == 0) {
                pstm = conn.prepareStatement(insertS);
                pstm.setString(1, dto.getNombre());
                pstm.setString(2, dto.getPais());
            } else {
                pstm = conn.prepareStatement(insertR);
                pstm.setInt(1, dto.getId());
                pstm.setString(2, dto.getNombre());
                pstm.setString(3, dto.getPais());
            }

            pstm.execute();

        } catch (SQLException ex) {
            System.out.println("Error en el insert");
            ex.printStackTrace();
        }
    }

    @Override
    public Autor select(String campos, String condicion) {
        Autor res= new Autor();
        try {
            conn = getConnection();
            
            Statement stm;
            ResultSet rs;
            
            stm = conn.createStatement();
            String temp = "Select "+campos+ "from autor where "+condicion+";";
            
            //stm.execute(temp);
            rs = stm.executeQuery(temp);
            
            if(rs.next())
            {
                if(testForColumn(rs,"id"))
                    res.setId(rs.getInt("id"));
                if(testForColumn(rs,"nombre"))
                    res.setNombre(rs.getString("nombre"));
                if(testForColumn(rs,"pais"))
                    res.setPais(rs.getString("pais"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en el select");
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public List<Autor> selectAll(String campos, String condicion) {
        ArrayList<Autor> res = new ArrayList<Autor>();
        Autor curr;
        try {
            conn = getConnection();
            
            Statement stm;
            ResultSet rs;
            
            stm = conn.createStatement();
            String temp = "Select "+campos+ "from autor where "+condicion+";";
            
            rs = stm.executeQuery(temp);
            
            while(rs.next()){
                curr = new Autor();
                if(testForColumn(rs,"id"))
                    curr.setId(rs.getInt("id"));
                if(testForColumn(rs,"nombre"))
                    curr.setNombre(rs.getString("nombre"));
                if(testForColumn(rs,"pais"))
                    curr.setPais(rs.getString("pais"));
                res.add(curr);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en el select");
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public void update(Autor dto) {
        try {
            conn = getConnection();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(updateS);
            pstm.setInt(1, dto.getId());
            pstm.setString(2, dto.getNombre());
            pstm.setString(3, dto.getPais());
            
            pstm.execute();

        } catch (SQLException ex) {
            System.out.println("Error en el update");
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Autor dto) {
        try {
            conn = getConnection();
            PreparedStatement pstm;
            pstm=conn.prepareStatement(deleteS);
            pstm.setInt(1, dto.getId());
            
            pstm.execute();
            
        } catch (SQLException ex) {
            System.out.println("Error en el delete");
            ex.printStackTrace();
        }
    }

}
