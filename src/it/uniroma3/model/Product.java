package it.uniroma3.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Column;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "findAllProducts", query = "SELECT p FROM Product p")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	private Float price;
	
	@Column(length = 2000)
	private String description;
	
	private Integer storageQuantity;
	
	private String picturePath;

	@Column(nullable = false)
	private String code;

	@ManyToMany(mappedBy="products")
	private List<Provider> providers;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private List<Review> reviews;


	public Product() {
		this.providers = new LinkedList<Provider>();
		this.reviews = new LinkedList<Review>();
	}

	public Product(String name, Float price, String description, String code, String picturePath, Integer quantity) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.storageQuantity = quantity;
		this.picturePath = picturePath;
		this.code = code;
		this.providers = new LinkedList<Provider>();
		this.reviews = new LinkedList<Review>();
	}

	//          Getters & Setters        

	public Long getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getStorageQuantity() {
		return storageQuantity;
	}

	public void setStorageQuantity(Integer storageQuantity) {
		this.storageQuantity = storageQuantity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", description=" + description + ", storageQuantity="
				+ storageQuantity + ", picturePath=" + picturePath + ", code="
				+ code + "]";
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

}