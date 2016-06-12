package com.guthrie.cameron;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Alice extends JFrame {

	public static Qubit[] alice_qubits;
	
	static public double key_length, total_key_l, d_key_size, key_length_perc, new_key_length_perc;
	static public int counter = 0; 
	
	public static int N;
	static JButton sendButton     = new JButton("Send qubits to Bob");
	static JButton create_basis   = new JButton("Measure Alice's Qubits With Random Basis");
	static JButton send_bob_basis = new JButton("Exchange Basis Arrays");
	
	static JLabel key_label                = new JLabel();
	static JLabel alice_N_label            = new JLabel();
	static JLabel alice_basis_label        = new JLabel();
	static JLabel bob_basis_label          = new JLabel();
	static JLabel alice_polarization_label = new JLabel();
	static JLabel bob_polarization_label   = new JLabel();
	static JLabel alice_bit_label          = new JLabel();
	static JLabel bob_bit_label            = new JLabel();
	static JLabel matching_bits            = new JLabel();
	static JLabel bob_receive_qubit        = new JLabel();
	static JLabel bob_key_label            = new JLabel();
	static JLabel alice_key_label          = new JLabel();
	static JLabel key_length_avg_label     = new JLabel();
	static JLabel alice_bit_array_label    = new JLabel();
	
	static boolean start = false;
	
	public static String[] bob_basis, alice_basis, bob_basis_array, alice_basis_array;
	public static int[] alice_bit, bob_bit_array, alice_bit_array;
	
    static JFrame alice_frame = new JFrame("Alice");
    static JFrame bob_frame   = new JFrame("Bob");

    static Qubit myq    = new Qubit();
    static Alice caller = new Alice();
    
	static JTextField textField         = new JTextField(10);
	static JPanel button_panel          = new JPanel();
	static JPanel button_panel_send     = new JPanel();
	static JPanel button_panel_exchange = new JPanel();
	static JPanel label_panel1          = new JPanel();
	static JPanel label_panel2          = new JPanel();
	static JPanel label_panel3          = new JPanel();
	static JPanel label_panel8          = new JPanel();
	static JPanel label_panel9          = new JPanel();
	static JPanel bob_button_panel      = new JPanel();
	static JPanel bob_basis_panel       = new JPanel();
	static JPanel bob_bit_panel         = new JPanel();
	static JPanel alice_key_panel       = new JPanel();
	static JPanel bob_key_panel         = new JPanel();
	static JPanel key_length_avg_panel  = new JPanel();
	
	public static void main(String[] args) throws Exception
	{
		runPresentation();
	}
	
	public static void getKey(String[] alice_basis, String[] bob_basis, int[] alice_bit)
	{
		ArrayList<Integer> key = checkArray(alice_bit, alice_basis, bob_basis);
		System.out.println("Alice's key: " + key.toString());
	}

	public static void createBitArray(Qubit[] qubit, String[] basis)
	{
		int N = qubit.length;
		int[] alice_bit_a = new int[N];
		for (int i = 0; i < N; i++)
		{
			alice_bit_a[i] = myq.measureQubit(qubit[i], basis[i]);
		}
	}
	
	public static void runPresentation()
	{
		String refrence = "<html>Reference<br>Rectilinear Basis: +  Polarization: |  - <br>"+
		"Diagonal Basis: X  Polarization: /  \\<br>" + ""
				+ "<br><br></html>";
			
		// bob frame
		bob_frame.setLayout(new FlowLayout());
        bob_frame.setSize(350, 400);
        
        label_panel9         .add(bob_receive_qubit);
        bob_button_panel     .add(create_basis);		
        bob_basis_panel      .add(bob_basis_label);
        bob_bit_panel        .add(bob_bit_label);
        button_panel_exchange.add(send_bob_basis);
        bob_key_panel        .add(bob_key_label);
        key_length_avg_panel .add(key_length_avg_label);
        
        bob_button_panel     .setVisible(false);
        button_panel_exchange.setVisible(false);

        
        bob_frame.add(label_panel9);
        bob_frame.add(bob_button_panel);
        bob_frame.add(bob_basis_panel);
        bob_frame.add(bob_bit_panel);
        bob_frame.add(button_panel_exchange);
        bob_frame.add(bob_key_panel);
        bob_frame.add(key_length_avg_panel);
        
        create_basis   .addActionListener(createBasisListener);
        send_bob_basis.addActionListener(exchangeBasisListener);
        		
        
		// alice frame
        JButton clearButton = new JButton("Clear All");
        clearButton.addActionListener(clearListener);
        
		JLabel reference_label = new JLabel(refrence);
		alice_key_panel.add(reference_label);
		
		JButton button = new JButton("Ok");
		button.addActionListener(actionListener);

		sendButton.addActionListener(sendListener);
		
		alice_frame .add(alice_key_panel);
		button_panel.add(new JLabel("Alice's N:"));
		button_panel.add(textField);
		button_panel.add(button);
		button_panel.add(clearButton);
		
		alice_frame.setLayout(new FlowLayout());
        alice_frame.setSize(380, 400);

        label_panel1.add(alice_basis_label);
        label_panel2.add(alice_polarization_label);
        label_panel3.add(alice_bit_array_label);
		
		alice_frame.add(button_panel);
		alice_frame.add(label_panel1);
		alice_frame.add(label_panel2);
		alice_frame.add(label_panel3);
		alice_frame.add(button_panel_send);
		alice_frame.add(alice_key_label);

		try {
			  UIManager.setLookAndFeel(
			    UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
		}
		
		alice_frame.setVisible(true);
		bob_frame  .setVisible(true);
	}
		
	public static void setN(int N_)
	{
		N = N_;
	}
	public static int getN()
	{
		return N;
	}
	
	public static void setAliceQubits(Qubit[] alice_qubits_)
	{
		alice_qubits = alice_qubits_;
	}
	public static Qubit[] getAliceQubits()
	{
		return alice_qubits;
	}
	
	public static void setBobBitArray(int[] bob_bit_array_)
	{
		bob_bit_array = bob_bit_array_;
	}
	
	public static void setAliceBitArray(int[] alice_bit_array_)
	{
		alice_bit_array = alice_bit_array_;
	}
	
	public static void setBobBasisArray(String[] bob_basis_array_)
	{
		bob_basis_array = bob_basis_array_;
	}
	public static String[] getBobBasisArray()
	{
		return bob_basis_array;
	}
	
	public static void setAliceBasisArray(String[] alice_basis_array_)
	{
		alice_basis_array = alice_basis_array_;
	}
	public static String[] getAliceBasisArray()
	{
		return alice_basis_array;
	}
	
	public static void avg_key_length(ArrayList<Integer> key, int N)
	{
		if (counter == 0)
		{
			total_key_l = key.size();
			counter     = 1;
			d_key_size  = (double)key.size();
			
			key_length_perc = d_key_size/N;
			key_length_perc = key_length_perc*100; 
		}
		else
		{
			counter++;
			total_key_l     = total_key_l + key.size();
			key_length_perc = total_key_l/counter;
			key_length_perc = key_length_perc/N;
			key_length_perc = key_length_perc*100;
		}
		key_length_avg_label.setText("Key length average: " + String.valueOf(key_length_perc)+"%");
	}
	
	static ActionListener clearListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			bob_basis_label         .setText("");
			alice_basis_label       .setText("");
			alice_key_label         .setText("");
			bob_key_label           .setText("");
			key_length_avg_label    .setText("");
    		bob_receive_qubit       .setText("");
    		alice_polarization_label.setText("");
    		alice_bit_array_label   .setText("");
    		bob_bit_label           .setText("");
    		
            bob_button_panel.setVisible(false);			
            
            counter     = 0;
            total_key_l = 0;
		}
	};
	
	static ActionListener exchangeBasisListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			ArrayList<Integer> bob_key   = caller.checkArray(bob_bit_array, alice_basis_array, bob_basis_array);
			ArrayList<Integer> alice_key = caller.checkArray(alice_bit_array, alice_basis_array, bob_basis_array);
			
			alice_key_label.setText("Alice's Secret Key: " + alice_key.toString());
			bob_key_label  .setText("Bob's Secret Key: "   + bob_key  .toString());
			
			avg_key_length(bob_key, N);
		}
	};
	
	static ActionListener createBasisListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
            bob_basis_array = basisArray(N);
            setBobBasisArray(bob_basis_array);
            String bob_basis_array_string = printArray(bob_basis_array);
            
            bob_basis_label.setText("Bob Basis: " + bob_basis_array_string);
             
           	String bob_measured_bit_array_string = "";
           	int[] bob_bit_array = new int[N];
            
           	for (int i = 0; i < N; i++)
            {
            	bob_bit_array[i]              = myq.measureQubit(alice_qubits[i], bob_basis_array[i]);
            	bob_measured_bit_array_string = bob_measured_bit_array_string + Integer.toString(bob_bit_array[i]);
            }
            bob_bit_label.setText("Bob's measured bit array: " + bob_measured_bit_array_string);
            button_panel_exchange.setVisible(true);
            setBobBitArray(bob_bit_array);
		}
	};
	
	static ActionListener sendListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			int N = getN();
    		bob_receive_qubit.setText("Received Alice's qubits");
            bob_button_panel .setVisible(true);
		}
	};
	
    static ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            String N_ = textField.getText();
            int N     = Integer.parseInt(N_);
    		
            String[] alice_basis = basisArray(N);
            setN(N);
            setAliceBasisArray(alice_basis);
    		String[] alice_polarization = polarizationArray(alice_basis);
    		alice_qubits                = generateQubitArray(alice_basis, alice_polarization);
    		
    		setAliceQubits(alice_qubits);
    		
    		alice_bit_array               = bitArray(alice_polarization); 
    		String alice_bit_array_string = printIntArray(alice_bit_array);
    		    		
            alice_basis_label       .setText("Alice's Basis: "        + printArray(alice_basis));
            alice_polarization_label.setText("Alice's polarization: " + printArray(alice_polarization));
            alice_bit_array_label   .setText("Alice's bit array: "    + alice_bit_array_string);
    		
            button_panel_send.add(sendButton);
        }
      };
	
	static ArrayList checkArray(int[] bit_a, String[] basis_a, String[] bob_basis)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < basis_a.length; i++)
		{
			if (basis_a[i].equals(bob_basis[i]))
			{
				list.add(bit_a[i]);
			}
		}
		return list;
	}
	
	public static int[] measureQubitArray(Qubit[] qubit, String[] basis)
	{
		int N = qubit.length;
		int[] bit_array = new int[N];
		for (int i = 0; i < N; i++)
		{
			bit_array[i] = myq.measureQubit(qubit[i], basis[i]);
		}
		return bit_array;
	}
	
	static public int[] bitArray(String[] polarization_a)
	{
		int[] bit_a = new int[polarization_a.length];
		for (int i = 0; i < polarization_a.length; i++)
		{
			bit_a[i] = myq.generateBit(polarization_a[i]);
		}
		return bit_a;
	}
	
	static public String printIntArray(int[] array_a)
	{
		String array = "";
		for (int i = 0; i < array_a.length; i++)
		{
			System.out.print(array_a[i] + " ");
			array = array + array_a[i];
		}
		return array;
	}
	
	static public String printArray(String[] array_a)
	{
		String array = "";
		for (int i = 0; i < array_a.length; i++)
		{
			System.out.print(array_a[i] + " ");
			array = array + array_a[i];
		}
		return array;
	}
	
	static public String[] basisArray(int N)
	{
		String[] basis_a = new String[N];
		for (int i = 0; i < N; i++)
		{
			basis_a[i] = myq.generateBasis();
		}
		return basis_a;
	}
	
	static public String[] polarizationArray(String[] basis_a)
	{
		String[] polarization_a = new String[basis_a.length];
		for (int i = 0; i < basis_a.length; i++)
		{
			polarization_a[i] = myq.generatePolarization(basis_a[i] + " ");
		}
		return polarization_a;
	}
	
	static public Qubit[] generateQubitArray(String[] basis_a, String[] polarization_a)
	{
		Qubit[] qubit_a = new Qubit[basis_a.length]; 

		for (int i = 0; i < basis_a.length; i++)
		{
			qubit_a[i] = new Qubit(polarization_a[i], basis_a[i]);
		}
		
		return qubit_a;
	}	
}

