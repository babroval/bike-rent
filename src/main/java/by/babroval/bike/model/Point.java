package by.babroval.bike.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "points")
public class Point implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Integer id;

	@NotNull
	@Column(name = "num_point", unique = true)
	private Integer numPoint;

	@NotNull
	@Column(name = "slots")
	private Integer slots;

	@Column(name = "free_bikes")
	private Integer freeBikes;

	@NotNull
	@Digits(integer = 2, fraction = 8)
	@Column(name = "longitude")
	private Double longitude;

	@NotNull
	@Digits(integer = 2, fraction = 8)
	@Column(name = "latitude")
	private Double latitude;

	@NotBlank
	@Column(name = "address_mark")
	private String addressMark;

	@Column(name = "active_status")
	private Integer activeStatus;

	@Column(name = "description")
	private String description;

	public Point() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumPoint() {
		return numPoint;
	}

	public void setNumPoint(Integer numPoint) {
		this.numPoint = numPoint;
	}

	public Integer getSlots() {
		return slots;
	}

	public void setSlots(Integer slots) {
		this.slots = slots;
	}

	public Integer getFreeBikes() {
		return freeBikes;
	}

	public void setFreeBikes(Integer freeBikes) {
		this.freeBikes = freeBikes;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getAddressMark() {
		return addressMark;
	}

	public void setAddressMark(String addressMark) {
		this.addressMark = addressMark;
	}

	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activeStatus == null) ? 0 : activeStatus.hashCode());
		result = prime * result + ((addressMark == null) ? 0 : addressMark.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((freeBikes == null) ? 0 : freeBikes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((numPoint == null) ? 0 : numPoint.hashCode());
		result = prime * result + ((slots == null) ? 0 : slots.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (activeStatus == null) {
			if (other.activeStatus != null)
				return false;
		} else if (!activeStatus.equals(other.activeStatus))
			return false;
		if (addressMark == null) {
			if (other.addressMark != null)
				return false;
		} else if (!addressMark.equals(other.addressMark))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (freeBikes == null) {
			if (other.freeBikes != null)
				return false;
		} else if (!freeBikes.equals(other.freeBikes))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (numPoint == null) {
			if (other.numPoint != null)
				return false;
		} else if (!numPoint.equals(other.numPoint))
			return false;
		if (slots == null) {
			if (other.slots != null)
				return false;
		} else if (!slots.equals(other.slots))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", numPoint=" + numPoint + ", slots=" + slots + ", freeBikes=" + freeBikes
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", addressMark=" + addressMark
				+ ", activeStatus=" + activeStatus + ", description=" + description + "]";
	}

}
