package ihm;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import buffer.Buffer;
import buffer.BufferImpl;
import command.ChangeCursor;
import command.Command;
import command.Copy;
import command.Cut;
import command.Delete;
import command.Insert;
import command.Paste;
import command.Selections;

public class IHMLdC implements IHM {

	//ATTRIBUTS
	boolean isStarted;
	Buffer workArea;
	Scanner sc;
	Command copy;
	Command cut;
	Command select;
	Command paste;
	Command insert;
	Command delete;
	Command changeCursor;

	//CONSTRUCTEUR
	public IHMLdC(){
		this.isStarted = false;
		this.workArea = new BufferImpl();
		this.sc = new Scanner(System.in);

	}

	public void menu() {
		System.out.println("---------- Le menu ----------");
		System.out.println("\t - 0 : Quitter l'editeur de texte");
		System.out.println("\t - 1 : Faire un copier");
		System.out.println("\t - 2 : Faire un coller");
		System.out.println("\t - 3 : Faire une selection");
		System.out.println("\t - 4 : Faire un couper");
		System.out.println("\t - 5 : Faire une insertion");
		System.out.println("\t - 6 : Faire une suppression");
		System.out.println("\t - 7 : Changer la position du curseur");
		System.out.println("\t - 8 : Effacer la selection");
		System.out.println("");
	}


	@Override
	public void lancement() {
		this.isStarted = true;
		int choixMenu;
		System.out.println("---------- BIENVENUE DANS VOTRE EDITEUR ----------");
		while(this.isStarted){
			this.workArea.workAreaInformation();
			this.menu();
			System.out.println("Veuillez choisir une option pour l'editeur");
			Scanner sc = new Scanner(System.in);
			choixMenu = sc.nextInt();
			switch(choixMenu){
			case 0:
				this.stop();
				break;
			case 1:
				this.copy();
				break;
			case 2:
				this.paste();
				break;
			case 3:
				this.selections();
				break;
			case 4:
				this.cut();
				break;
			case 5:
				this.insert();
				break;
			case 6:
				this.delete();
				break;
			case 7:
				this.changeCursor();
				break;
			case 8:
				this.deleteSelections();
			default:
				System.out.println("erreur, veuillez recommencer");
				break;

			}
		}
	}

	public void stop() {
		this.isStarted = false;
		System.out.println(this.workArea.clone().getContent());
		System.out.println("Fin de l'editeur, merci au revoir");

	}
	@Override
	public void copy() {
		if(this.workArea.getStartSel() == 0 && this.workArea.getEndSel() ==0) {
			System.out.println("Impossible de couper, vous devez selectionner du texte avant");
		}
		else {
			this.copy = new Copy(this.workArea);
			this.copy.execute();
		}

	}

	@Override
	public void paste() {
		System.out.println("A quel endroit souhaitez-vous positionner votre curser pour coller ?");
		this.changeCursor();
		this.paste = new Paste(this.workArea);
		this.paste.execute();

	}

	@Override
	public void cut() {
		if(this.workArea.getStartSel() == 0 && this.workArea.getEndSel() ==0) {
			System.out.println("Impossible de couper, vous devez selectionner du texte avant");
		}
		else {
			this.cut = new Cut(this.workArea);
			this.cut.execute();
		}

	}

	@Override
	public void insert() {
		System.out.println("Que voulez-vous inserer ?");
		this.insert = new Insert(this.workArea, this.sc.nextLine());
		this.insert.execute();

	}

	@Override
	public void selections() {
		int debSel;
		int finSel;
		do {
			System.out.println("Donnez le debut et la fin de la selection en entier");
			System.out.println("Attention, le debut de la selection doit etre positif ou nulle");
			debSel = this.sc.nextInt();
			finSel = this.sc.nextInt();
		}
		while(debSel<0);

		this.select = new Selections(this.workArea, debSel, finSel);
		this.select.execute();

	}

	@Override
	public void delete() {
		this.delete = new Delete(this.workArea);
		this.delete.execute();
	}

	@Override
	public void changeCursor() {
		int positionCurseur;
		do {
			System.out.println("A quelle position souhaitez-vous positionner votre curseur ?");
			positionCurseur = this.sc.nextInt();
		}
		while(positionCurseur > this.workArea.getContent().length() || positionCurseur < 0);
		this.changeCursor = new ChangeCursor(this.workArea, positionCurseur);
		this.changeCursor.execute();
	}
	
	public void deleteSelections() {
		this.select = new Selections(this.workArea, 0, 0);
		this.select.execute();
	}


}
