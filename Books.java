public class books {

	private int id;

	private String title;

	private String author;

	private int year;

	private int pages;

	public books(int iD, String title, String author, int year, int pages) {
		this.id = iD;
		this.title = title;
		this.author = author;
		this.year = year;
		this.pages = pages;
	}

	public int getId() {
		return id;
	}

	public void setId(int iD) {
		this.id = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
}
