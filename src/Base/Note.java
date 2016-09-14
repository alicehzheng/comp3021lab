package Base;
import java.util.Date;


public class Note {
	private Date date;
	private String title;
	
	public Note(String t_title){
		this.title = t_title;
		date = new Date(System.currentTimeMillis());
	}
	
	String getTitle(){
		return title;
	}
	
	boolean equals(Note note)
	{
		String n_title = note.getTitle();
		return this.title == n_title;
	}

}



