package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jrp
 */
public class Conexao {

    
    String banco = "janus";
    String usuario = "root";
    String senha = "";
    String servidor = "localhost";
    
    
    /*String banco = "janus";
    String usuario = "janus";
    String senha = "janus@031_Etec";
    String servidor = "bicudo.sytes.net";*/
    
    private static Connection conexao;
    private static Conexao instancia;

    public Conexao() throws SQLException {
        try {
            String url = "jdbc:mysql://" + servidor + "/" + banco;
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new SQLException("Erro: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            new Conexao();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static Connection getConexao() throws SQLException {
        if (instancia == null) {
            instancia = new Conexao();
        }
            
        return conexao;
    }
    public static void desconectar(){
        conexao=null;
        instancia =null;
        
    }
}
