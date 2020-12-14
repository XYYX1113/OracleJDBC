package demo.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;


import oracle.jdbc.OracleTypes;
import org.junit.jupiter.api.Test;

public class TestProcedure {
	
	/*
	 * 
	 * create or replace 
PROCEDURE  queryNameAndJobAndSal(eno in number,
		pename out varchar2,
		psal out number,
		pjob out varchar2)
	 */
	
	@Test
	public void testProcedure() {
		String sql ="{call queryNameAndJobAndSal(?,?,?,?)}";
		Connection conn=null;
		CallableStatement call=null;
		try {
			conn=JDBCUtil.getConnect();
			call=conn.prepareCall(sql);
			
			//给输入参数赋值
			call.setInt(1, 3333);
			//申明输出参数
			call.registerOutParameter(2, OracleTypes.VARCHAR);
			call.registerOutParameter(3, OracleTypes.NUMBER);
			call.registerOutParameter(4, OracleTypes.VARCHAR);
			
			//执行调用
			call.execute();
			//取数结果
			String name=call.getString(2);
			String sal=call.getString(3);
			String job=call.getString(4);
			System.out.println(name);
			System.out.println(sal);
			System.out.println(job);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, call, null);
		}
	}

}
