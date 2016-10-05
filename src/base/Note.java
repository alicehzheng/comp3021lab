package base;
import java.util.Date;


public class Note implements Comparable<Note>, java.io.Serializable{
	private Date date;
	private String title;

	public Note(String t_title){
		this.title = t_title;
		date = new Date(System.currentTimeMillis());
	}
	
	String getTitle(){
		return title;
	}

	public int compareTo(Note o){
		return this.date.compareTo(o.date) * (-1);
	}
	
	public String toString() {
		return date.toString() + "\t" + title;
	}
	
	public String getContent(){
		return " ";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Note other = (Note) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

}



