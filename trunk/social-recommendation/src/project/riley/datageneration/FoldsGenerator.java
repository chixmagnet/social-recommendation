package project.riley.datageneration;

import project.riley.predictor.ArffData;
import project.riley.predictor.ArffData.FoldData;

/*
 * generate n-fold data for cross validation
 * 
 */
public class FoldsGenerator {
	
	public static final String FILENAME = "passive.arff";
	public static final int NUM_FOLDS = 10;
	
	/*
	 * Generate data for interaction thresholds up to size interaction_threshold
	 */
	public static void interactionThresholdGeneration(int interaction_threshold) throws Exception{		
		for (int i = 0; i < interaction_threshold; i++){
			String name = "threshold_" + (i+1) + "_" + FILENAME;
			DataGeneratorPassiveActive.writeData(name, i /* interaction threshold */);				// write data
			
			ArffData arff = new ArffData(name);														// load data
			FoldData folds = arff.foldData(NUM_FOLDS);
			folds.writeData();																		// split into folds
		}		
	}
	
	public static void main(String args[]) throws Exception {
		//DataGeneratorPassiveActive.populateCachedData(true /* active */);							// generate data
		DataGeneratorPassiveActive.populateCachedData(false /* passive */);							// generate data
		
		interactionThresholdGeneration(10);
		
		System.out.println("Generated " + NUM_FOLDS + " folds and exported files.");
	}
	
}