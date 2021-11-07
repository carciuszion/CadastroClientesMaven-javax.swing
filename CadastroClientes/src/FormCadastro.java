//import java.awt.BorderLayout;
import java.util.ArrayList;
import  java.util.List;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.models.Cliente;

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

public class FormCadastro extends JFrame {

	protected static final int YES_NO_OPTION = 0;
	private JPanel contentPane;
	private JTextField txtApelido;
	private List<Cliente> lista = new ArrayList<Cliente>();
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txttelefone;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		txtApelido = new JTextField();
		txtApelido.setBounds(283, 65, 149, 20);
		contentPane.add(txtApelido);
		txtApelido.setColumns(10);
		
		JList listClientes = new JList();
		listClientes.setBounds(21, 24, 172, 126);
		contentPane.add(listClientes);
		
		
		
		JButton btnAdicionar = new JButton("Adicionar");
		
		
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente(txtNome.getText(), txtApelido.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txttelefone.getText());
				lista.add(cliente);
				atualizalista();
				limpatextos();
			}
			private void atualizalista() {
				DefaultListModel dados = new DefaultListModel();
				String Nome;
				for(int i=0; i<lista.size();i++) {
					Nome = lista.get(i).getNomeCompleto();
					dados.addElement(Nome);
					
				}
				listClientes.setModel(dados);
				
			}
			private void limpatextos() {
				txtApelido.setText("");
				txtEmail.setText("");
				txtNome.setText("");
				txttelefone.setText("");
			}
		});
		

		
		btnAdicionar.setBounds(317, 161, 89, 23);
		contentPane.add(btnAdicionar);
		
		JButton btnRemover = new JButton("Remover");
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indice = listClientes.getSelectedIndex();
				String nome = listClientes.getSelectedValue().toString(); 
				if (indice>=0) {
					int resposta = JOptionPane.showConfirmDialog(rootPane, "Confirma a remoção do cliente " + nome +"?", "Confirmaçao", YES_NO_OPTION);
					if (resposta == 0) {
						lista.remove(indice);
						atualizalista();
					}
				}
			}
			private void atualizalista() {
				DefaultListModel dados = new DefaultListModel();
				String Nome;
				for(int i=0; i<lista.size();i++) {
					Nome = lista.get(i).getNomeCompleto();
					dados.addElement(Nome);
					
				}
				listClientes.setModel(dados);
				
			}
			
		});
		
		btnRemover.setBounds(61, 161, 89, 23);
		contentPane.add(btnRemover);
		
		JLabel lblNewLabel = new JLabel("Apelido:");
		lblNewLabel.setBounds(221, 68, 52, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(221, 37, 52, 14);
		contentPane.add(lblNewLabel_1);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(283, 34, 149, 20);
		contentPane.add(txtNome);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(221, 99, 52, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(283, 96, 149, 20);
		contentPane.add(txtEmail);
		
		txttelefone = new JTextField();
		txttelefone.setColumns(10);
		txttelefone.setBounds(283, 130, 149, 20);
		contentPane.add(txttelefone);
		
		JLabel lblFone = new JLabel("Fone:");
		lblFone.setBounds(221, 133, 52, 14);
		contentPane.add(lblFone);
	}
}


