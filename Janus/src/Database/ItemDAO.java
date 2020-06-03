/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Devisate
 */
public class ItemDAO {
    public int[] itemid = new int[3];
    public int[] itemqntd = new int[3];
    public int aux = 0;
    Connection conexao;
    Item item = new Item();
    Usuario usuario;
    public ItemDAO() throws SQLException {
        conexao =Conexao.getConexao();
        usuario = new Usuario();
    }

    public void SelectItem() throws SQLException {
        try {
            //System.out.println(usuario.getId());
            String sql = "select * from inventario where usu_id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1,usuario.getId());
            ResultSet rs = ps.executeQuery();
            
            int nextcount = 0;
            while(rs.next()){
                itemid[nextcount] = rs.getInt("item_id");
                itemqntd[nextcount] = rs.getInt("item_quant");
                nextcount++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void UpdateItem(int slot,int item_quant,int item_id) throws SQLException {
        try {
            String sql = "update inventario set item_quant=?, item_id=? where usu_id=? and slot=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1,item_quant );
            ps.setInt(2,item_id );
            ps.setInt(3,usuario.getId());
             ps.setInt(4,slot);
            ps.execute();
        } catch (Exception e) {
              System.out.println(e.getMessage());
        }
    }
    
    public void DItem(int slot) throws SQLException {
       
        try {
            String sql = "update inventario set item_quant=0, item_id=0 where usu_id=? and slot=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1,usuario.getId());
            
             ps.setInt(2,slot);
            ps.execute();
        } catch (Exception e) {
              System.out.println(e.getMessage());
        }
    }
    
     
}
