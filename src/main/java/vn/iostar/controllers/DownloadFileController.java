package vn.iostar.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.utils.Constant;

@WebServlet(urlPatterns = "/uploads/*")
public class DownloadFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getPathInfo().substring(1); // Bỏ dấu / đầu
		File file = new File(Constant.DIR + File.separator + fileName);
		
		resp.setContentType(getServletContext().getMimeType(file.getName()));
		resp.setContentLength((int) file.length());
		
		try (FileInputStream in = new FileInputStream(file)) {
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0) {
				resp.getOutputStream().write(buffer, 0, length);
			}
		}
	}
}
