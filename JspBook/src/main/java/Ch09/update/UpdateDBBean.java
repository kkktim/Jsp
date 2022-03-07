package Ch09.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UpdateDBBean {
	private static UpdateDBBean instance = new UpdateDBBean();
	
	public UpdateDBBean() {}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsptest");
		return ds.getConnection();
	}
	
	//B_Member ���̺��� ������ ��ֳ���, cryptProcessList.jsp ���������� ���
	public List<UpdateDataBean> getMembers() throws Exception {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<UpdateDataBean> memberList = null;
		int x = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT COUNT(*) FROM B_Member;");
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				memberList = new ArrayList<UpdateDataBean>(x);
				do{
					UpdateDataBean member = new UpdateDataBean();
					member.setId(rs.getString("id"));
					member.setPasswd(rs.getString("passwd"));
					memberList.add(member);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)
				try{rs.close();}catch(SQLException sqle){}
			if(psmt != null)
				try{psmt.close();}catch(SQLException sqle){}
			if(conn != null)
				try{conn.close();}catch(SQLException sqle){}
		}
		return memberList;
	}
	
	//B_Member ���̺��� ��й�ȣ�� �ϰ������� ��ȣȭ�ؼ� �����ϸ�, cryptProcess.jsp���� ���
}
