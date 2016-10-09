package fi.haagahelia.course.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    
    @NotNull
	private String author;
    
    @NotNull
	private String title;
	
	@NotNull
	private String year;
	
	@NotNull
	private String isbn;
	
	@NotNull
	private String price;
	
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "categoryid")
    private Category category;
	
    public Book() {}

	public Book(String author, String title, String year, String isbn, String price, Category category) {
		super();
		this.author = author;
		this.title = title;
		this.year = year;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getYear() {
		return year;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		if (this.category != null)
			return "Book [" + author + " " + title + " " + isbn + " " + year + " category =" + this.getCategory() + "]";
		else
			return "Book [" + author + " " + title + " " + isbn + " " + year + "]";
	}
}
