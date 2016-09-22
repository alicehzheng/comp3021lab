package base;
import java.util.ArrayList;

public class NoteBook {
	private ArrayList<Folder> folders;
	
	public NoteBook(){
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String foldername, String title){
		TextNote note = new TextNote(title);
		return insertNote(foldername,note);
	}
	
	public boolean createImageNote(String foldername, String title){
		ImageNote note = new ImageNote(title);
		return insertNote(foldername,note);
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	
	private boolean insertNote(String folderName, Note note){
		Folder folder = null;
		boolean f_exist = false;
		for(Folder f: folders){
			if(f.getName().equals(folderName)){
				folder = f;
				f_exist = true;
				break;
			}								//if folder with folderName already exists, get it.
		}
		
		if(!f_exist){                       //if not, create a new Folder using the folderName.
			folder = new Folder(folderName);
			folder.addNote(note);
			folders.add(folder);
			return true;
		}
		else
		{
			ArrayList<Note> notes = folder.getNotes();
			String title = note.getTitle();
			for(Note n: notes){
				if(n.getTitle().equals(title)){
					System.out.println("Creating note " + title + "under folder "  + folderName + " failed");
					return false;
				}
			}
			folder.addNote(note);
			return true;
		}
		
		
		
		
		
	}
}
