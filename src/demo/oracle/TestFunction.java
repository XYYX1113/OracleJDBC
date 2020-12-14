package demo.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;


import oracle.jdbc.OracleTypes;
import org.junit.jupiter.api.Test;

public class TestFunction {
	
	/*
	 * 
	 *create or replace 
		FUNCTION QUERYMONERY(eno in number)
		RETURN NUMBER
	 */
	
	@Test
	public void testProcedure() {
		String sql ="{?=call QUERYMONERY(?)}";
		Connection conn=null;
		CallableStatement call=null;
		try {
			conn=JDBCUtil.getConnect();
			call=conn.prepareCall(sql);
			//给输入参数赋值
			call.setInt(2, 3333);
			//申明输出参数
			call.registerOutParameter(1, OracleTypes.VARCHAR);
			//执行调用
			call.execute();
			//取数结果
			Double comm=call.getDouble(1);
			System.out.println(comm);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, call, null);
		}
	}

}
