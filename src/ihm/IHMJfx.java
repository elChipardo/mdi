package ihm;


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
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import memento.MemoryActions;


public class IHMJfx extends Application implements IHM {

	//ATTRIBUTS
	boolean deleted;
	Buffer workArea;
	MemoryActions listActions;
	TextArea zoneSaisie;
	char carac;
	String punctuation = ".?;,:/!*-+~\"\'{([|`_^)]<>²§%ù£µ°=";
	Command copy;
	Command cut;
	Command select;
	Command paste;
	Command insert;
	Command delete;
	Command changeCursor;

	public IHMJfx() {
		this.workArea = new BufferImpl();
		this.listActions = new MemoryActions();
		this.deleted = false;
		this.carac = ' ';		
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO
		stage.setWidth(800);
		stage.setHeight(600);
		stage.setTitle("Editeur de texte de Victor et Hugues");

		Group root = new Group();
		Scene scene = new Scene(root);

		GridPane grid = new GridPane();
		grid.setHgap(8);
		grid.setVgap(8);
		grid.setPadding(new Insets(10, 10, 10, 10));

		ColumnConstraints cons1 = new ColumnConstraints();
		cons1.setHgrow(Priority.NEVER);
		grid.getColumnConstraints().add(cons1);

		ColumnConstraints cons2 = new ColumnConstraints();
		cons2.setHgrow(Priority.ALWAYS);

		grid.getColumnConstraints().addAll(cons1, cons2);

		RowConstraints rcons1 = new RowConstraints();
		rcons1.setVgrow(Priority.NEVER);

		RowConstraints rcons2 = new RowConstraints();
		rcons2.setVgrow(Priority.ALWAYS);  

		grid.getRowConstraints().addAll(rcons1, rcons2);

		this.zoneSaisie = new TextArea();
		Label l = new Label("Saisie de votre texte : ");
		this.zoneSaisie.setEditable(false);

		this.zoneSaisie.setPromptText("Input your text here, please");
		this.zoneSaisie.setPrefSize(500, 400);

		//-----------------------------SELECTIONS + CHANGEMENT CURSEUR ---------------------------
		this.zoneSaisie.setOnMouseClicked(new EventHandler<Event>()
		{
			@Override
			public void handle(Event arg0)
			{
				changeCursor();
				selections();
				workArea.workAreaInformation();
			}
		});

		//-----------------------------SELECTIONS + CHANGEMENT CURSEUR ---------------------------
		this.zoneSaisie.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.BACK_SPACE) {
					delete();
					workArea.workAreaInformation();
				}
			}
		});

		//-----------------------------SELECTIONS + CHANGEMENT CURSEUR ---------------------------
		this.zoneSaisie.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(KeyEvent event) {
				carac = event.getCharacter().charAt(0);
				if(Character.isLetter(carac) || Character.isDigit(carac) || Character.isSpace(carac) || punctuation.contains(carac + "")) {
					insert();
					workArea.workAreaInformation();
				}
			}
		});

		grid.add(l, 0, 0);
		grid.add(this.zoneSaisie, 0, 1);

		Button copy = new Button("Copy");
		Button cut = new Button("Cut");
		Button paste = new Button("Paste");

		paste.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("coller");
				paste();
				workArea.workAreaInformation();
			}
		});

		cut.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("couper");
				cut();
				workArea.workAreaInformation();
			}
		});

		copy.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("copier");
				copy();
				workArea.workAreaInformation();
			}
		});
		
		grid.add(copy, 1, 3);
		grid.add(cut, 2, 3);
		grid.add(paste, 3, 3);


		//--------------------------------- Presse papier information ---------------------------------
		//		Label information = new Label ("Information buffer");
		//		Label positionCurseur = new Label ("Position curseur : " + this.workArea.getPositionCurseur());
		//		Label contenuMemory = new Label ("Memory content : " + this.workArea.getBuffer().getMemory().read());
		//		Label debSel = new Label ("Start selection : " + this.workArea.getBuffer().getStartSel());
		//		Label endSel = new Label ("End selection : " + this.workArea.getBuffer().getEndSel());
		//		GridPane informationMemory = new GridPane();
		//		informationMemory.setHgap(8);
		//		informationMemory.setVgap(8);
		//		informationMemory.setPadding(new Insets(10, 10, 10, 10));
		//		informationMemory.add(information, 0, 0);
		//		informationMemory.add(positionCurseur, 0, 1);
		//		informationMemory.add(contenuMemory, 0, 2);
		//		informationMemory.add(debSel, 0, 3);
		//		informationMemory.add(endSel, 0, 4);
		//		grid.add(informationMemory, 2, 0);

		root.getChildren().add(grid);
		stage.setScene(scene);
		stage.show();

	}

	@Override
	public void lancement() {
		Application.launch();
	}

	@Override
	public void copy() {
		this.copy = new Copy(this.workArea);
		this.copy.execute();
	}

	@Override
	public void paste() {
		this.paste = new Paste(this.workArea);
		this.paste.execute();
		this.zoneSaisie.setText(this.workArea.getContent());

	}

	@Override
	public void cut() {
		this.cut = new Cut(this.workArea);
		this.cut.execute();
		zoneSaisie.setText(workArea.getContent());
	}

	@Override
	public void insert() {
		this.insert = new Insert(this.workArea, this.carac + "");
		this.insert.execute();
		this.zoneSaisie.setText(this.workArea.getContent());

	}

	@Override
	public void selections() {
		int debSel = this.zoneSaisie.getSelection().getStart();
		int finSel = this.zoneSaisie.getSelection().getEnd();
		if(debSel == finSel) {
			this.select = new Selections(this.workArea, 0, 0);
			this.select.execute();
		}
		if(debSel < finSel) {
			this.select = new Selections(this.workArea, debSel, finSel);
			this.select.execute();
		}

	}

	@Override
	public void delete() {
		this.delete = new Delete(this.workArea);
		this.delete.execute();
		this.zoneSaisie.setText(this.workArea.getContent());
	}

	@Override
	public void changeCursor() {
		this.changeCursor = new ChangeCursor(workArea, this.zoneSaisie.getCaretPosition());
		this.changeCursor.execute();
	}


}
