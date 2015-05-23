package it.uniroma3.controller;

import java.util.LinkedList;
import java.util.List;

import it.uniroma3.model.Customer;
import it.uniroma3.model.Product;
import it.uniroma3.model.Provider;
import it.uniroma3.model.Review;
import it.uniroma3.facade.CustomerFacade;
import it.uniroma3.facade.ProductFacade;
import it.uniroma3.facade.ReviewFacade;
import it.uniroma3.helper.ContextHelper;
import it.uniroma3.helper.FileHelper;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 * System operations for Product and Review management
 *
 * @author Gaetano
 *
 */

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

	private Long[] providerIds;

	private Product product;
	private List<Product> products;
	private List<Provider> providers;

	// review form data
	private String comment;
	private Integer stars;
	private Long idProduct;

	private Review review;

	private String page;

	@EJB
	private ProductFacade productFacade;
	@EJB
	private CustomerFacade customerFacade;
	//not ejb
	private ReviewFacade reviewFacade;

	private ContextHelper ch;


	public ProductController() {
		this.ch = new ContextHelper();
		this.reviewFacade = new ReviewFacade();
	}

	public String createProduct() {
		// security check
		AdminController ac = new AdminController();

		//file path functions
		FileHelper fh = new FileHelper();

		if(ac.isLogged()){
			String filename = fh.getFileNameFromHeader(picture);
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String path = ctx.getRealPath("/");
			path = path + fh.makePath(path, "uploads");
			System.out.println("\nFile system context path (in TestServlet): " + path);

			try {
				System.out.println("Trying to upload "+ filename +" in "+ path);
				picture.write(path+filename);
				System.out.println("Succesful! "+ filename + " is uploaded");

				this.picturePath = filename.toString();

				System.out.println("Trying to create and add product to database");
				this.product = this.productFacade.createProduct(name, code, price, description, picturePath, quantity, makeListFromIds(this.providerIds));
				System.out.println("All done");

				return "product?id="+this.product.getId()+"&faces-redirect=true&includeViewParams=true";
			}
			catch (Exception e) {
				return "WEB-INF/errorProduct";
			}
		}
		else {
			return "admin?faces-redirect=true";
		}
	}

	public List<Provider> makeListFromIds(Long[] providerIds){
		List<Provider> providers = new LinkedList<Provider>();
		Integer len = providerIds.length;
		for(Integer i=0; i<len; i++){
			providers.add(productFacade.getProvider(providerIds[i]));
		}
		return providers;
	}

	public String listProducts() {
		this.products = productFacade.getAllProducts();
		return "products?faces-redirect=true";
	}

	public List<Product> getListProducts() {
		return productFacade.getAllProducts();
	}
	
	public List<Provider> findProviders(Long productId) {
		this.providers = productFacade.findProviders(productId);
		return this.providers;
	}

	public List<Provider> getListProviders() {
		return productFacade.getAllProviders();
	}

	public List<Product> getLastProducts() {
		return productFacade.getLastProducts();
	}
	public List<Product> getLastFourProducts() {
		return productFacade.getLastProducts(4);
	}

	public String findProduct() {
		return this.findProduct(this.id);
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
		//must verify that the user is actually logged in to add a review
		CustomerController cc = new CustomerController();
		if(cc.isNotLogged()) return "WEB-INF/errorReview";

		try{
			Customer c = (Customer) this.ch.getFromSession("customer");

			Product p = this.productFacade.getProduct(idProduct);
			Review r = this.reviewFacade.createReview(stars, comment, c);


			this.productFacade.addReviewToProduct(r, p);
			System.out.println("Review Added!");

			this.setReview(r);
			return "WEB-INF/successReview";
		}
		catch (Exception e){
			return "WEB-INF/errorReview";
		}

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

	public String getReviewHtml(){
		Integer stars = review.getStars();
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

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public ContextHelper getCh() {
		return ch;
	}

	public void setCh(ContextHelper ch) {
		this.ch = ch;
	}

	public Long[] getProviderIds() {
		return providerIds;
	}

	public void setProviderIds(Long[] providerIds) {
		this.providerIds = providerIds;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}
	


}
