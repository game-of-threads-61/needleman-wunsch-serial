import java.io.FileWriter;
import java.io.IOException;

public class FileWriterResult {
	/*public static void whenWriteStringUsingBufferedWritter(String fileName, List<Float> fileContent)throws IOException {
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName + "-result.txt"), "utf-8"))) 
		{
			for(Float score: fileContent) {
				writer.write(score.toString());
			}
			writer.close();
		}
	}*/
	public static void usingFileWriter() throws IOException
	{
	    String fileContent = "Hello Learner !! Welcome to howtodoinjava.com.";
	     
	    FileWriter fileWriter = new FileWriter("C:/Users/CasperPc/Desktop/ender.txt");
	    fileWriter.write(fileContent);
	    fileWriter.close();
	}
}
