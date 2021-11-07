package cliente.forms;

import cliente.dao.ClienteDao;
import cliente.models.Cliente;

//import java.awt.BorderLayout;
import java.util.ArrayList;
import  java.util.List;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import java.awt.Button;
import javax.swing.JList;
//import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
//import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormCadastro extends JFrame {

	protected static final int YES_NO_OPTION = 0;
	private JPanel contentPane;
	private JTextField txtApelido;
	private List<Cliente> lista = new ArrayList<Cliente>();
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txttelefone;
	private JLabel lblId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastro frame = new FormCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormCadastro() {

		//private List<Cliente> lista; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		txtApelido = new JTextField();
		txtApelido.setBounds(283, 80, 190, 20);
		contentPane.add(txtApelido);
		txtApelido.setColumns(10);
		lblId = new JLabel();
				
		JList listClientes = new JList();
		

		
		listClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indice = listClientes.getSelectedIndex();
				Cliente cliente = lista.get(indice);
				txtApelido.setText(cliente.getApelido()); 	
				txtEmail.setText(cliente.getEmail());
				txtNome.setText(cliente.getNome());
				txttelefone.setText(cliente.getTelefone());
				lblId.setText(cliente.getId());
			}
		});
		listClientes.setBounds(21, 24, 172, 126);
		contentPane.add(listClientes);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				atualizalista();				
			}
			private void atualizalista() {
				ClienteDao dao = new ClienteDao();
				lista = dao.Listar();
				DefaultListModel dados = new DefaultListModel();
				String Nome;
				for(int i=0; i<lista.size();i++) {
					Nome = lista.get(i).getNomeCompleto();
					dados.addElement(Nome);
					
				}
				listClientes.setModel(dados);
				
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cliente cliente = new Cliente(txtNome.getText(), txtApelido.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txttelefone.getText());
				
				ClienteDao dao = new ClienteDao();
				if(lblId.getText().compareTo("")==0) {
					dao.Inserir(cliente);	
				}else {
					cliente.setId(lblId.getText());	
					dao.Alterar(cliente);
				}
					
				
				atualizalista();
				
			}
			private void atualizalista() {
				ClienteDao dao = new ClienteDao();
				lista = dao.Listar();
				DefaultListModel dados = new DefaultListModel();
				String Nome;
				for(int i=0; i<lista.size();i++) {
					Nome = lista.get(i).getNomeCompleto();
					dados.addElement(Nome);
					
				}
				listClientes.setModel(dados);
				limpatextos();
				
			}
			
			private void limpatextos() {
				txtApelido.setText("");
				txtEmail.setText("");
				txtNome.setText("");
				txttelefone.setText("");
				lblId.setText("");
			}

		});
		

		
		btnSalvar.setBounds(359, 161, 77, 23);
		contentPane.add(btnSalvar);
		
		JButton btnRemover = new JButton("Remover");
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indice = listClientes.getSelectedIndex();
				Cliente cliente = lista.get(indice);
				String nome = listClientes.getSelectedValue().toString(); 
				if (indice>=0) {
					int resposta = JOptionPane.showConfirmDialog(rootPane, "Confirma a remoção do cliente " + nome +"?", "Confirmaçao", YES_NO_OPTION);
					if (resposta == 0) {
						ClienteDao dao = new ClienteDao();
						dao.deletar(cliente.getId());
						atualizalista();
					}
				}
			}
			private void atualizalista() {
				ClienteDao dao = new ClienteDao();
				lista = dao.Listar();
				DefaultListModel dados = new DefaultListModel();
				String Nome;
				for(int i=0; i<lista.size();i++) {
					Nome = lista.get(i).getNomeCompleto();
					dados.addElement(Nome);
					
				}
				listClientes.setModel(dados);
				limpatextos();
			}
			
			private void limpatextos() {
				txtApelido.setText("");
				txtEmail.setText("");
				txtNome.setText("");
				txttelefone.setText("");
				lblId.setText("");
			}
			
		});
		
				
		btnRemover.setBounds(21, 161, 89, 23);
		contentPane.add(btnRemover);
		
		JLabel lblNewLabel = new JLabel("Apelido:");
		lblNewLabel.setBounds(221, 83, 52, 14);
		contentPane.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(283, 55, 190, 20);
		contentPane.add(txtNome);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(221, 108, 52, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(283, 105, 190, 20);
		contentPane.add(txtEmail);
		
		txttelefone = new JTextField();
		txttelefone.setColumns(10);
		txttelefone.setBounds(283, 130, 190, 20);
		contentPane.add(txttelefone);
		
		JLabel lblFone = new JLabel("Fone:");
		lblFone.setBounds(221, 133, 52, 14);
		contentPane.add(lblFone);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome:");
		lblNewLabel_1_1.setBounds(221, 58, 52, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblId = new JLabel();
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setBounds(285, 36, 52, 14);
		contentPane.add(lblId);
		
		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtApelido.setText("");
				txtEmail.setText("");
				txtNome.setText("");
				txttelefone.setText("");
				lblId.setText("");
				
			}
			
		});
		btnlimpar.setBounds(283, 161, 77, 23);
		contentPane.add(btnlimpar);
	}
}


