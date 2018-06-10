package by.babroval.bike.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "bikes")
public class Bike implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Integer id;

	@NotBlank
	@Column(name = "vin")
	private String vin;

	@NotBlank
	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "point_id")
	private Point point;

	@Column(name = "available_status")
	private Integer availableStatus;

	@Column(name = "condit")
	private String condit;

	@ManyToOne
	@JoinColumn(name = "price_id")
	private Price price;

	public Bike() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Integer getAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(Integer availableStatus) {
		this.availableStatus = availableStatus;
	}

	public String getCondit() {
		return condit;
	}

	public void setCondit(String condit) {
		this.condit = condit;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((availableStatus == null) ? 0 : availableStatus.hashCode());
		result = prime * result + ((condit == null) ? 0 : condit.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Bike))
			return false;
		Bike other = (Bike) obj;
		if (availableStatus == null) {
			if (other.availableStatus != null)
				return false;
		} else if (!availableStatus.equals(other.availableStatus))
			return false;
		if (condit == null) {
			if (other.condit != null)
				return false;
		} else if (!condit.equals(other.condit))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!vin.equals(other.vin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bike [id=" + id + ", vin=" + vin + ", description=" + description + ", point=" + point
				+ ", availableStatus=" + availableStatus + ", condit=" + condit + ", price=" + price + "]";
	}

}
