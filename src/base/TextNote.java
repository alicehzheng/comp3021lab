package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note implements java.io.Serializable{
	private String content;
	
	public TextNote(String title){
		super(title);
	}
	
	public TextNote(String title, String content){
		super(title);
		this.content = content;
	}
	
	public TextNote(File f){
		super(f.getName());//the title of the textnote is the name of the file
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath){
		StringBuffer result = new StringBuffer();
		File file = new File(absolutePath);
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(file);
			int content;
			while((content = fis.read()) != -1 ){
				result.append((char)content);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(fis != null)
					fis.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result.toString();
	}
	
	public void exportTextToFile(String pathFolder){
		FileOutputStream fop = null;
		try{
			String title = this.getTitle();
			String filename = title.replaceAll(" ", "_");
			File file = new File(pathFolder+ File.separator + filename + ".txt");
			if(!file.exists()){
				file.createNewFile();
				fop = new FileOutputStream(file);
			}
			byte[] contentInBytes = this.getContent().getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(fop != null){
					fop.close();
				}
			}catch(IOException ec){
				ec.printStackTrace();
			}
		}
		return;	
	}
	public String getContent(){
		return this.content;
	}
	
}
