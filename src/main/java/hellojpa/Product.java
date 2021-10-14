package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
	
	@Id @GeneratedValue
	@Column(name = "PRODUCT_ID")
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "product")
	private List<MemberProduct> memberProducts = new ArrayList<>();
	
	private int count;
	private int price;
	
	private LocalDateTime orderDateTime;
	
}
