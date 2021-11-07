package cliente.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cliente.models.Cliente;

@SuppressWarnings("unused")
public class ClienteDao {
	
	/* Modulo de conexão */
	/** The driver. */
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3307/clientes?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "zion05";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public void Inserir( Cliente cliente) {
		try {
			Connection conn = conectar();
			String sql = "INSERT INTO `cliente` (`idCliente`, `nome`, `apelido`, `email`, `fone`) VALUES "; 
					sql +=	"(NULL, '" + cliente.getNome() + "', "; 
					sql +=	"'" + cliente.getApelido() + "',";
					sql +=	"'" + cliente.getEmail() + "',";
					sql +=	"'" + cliente.getTelefone() + "');";
			Statement statment = conn.createStatement();
			statment.execute(sql);
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Alterar( Cliente cliente) {
		try {
			Connection conn = conectar();
			String sql = "UPDATE `cliente` SET "; 
					sql +=	" `nome`   = '" + cliente.getNome() + "', "; 
					sql +=	" `apelido`= '" + cliente.getApelido() + "',";
					sql +=	" `email`  = '" + cliente.getEmail() + "',";
					sql +=	" `fone`   = '" + cliente.getTelefone() + "'";
					sql +=	" WHERE `cliente`.`idCliente` = " + cliente.getId() + ";";
			Statement statment = conn.createStatement();
			statment.execute(sql);
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Cliente> Listar( ) {
		List<Cliente> listaClientes = new ArrayList<>();
		try {
			Connection conn = conectar();
			String sql = "Select * from  `cliente`";
			Statement statment = conn.createStatement();
			ResultSet resultado = statment.executeQuery(sql);
			while (resultado.next()) {
				Cliente cliente = new Cliente(resultado.getString("nome"),resultado.getString("apelido") );
				cliente.setEmail(resultado.getString("email")); 
				cliente.setTelefone(resultado.getString("fone"));
				cliente.setId(resultado.getString("idcliente"));
				listaClientes.add(cliente);
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaClientes;
	}
	public void deletar ( String idCliente) {
		try {
			Connection conn = conectar();
			String sql = "DELETE FROM `cliente` WHERE `idCliente` = " + idCliente + ";"; 
			Statement statment = conn.createStatement();
			statment.execute(sql);
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
