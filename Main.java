import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
	 
    // ********************** Deðiþkenlerin tanýmlanmasý  ***********************

	Score scoreObj;
	  
	long startTime = System.currentTimeMillis();
	
    int count = 0;
    int counter = 0;
    
    String sequence1 = "";
    String sequence2 = "";
    
    float matchScore = (float) 3.621354295;
    float mismatchScore = (float) -2.451795405;
    float indelScore = (float) -1.832482334;
    
    float score = 0;
    float maxScore = 0;
    
    List<Object> listDistinct = new ArrayList<Object>();
    List<Score> scoreList = new ArrayList<Score>();
    List<Float> successScores = new ArrayList<Float>();
    
    HashMap<Integer, String> sequencesList = new HashMap<Integer, String>();
    
    String fileName = "5K_Sequence"; 
    
    // ***************************************************************************
     
    try (Scanner sc = new Scanner(new File(fileName + ".fasta"))) {
    	
    	// Fasta dosyasindan verilerin okunmasi
    	
        while (sc.hasNextLine()) {
        	
            String line = sc.nextLine().trim();
            
            if(!line.startsWith(">") && line != null && line.trim().length() > 0) {
            	
            	sequencesList.put(count, line);
            	count++;
            }
        }
        
        // Hizalama matrisinin hesaplanmasi
        
        for(int a = 0; a < 100; a++) {
        	
        	for(int b = a+1; b < 100; b++) {
        			
        		
	    		sequence1 = sequencesList.get(a);
	        	sequence2 = sequencesList.get(b);
	        	
        		float[][] computedMatrix = NeedlemanWunsch.computeMatrix(sequence1, sequence2, matchScore, mismatchScore, indelScore);
        		score = computedMatrix[computedMatrix.length - 1][computedMatrix[0].length - 1];
        		
        		scoreObj = new Score();
        		
        		scoreObj.setA(a);
        		scoreObj.setB(b);
        		scoreObj.setScore(score);
        		
        		scoreList.add(scoreObj);
    		}
        }
        
        // En yüksek skor bilgisine göre sýralama iþlemi 
        scoreList.sort(Comparator.comparing(Score::getScore).reversed());
        
        System.out.println("Max Scores:");
        
        
        
        // En iyi ilk 20 skorun listelenmesi
        for(Score obj:scoreList) {
        	
        	if(counter < 20) {
        		System.out.println(counter + 1 + ". a: " + obj.getA()+ " b: " + obj.getB() + " score: " + obj.getScore());
        	}
        	
        	counter++;
        }
        
        System.out.println("#############################");
        
	} catch (Exception e) {
		
		System.out.println(e.getMessage());
		
	}
    
    long endTime = System.currentTimeMillis();
    long estimatedTime = endTime - startTime; // Geçen süreyi milisaniye cinsinden elde ediyoruz
    double seconds = (double)estimatedTime/1000; // saniyeye çevirmek için 1000'e bölüyoruz.
    
    System.out.println("Geçen süre: " + seconds + " ms");
  }
}
