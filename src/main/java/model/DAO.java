package model;

import model.tables.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import model.tables.Product;
import model.tables.Purchase;

public class DAO {
    
    private final DataSource ds;
    private String emailAdmin = "root";
    private String mdpAdmin = "root";
    
    
    
    

    public DAO(DataSource dataSource) {
    
        this.ds = dataSource;
    }
    
    
    public boolean getLogin(String email, int customer_id) throws SQLException{//Si les paramètres de login sont vérifies return le customer
       
        String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID="+customer_id;
		try (Connection connection = ds.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                            String mail = rs.getString("EMAIL");
                            if(email.equals(mail)){//Si les identifiants sont exact on rempli le customer
                                return true;
                            }
                            
			}
		}
        return false;
    }
    
    
    public Customer getCustomer(int customer_id) throws SQLException{
        Customer customer = null;
        String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID="+customer_id;
        try (Connection connection = ds.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                            String discount_code = rs.getString("DISCOUNT_CODE");
                            String zip = rs.getString("ZIP");
                            customer = new Customer(customer_id, discount_code, zip);//Initialisation de customer avec les clé (primaires et etrengère)
                            customer.setName(rs.getString("NAME"));
                            customer.setAddressline1(rs.getString("ADDRESSLINE1"));
                            customer.setAddressline2(rs.getString("ADDRESSLINE2"));
                            customer.setCity(rs.getString("CITY"));
                            customer.setState(rs.getString("STATE"));
                            customer.setPhone(rs.getString("PHONE"));
                            customer.setFax(rs.getString("FAX"));
                            customer.setEmail(rs.getString("EMAIL"));
                            customer.setCredit_limit(rs.getInt("CREDIT_LIMIT"));
                            
                            
			}
		}
        return customer;
        
    }
    
    public boolean isAdmin(String email, String mdp){
        return email.equals(emailAdmin) && mdp.equals(mdpAdmin);
    }
    
    public List<Product> getProducts() throws SQLException{
        List<Product> products = null;
        String sql = "SELECT * FROM PRODUCT";
        try (Connection connection = ds.getConnection(); 
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                            
                            Product product = new Product();
                            product.setProduct_id(rs.getInt("product_id"));//clé primaire
                            product.setManufacturer_id(rs.getInt("manufacturer_id"));//clé etrangère (manufacturer_id)
                            product.setProduct_code(rs.getString("product_code"));//clé etrangère (product_code)
                            product.setPurchase_cost(Double.parseDouble(rs.getString("purchase_cost")));
                            product.setQuantity_on_hand(rs.getInt("quantity_on_hand"));
                            product.setMarkup(Double.parseDouble(rs.getString("markup")));
                            product.setAvailable(rs.getString("available"));
                            product.setDescription(rs.getString("description"));
                            products.add(product);
                        }
        }
	
        return products;
    }
    
    public List<Purchase> getPurchaseOfCustomer(int customer_id) throws SQLException{
        List<Purchase> purchases = null;
        String sql = "SELECT * FROM PURCHASE WHERE CUSTOMER_ID="+customer_id;
        try(Connection connection =ds.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Purchase purchase = new Purchase();
                purchase.setCustomer_id(customer_id);
                purchase.setFreight_company(rs.getString("freight_company"));
                
            }
        }
        return purchases;
    }
    
    
    
}
