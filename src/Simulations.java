import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Simulations {

	public static void main(String[] args) {
		Problems.setNearCityParameters(Simulations.nearCityNumber);
		String filePath = (new File("")).getAbsolutePath() + "/../TSPLarge4/"; 
		filePath = (new File("")).getAbsolutePath() + "/../TSPLarge33/"; 
		//filePath = (new File("")).getAbsolutePath() + "/../TSPLIB35/"; 
		//filePath = (new File("")).getAbsolutePath() +"/../TSPLIB-S-CLPSO/"; //"/../TSP_immune/"; //"/../TSP_ESACO/";// "/../TSP_ANT/";//"/../TSP_IDBA/";//"/../TSPLIB_BCO/";//"/../TSP_WEED/";//"/../TSP_DSOS/";//"/../TSPLIB24/";//"/../TSP_GA/";//"/../TSPLIB18-HMMA/";//"/../TSPLIB25-ACE/"; //"/../TSP_DCS/"; //"/../TSP_immune/"; //"/../TSP_bat/"; 
		//float: DCS, HMMA, DIWO
		//int:TSPLIB24--DMRSA,GSAACS-PSO(HGA); TSP_bat--DBA, IDCS;TSP_GA--PCGA; TSP_ANT--TSHACO
		//filePath = (new File("")).getAbsolutePath() +"/../TSP_immune/";
		if (Simulations.TEST_TYPE == ETestType.SINGLE_INSTANCE) {
			filePath = (new File("")).getAbsolutePath() + "/../TSPLarge33/";
			String fileName = filePath+"01dsj1000.txt";
//			filePath = (new File("")).getAbsolutePath() + "/../TSPLIB35/";
//			String fileName = filePath+"35lin318.txt";//"01eil51.txt";//"35lin318.txt";
			testSingleInstance(fileName);
		} else if (Simulations.TEST_TYPE == ETestType.MULTIPLE_INSTANCE) {
			testPerformance(filePath);
		} else if (Simulations.TEST_TYPE == ETestType.PARAMETER_TUNNING_FOR_LIST_LENGTH) {
			parametersTunning4ListLength(filePath);
		} else if (Simulations.TEST_TYPE == ETestType.PARAMETER_TUNNING_FOR_POSITION) {
			parametersTunning4Position(filePath);
		} else if (Simulations.TEST_TYPE == ETestType.PARAMETER_TUNING) {
			parametersTunning(filePath);
		} else if (Simulations.TEST_TYPE == ETestType.COMPARE_KNOWLEDGE_TYPE) {
			compareKnowledgeType(filePath);
		} 
	}
	
	
	private static void parametersTunning(String filePath) {
		java.io.File dir = new java.io.File(filePath);
		java.io.File[] files = dir.listFiles();
		String fileName = (new File("")).getAbsolutePath() + "\\results\\Parameters\\";
		Simulations.selectionType = ESelectionType.RANDOM;
		Simulations.selectionType = ESelectionType.SYSTEMATIC_SEQUENCE;
		fileName += "list-based SA-" + Simulations.selectionType + " parameter tunning results for 26 instances.csv";
		
		//System.out.println(dir.exists());
		List<double[]> resultsList = new ArrayList<>();
		List<Double> paras = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			double scale = 130 + 10 * i;
			paras.add(scale);
		}
		double[] poses = new double[]{0.25,0.375, 0.5};
		for (java.io.File file : files) {
		    Problems.setFileName(file.getAbsolutePath());
		    if (Problems.getProblem().getCityNumber() > 10000) {
		    	continue;
		    }
		    //setPopulationSize();
			for (double para : paras) {
				Simulations.listLength= (int)para;
				for (double pos :poses) {
					Simulations.listArithmeticPosition = pos;
					System.out.println(file.getName() + ", list length--" + Simulations.listLength);
					double[] rs = runSA(Simulations.MAX_GENERATION, Simulations.populationSize, Simulations.TIMES);
					for (double r : rs) {
						System.out.print(r + "\t");
					}

					System.out.println();
					resultsList.add(rs);

					Simulations.saveParametersTunningResults(fileName, paras, resultsList);
				}
			}
		}
	}
	
	private static void parametersTunning4ListLength(String filePath) {
		java.io.File dir = new java.io.File(filePath);
		java.io.File[] files = dir.listFiles();
		String fileName = (new File("")).getAbsolutePath() + "\\results\\Parameters\\";
		fileName += "list-based SA-" + Simulations.selectionType + "-" + Simulations.knowledgeType + "-" + Simulations.sequenceType + "-";
		fileName += Simulations.nearCityNumber + "-" + Simulations.MAX_GENERATION + "-" + Simulations.populationSize + " list length tunning results for large TSP.csv";
		
		//System.out.println(dir.exists());
		List<double[]> resultsList = new ArrayList<>();
		List<Double> paras = new ArrayList<>();
		for (int i = 0; i <= 10; i++) {
			double scale = 100 + 10 * i;
			paras.add(scale);
		}
		for (java.io.File file : files) {
			Problems.setFileName(file.getAbsolutePath());
			for (double para : paras) {
				Simulations.listLength= (int)para;
				System.out.println(file.getName() + ", list length--" + Simulations.listLength);
				double[] rs = runSA(Simulations.MAX_GENERATION, Simulations.populationSize, Simulations.TIMES);
				for (double r : rs) {
					System.out.print(r + "\t");
				}
				System.out.println();
				resultsList.add(rs);
				Simulations.saveParametersTunningResults(fileName, paras, resultsList);
			}
		}
	}
	
	private static void parametersTunning4Position(String filePath) {
		java.io.File dir = new java.io.File(filePath);
		java.io.File[] files = dir.listFiles();
		String fileName = (new File("")).getAbsolutePath() + "\\results\\Parameters\\";
		fileName += "list-based SA-" + Simulations.selectionType + "-"  + Simulations.knowledgeType + "-" + Simulations.sequenceType + "-";
		fileName +=  Simulations.listLength + "-" + Simulations.MAX_GENERATION + "-" + Simulations.populationSize + " position tunning results v1.csv";
		
		//System.out.println(dir.exists());
		List<double[]> resultsList = new ArrayList<>();
		List<Double> paras = new ArrayList<>();
		for (int i = 0; i <= 8; i += 8) {//0.125, 0.25, ..., 0.875
			double scale = i / 8.0;
			paras.add(scale);
		}
		for (java.io.File file : files) {
			Problems.setFileName(file.getAbsolutePath());
			for (double para : paras) {
				Simulations.listArithmeticPosition = para;
				double[] rs = runSA(Simulations.MAX_GENERATION, Simulations.populationSize, Simulations.TIMES);
				for (double r : rs) {
					System.out.print(r + "\t");
				}
				System.out.println();
				resultsList.add(rs);
				Simulations.saveParametersTunningResults(fileName, paras, resultsList);
			}
		}
	}

	private static void compareKnowledgeType(String filePath) {
		java.io.File dir = new java.io.File(filePath);
		java.io.File[] files = dir.listFiles();
		String fileName = (new File("")).getAbsolutePath() + "\\results\\Parameters\\";
		fileName += "list-based SA-" + Simulations.selectionType + "-" + Simulations.sequenceType + "-" + Simulations.listLength +  "-" + Simulations.nearCityNumber + "-";
		fileName += Simulations.MAX_GENERATION + "-" + Simulations.populationSize + " compare knowledge type results.csv";
		
		//System.out.println(dir.exists());
		List<double[]> resultsList = new ArrayList<>();
		EKnowledgeType[] knowledgeTypes = EKnowledgeType.values();
		List<Double> paras = new ArrayList<>();
		for (int i = 0; i < knowledgeTypes.length; i++) {
			paras.add(new Double(i));
		}
		for (java.io.File file : files) {
			Problems.setFileName(file.getAbsolutePath());
			for (EKnowledgeType knowledgeType : knowledgeTypes) {
				Simulations.knowledgeType = knowledgeType;
				if (knowledgeType == EKnowledgeType.NONE) {
					Simulations.listLength = 10;
				} else {
					Simulations.listLength = 150;
				}
				System.out.println(file.getName() + ", knowledge type--" + knowledgeType);
				double[] rs = runSA(Simulations.MAX_GENERATION, Simulations.populationSize, Simulations.TIMES);
				for (double r : rs) {
					System.out.print(r + "\t");
				}
				System.out.println();
				resultsList.add(rs);
				Simulations.saveParametersTunningResults(fileName, paras, resultsList);
			}
		}
	}
	
	
	private static double[] testSingleInstance(String fileName) {
		Problems.setFileName(fileName);
		double[] results = runSA(Simulations.MAX_GENERATION, Simulations.populationSize, Simulations.TIMES);
		for (double d : results) {
			System.out.print(d + "\t");
		}
		System.out.println();
		return results;
	}

	private static double[] testPerformance(String filePath) {
		java.io.File dir = new java.io.File(filePath);
		java.io.File[] files = dir.listFiles();
		String pathName = filePath.substring(filePath.lastIndexOf("/", filePath.length()-2)).substring(1);
		pathName = pathName.substring(0, pathName.length()-1);
		String fileName = (new File("")).getAbsolutePath() + "/results/Performance/";
		fileName += pathName + "-" + Simulations.getParaSetting() + " results 100000-2.csv";
		
		//System.out.println(dir.exists());
		List<double[]> resultList = new ArrayList<>();
		List<String> fileList = new ArrayList<>();
		for (java.io.File file : files) {
			Problems.setFileName(file.getAbsolutePath());
			if (Problems.getProblem().getCityNumber() < 100000) {
			    continue;
			}
//			if (Problems.getProblem().getCityNumber() > 138000) {
//		    	break;
//		    }
			//if (files.length == 33) { //33 large TSP instances
			setPopulationSize();
			//}
			double[] rs = runSA(Simulations.MAX_GENERATION, Simulations.populationSize, Simulations.TIMES);
			resultList.add(rs);
			fileList.add(file.getName());
			System.out.print(file.getName()+"\t");
			for (double d : rs) {
				System.out.print(d+"\t");
			}
			System.out.println();
			Simulations.saveFinalResults(fileName, fileList, resultList);
		}
		double[] totals = new double[resultList.get(0).length];
		for (int i = 0; i < files.length; i++) {
			System.out.println();
			System.out.print(files[i].getName()+"\t");
			double[] datas = resultList.get(i);
			for (int j = 0; j < datas.length; j++) {
				System.out.print(datas[j]+"\t");
				totals[j] += datas[j];
			}
		}
		System.out.println("\t");
		for (int j = 0; j < totals.length; j++) {
			totals[j] = Math.round(totals[j]/files.length*1000)/1000.0;
			System.out.print(totals[j]+"\t");
		}
		return totals; //average data for all files
	}
	
	private static void setPopulationSize() {
		int cityNumber = Problems.getProblem().getCityNumber();
		if ( cityNumber < 1000) {
			Simulations.populationSize = 50;
		}	else if ( cityNumber < 2000) {
			Simulations.populationSize = 30;
		} else if ( cityNumber < 4000) {
			Simulations.populationSize = 20;
		} else if ( cityNumber < 50000) {
			Simulations.populationSize = 10;
		} else {
			if (Simulations.knowledgeType == EKnowledgeType.SEARCH &&
					Simulations.selectionType == ESelectionType.RANDOM &&
					Simulations.sequenceType == ESequenceType.CONSTANT) {
				Simulations.populationSize = 5;//5 for LBSA
			} else {
			    Simulations.populationSize = 3;//3 for ELBSA
			}
		}
	}
	
	private static void saveFinalResults(String fileName, List<String> fileList, List<double[]> resultList) {
		if ( !Simulations.SAVING_FINAL_RESULTS) {
			return;
		}
		try {
			PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
			for (int i = 0; i < fileList.size(); i++) {
				printWriter.println();
				printWriter.print(fileList.get(i));
				for (double data : resultList.get(i)) {
					printWriter.print("," + data);
				}
			}
			printWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static double[] runSA(final int MAX_GENERATION, final int POPULATION_SIZE, final int TIMES) {
		double duration = (new java.util.Date()).getTime();
		double bMakespan = Problems.getProblem().getBestTourLength();
		Solution s = null;
		Solution bs = null;
		double[] makespans = new double[TIMES];
		int[] iterations = new int[TIMES]; //last improving iteration
		System.out.println(Simulations.getParaSetting());
		for (int i = 0; i < TIMES; i++) {
			s = Methods.listBasedSA(MAX_GENERATION, POPULATION_SIZE );
			makespans[i] = s.getTourLength();
			iterations[i] = s.getLastImproving();
			if (Simulations.OUT_INDIVIDUAL_RUNNING_DATA) {
				System.out.println( i + " -- " + makespans[i] + "," + iterations[i]);
			}
			if (bs == null || bs.getTourLength() < s.getTourLength()) {
				bs = s;
			}
		}
		
		if (Simulations.SAVING_FINAL_TOUR) {
			Simulations.saveTour(bs);
		}
		
		duration = (new java.util.Date()).getTime()-duration;
		duration /= TIMES;
		duration = Math.round(duration/1000*1000)/1000.0;

		double min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, count = 0;
		double total = 0;
		double totalIterations = 0;
		for (int i = 0; i < makespans.length; i++) {
			double mk = makespans[i];
			total += mk;
			if ( (mk-bMakespan) * (1.0/bMakespan) *100 < 1) {
				count++;
			}
			if ( mk < min) {
				min = mk;
			}
			if (mk > max) {
				max = mk;
			}
			totalIterations += iterations[i];
		}
		double ave = total / TIMES;
		double median = DataStatisticalUtils.getMedian(makespans);
		double STD = DataStatisticalUtils.getStandardDevition(makespans);
	
		double bpd = Math.round((min-bMakespan) * (1.0/bMakespan) *100*1000)/1000.0;
		double wpd = Math.round((max-bMakespan) * (1.0/bMakespan) *100*1000)/1000.0;
		double apd = Math.round((ave-bMakespan) * (1.0/bMakespan) *100*1000)/1000.0;
		double mpd = Math.round((median-bMakespan) * (1.0/bMakespan) *100*1000)/1000.0;
		double itr = Math.round(totalIterations/iterations.length*10)/10; //average last improving iteration
		double[] stat =  new double[] {bMakespan, min, max, ave, median, STD, bpd, wpd, apd, mpd, count, itr, duration};
	
		double[] results = new double[stat.length + makespans.length];
		System.arraycopy(stat, 0, results, 0, stat.length);
		System.arraycopy(makespans, 0, results, stat.length, makespans.length);
		return results;
	}
	
	
	private static void saveParametersTunningResults(String fileName, List<Double> paras, List<double[]> resultsList) {
		if (!Simulations.SAVING_PARA_TUNNING) { return;	}
		try {
			PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
			for (int idx = 0; idx < resultsList.size(); idx++) {
				double[] rs = resultsList.get(idx);
				printWriter.println();
				printWriter.print(paras.get(idx % paras.size()));
				for (int j = 0; j < rs.length; j++) {
					printWriter.print(","+rs[j]);
				}
			}
			for (int idx = 0; idx < resultsList.size(); idx++) {
				double[] rs = resultsList.get(idx);
				if (idx % 2 == 0) {
				    printWriter.println();
				} 
				printWriter.print(","+rs[8]);
			}
			printWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void saveTour( Solution s) {
		File file = new File(Problems.getFileName());
		String fileName = (new File("")).getAbsolutePath() + "\\results\\" + file.getName();
		try {
			PrintWriter printWriter = new PrintWriter(new FileWriter(fileName + "-tour.csv"));
			int city = 0;
			printWriter.println(city);
			int nextCity = s.next(city);
			while (nextCity != city) {
				printWriter.println(nextCity);
				nextCity = s.next(nextCity);
			}
			printWriter.close();
			
			printWriter = new PrintWriter(new FileWriter(fileName + "-position.csv"));
			double[][] position = Problems.getProblem().getCityPosition();
			for (int idx = 0; idx < position.length; idx++) {
				printWriter.println(position[idx][0] + "," + position[idx][1]);
			}
			printWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static boolean isSavingFinalResults() { return Simulations.SAVING_FINAL_RESULTS;}
	public static boolean isSavingProcessData() { return Simulations.SAVING_PROCESS_DATA;}
	public static String getParaSetting() {
		String str = "list-based SA-" + Simulations.knowledgeType +"-" + Simulations.selectionType + "-";
		str += Simulations.sequenceType;
		if (Simulations.sequenceType == ESequenceType.ARIHMETIC) {
			str += "-" + Simulations.listArithmeticPosition + "-" + Simulations.LIST_ARITHMETIC_STRENGTH;
		}
		str += " LS=" + listLength + " NCN=" + Simulations.nearCityNumber;
		str += " G=" + Simulations.MAX_GENERATION + "-P=" + Simulations.populationSize;
		str += (Problems.USE_INTEGER_EDGE)? "-Int" : "-Float";
		return str;
	}
	
	
	public static EKnowledgeType getKnowledgeType() { return Simulations.knowledgeType; }
	public static ESelectionType getSamplingType() { return Simulations.selectionType; }
	public static ESequenceType getSequenceType() { return Simulations.sequenceType; }
	public static double getListArithmeticPosition() { return Simulations.listArithmeticPosition; }
	public static int getListLength() { return Simulations.listLength; }
	public static int getMaxInsertBlockSize() { return Simulations.maxInsertBlockSize; }
	public static double getListArithmeticStrength() { return Simulations.LIST_ARITHMETIC_STRENGTH; }
	
	private static EKnowledgeType knowledgeType = EKnowledgeType.PROBLEM_SEARCH;
	private static ESelectionType selectionType = ESelectionType.SYSTEMATIC_SEQUENCE;
	private static ESequenceType sequenceType = ESequenceType.ARIHMETIC;//.CONSTANT;
	
	public static final int MAX_GENERATION = 1000;
	public static final int TIMES = 25;
	public static int populationSize = 30;

	public static final boolean OUT_INDIVIDUAL_RUNNING_DATA = true;
	public static final boolean SAVING_PROCESS_DATA = false;
	public static final boolean SAVING_FINAL_RESULTS = true;
	public static final boolean SAVING_PARA_TUNNING = true;
	public static final boolean SAVING_FINAL_TOUR = true;
	public static final boolean USE_GREEDY_RANDOM_STRATEGY = true;
	public static final ETestType TEST_TYPE = ETestType.SINGLE_INSTANCE;
	
	//parameters for list-based SA algorithm 
	public static final double LIST_ARITHMETIC_STRENGTH = 0.5;
	private static double listArithmeticPosition = 0.375;//0.5
	private static int listLength = 150; //150 for large TSP instances£¬ 120 for LBSA
	private static int maxInsertBlockSize = 10;
	private static int nearCityNumber = 20;
}
