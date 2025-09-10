HƯỚNG DẪN RESTFULL API TRÊN SERVLET JPA VÀ SPRING BOOT

1. HƯỚNG DẪN CRUD CATEGORY TRÊN SERVLET JPA VỚI THƯ VIỆN JACKSON

Bước 1: thêm thư viện

```xml
<!-- json -->
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-core</artifactId>
  <version>2.6.3</version>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.6.3</version>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-annotations</artifactId>
  <version>2.6.3</version>
</dependency>
<dependency>
  <groupId>org.codehaus.jackson</groupId>
  <artifactId>jackson-mapper-asl</artifactId>
  <version>1.9.13</version>
</dependency>
<dependency>
  <groupId>org.codehaus.jackson</groupId>
  <artifactId>jackson-core-asl</artifactId>
  <version>1.9.13</version>
</dependency>
<!--end json -->
```

Bước 2: Xây dựng Entity CATEGORY và PRODUCT

```java
package vn.iotstar.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Table(name="Category")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int CategoryID;
  @Column(columnDefinition = "nvarchar(200)")
  private String CategoryName;
  @Column(columnDefinition = "nvarchar(MAX)")
  private String icon;

  @JsonManagedReference
  @OneToMany(mappedBy="category", fetch=FetchType.EAGER)
  private List<Product> products;

  //getters, setters, constructor
}
```

```java
package vn.iotstar.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Product")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int ProductID;
  private int stoke;
  private int Amount;
  @Column(columnDefinition = "nvarchar(MAX)")
  private String Description;
  @Column(columnDefinition = "nvarchar(255)")
  private String ProductName;
  private String imageLink;
  private int SellerID;
  private int Price;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name="CategoryID")
  private Category category;

  //getters, setters, constructor
}
```

Bước 3: xây dựng tầng DAO

```java
package vn.iotstar.Dao;

import java.util.List;
import vn.iotstar.entity.Category;

public interface ICategoryDao {
  int count();
  List<Category> findByCategoryname(String catname);
  List<Category> findAll(int page, int pagesize);
  List<Category> findAll();
  Category findById(int categoryId);
  void delete(int cateid) throws Exception;
  void update(Category category);
  void insert(Category category);
}
```

```java
package vn.iotstar.Dao;

import java.util.List;
import javax.persistence.*;
import vn.iotstar.JPAConfig.JPAConfig;
import vn.iotstar.entity.Category;

public class CategoryDaoImpl implements ICategoryDao {
  @Override
  public void insert(Category category) {
    EntityManager enma = JPAConfig.getEntityManager();
    EntityTransaction trans = enma.getTransaction();
    try {
      trans.begin();
      enma.persist(category);
      trans.commit();
    } catch (Exception e) {
      e.printStackTrace();
      trans.rollback();
      throw e;
    } finally {
      enma.close();
    }
  }

  @Override
  public void update(Category category) {
    EntityManager enma = JPAConfig.getEntityManager();
    EntityTransaction trans = enma.getTransaction();
    try {
      trans.begin();
      enma.merge(category);
      trans.commit();
    } catch (Exception e) {
      e.printStackTrace();
      trans.rollback();
      throw e;
    } finally {
      enma.close();
    }
  }

  @Override
  public void delete(int cateid) throws Exception {
    EntityManager enma = JPAConfig.getEntityManager();
    EntityTransaction trans = enma.getTransaction();
    try {
      trans.begin();
      Category category = enma.find(Category.class, cateid);
      if(category != null) {
        enma.remove(category);
      } else {
        throw new Exception("Không tìm thấy");
      }
      trans.commit();
    } catch (Exception e) {
      e.printStackTrace();
      trans.rollback();
      throw e;
    } finally {
      enma.close();
    }
  }

  @Override
  public Category findById(int categoryId) {
    EntityManager enma = JPAConfig.getEntityManager();
    return enma.find(Category.class, categoryId);
  }

  @Override
  public List<Category> findAll() {
    EntityManager enma = JPAConfig.getEntityManager();
    TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
    return query.getResultList();
  }

  @Override
  public List<Category> findAll(int page, int pagesize) {
    EntityManager enma = JPAConfig.getEntityManager();
    TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
    query.setFirstResult(page * pagesize);
    query.setMaxResults(pagesize);
    return query.getResultList();
  }

  @Override
  public List<Category> findByCategoryname(String catname) {
    EntityManager enma = JPAConfig.getEntityManager();
    String jpql = "SELECT c FROM Category c WHERE c.catename like :catname";
    TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
    query.setParameter("catename", "%" + catname + "%");
    return query.getResultList();
  }

  @Override
  public int count() {
    EntityManager enma = JPAConfig.getEntityManager();
    String jpql = "SELECT count(c) FROM Category c";
    Query query = enma.createQuery(jpql);
    return ((Long) query.getSingleResult()).intValue();
  }
}
```

Bước 4: xây dựng tầng service

```java
package vn.iotstar.services;

import java.util.List;
import vn.iotstar.entity.Category;

public interface ICategoryService {
  void insert(Category category);
  void update(Category category);
  void delete(int cateid) throws Exception;
  Category findById(int cateid);
  List<Category> findAll();
  List<Category> findByCategoryname(String catname);
  List<Category> findAll(int page, int pagesize);
  int count();
}
```

```java
package vn.iotstar.services;

import java.util.List;
import vn.iotstar.Dao.CategoryDaoImpl;
import vn.iotstar.Dao.ICategoryDao;
import vn.iotstar.entity.Category;

public class CategoryServiceImpl implements ICategoryService {
  ICategoryDao categoryDao = new CategoryDaoImpl();

  @Override
  public void insert(Category category) {
    categoryDao.insert(category);
  }

  @Override
  public void update(Category category) {
    categoryDao.update(category);
  }

  @Override
  public void delete(int cateid) throws Exception {
    categoryDao.delete(cateid);
  }

  @Override
  public Category findById(int cateid) {
    return categoryDao.findById(cateid);
  }

  @Override
  public List<Category> findAll() {
    return categoryDao.findAll();
  }

  @Override
  public List<Category> findByCategoryname(String catname) {
    return categoryDao.findByCategoryname(catname);
  }

  @Override
  public List<Category> findAll(int page, int pagesize) {
    return categoryDao.findAll(page, pagesize);
  }

  @Override
  public int count() {
    return categoryDao.count();
  }
}
```

Bước 5: Viết hàm thư viện hỗ trợ chuyển đổi Object sang Json và ngược lại

```java
package vn.iotstar.util;

import java.io.BufferedReader;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
  private String value;

  public HttpUtil(String value) {
    this.value = value;
  }

  public <T> T toModel(Class<T> tClass) {
    try {
      return new ObjectMapper().readValue(value, tClass);
    } catch (Exception e) {
      System.out.print(e.getMessage());
    }
    return null;
  }

  public static HttpUtil of(BufferedReader reader) {
    StringBuilder sb = new StringBuilder();
    try {
      String line;
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      System.out.print(e.getMessage());
    }
    return new HttpUtil(sb.toString());
  }
}
```

```xml
<dependency>
  <groupId>commons-beanutils</groupId>
  <artifactId>commons-beanutils</artifactId>
  <version>1.9.4</version>
</dependency>
<dependency>
  <groupId>commons-io</groupId>
  <artifactId>commons-io</artifactId>
  <version>2.11.0</version>
</dependency>
```

```java
public class Constant {
  public static final String DIR = "E:\\uploads";
}
```

```java
package vn.iotstar.util;

import java.io.IOException;
import java.nio.file.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;

public class UploadUtils {
  public static String processUpload(String fieldName, HttpServletRequest req,
      String storeFolder, String storeFilename) throws IOException, ServletException {
    Part filePart = req.getPart(fieldName);
    if(filePart == null || filePart.getSize() == 0) {
      return "";
    }
    if(storeFolder == null) {
      storeFolder = "E:\\uploads";
    }
    if (storeFilename == null) {
      storeFilename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
    } else {
      storeFilename += "." + FilenameUtils.getExtension(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
    }
    Path uploadPath = Paths.get(storeFolder);
    if(!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }
    filePart.write(Paths.get(uploadPath.toString(), storeFilename).toString());
    return storeFilename;
  }

  public static String processUploadFolderWeb(String fieldName,
      HttpServletRequest req, String storeFolder, String storeFilename) throws IOException, ServletException {
    Part filePart = req.getPart(fieldName);
    if(filePart == null || filePart.getSize() == 0) {
      return "";
    }
    if(storeFolder == null) {
      storeFolder = "/uploads";
    }
    if (storeFilename == null) {
      storeFilename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
    } else {
      storeFilename += "." + FilenameUtils.getExtension(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
    }
    String uploadFolder = req.getServletContext().getRealPath(storeFolder);
    Path uploadPath = Paths.get(uploadFolder);
    if(!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }
    filePart.write(Paths.get(uploadPath.toString(), storeFilename).toString());
    return storeFilename;
  }
}
```

Bước 6: Viết Controller API

```java
package vn.iotstar.controllers.api;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.util.Constant;
import vn.iotstar.util.HttpUtil;
import vn.iotstar.util.UploadUtils;

@MultipartConfig
@WebServlet(urlPatterns = {"/api-admin-category"})
public class CategoryAPI extends HttpServlet {

  @Inject
  private ICategoryService categoryService;
  private static final long serialVersionUID = -915988021506484384L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    request.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    mapper.writeValue(response.getOutputStream(), categoryService.findAll());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    request.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    Category category = new Category();
    try {
      BeanUtils.populate(category, request.getParameterMap());
    } catch (IllegalAccessException | InvocationTargetException e)  {
      e.printStackTrace();
    }
    String fileName = "" + System.currentTimeMillis();
    category.setIcon(UploadUtils.processUpload("icon", request, Constant.DIR+"\\category\\", fileName));
    categoryService.insert(category);
    mapper.writeValue(response.getOutputStream(), category);
  }

  protected void doPut(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    request.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    Category category = new Category();
    try {
      BeanUtils.populate(category, request.getParameterMap());
    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
    Category oldcate = categoryService.findById(category.getCategoryID());
    if (request.getPart("icon").getSize() == 0) {
      category.setIcon(oldcate.getIcon());
    } else {
      if (oldcate.getIcon() != null) {
        String fileName = oldcate.getIcon();
        File file = new File(Constant.DIR+"\\category\\" + fileName);
        if (file.delete()) {
          System.out.println("Đã xóa thành công");
        } else {
          System.out.println(Constant.DIR+"\\category\\" + fileName);
        }
      }
      String fileName = "" + System.currentTimeMillis();
      category.setIcon(UploadUtils.processUpload("icon", request, Constant.DIR+"\\category\\", fileName));
    }
    categoryService.update(category);
    mapper.writeValue(response.getOutputStream(), category);
  }

  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    request.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    Category cateModel = HttpUtil.of(request.getReader()).toModel(Category.class);
    try {
      categoryService.delete(cateModel.getCategoryID());
    } catch (Exception e) {
      e.printStackTrace();
    }
    mapper.writeValue(response.getOutputStream(), "{Đã xóa thành công}");
  }
}
```

Bước 7: Kết quả
- GET
- POST
- PUT
- DELETE

---

2. HƯỚNG DẪN CRUD CATEGORY TRÊ

