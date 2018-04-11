package command;

import buffer.Buffer;

public class Copy implements Command{
	
	private Buffer workArea;
	
	public Copy(Buffer workArea) {
		this.workArea = workArea;
	}
	
	public void execute(){
		this.workArea.copy();
	}
}
