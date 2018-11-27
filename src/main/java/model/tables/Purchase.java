package model.tables;

import java.time.LocalDate;

   
public class Purchase {
    
    int order_num;//clé primaire
    int customer_id;//clé etrangère
    int product_id;//clé étrangère
    int quantity;
    double shipping_cost;
    LocalDate shipping_date;
    LocalDate sales_dates;
    String freight_company;

    public Purchase(int order_num, int customer_id, int product_id) {
        this.order_num = order_num;
        this.customer_id = customer_id;
        this.product_id = product_id;
    }

    public Purchase(int order_num, int customer_id, int product_id, int quantity, double shipping_cost, LocalDate shipping_date, LocalDate sales_dates, String freight_company) {
        this.order_num = order_num;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.shipping_cost = shipping_cost;
        this.shipping_date = shipping_date;
        this.sales_dates = sales_dates;
        this.freight_company = freight_company;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setShipping_cost(double shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public void setShipping_date(LocalDate shipping_date) {
        this.shipping_date = shipping_date;
    }

    public void setSales_dates(LocalDate sales_dates) {
        this.sales_dates = sales_dates;
    }

    public void setFreight_company(String freight_company) {
        this.freight_company = freight_company;
    }
    
    
    
    
}
