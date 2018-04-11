package command;

import buffer.Buffer;

public class ChangeCursor implements Command {

	private Buffer workArea;
	private int positionCursor;
	
	public ChangeCursor(Buffer workArea, int positionCursor) {
		this.workArea = workArea;
		this.positionCursor = positionCursor;
	}
	@Override
	public void execute() {
		this.workArea.changeCursor(this.positionCursor);
		
	}

}
