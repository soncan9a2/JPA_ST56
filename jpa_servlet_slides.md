### Slide 1
WEB

PR33

0479

KHOA CÔNG NGHỆ THÔNG TIN  
BỘ MÔN CÔNG NGHỆ PHẦN MỀM

LẬP TRÌNH WEB (WEBPR330479)

THS. NGUYỄN HỮU TRUNG

Java Persistence API - JPA

---

### Slide 2: Thông tin Giảng viên
- ThS. Nguyễn Hữu Trung  
- Khoa Công Nghệ Thông Tin  
- Trường Đại học Sư Phạm Kỹ Thuật TP.HCM  
- 090.861.7108  
- trungnh@hcmute.edu.vn  
- https://www.youtube.com/@baigiai

---

### Slide 3: Nội dung
- So sánh giữa JDBC và JPA  
- Giới thiệu JPA  
- Kiến trúc JPA  
- Thành phần JPA  
- OneToOne  
- OneToMany  
- ManyToMany  
- CascadeType  
- FetchType  
- Cài đặt JPA  
- Quản lý thực thể JPA  
- Query in JPA  
- Bài tập vận dụng

---

### Slide 4: Mô hình ứng dụng JDBC
- Tốn nhiều thời gian, dễ sai sót vì viết nhiều SQL  
- Viết quá nhiều mã Java cho truy vấn và thao tác dữ liệu  
- Khó khăn trong việc điều khiển Transaction  
- Nâng cấp khó khăn vì SQL phụ thuộc hệ quản trị CSDL

---

### Slide 5: Mô hình ứng dụng JPA
- Java chỉ làm việc với các đối tượng  
- ORM ánh xạ đối tượng với bản ghi CSDL qua Entity Class  
- JPA tự động chuyển đổi đối tượng ↔ SQL

---

### Slide 6: Giới thiệu JPA
- JPA = Java Persistence API  
- ORM mapping bảng, column, quan hệ thành lớp Java  
- Quản lý POJO thành thực thể và các quan hệ

---

### Slide 7: Kiến trúc JPA
- **EntityManagerFactory**: tạo, quản lý EntityManager  
- **EntityManager**: quản lý hoạt động liên tục trên đối tượng  
- **Entity**: thực thể lưu trong CSDL  
- **EntityTransaction**: duy trì giao dịch của EntityManager  
- **Persistence**: lấy thể hiện EntityManagerFactory  
- **Query**: truy vấn dữ liệu

---

### Slide 8: Kiến trúc công nghệ JPA
- persistence.xml  
- EntityManagerFactory, EntityManager, EntityTransaction, Query

---

### Slide 9: Các thành phần JPA
- persistence.xml: cấu hình CSDL  
- Persistence: nạp file cấu hình  
- EntityManagerFactory: tạo EntityManager  
- EntityManager: thêm, sửa, xóa, truy vấn  
- EntityTransaction: điều khiển transaction  
- Query/TypedQuery<T>: truy vấn bằng JPQL

---

### Slide 10: Mối quan hệ lớp trong JPA
- EntityManagerFactory ↔ EntityManager: 1-nhiều  
- EntityManager ↔ EntityTransaction: 1-1  
- EntityManager ↔ Query: 1-nhiều  
- EntityManager ↔ Entity: 1-nhiều

---

### Slide 11-12: JPA - ORM
**Employee.java:**
```java
public class Employee {
 private int eid;
 private String ename;
 private double salary;
 private String deg;
 public Employee(int eid, String ename, double salary, String deg) {
   super(); this.eid = eid; this.ename = ename; this.salary = salary; this.deg = deg;
 }
 public Employee() { super(); }
 // Getters & Setters
}
```

**mapping.xml:**
```xml
<entity-mappings xmlns="http://java.sun.com/xml/ns/persistence/orm" ...>
 <entity class="Employee">
   <table name="EMPLOYEETABLE"/>
   <attributes>
     <id name="eid"><generated-value strategy="TABLE"/></id>
     <basic name="ename"><column name="EMP_NAME" length="100"/></basic>
     <basic name="salary"/>
     <basic name="deg"/>
   </attributes>
 </entity>
</entity-mappings>
```

---

### Slide 13-15: Annotations
Annotation Description
@Entity chỉ định khai báo lớp dưới dạng thực thể hoặc bảng.
@Table chỉ định khai báo tên bảng.
@Basic chỉ định các trường không ràng buộc một cách rõ ràng.
@Embedded chỉ định các thuộc tính của lớp hoặc một thực thể có thể hiện giá trị của một lớp có
thể nhúng.
@Id chỉ định thuộc tính, sử dụng cho danh tính (khóa chính của bảng) của lớp.
@GeneratedValue chỉ định, cách thuộc tính nhận dạng có thể được khởi tạo, chẳng hạn như Tự động,
thủ công hoặc giá trị được lấy từ bảng trình tự.
@Transient chỉ định thuộc tính không liên tục, tức là giá trị không bao giờ được lưu trữ trong cơ
sở dữ liệu.
@Column chỉ định cột hoặc thuộc tính cho thuộc tính kiên trì.
@SequenceGenerator xác định giá trị cho thuộc tính được chỉ định trong chú thích @GeneratedValue. Nó
tạo ra một chuỗi.
@TableGenerator chỉ định trình tạo giá trị cho thuộc tính được chỉ định trong chú thích
@GeneratedValue. Nó tạo ra một bảng để tạo giá trị.
@AccessType để đặt loại truy cập. Nếu bạn đặt @AccessType (FIELD) thì quyền truy
cập thông thường của Trường sẽ xảy ra. Nếu bạn đặt @AccessType
(THUỘC TÍNH) thì việc đánh giá Thuộc tính khôn ngoan sẽ xảy ra.
@JoinColumn chỉ định một liên kết thực thể hoặc tập hợp thực thể. Điều này được
sử dụng trong các mối liên hệ nhiều-một và một-nhiều.
@UniqueConstraint chỉ định trường, ràng buộc duy nhất cho bảng chính hoặc phụ.
@NamedQuery chỉ định một Truy vấn sử dụng tên tĩnh.
@ColumnResult tham chiếu đến tên của một cột trong truy vấn SQL bằng mệnh đề select.
@ManyToMany xác định mối quan hệ nhiều-nhiều giữa các Bảng nối.
@ManyToOne xác định mối quan hệ nhiều-một giữa các Bảng nối.
@OneToMany xác định mối quan hệ một-nhiều giữa các Bảng nối.
@OneToOne xác định mối quan hệ một-một giữa các Bảng nối.
@NamedQueries chỉ định danh sách các truy vấn được đặt tên.

---

### Slide 16-22: Quan hệ
- **@OneToOne**: Nhân viên ↔ Địa chỉ  
- **@OneToMany/@ManyToOne**: Giỏ hàng ↔ Sản phẩm  
- **@ManyToMany**: Sinh viên ↔ Khóa học (dùng bảng trung gian student_course, CompositeKey)

---

### Slide 23: CascadeType
CASCADE
MỘT TẢ
ALL
Tương ứng với tất cả các loại cascade. cascade={DETACH, MERGE, PERSIST, REFRESH, REMOVE}
DETACH
Nếu đối tượng cha bị detached khỏi persistence context thì các đối tượng tham chiếu tới nó cũng bị detached.
MERGE
Nếu đối tượng cha được merged vào persistence context, thì các đối tượng tham chiếu tới nó cũng được merged.
PERSIST
Nếu đối tượng cha được persisted vào persistence context, thì các đối tượng tham chiếu tới nó cũng được persisted.
REFRESH
Nếu đối tượng cha được refreshed ở persistence context hiện tại, thì các đối tượng tham chiếu tới nó cũng được refreshed.
REMOVE
Nếu đối tượng cha bị removed khỏi persistence context, thì các đối tượng tham chiếu tới nó cũng được removed.

---

### Slide 24: FetchType
- EAGER: lấy dữ liệu chủ động  
- LAZY: lấy khi được truy cập  
- Mặc định:  
  - OneToMany: LAZY  
  - ManyToOne: EAGER  
  - ManyToMany: LAZY  
  - OneToOne: EAGER

---

### Slide 25: Khai báo thư viện Pom.xml
```xml
<dependency>
 <groupId>org.hibernate</groupId>
 <artifactId>hibernate-core</artifactId>
 <version>6.6.1.Final</version>
</dependency>
<dependency>
 <groupId>org.hibernate</groupId>
 <artifactId>hibernate-validator</artifactId>
 <version>8.0.1.Final</version>
</dependency>
<dependency>
 <groupId>jakarta.validation</groupId>
 <artifactId>jakarta.validation-api</artifactId>
 <version>3.1.0</version>
</dependency>
```

---

### Slide 26: ORM – Object Relation Mapping
- Entity Class ↔ Table  
- Field ↔ Column  
- Association ↔ Relationship  
- Entity Class phải tuân thủ JavaBean:
 Public class
 Constructor mặc định (không có tham số)
 Getters/Setters

---

### Slide 27: Lớp thực thể
```java
@Entity
@Table(name="users")
public class User {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private int id;
 @NotNull(message="Không được bỏ trống")
 private String username;
 @Column(name="fname")
 private String fullname;
 @Column(name="password")
 private String password;
 @Column(name="roleid")
 private int roleid;
}
```
Các @Annotation ánh xạ đã sử dụng
• @Entity: đây là lớp thực thể (Entity Class)
• @Table: Lớp thực thể ánh xạ với bảng
• @Column: trường ánh xạ với cột
• @Id: trường ánh xạ với cột khóa chính

---

### Slide 28-29: Cấu hình persistence.xml
SQL Server và MySQL cấu hình chi tiết (url, driver, user, password, dialect, show_sql...)
<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence
https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"
version="3.0">
<persistence-unit name="dataSource">
<class>vn.iotstar.entity.Category</class>
<properties>
<property name="jakarta.persistence.jdbc.url"
value="jdbc:sqlserver://localhost:1433;databaseName=jpast4" />
<property name="jakarta.persistence.jdbc.driver"
value="com.microsoft.sqlserver.jdbc.SQLServerDriver" />
<property name="jakarta.persistence.jdbc.user" value="sa" />
<property name="jakarta.persistence.jdbc.password" value="1234567@a$" />
<property name="hibernate.show_sql" value="true" />
<property name="hibernate.format_sql" value="true" />
<property name="hibernate.hbm2ddl.auto" value="update" />
<property name="hibernate.dialect"
value="org.hibernate.dialect.SQLServer2012Dialect"/>
</properties>
</persistence-unit>
</persistence>

<persistence-unit name="dataSource">
<class>vn.iotstar.entity.Category</class>
<class>vn.iotstar.entity.Favorite</class>
<class>vn.iotstar.entity.Share</class>
<class>vn.iotstar.entity.User</class>
<class>vn.iotstar.entity.Video</class>
<properties>
<property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
<property name="jakarta.persistence.jdbc.url"
value="jdbc:mysql://localhost:3306/servletjpa" />
<property name="jakarta.persistence.jdbc.user" value="root" />
<property name="jakarta.persistence.jdbc.password" value="1234567@a$" />
<property name="jakarta.persistence.schema-generation.database.action" value="create" />
<property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" />
<property name="hibernate.show_sql" value="true" />
<property name="hibernate.format_sql" value="true" />
</properties>
</persistence-unit>
---

### Slide 30-31: Lập trình JPA
1. Cấu hình ban đầu

persistence.xml: file cấu hình, trong đó định nghĩa:

Tên persistence unit (tập hợp cài đặt kết nối DB).

Thông tin driver, URL, username, password.

Các entity (class ánh xạ sang bảng CSDL).

2. Khởi tạo EntityManager

Persistence: Lớp tiện ích, dùng để tạo ra EntityManagerFactory.

createEntityManagerFactory() → Tạo EntityManagerFactory (nhà máy sinh EntityManager).

EntityManagerFactory: Tạo ra EntityManager.

createEntityManager() → Lấy ra EntityManager.

👉 EntityManager chính là đối tượng trung tâm, cho phép:

Quản lý entity (find, persist, remove…).

Tạo query để lấy dữ liệu.

Quản lý transaction (giao dịch).

3. Thao tác với EntityManager
a) Truy vấn dữ liệu

createQuery() → Tạo ra TypedQuery<T>.

TypedQuery<T> có các phương thức:

getResultList() → Trả về nhiều kết quả.

getSingleResult() → Trả về 1 kết quả duy nhất.

getParameter() → Gắn tham số vào query.

getFirstResult() / getMaxResult() → Giới hạn kết quả.

b) Quản lý entity

find() → Tìm entity theo khóa chính.

refresh() → Cập nhật lại dữ liệu từ DB.

persist() → Thêm mới entity vào DB.

merge() → Cập nhật entity đã có.

remove() → Xóa entity.

clear() → Xóa cache của EntityManager.

close() → Đóng EntityManager.

4. Giao dịch (Transaction)

getTransaction() → Lấy ra EntityTransaction.

EntityTransaction có các thao tác:

begin() → Bắt đầu giao dịch.

commit() → Xác nhận (lưu thay đổi xuống DB).

rollback() → Hủy giao dịch (quay lại trạng thái trước đó).

Tóm tắt dễ hiểu

persistence.xml: cấu hình.

EntityManager: "cầu nối" giữa Java object và database.

TypedQuery: để SELECT dữ liệu.

EntityManager (persist, merge, remove): để INSERT, UPDATE, DELETE.

EntityTransaction: đảm bảo các thao tác được thực hiện an toàn trong một giao dịch.

👉 Nói ngắn gọn:

Khởi tạo EntityManager.

Bắt đầu transaction.

Thao tác (persist, merge, remove, query…).

Commit hoặc rollback.

Đóng EntityManager.

Ví dụ thao tác EntityManager:
```java
EntityManagerFactory factory = Persistence.createEntityManagerFactory("dataSource");
EntityManager enma = factory.createEntityManager();
enma.getTransaction().begin();
try {
  enma.persist(entity);
  enma.getTransaction().commit();
} catch(Exception e) {
  enma.getTransaction().rollback();
}
User user = enma.find(User.class,"Hữu Trung");
enma.close();
```

---

### Slide 32-33: JPQL
- `SELECT o FROM User o`  
- `getResultList()`, `getSingleResult()`  
- Tham số: `:role`, `?0`  
- Phân trang: `setFirstResult()`, `setMaxResult()`

---

### Slide 34-36: Query Creation
 Cơ chế giúp chúng ta tạo ra các câu Query mà không cần viết thêm code. Cơ chế này xây
dựng Query từ tên của method.
 Khi chúng ta đặt tên method là: findByName(int name) thì sẽ tự định nghĩa câu Query
cho method này ứng với biến private String name, bằng cách xử lý tên method. Vậy là
chúng ta đã có thể truy vấn dữ liệu mà chỉ mất thêm 1 dòng code.
 Cơ chế xây dựng Query từ tên method này giúp chúng ta tiết kiệm thời gian với những
query có logic đơn giản, và cũng đặc biệt hữu ích là nó giống ngôn ngữ con người
thường nói hơn là SQL. (human-readable).

- Method naming convention: `findBy...`, `readBy...`, `countBy...`  
- Hỗ trợ kết hợp And/Or, IgnoreCase, OrderBy  
- Query trên thuộc tính con: `findByAddressZipCode`
- Quy tắc đặt tên method trong Spring JPA
❑ Trong một số trường hợp bạn có thể query bằng thuộc tính con
❑ Ví dụ: Đối tượng Person có thuộc tính là Address và trong Address lại có ZipCode
// person.address.zipCode
List<Person> findByAddressZipCode(ZipCode zipCode)
---

### Slide 37-38: @Query
- JPQL: `@Query("select u from User u where u.emailAddress = ?1")`  
- Native SQL: `@Query(value="select * from User u where u.email_address=?1", nativeQuery=true)`  
- Tham số đặt tên: `:status`, `:name`

---

### Slide 39-42: Criteria API
Ví dụ:
```java
CriteriaBuilder cb = em.getCriteriaBuilder();
CriteriaQuery<Office> q = cb.createQuery(Office.class);
Root<Office> c = q.from(Office.class);
q.select(c);
```
Ứng dụng: xây dựng linh hoạt, query động, theo dõi lỗi dễ hơn JPQL.

---

### Slide 43-45: Bài tập CRUD Category
- Chức năng: Find, Edit, Create, Update, Delete  
- Entity: Category (cate_id, cate_name, icons)  
- DAO, Servlet, JPAConfig  
- JSP giao diện  
- Các bước: Cài đặt Hibernate, cấu hình pom.xml, tạo DB, entity, DAO, Controller

