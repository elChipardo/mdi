package buffer;

import command.PressePapier;
import command.PressePapierImpl;

public class BufferImpl implements Buffer {

	private String content;
	private String contentSel;
	private PressePapier memory;
	private int startSel;
	private int endSel;
	private int positionCurseur;
	private boolean isSelect;

	public BufferImpl(){
		this.content = "";
		this.contentSel = "";
		this.memory = new PressePapierImpl();
		this.startSel = 0;
		this.endSel = 0;
		this.positionCurseur = 0;
		this.isSelect = false;
	}

	@Override
	public void copy(){
		this.memory.write(this.contentSel);
	}

	@Override
	public void paste(){
		String contentLeft = this.content.substring(0, this.positionCurseur);
		String contentRight = this.content.substring(this.positionCurseur, this.content.length());
		String contenttmp = contentLeft.concat(this.memory.read());
		this.content = contenttmp.concat(contentRight);
		this.positionCurseur = this.content.length();
	}

	@Override
	public void cut(){
		String contentLeft = this.content.substring(0, this.startSel);
		String contentRight = this.content.substring(this.endSel, this.content.length());
		this.memory.write(this.contentSel);
		this.content = contentLeft + contentRight;
		this.positionCurseur = this.content.length();
	}

	@Override
	public void insert(String contentInsert){
		String contentLeft = this.content.substring(0, this.positionCurseur);
		String contentRight = this.content.substring(this.positionCurseur, this.content.length());
		this.content = contentLeft + contentInsert;
		this.positionCurseur = this.content.length();
		this.content += contentRight;
	}

	@Override
	public void selections(){
		if(this.startSel == 0 && this.endSel == 0) {
			this.isSelect = false;
			this.contentSel = "";
		}
		else {
			this.contentSel = this.content.substring(this.startSel, this.endSel);
			this.isSelect = true;
			this.positionCurseur = this.startSel;
		}
	}

	@Override
	public void delete() {
		if (this.isSelect) {
			String contentLeft = this.content.substring(0, this.startSel);
			String contentRight = this.content.substring(this.endSel, this.content.length());
			this.content = contentLeft + contentRight;
			this.positionCurseur = this.content.length();
			this.contentSel = "";
		}
		else {
			String contentLeft = this.content.substring(0, this.positionCurseur-1);
			System.out.println("left : " + contentLeft);
			String contentRight = this.content.substring(this.positionCurseur, this.content.length());
			System.out.println("right : " + contentRight);
			this.content = contentLeft;
			this.positionCurseur = this.content.length();
			this.content += contentRight;
			this.contentSel = "";
		}
	}

	public void changeCursor(int newPositionCursor) {
		this.positionCurseur = newPositionCursor;
	}

	public int getPositionCurseur() {
		return positionCurseur;
	}

	public boolean getIsSelect() {
		return isSelect;
	}

	public PressePapier getMemory() {
		return this.memory;
	}

	public void workAreaInformation() {
		System.out.println("---------- Informations ----------");
		System.out.println("position du curseur : " + this.positionCurseur);
		System.out.println("taille du contenu : " + this.content.length());
		System.out.println("debut selection : " + this.startSel);
		System.out.println("fin selection : " + this.endSel);
		System.out.println("le contenu de l'editeur est : " + this.content);
		System.out.println("le contenu de la selection est : " + this.contentSel);
		System.out.println("le contenu du presse papier est : " + this.memory.read());
		System.out.println("");
	}

	public void setStartSel(int startSel) {
		this.startSel = startSel;
	}

	public void setEndSel(int endSel) {
		this.endSel = endSel;
	}

	public String getContent() {
		return content;
	}

	public void setContentSel(String contentSel) {
		this.contentSel = contentSel;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	public int getStartSel() {
		return startSel;
	}

	public int getEndSel() {
		return endSel;
	}
	
	public BufferImpl clone() {
		BufferImpl bufferClone = new BufferImpl();
		bufferClone.content = this.content;
		bufferClone.contentSel = this.contentSel;
		bufferClone.startSel = this.startSel;
		bufferClone.endSel = this.endSel;
		bufferClone.positionCurseur = this.positionCurseur;
		bufferClone.isSelect = this.isSelect;
		bufferClone.memory = this.memory;
		return bufferClone;
	}

}
