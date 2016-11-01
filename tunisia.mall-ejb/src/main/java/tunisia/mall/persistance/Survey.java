package tunisia.mall.persistance;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity:Survey
 *
 */
@Entity

public class Survey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2257943426712855679L;

	private int id;
	
	private String r1;
	private String r2;
	private String r3;
	private String r4;
	private String r5;
	private String r6;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getR1() {
		return r1;
	}
	public void setR1(String r1) {
		this.r1 = r1;
	}
	public String getR2() {
		return r2;
	}
	public void setR2(String r2) {
		this.r2 = r2;
	}
	public String getR3() {
		return r3;
	}
	public void setR3(String r3) {
		this.r3 = r3;
	}
	public String getR4() {
		return r4;
	}
	public void setR4(String r4) {
		this.r4 = r4;
	}
	public String getR5() {
		return r5;
	}
	public void setR5(String r5) {
		this.r5 = r5;
	}
	public String getR6() {
		return r6;
	}
	public void setR6(String r6) {
		this.r6 = r6;
	}
	
	
	
}