package Base;
import java.util.ArrayList;

public class Folder {
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String n_name){
		this.name = n_name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note new_note){
		notes.add(new_note);
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Note> getNotes(){
		return this.notes;
	}
	
	public boolean equals(Folder folder){
		return this.name == folder.getName();
	}
	
	public String toString(){ 
		int nText = 0;
		int nImage = 0;
		
		for(Note note: notes){
			if(note instanceof TextNote)
				nText++;
			else if(note instanceof ImageNote)
				nImage++;
		}
		
		return name + ":" + nText + ":" + nImage;
	}
}
