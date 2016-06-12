package com.guthrie.cameron;

import java.util.Random;

public class Qubit {

	static Qubit qubit = new Qubit();
	static Alice caller = new Alice();
	
	static private Random generator = new Random(System.currentTimeMillis());
	private String polarization, basis;
	static int bit;
	public static String plus, X, plus_, X_;
	
	public Qubit(){}
	
	public Qubit(String polarization, String basis)
	{
		this.polarization = polarization;
		this.basis        = basis;
	}
	
	static double randomGenerator() 
	{
		return generator.nextDouble();
	}
	
	public int generateBit(String polarization)
	{
		if      (polarization.equals("|"))  return 1;
		else if (polarization.equals("/"))  return 1;
		else if (polarization.equals("-"))  return 0;
		else if (polarization.equals("\\")) return 0;
		else return -99;
	}
	
	public int measureQubit(Qubit myqubit, String basis_)
	{
		int bit;
		if (myqubit.basis.equals(basis_)) 
		{
			bit = generateBit(myqubit.polarization);
			myqubit = null;
		}
		else
		{
			myqubit.basis        = basis_;
			myqubit.polarization = myqubit.generatePolarization(basis_);	
			bit = generateBit(myqubit.polarization);	
			myqubit = null;
		}
		return bit;
	}
	
	public String getBasis()
	{
		return basis;
	}
	
	public String getPolarization()
	{
		return polarization;
	}
	
	public String generatePolarization(String basis)
	{
		plus  = "+ ";
		plus_ = "+";
		X     = "X ";
		X_    = "X";
		
		if (basis.equals(plus) || basis.equals(plus_))
		{
			double polarization_num = qubit.randomGenerator();
			if (polarization_num < 0.5) 
			{
				polarization = "|";
			}
			else 
			{
				polarization = "-";
			}
		}
		else if (basis.equals(X))
		{
			double polarization_num = qubit.randomGenerator();
			if (polarization_num < 0.5) 
			{
				polarization = "/";
			}
			else 
			{
				polarization = "\\";
			}
		}

		return polarization;
	}
	
	
	public String generateBasis()
	{
		double basis_num = qubit.randomGenerator();
		if (basis_num < 0.5) basis = "+";
		else basis = "X";
		return basis;
	}
}