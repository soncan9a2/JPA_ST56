package vn.iostar.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@NamedQuery(name="User.findByUsername", query="SELECT u FROM User u WHERE u.username = :username")
@NamedQuery(name="User.findByUsernameAndPassword", query="SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	@NotNull(message="Không được bỏ trống username")
	@Column(name="username", columnDefinition = "varchar(50)", unique = true)
	private String username;

	@NotNull(message="Không được bỏ trống password")
	@Column(name="password", columnDefinition = "varchar(100)")
	private String password;

	@Column(name="fullname", columnDefinition = "nvarchar(100)")
	private String fullname;

	@Column(name="email", columnDefinition = "varchar(100)")
	private String email;

	@Column(name="phone", columnDefinition = "varchar(20)")
	private String phone;

	@Column(name="roleid")
	private int roleid; // 1 = user, 2 = manager, 3 = admin

	@Column(name="active")
	private boolean active = true;

	// OneToMany with Category
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Category> categories;

	public User() {
		super();
	}

	public User(String username, String password, String fullname, String email, String phone, int roleid) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.roleid = roleid;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", fullname=" + fullname + ", email=" + email
				+ ", phone=" + phone + ", roleid=" + roleid + ", active=" + active + "]";
	}
}
