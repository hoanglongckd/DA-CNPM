package controller.BaoDuongXe;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.BaoDuongXeBEAN;
import model.bean.XeBEAN;
import model.bo.BaoDuongXeBO;

/**
 * Servlet implementation class AddBaoDuongXeController
 */
//@WebServlet("/them-xe-bao-duong")
public class ThemXeBaoDuongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThemXeBaoDuongController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<XeBEAN> listXe =  BaoDuongXeBO.lietKeXe();
		request.setAttribute("listXe", listXe);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/BaoDuongXe/them-xe-bao-duong.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int idXe = Integer.parseInt(request.getParameter("idCar"));
		String ngayBaoDuongHienTai = request.getParameter("current-date");
		String ngayBaoDuongTiepTheo = request.getParameter("next-date");
		long chiPhiBaoDuong = 0;
		try {
			chiPhiBaoDuong = Long.parseLong(request.getParameter("cost"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		String chiTiet = request.getParameter("detail");
		
		BaoDuongXeBEAN baoDuongXeBEAN = new BaoDuongXeBEAN();
		baoDuongXeBEAN.setIdXe(idXe);
		baoDuongXeBEAN.setNgayBaoDuong(ngayBaoDuongHienTai);
		baoDuongXeBEAN.setNgayBaoDuongTiepTheo(ngayBaoDuongTiepTheo);
		baoDuongXeBEAN.setSoTien(chiPhiBaoDuong);
		baoDuongXeBEAN.setChiTiet(chiTiet);
		
		if (BaoDuongXeBO.themXeBaoDuong(baoDuongXeBEAN)) {
			doGet(request, response);
		}
		else {
			System.out.println("Fail");
		}
		
		System.out.println(idXe + "|" + ngayBaoDuongHienTai + "|" + ngayBaoDuongTiepTheo + "|" + chiPhiBaoDuong + "|" + chiTiet);
	}

}
