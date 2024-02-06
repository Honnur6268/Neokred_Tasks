package in.nk.tech.encdec.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_list")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	private String name;
	
	private String address;
	
	@Column(name = "zip code")
	private String zipCode;
	
	private String phone;
	
	private String city;
	
	private String country;
	
	private String notes;
	
	@Column(name = "SID")
	private int sid;
}
