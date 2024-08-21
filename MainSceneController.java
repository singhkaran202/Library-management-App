import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class mainSceneController implements Initializable {
	@FXML

	private TextField tfID;
	@FXML

	private TextField tfTitle;
	@FXML

	private TextField tfAuthor;
	@FXML

	private TextField tfYear;
	@FXML

	private TextField tfPages;
	@FXML

	private TableView<books> tvBooks;
	@FXML

	private TableColumn<books, Integer> colID;
	@FXML

	private TableColumn<books, String> colTitle;
	@FXML

	private TableColumn<books, String> colAuthor;
	@FXML

	private TableColumn<books, Integer> colYear;
	@FXML

	private TableColumn<books, Integer> colPages;
	@FXML

	private Button btnInsert;
	@FXML

	private Button btnUpdate;
	@FXML

	private Button btnDelete;
	@FXML

	private void handleButtonAction(ActionEvent event) {
		if (event.getSource() == btnInsert) {
			insertRecord();
		} else if (event.getSource() == btnUpdate) {
			updateRecord();
		} else if (event.getSource() == btnDelete) {
			deleteRecord();
		}
	}
	@FXML
	void handleMouseAction(MouseEvent event) {
		books book = tvBooks.getSelectionModel().getSelectedItem();
		tfID.setText("" + book.getId());
		tfTitle.setText(book.getTitle());
		tfAuthor.setText(book.getAuthor());
		tfYear.setText("" + book.getYear());
		tfPages.setText("" + book.getPages());
	}
	@Override

	public void initialize(URL url, ResourceBundle rb) {
		showBooks();
	}
	//used for connectivity to the database
	public Connection getConnection() {
		Connection conn;
		try {
			conn =
				DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root",
					"");
			return conn;
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return null;
		}
	}

	public ObservableList<books> getBooksList() {
		ObservableList<books> bookList = FXCollections.observableArrayList();
		Connection conn = getConnection();
		String query = "SELECT * FROM books";
		java.sql.Statement st;
		ResultSet rs;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			books books;

			while (rs.next()) {
				books = new books(rs.getInt("id"), rs.getString("title"),
					rs.getString("author"), rs.getInt("year"),
					rs.getInt("pages"));
				bookList.add(books);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return bookList;
	}

	public void showBooks() {
		ObservableList<books> list = getBooksList();
		colID.setCellValueFactory(new PropertyValueFactory < books,
			Integer > ("id"));
		colTitle.setCellValueFactory(new PropertyValueFactory < books,
			String > ("title"));
		colAuthor.setCellValueFactory(new PropertyValueFactory < books,
			String > ("author"));
		colYear.setCellValueFactory(new PropertyValueFactory < books,
			Integer > ("year"));
		colPages.setCellValueFactory(new PropertyValueFactory < books,
			Integer > ("pages"));
		tvBooks.setItems(list);
	}

	private void insertRecord() {
		String query = "INSERT INTO BOOKS VALUES(" + tfID.getText() + ",'" +
			tfTitle.getText() + "','" +
			tfAuthor.getText() +
			"'," + tfYear.getText() + "," + tfPages.getText() + ")";
		executeQuery(query);
		showBooks();
	}

	private void updateRecord() {
		String query = "UPDATE BOOKS SET PAGES =" +
			tfPages.getText() + ", TITLE = '" +
			tfTitle.getText() + "', AUTHOR = '" +
			tfAuthor.getText() + "', YEAR = " +
			tfYear.getText() + " WHERE id = " +
			tfID.getText();
		executeQuery(query);
		showBooks();
	}

	private void deleteRecord() {
		String query = "DELETE FROM BOOKS WHERE ID =" + tfID.getText();
		executeQuery(query);
		showBooks();
	}

	private void executeQuery(String query) {
		Connection conn = getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
