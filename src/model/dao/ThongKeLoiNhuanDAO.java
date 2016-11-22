package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.ThongKeLoiNhuanBEAN;
import utils.SQLServerConnUtils;

public class ThongKeLoiNhuanDAO {
	Connection conn = SQLServerConnUtils.getSQLServerConnection();
	ResultSet rs;
	Statement st;
	
	public ArrayList<ThongKeLoiNhuanBEAN> thongKeTheoThang(String year, String month) {
		ArrayList<ThongKeLoiNhuanBEAN> lists = new ArrayList<>();
		try {
			String sql = "SELECT * FROM "
						+ "(SELECT XE.BienSoXe as Xe1, SUM(GIATIEN.ThanhTien) as Cost "
						+ "FROM XE JOIN PHANCONGTX "
						+ "ON XE.id = PHANCONGTX.idXe "
						+ "JOIN DIEUPHOI "
						+ "ON PHANCONGTX.id = DIEUPHOI.idPhanCong "
						+ "JOIN GIATIEN "
						+ "ON DIEUPHOI.id = GIATIEN.idDieuPhoi "
						+ "WHERE YEAR(DIEUPHOI.ThoiGianKetThuc) = ? AND MONTH(DIEUPHOI.ThoiGianKetThuc) = ? "
						+ "GROUP BY Xe.BienSoXe) as i "
						+ "FULL JOIN "
						+ "(SELECT XE.BienSoXe as Xe2, SUM(BAODUONGXE.SoTien) as Fee "
						+ "FROM XE JOIN BAODUONGXE "
						+ "ON XE.id = BAODUONGXE.idXe "
						+ "WHERE YEAR(BAODUONGXE.NgayBaoDuong) = ? AND MONTH(BAODUONGXE.NgayBaoDuong) = ? "
						+ "GROUP BY XE.BienSoXe) as j "
						+ "ON i.Xe1 = j.Xe2";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, year);
			ps.setString(2, month);
			ps.setString(3, year);
			ps.setString(4, month);
			rs = ps.executeQuery();
			while(rs.next()) {
				if (rs.getString("Xe1") != null) {
					ThongKeLoiNhuanBEAN item = new ThongKeLoiNhuanBEAN(
							rs.getString("Xe1"), 
							rs.getLong("Cost"), 
							rs.getLong("Fee"));
					lists.add(item);
				} else {
					ThongKeLoiNhuanBEAN item = new ThongKeLoiNhuanBEAN(
							rs.getString("Xe2"), 
							rs.getLong("Cost"), 
							rs.getLong("Fee"));
					lists.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}
	
	public ArrayList<ThongKeLoiNhuanBEAN> thongKeTheoNam (String year) {
		ArrayList<ThongKeLoiNhuanBEAN> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM "
						+ "(SELECT XE.BienSoXe as Xe1, SUM(GIATIEN.ThanhTien) as Cost "
						+ "FROM XE JOIN PHANCONGTX "
						+ "ON XE.id = PHANCONGTX.idXe "
						+ "JOIN DIEUPHOI "
						+ "ON PHANCONGTX.id = DIEUPHOI.idPhanCong "
						+ "JOIN GIATIEN "
						+ "ON DIEUPHOI.id = GIATIEN.idDieuPhoi "
						+ "WHERE YEAR(DIEUPHOI.ThoiGianKetThuc) = ? "
						+ "GROUP BY Xe.BienSoXe) as i "
						+ "FULL JOIN "
						+ "(SELECT XE.BienSoXe as Xe2, SUM(BAODUONGXE.SoTien) as Fee "
						+ "FROM XE JOIN BAODUONGXE "
						+ "ON XE.id = BAODUONGXE.idXe "
						+ "WHERE YEAR(BAODUONGXE.NgayBaoDuong) = ? "
						+ "GROUP BY XE.BienSoXe) as j "
						+ "ON i.Xe1 = j.Xe2";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, year);
			ps.setString(2, year);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("Xe1") != null) {
					ThongKeLoiNhuanBEAN item = new ThongKeLoiNhuanBEAN(
							rs.getString("Xe1"), 
							rs.getLong("Cost"), 
							rs.getLong("Fee"));
					list.add(item);
				} else {
					ThongKeLoiNhuanBEAN item = new ThongKeLoiNhuanBEAN(
							rs.getString("Xe2"), 
							rs.getLong("Cost"), 
							rs.getLong("Fee"));
					list.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
