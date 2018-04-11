package command;

import buffer.Buffer;

public class Cut implements Command {

	private Buffer workArea;
	
	public Cut(Buffer workArea) {
		this.workArea = workArea;
	}
	
	public void execute(){
		this.workArea.cut();
	}
}
