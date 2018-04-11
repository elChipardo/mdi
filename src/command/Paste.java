package command;

import buffer.Buffer;

public class Paste implements Command {

	private Buffer workArea;
	
	public Paste(Buffer workArea) {
		this.workArea = workArea;
	}
	
	public void execute(){
		this.workArea.paste();
	}
}
