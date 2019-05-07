import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
	  
	Score scoreObj;
	  
	long startTime = System.currentTimeMillis();
    int count = 0;
    
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
    
    try (Scanner sc = new Scanner(new File(fileName + ".fasta"))) {
    	
    	//Fasta dosyasindan verilerin okunmasi
        while (sc.hasNextLine()) {
        	
            String line = sc.nextLine().trim();
            
            if(!line.startsWith(">") && line != null && line.trim().length() > 0) {
            	
            	sequencesList.put(count, line);
            	count++;
            }
        }
        
        for(int a = 0; a < 5; a++) {
        	
        	for(int b = a+1; b < 5; b++) {
        			
        		//Hizalama matrisinin hesaplanmasi
	    		sequence1 = sequencesList.get(a);
	        	sequence2 = sequencesList.get(b);
        		float[][] computedMatrix = NeedlemanWunsch.computeMatrix(sequence1, sequence2, matchScore, mismatchScore, indelScore);
        		score = computedMatrix[computedMatrix.length - 1][computedMatrix[0].length - 1];
        		scoreObj = new Score();
        		scoreObj.setA(a);
        		scoreObj.setB(b);
        		scoreObj.setScore(score);
        		scoreList.add(scoreObj);

        		/*
        		//Her bir sekans degerinin maksimum skorunun bulunmasi
        		if(score > maxScore) {
        			maxScore = score;
        		}
        		*/
    		}
        	//scoreList.add(maxScore);
        	/*Collections.sort(scoreList);
        	listDistinct = scoreList.stream().distinct().collect(Collectors.toList());
        	maxScore = 0;*/
        }
        for(Score obj:scoreList) {
        	
        	System.out.println("a: " + obj.getA()+ " b: " + obj.getB() + " score: " + obj.getScore());
        }
        Collections.sort(scoreList);
        for(Score obj:scoreList) {
        	
        	System.out.println(" score: " + obj.getScore());
        }
        //Collections.reverse(listDistinct);
        //System.out.println("Max Scores:");
        //En iyi ilk 20 maksimum degerin listelenmesi
        /*for(int r = 0; r < 20; r++) {
        	//successScores.add(listDistinct.get(r));
        	System.out.println(listDistinct.get(r));
        }*/
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
    
    long endTime = System.currentTimeMillis();
    long estimatedTime = endTime - startTime; // Geçen süreyi milisaniye cinsinden elde ediyoruz
    double seconds = (double)estimatedTime/1000; // saniyeye çevirmek için 1000'e bölüyoruz.
    
    System.out.println("Geçen süre: " + seconds);
  }
}
