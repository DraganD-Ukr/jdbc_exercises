package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@AllArgsConstructor
@Getter
@ToString
public class Product implements Comparable<Product> {

    private String productCode;
    private String productName;
    private String productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private Integer quantityInStock;
    private Double buyPrice;
    private Double MSRP;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productCode, product.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productCode);
    }

    @Override
    public int compareTo(Product o) {
        return -this.productCode.compareTo(o.productCode);
    }
}
