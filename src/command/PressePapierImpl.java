package command;

public class PressePapierImpl implements PressePapier{

	private String content;
	
	public PressePapierImpl(){
		this.content = "";
	}
	
	public void write(String s){
		this.content = s;
	}
	
	public String read(){
		return this.content;
	}
	
}
