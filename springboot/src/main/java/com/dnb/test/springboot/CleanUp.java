package com.dnb.test.springboot;

import java.util.ArrayList;

import com.dnb.test.springboot.connection.Connection;
import com.jcraft.jsch.Session;

public class CleanUp {
	public void cleanup(ArrayList<String> paths){
		System.out.println("cleanUP : starting clean up");
		Connection conn = new Connection();
		Session session =conn.getConnection();
		conn.cleanup(session,paths);
		conn.disconnect(session);
		System.out.println("cleanUP : completed clean up");
	}

}
