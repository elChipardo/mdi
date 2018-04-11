package command;

import buffer.Buffer;

public class Insert implements Command {

	private Buffer workArea;
	private String contentInsert;
	
	public Insert(Buffer workArea, String contentInsert) {
		this.workArea = workArea;
		this.contentInsert = contentInsert;
	}
	
	@Override
	public void execute(){
		this.workArea.insert(this.contentInsert);
	}

}
