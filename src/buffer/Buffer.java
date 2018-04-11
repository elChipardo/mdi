package buffer;

import command.PressePapier;

public interface Buffer {

	public void copy();
	
	public void paste();
	
	public void cut();
	
	public void insert(String contentInsert);
	
	public void selections();
	
	public void delete();
	
	public BufferImpl clone();
	
	public void changeCursor(int newPositionCursor);
	
	public int getPositionCurseur();
	
	public boolean getIsSelect();
	
	public void setStartSel(int startSel);
	
	public void setEndSel(int endSel);
	
	public String getContent();
	
	public void setSelect(boolean isSelect);
	
	public void setContentSel(String contentSel);
	
	public void workAreaInformation();
	
	public PressePapier getMemory();
	
	public int getStartSel();
	
	public int getEndSel();
}
