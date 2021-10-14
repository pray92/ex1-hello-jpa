package hellojpa;

import javax.persistence.*;

/**
 * 연결 테이블 엔티티
 */
@Entity
public class MemberProduct {
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
}
