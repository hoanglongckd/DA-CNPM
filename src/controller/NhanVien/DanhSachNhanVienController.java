package controller.NhanVien;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import model.bean.NhanVien.NhanVienBEAN;
import model.bo.NhanVien.DanhSachNhanVienBO;
import model.bo.NhanVien.ThemNhanVienBO;
import model.dao.NhanVien.ThemNhanVienDAO;

/**
 * Servlet implementation class AddBaoDuongXeController
 */
@WebServlet("/danh-sach-nhan-vien")
public class DanhSachNhanVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DanhSachNhanVienController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DanhSachNhanVienBO danhSachNhanVienBO = new DanhSachNhanVienBO();

		ArrayList<NhanVienBEAN> listNhanVien = DanhSachNhanVienBO.getDanhSachNhanVien();
		request.setAttribute("listNhanVien", listNhanVien);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/NhanVien/danh-sach-nhan-vien.jsp");
		rd.forward(request, response);
	}

}
