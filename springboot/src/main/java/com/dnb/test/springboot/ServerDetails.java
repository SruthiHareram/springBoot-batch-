package com.dnb.test.springboot;

import com.dnb.test.springboot.connection.Connection;
import com.jcraft.jsch.Session;

public class ServerDetails {
	public String getSpace(){
		System.out.println("in getSpace");
		Connection conn = new Connection();
		Session session = conn.getConnection();
		String status =conn.box(session); 
		conn.disconnect(session);
		System.out.println("out getSpace");
		return status;
		
	}
}
