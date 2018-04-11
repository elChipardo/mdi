package commandMemento;

import buffer.Buffer;
import command.Command;

public class InsertMemento implements CommandMemento {

	private Buffer workArea;
	private String contentInsert;
	
	public InsertMemento(Buffer workArea, String contentInsert) {
		this.workArea = workArea;
		this.contentInsert = contentInsert;
	}
	
	@Override
	public void execute(){
		this.workArea.insert(this.contentInsert);
	}

