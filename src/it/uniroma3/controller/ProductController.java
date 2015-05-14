package it.uniroma3.controller;

import java.util.List;

import it.uniroma3.model.Product;
import it.uniroma3.facade.ProductFacade;
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
	private int orderQuantity;

	private Product product;
	private List<Product> products;

	@EJB
	private ProductFacade productFacade;

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
				return "error"; //TODO
			}
		}
		else {
			return "admin?faces-redirect=true";
		}
	}
	
	public String addToCart(){
		CartController cc = new CartController();
		
		if(cc.addProductToCart(this.product, this.orderQuantity))
			return "products";
		else return "error";
	}

	public String listProducts() {
		//this.products = productFacade.getAllProducts();
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

	public void setProductFromId() {
		this.product = productFacade.getProduct(id);
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
	
	
}


