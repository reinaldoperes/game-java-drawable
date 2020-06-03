/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;

/**
 *
 * @author Devisate
 */
public class UsuarioDAO {
    public int aux = 0;
    Connection conexao;
    Usuario usuario = new Usuario();
    public UsuarioDAO() throws SQLException {
        conexao = Conexao.getConexao();
        
    }

     public Usuario Login(String login, String senha) throws SQLException {
       
        try {
                String sql = "select * from usuario where usu_login = ? and usu_senha = ?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setString(1, login);
                ps.setString(2, senha);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    usuario.setId(rs.getInt("usu_id"));
                    usuario.setNome(rs.getString("usu_nome"));
                    usuario.setCoins(rs.getInt("usu_coins"));
                    usuario.setSenha(rs.getString("usu_senha"));
                    usuario.setEmail(rs.getString("usu_email"));
                    usuario.setLogin(rs.getString("usu_login"));
                    usuario.setCheckpoint(rs.getInt("usu_checkpoint"));
                    aux = 1;
                    return usuario;
                }                
        } catch (Exception e) {
            throw new SQLException("Erro: " + e.getMessage());
        }
                    return null;
    }
    
    public void updateCoins() {
        try {
            String sql = "UPDATE usuario set usu_coins = usu_coins+1 where usu_id =?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            int qtd = ps.executeUpdate();
            usuario.setCoins(usuario.getCoins()+1);
            //System.out.println(usuario.getCoins()+1);
            
        } catch (Exception e) {
            System.out.println("Erro: "+ e.getMessage());
        }
    }
    
     public void DieCoins() {
        try {
            String sql = "UPDATE usuario set usu_coins = usu_coins-10 where usu_id =?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            int qtd = ps.executeUpdate();
            usuario.setCoins(usuario.getCoins()-10);
            //System.out.println(usuario.getCoins()+1);
            
        } catch (Exception e) {
            System.out.println("Erro: "+ e.getMessage());
        }
    }

    public int showCoins(){
        int aux = 0;
        try {
            String sql = "select usu_coins from usuario where usu_id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
        {
            aux = rs.getInt("usu_coins"); 
            
        }
        } catch (Exception e) {
            System.out.println("Erro: "+ e.getMessage());
        }
        return aux;
    }
}
