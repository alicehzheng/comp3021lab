package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>, java.io.Serializable{
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
	
	public int compareTo(Folder f){
		return this.name.compareTo(f.name);
	}
	
	public void sortNotes(){
		List<Note> lnotes = notes;
		Collections.sort(lnotes);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> result = new ArrayList<Note>();
		String[] sp = keywords.split(" ");
		int len = sp.length;
		for(int i = 0; i < len; i++){
			sp[i] = sp[i].toUpperCase();
		}
		for(Note note:notes){
			if(note instanceof ImageNote){
				String title = note.getTitle();
				title = title.toUpperCase();
				boolean find = true;
				int i;
				for(i = 0; i < len - 1; ){
					if(title.contains(sp[i])){
						if(sp[i+1].equals("OR"))
							i += 3;
						else
							i++;
					}
					else{
						if(sp[i+1].equals("OR") && title.contains(sp[i+2]))
							i+=3;
						else{
							find = false;
							break;
						}
					}
				}
				if(find && i == len - 1)
					find = title.contains(sp[len-1]);
				if(find)
					result.add(note);
			}
			else{
				String title = note.getTitle();
				title = title.toUpperCase();
				String content = note.getContent();
				content = content.toUpperCase();
				boolean find = true;
				int i;
				for(i = 0; i < len - 1; ){
					if(title.contains(sp[i])||content.contains(sp[i])){
						if(sp[i+1].equals("OR"))
							i += 3;
						else
							i++;
					}
					else{
						if(sp[i+1].equals("OR") && (title.contains(sp[i+2]) || content.contains(sp[i+2])))
							i += 3;
						else{
							find = false;
							break;
						}
					}
				}
				if(find && i == len - 1)
					find = title.contains(sp[len-1]) || content.contains(sp[len-1]);
				if(find)
					result.add(note);
			}
		};
		return result;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
