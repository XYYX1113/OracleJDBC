package demo.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;


import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import org.junit.jupiter.api.Test;

public class TestCursor {
	
	/*
	 create or replace 
	PACKAGE MYPACKAGE AS
  	TYPE EMPCURSOR IS REF CURSOR;
  	PROCEDURE queryList (dno in number,empList out EMPCURSOR);
	 */
	
	@Test
	public void testProcedure() {
		String sql ="{call MYPACKAGE.queryList(?,?)}";
		Connection conn=null;
		CallableStatement call=null;
		ResultSet rs =null;
		try {
			conn=JDBCUtil.getConnect();
			call=conn.prepareCall(sql);
			//给输入参数赋值
			call.setInt(1, 30);
			//申明输出参数
			call.registerOutParameter(2, OracleTypes.CURSOR);
			//执行调用
			call.execute();
			//取数结果
			rs = ((OracleCallableStatement)call).getCursor(2);
			 while(rs.next()) {
				 System.out.println(rs.getInt("empno"));
			 }
//			System.out.println(comm);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, call, null);
		}
	}

}
