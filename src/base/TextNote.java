package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.io.FileOutputStream;


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
			File file = new File(pathFolder + filename + ".txt");
			System.out.println(file.getAbsolutePath());
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
	
	//for test
	public Character countLetters(){
		HashMap<Character,Integer> count = new HashMap<Character,Integer>();
		String a = this.getTitle() + this.getContent();
		int b = 0;
		Character r = ' ';
		for (int i = 0; i < a.length(); i++) {
			Character c = a.charAt(i);
			if (c <= 'Z' && c >= 'A' || c <= 'z' && c >= 'a') {
				if (!count.containsKey(c)) {
					count.put(c, 1);
				} else {
					count.put(c, count.get(c) + 1);
					if (count.get(c) > b) {
						b = count.get(c);
						r = c;
					}
				}
			}
		}
		return r;
	}
	
}
