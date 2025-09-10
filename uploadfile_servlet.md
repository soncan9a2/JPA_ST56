Slide 1

WEB

PR33

0479

KHOA CÔNG NGHỆ THÔNG TIN
BỘ MÔN CÔNG NGHỆ PHẦN MỀM

LẬP TRÌNH WEB (WEBPR330479)

THS. NGUYỄN HỮU TRUNG

Upload File

trong Java Servlet

---

Slide 2: Thông tin Giảng viên

ThS. Nguyễn Hữu Trung

- Ths. Nguyễn Hữu Trung
- Khoa Công Nghệ Thông Tin
- Trường Đại học Sư Phạm Kỹ Thuật TP.HCM
- 090.861.7108
- trungnh@hcmute.edu.vn
- https://www.youtube.com/@baigiai

---

Slide 3: Nội dung

- Upload file lên server:
  - Dùng @MultipartConfig
  - Dùng thư viện Commons FileUpload
- Upload file lên CloudDinary

---

Slide 4: Upload file lên server dùng @MultipartConfig

@MultipartConfig() được sử dụng để khai báo servlet 3.0 xử lý upload file. Annotation này có 4 tham số:

- fileSizeThreshold: kích thước giữ trên bộ nhớ, nếu vượt quá sẽ lưu vào đĩa, mặc định là 0 (không giữ trên bộ nhớ).
- maxFileSize: kích thước tối đa cho phép upload, mặc định là -1L (không giới hạn).
- maxRequestSize: Tổng kích thước tối đa, mặc định là -1L (không giới hạn).
- Location: thư mục chứa file upload, mặc định là “” (thư mục hiện hành của web server).

---

Slide 5: Upload file lên server dùng @MultipartConfig

Part là lớp mô tả file upload. Đọc file upload bởi 2 phương thức:

- Part part = request.getPart(name): lấy file theo tên Field.
- Collection<Part> parts = request.getParts(): lấy tất cả file upload.

Các phương thức của Part:

- getSubmittedFileName(): String : lấy file gốc
- write(String): lưu file upload vào đường dẫn mới
- getContentType(): String : lấy kiểu file
- getSize(): long: lấy kích thước file
- getInputStream():InputStream : lấy luồng dữ liệu vào từ file upload.

---

Slide 6: Form upload file

```html
<form method="post"
action="${pageContext.request.contextPath}/multiPartServlet" enctype="multipart/form-data">
       
        Select file to upload:
        <br />
        <input type="file" name="multiPartServlet"  />
        <br />
        
        Name:
        <br />
      <input type="text" name="name" size="100" />
        <br />
        <br />
        <input type="submit" value="Upload" />
    </form>
```

---

Slide 7: Upload file – Servlet

```java
@WebServlet("/uploadmulti")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
       maxFileSize = 1024 * 1024 * 10, // 10MB
       maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadFileMultipart extends HttpServlet {

     public static final String SAVE_DIRECTORY = "uploads";

@Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

request.getRequestDispatcher("/uploadfilemulti.jsp").forward(request, response);
   }
```

---

Slide 8: Upload file – Servlet

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
IOException,SecurityException {
String uploadPath = UPLOAD_DIRECTORY; //upload vào thư mục bất kỳ
//String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//upload vào thư mục project
File uploadDir = new File(uploadPath);
if (!uploadDir.exists())
 uploadDir.mkdir();
try {
String fileName = "";
for (Part part : request.getParts()) {

fileName = getFileName(part);
part.write(uploadPath + fileName);

}
 request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
} catch (FileNotFoundException fne) {
 request.setAttribute("message", "There was an error: " + fne.getMessage());
}
 getServletContext().getRequestDispatcher("/views/result.jsp").forward(request, response);
}
}
```

---

Slide 9: Upload file – Servlet

```java
private String getFileName(Part part) {
for (String content : part.getHeader("content-
disposition").split(";")) {

if (content.trim().startsWith("filename"))
return content.substring(content.indexOf("=") + 2,
content.length() - 1);

}
return Constants.DEFAULT_FILENAME;
}
```

---

Slide 10: Upload file lên server dùng thư viện Commons FileUpload

Bước 1: Tải thư viện common-io và common-fileupload trên Maven. Vào google gõ “common file upload maven” để lấy dependency Apache Commons FileUpload về bỏ vào file pom.xml

```xml
<!-- File Uploading -->
<dependency>
<groupId>org.apache.commons</groupId>
<artifactId>commons-fileupload2-jakarta-servlet6</artifactId>
<version>2.0.0-M2</version>
</dependency>
```

---

Slide 11: Form upload file

```html
<form method="post"
action="${pageContext.request.contextPath}/uploadFile"
enctype="multipart/form-data">
       
        Select file to upload:
        <br />
        <input type="file" name="uploadFile"  />
        <br />
        
        Name:
        <br />
      <input type="text" name="name" size="100" />
        <br />
        <br />
        <input type="submit" value="Upload" />
    </form>
```

---

Slide 12: Bước 3: Viết Servlet HomeController.java

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
IOException {
if (JakartaServletFileUpload.isMultipartContent(request)) {
DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
upload.setFileSizeMax(MAX_FILE_SIZE);
upload.setSizeMax(MAX_REQUEST_SIZE);
String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
File uploadDir = new File(uploadPath);
if (!uploadDir.exists()) {
uploadDir.mkdir();
}
try {
List<FileItem> formItems = upload.parseRequest(request);
if (formItems != null && formItems.size() > 0) {
for (FileItem item : formItems) {
if (!item.isFormField()) {
String fileName = new File(item.getName()).getName();
item.write(Path.of(uploadPath, fileName));
request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
}
}
}
} catch (Exception ex) {
request.setAttribute("message", "There was an error: " + ex.getMessage());
}
getServletContext().getRequestDispatcher("/views/result.jsp").forward(request, response);
}
}
```

