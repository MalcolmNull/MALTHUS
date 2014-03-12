package malthus;

import malthus.util.Random;
import malthus.util.Sort;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */


public abstract class Population
{
	protected Configuration conf = new Configuration();

	protected Individual[] generation;

	protected double populationFitness;
	@SuppressWarnings("unused")
	protected double mostFit;
	@SuppressWarnings("unused")
	protected double leastFit;

	
	public Population( )
	{
		this.conf = new Configuration();

		// Initilize Population
		int size = (Integer) this.conf.getInt("population_size");
		this.generation = new Individual[this.size];
		for(int i = 0; i < this.generation.length; i++)
			this.generation[i] = new Individual();

		// Subject to changes for a better sorting algorithm
		Sort.heap(generation);

		calStatistics();
	}


	public Population(Population previousPopulation) 
	{
		this.conf = new Configuration;

		// Initilize Population
		int size = this.conf.getInt("population_size");
		this.generation = new Individual[this.size];

		// Generate New Population
		Individual[] selected = previousPopulation.selectIndividuals();
		for(int i = 0 ; i < this.generation.length; i++)
			generation[i] = new Individual(selected[selectParent()], selected[selectParent()]);
		
		// Subject to changes for a better sorting algorithm
		Sort.heap(generation);

		calStatistics();

	}


	private void calStatistics()
	{
		double max = Double.MAX_VALUE;
		double min = Double.MIN_VALUE;
		double ave = 0.0;


		for(int i = 0; i < this.generation.length; i++) {
			// Maximum
			max = (max < this.generation[i].getFitness()) ? this.generation[i].getFitness() : max;
			
			// Minimum
			min = (min > this.generation[i].getFitness()) ? this.generation[i].getFitness() : min;


			// Average
			ave += this.generation[i].getFitness();
		}

		this.mostFit = max;
		this.leastFit = min;
		this.populationFitness = (ave / (double) this.generation.length);
	}


	protected abstract Individual[] selectIndividuals( );
	protected abstract int selectParent( );
	

	public double getPopulationFitness()
	{
		return populationFitness;
	}
	

	public double getMeanPopulationFitness()
	{
		return populationFitness;
	}
	

	public int getSize()
	{
		return generation.length;
	}
}
