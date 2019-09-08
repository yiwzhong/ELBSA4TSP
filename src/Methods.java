import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;


public class Methods {
	/**
	 * Parallel List-based Simulated Annealing Algorithm for TSP.
	 * Each SA uses its temperature list independently.
	 * 
	 * @param MAX_G maximum generation
	 * @param POP_SIZE population size
	 * @return best solution found
	 */
	public static Solution listBasedSA( final int MAX_G, final int POP_SIZE) {
		Solution[] solutions = new Solution[POP_SIZE]; 
		int bestIdx = 0;
		for (int i = 0; i < POP_SIZE; i++) {
			solutions[i] = new Solution(Simulations.USE_GREEDY_RANDOM_STRATEGY);
			if (solutions[i].getTourLength() < solutions[bestIdx].getTourLength()) {
				bestIdx = i;
			}
		}
		Solution best = new Solution(solutions[bestIdx]);

		final int cityNumber = Problems.getProblem().getCityNumber();
		final int MARKOV_CHAIN_LENGTH = cityNumber;
		
		//create initial temperature list for each SA
		PriorityQueue<Double>[] tempLists = Methods.produceTemperatureLists(solutions, Simulations.getListLength());

		//create Markov chain length for each temperature
		int[] seq;
		if (Simulations.getSequenceType() == ESequenceType.CONSTANT) {
			seq = Methods.produceConstantSequence(MARKOV_CHAIN_LENGTH, MAX_G);
		} else {
			seq = Methods.produceArithmeticSequence((int)(MARKOV_CHAIN_LENGTH * Simulations.getListArithmeticStrength()), 
					MARKOV_CHAIN_LENGTH, 
					(int)(MAX_G * Simulations.getListArithmeticPosition()), MAX_G);
		}
		
		double[] temperatures = new double[MAX_G];
		double[] makespans = new double[MAX_G];
		double[] bestMakespans = new double[MAX_G];
		int[] cis = new int[POP_SIZE];
		for (int q = 0; q < MAX_G; q++) {
			temperatures[q] = Methods.averageTemperatur(tempLists);
			makespans[q] = Methods.averageTourLength(solutions);
			bestMakespans[q] = best.getTourLength();
			for (int id = 0; id < POP_SIZE; id++) {
				double t = -tempLists[id].peek();
				double totalTemp = 0;
				int counter = 0;
				Solution current = solutions[id];
				for (int k = 0; k < seq[q]; k++) {
					Neighbor move = Methods.produceMove(id, cis[id], current, solutions);
					double p = Methods.rand.nextDouble();
					if (move.getDelta() < 0 || p < 1.0 / Math.exp(move.getDelta()/t)) {
						current.update(move);
						if (current.getTourLength() < best.getTourLength()) {
							best.update(current);
							best.setLastImproving(q);
						}
						if ( move.getDelta() > 0) {
							totalTemp += move.getDelta() / Math.log(1.0/p);
							counter++;
						}
					}
					if (Simulations.getSamplingType() == ESelectionType.RANDOM) {
						cis[id] = Methods.rand.nextInt(cityNumber);
					} else if (Simulations.getSamplingType() == ESelectionType.SYSTEMATIC_SEQUENCE){
						cis[id] = (cis[id] + 1) % cityNumber;
					} else {
						cis[id] = current.next(cis[id]);
					}
					
				}
				//update temperature list
				if ( counter != 0) {
					tempLists[id].remove();
					tempLists[id].offer( - totalTemp/counter);
				} 
			}
		}

		if (Simulations.SAVING_PROCESS_DATA) Methods.saveConvergenceData(temperatures, makespans, bestMakespans);
		return best;
	}
	
	/**
	 * Parameter solutions will not be changed in the method. 
	 * To improve the stability, part of the biggest and smallest temperatures will be discarded.
	 * 
	 * @param solutions
	 * @param LIST_LENGTH
	 * @return
	 */
	private static PriorityQueue<Double>[] produceTemperatureLists(final Solution[] solutions, final int LIST_LENGTH) {
		//create initial temperature list for each SA
		final int TOP_BOTTOM = LIST_LENGTH;
		final int listLength = LIST_LENGTH + TOP_BOTTOM;
		@SuppressWarnings("unchecked")
		PriorityQueue<Double>[] tempLists= new PriorityQueue[solutions.length];
		for (int i = 0; i < tempLists.length; i++) {
			Solution s = new Solution(solutions[i]);
			PriorityQueue<Double> list = new PriorityQueue<Double>();
			while (list.size() < listLength) {
				Neighbor[] moves = s.findNeighbors();
				Neighbor bestMove = moves[0];
				for (Neighbor move : moves) {
					if (move != null && list.size() < listLength) {
						double t = move.delta;
						list.offer( (t > 0)? -t : t);
					}
					if ( move != null && move.getDelta() < bestMove.getDelta()) {
						bestMove = move;
					}
				}
				if (bestMove.getDelta() < 0) {
					s.update(bestMove);
				}
			}
			//remove the top TOP_BOTTOM/2 elements
			for (int idx = 0; idx < TOP_BOTTOM / 2; idx++) {
				list.poll();
			}
			//move the remained first LIST_LENGTH elements into tempLists[i]
			tempLists[i] = new PriorityQueue<Double>();
			while ( tempLists[i].size() < LIST_LENGTH && !list.isEmpty()) {
				tempLists[i].offer(list.poll());
			}
		}
		return tempLists;
	}

	
	private static Neighbor produceMove(int ID, int ci, Solution current, Solution[] s ) {
		int[][] nearCityList = Problems.getProblem().getNearCityList();
		int cityNumber = current.getCityNumber();
		Neighbor move = null;

		int nci = current.next(ci);
		int pci = current.previous(ci);	
		int cj = ci;
		if ((Simulations.getKnowledgeType() == EKnowledgeType.SEARCH || Simulations.getKnowledgeType() == EKnowledgeType.PROBLEM_SEARCH)) {
			int anotherPos = ID;	   
			while (anotherPos == ID && s.length > 1) {
				anotherPos = Methods.rand.nextInt(s.length);
			}
			Solution another = s[anotherPos];

			if (another != null) {
				cj = another.next(ci);
				if ( nci != cj && pci != cj && ci != cj ) {
					Methods.histCount++;
				} else {
					cj = another.previous(ci);
					if ( nci != cj && pci != cj && ci != cj ) {
						Methods.histCount++;
					}
				}
			}
		}

		if ( Simulations.getKnowledgeType() == EKnowledgeType.PROBLEM || Simulations.getKnowledgeType() == EKnowledgeType.PROBLEM_SEARCH) {
			//using near city list to select a city randomly
			if ( nci == cj || pci == cj || ci == cj ) {
				int m = Methods.rand.nextInt(nearCityList[ci].length);
				while ( nearCityList[ci][m] == nci || nearCityList[ci][m] == pci) {
					m = Methods.rand.nextInt(nearCityList[ci].length);
				}
				cj = nearCityList[ci][m];
				Methods.probCount++;
			}
		} 

		if ( Simulations.getKnowledgeType() == EKnowledgeType.NONE || Simulations.getKnowledgeType() == EKnowledgeType.SEARCH) {
			if ( nci == cj || pci == cj || ci == cj ) {
				while ( nci == cj || pci == cj || ci == cj ) {
					cj  = Methods.rand.nextInt(cityNumber);
				} 
				Methods.randCount++;
			}
		}
		move =current.findNeighbor(ci, cj);
		return move;
	}


	private static double averageTourLength(Solution[] ss) {
		double tl = 0;
		for (Solution s : ss) {
			tl += s.getTourLength();
		}
		return tl / ss.length;
	}
	
	private static double averageTemperatur(Queue<Double>[] qs) {
		double t = 0;
		for (Queue<Double> q : qs) {
			t += q.peek();
		}
		return -t / qs.length;
	}

	private static void saveConvergenceData( double[] ts, double[] vs, double[] bs) {
		try {
			String f = Problems.getFileName();
			File file = new File(f);
			f = (new File("")).getAbsolutePath() + "\\results\\Convergence\\" + file.getName();
			f += " " + Simulations.getParaSetting() + " convergence process by list-based SA for TSP results.csv";

			System.out.println(f);
			PrintWriter printWriter = new PrintWriter(new FileWriter(f));
			for (int idx=0; idx<ts.length; idx++) {
				printWriter.println(ts[idx] + "," + vs[idx] + "," + bs[idx]);
			}
			printWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static int[] produceConstantSequence(int x, int z) {
		int[] seq = new int[z];
		for (int i=0; i<z; i++) {
			seq[i] = x;
		}
		return seq;
	}

	private static int[] produceArithmeticSequence(int x, int y, int m, int z) {
		//produce sequence
		int[] seq = new int[z];
		double value = x;
		double step;
		if (m > 0) {
		    step = (2*y - 2*x)*1.0/m;
		    for (int i=0; i<m; i++) {
			    seq[i] = (int)(value+0.5);
			    value += step;
		    }
		} else {
			value = y + x;
		}
		step = (2*y - 2*x)*1.0/(z-m);
		for (int i=m; i < seq.length; i++) {
			seq[i] = (int)(value+0.5);
			value -= step;
		}
		return seq;
	}

	private static Random rand = new Random();

	public static int histCount;
	public static int probCount;
	public static int randCount;


	public static void main(String[] args) {
//		final int TIMES = 25;
//		String fileName = (new File("")).getAbsolutePath() + "\\..\\TSP4\\01lin318.txt";
//		Problems.setFileName(fileName);
//		Solution s;
//		double tourLength = 0;
//		for (int i = 0; i < TIMES; i++) {
//			s = Methods.listBasedSA(1000, 10);
//			System.out.println(i + "-:" + s.getTourLength());
//			tourLength += s.getTourLength();
//		}
//		tourLength /= TIMES;
//		System.out.println("Average: " + tourLength);
		int[] aMCL = Methods.produceArithmeticSequence(100/2, 100, 0, 1000);
		for (int m : aMCL) {
			System.out.println(m);
		}
	}
}
