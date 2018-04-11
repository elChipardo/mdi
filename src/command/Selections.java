package command;

import buffer.Buffer;

public class Selections implements Command {

	private Buffer workArea;
	private int debSel;
	private int finSel;	
	
	public Selections(Buffer workArea, int debSel, int finSel) {
		this.workArea = workArea;
		this.debSel = debSel;
		this.finSel = finSel;
	}
	
	
	public void execute(){
		this.workArea.setStartSel(this.debSel);
		this.workArea.setEndSel(finSel);
		this.workArea.selections();
	}
}
