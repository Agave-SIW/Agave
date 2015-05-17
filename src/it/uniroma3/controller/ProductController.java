package it.uniroma3.controller;

import java.util.List;
import java.util.Map;

import it.uniroma3.model.Customer;
import it.uniroma3.model.Product;
import it.uniroma3.model.Review;
import it.uniroma3.facade.CustomerFacade;
import it.uniroma3.facade.ProductFacade;
import it.uniroma3.facade.ReviewFacade;
import it.uniroma3.helper.FileHelper;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;


@ManagedBean
public class ProductController {

	@ManagedProperty(value="#{param.id}")
	private Long id;
	private String name;
	private Float price;
	private String description;
	private String code;
	private Integer quantity;
	private Part picture;
	private String picturePath;

	private Product product;
	private List<Product> products;

	// review form data
	private String comment;
	private Integer stars;
	private Long idProduct;

	private String page;

	@EJB
	private ProductFacade productFacade;
	@EJB
	private CustomerFacade customerFacade;

	private ReviewFacade reviewFacade;
	
	private Map<String, Object> currentSessionMap;
	
	
	public ProductController() {
		this.currentSessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		this.reviewFacade = new ReviewFacade();
	}

	public String createProduct() {
		// security check
		AdminController ac = new AdminController();

		//file path functions
		FileHelper fh = new FileHelper();

		if(ac.loggedIn()){
			String filename = fh.getFileNameFromHeader(picture);
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String path = ctx.getRealPath("/");
			path = path + fh.makePath(path, "uploads");
			System.out.print("\n\nFile system context path (in TestServlet): " + path + "\n");

			try {
				System.out.print("\nTrying to upload "+ filename +" in "+ path +"\n");
				picture.write(path+filename);
				System.out.print("\nSuccesful! "+ filename + " is uploaded\n\n");

				this.picturePath = filename.toString();

				this.product = productFacade.createProduct(name, code, price, description, picturePath, quantity);
				return "product"; 
			} 
			catch (Exception e) {
				return "errorProduct"; 
			}
		}
		else {
			return "admin?faces-redirect=true";
		}
	}

	public String listProducts() {
		this.products = productFacade.getAllProducts();
		return "products?faces-redirect=true"; 
	}

	public List<Product> getListProducts() {
		return productFacade.getAllProducts();
	}

	public List<Product> getLastProducts() {
		return productFacade.getLastProducts();
	}
	public List<Product> getLastFourProducts() {
		return productFacade.getLastProducts(4);
	}

	public String findProduct() {
		this.product = productFacade.getProduct(id);
		return "product?id="+id+"&faces-redirect=true&includeViewParams=true";
	}

	public String findProduct(Long id) {
		this.product = productFacade.getProduct(id);
		return "product?id="+id+"&faces-redirect=true&includeViewParams=true";
	}

	public Product getProductFromId() {
		this.product = getProductFromId(id);
		return this.product;
	}
	
	public Product getProductFromId(Long id) {
		this.product = productFacade.getProduct(id);
		return this.product;
	}

	public String addReview(Long idProduct){
		//must verify that the user is actually logged in
		Customer c = (Customer) currentSessionMap.get("customer");
		if(c==null) return "error";

		Product p = this.productFacade.getProduct(idProduct);
		Review r = this.reviewFacade.createReview(stars, comment, c);

		try{
			this.productFacade.addReviewToProduct(r, p);
		}
		catch (Exception e){
			return "error?faces-redirect=true";
		}

		System.out.println("Review Added!");

		return "successReview";
	}

	public List<Review> getReviews(Product product){
		return product.getReviews();
	}

	public List<Review> getReviews(){
		return getReviews(this.product);
	}

	public Integer getReviewNumber(Product product){
		return product.getReviews().size();
	}

	public Integer getReviewNumber(){
		return getReviewNumber(this.product);
	}

	public Integer getReviewScore(Review review){
		return review.getStars();
	}

	public Float getReviewAverage(Product product){
		List<Review> rws = product.getReviews();
		Float size = (float) rws.size();
		Float total = new Float(0);

		for (Integer i = 0; i < size; i++) {
			total += (rws.get(i).getStars());
		}
		if(size!=0)
			return (float) (total/size);
		else
			return (float) 0;
	}

	public Float getReviewAverage(){		
		return getReviewAverage(this.product);
	}

	public String getReviewAverageHtml(){			
		return getReviewAverageHtml(this.product);
	}

	public String getReviewAverageHtml(Product product){		
		Integer stars = Math.round(getReviewAverage(product));
		return starsToHtml(stars);
	}

	public String getReviewHtml(Review review){		
		Integer stars = review.getStars();
		return starsToHtml(stars);
	}

	public String starsToHtml(Integer stars){
		String html = "";

		for (Integer i = 0; i < stars; i++) {
			html += "<span class=\"glyphicon glyphicon-star\"></span> ";
		}
		for (Integer i = stars; i < 5; i++) {
			html += "<span class=\"glyphicon glyphicon-star-empty\"></span> ";
		}

		return html;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Part getPicture() {
		return picture;
	}

	public void setPicture(Part picture) {
		this.picture = picture;
	}

	public ProductFacade getProductFacade() {
		return productFacade;
	}

	public void setProductFacade(ProductFacade productFacade) {
		this.productFacade = productFacade;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public CustomerFacade getCustomerFacade() {
		return customerFacade;
	}

	public void setCustomerFacade(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}

	public ReviewFacade getReviewFacade() {
		return reviewFacade;
	}

	public void setReviewFacade(ReviewFacade reviewFacade) {
		this.reviewFacade = reviewFacade;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Map<String, Object> getCurrentSessionMap() {
		return currentSessionMap;
	}

	public void setCurrentSessionMap(Map<String, Object> currentSessionMap) {
		this.currentSessionMap = currentSessionMap;
	}
	
	

}


