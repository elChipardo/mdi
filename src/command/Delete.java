package command;

import buffer.Buffer;

public class Delete implements Command{

	private Buffer workArea;
	
	public Delete(Buffer workArea) {
		this.workArea = workArea;
	}
	
	@Override
	public void execute() {
		this.workArea.delete();
		
	}

}
