package vn.iostar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cate_id")
	private int cateId;

	@NotNull(message="Không được bỏ trống")
	@Column(name="cate_name", columnDefinition = "nvarchar(200)")
	private String cateName;

	@Column(name="icons", columnDefinition = "nvarchar(MAX)")
	private String icons;

	// ManyToOne with User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	public Category() {
		super();
	}

	public Category(String cateName, String icons) {
		super();
		this.cateName = cateName;
		this.icons = icons;
	}

	public Category(int cateId, String cateName, String icons) {
		super();
		this.cateId = cateId;
		this.cateName = cateName;
		this.icons = icons;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getIcons() {
		return icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Category [cateId=" + cateId + ", cateName=" + cateName + ", icons=" + icons + "]";
	}
}
