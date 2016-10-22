package controller.NhanVien;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.NhanVienBEAN;
import model.bo.NhanVienBO;

/**
 * Servlet implementation class AddBaoDuongXeController
 */
@WebServlet("/them-nhan-vien")
public class AddNhanVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNhanVienController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/NhanVien/them-nhan-vien.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maNV = request.getParameter("ma-nhan-vien");
		String hoTen = request.getParameter("ho-va-ten");
		int chucVu = Integer.parseInt(request.getParameter("chuc-vu"));
		String ngaySinh = request.getParameter("ngay-sinh");
		int caLamViec = Integer.parseInt(request.getParameter("ca-lam-viec"));
		
	
		NhanVienBEAN nhanVienBEAN = new NhanVienBEAN();
		
		nhanVienBEAN.setMaNhanVien(maNV);
		nhanVienBEAN.setChucVu(chucVu);
		nhanVienBEAN.setHoTen(hoTen);
		nhanVienBEAN.setNgaySinh(ngaySinh);
		nhanVienBEAN.setCaLamViec(caLamViec);
		
		if (NhanVienBO.addNhanVien(nhanVienBEAN)) {
			doGet(request, response);
		}
		else {
			System.out.println("Fail");
		}
		System.out.println(maNV + "|" + hoTen + "|" + ngaySinh + "|" + chucVu + "|" + caLamViec);
	}

}
