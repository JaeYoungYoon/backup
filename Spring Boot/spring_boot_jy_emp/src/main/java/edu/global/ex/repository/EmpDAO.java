package edu.global.ex.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.global.ex.vo.EmpVO;

@Repository
public class EmpDAO {

	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "scott";
	private String upw = "tiger";

	public EmpDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<EmpVO> empSelect() {
		List<EmpVO> emps = new ArrayList<EmpVO>();

		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try {
			String sql = "Select * from emp";

			con = DriverManager.getConnection(url, uid, upw);
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
			
				int empno = resultSet.getInt("empno");
				String ename = resultSet.getString("ename");
				String job = resultSet.getString("job");
				int mgr = resultSet.getInt("mgr");
				Date hiredate = resultSet.getDate("hiredate");
				int sal = resultSet.getInt("sal");
				int comm = resultSet.getInt("comm");
				int deptno = resultSet.getInt("deptno");

				EmpVO vo = new EmpVO(empno, ename, job, mgr, hiredate, sal, comm, deptno);
				emps.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();

				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return emps;
	}

	public EmpVO empView(int bno) {
		EmpVO emp = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			String query = "select * from emp where empno = ?";

			connection = DriverManager.getConnection(url, uid, upw);
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, Integer.valueOf(bno));
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int empno = resultSet.getInt("empno");
				String ename = resultSet.getString("ename");
				String job = resultSet.getString("job");
				int mgr = resultSet.getInt("mgr");
				Date hiredate = resultSet.getDate("hiredate");
				int sal = resultSet.getInt("sal");
				int comm = resultSet.getInt("comm");
				int deptno = resultSet.getInt("deptno");
				

				emp = new EmpVO(empno, ename, job, mgr, hiredate, sal, comm, deptno);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (resultSet != null)
					resultSet.close();

				if (preparedStatement != null)
					preparedStatement.close();

				if (connection != null)
					connection.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return emp;
	}

}