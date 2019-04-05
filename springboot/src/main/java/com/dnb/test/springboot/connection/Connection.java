package com.dnb.test.springboot.connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.opencsv.CSVReader;

@Component
public class Connection {
	
	/*@Value("${user}")
	public String user;
	@Value("${host}")
	public String host;
	@Value("${password}")
	public String password;*/
	/*@Value("${csvFilepath}")
	public String csvFilepath;*/
	private Session session;
	public String[] filePaths={"/dnbusr1/z-cc-dad/testDirectory"};
	
	@PostConstruct
	public Session getConnection(){
		System.out.println("connecting");
		String status="";
		System.out.println("test");
		try {
			java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
	    	String host="158.151.16.162";
	    	String user="z-cc-dad";
	    	String password="Comp!i@nce.123";
	    	JSch jsch = new JSch();
	    		session = jsch.getSession(user, host, 22);
				session.setPassword(password);
		    	session.setConfig(config);
		    	session.connect();
		 
		} catch (JSchException e) {
				
				e.printStackTrace();
			}
		
		System.out.println("connected");
		return session;
	}
	public static void disconnect(Session session){
		  session.disconnect();
		
	}
	public String box (Session session)
	{  
		System.out.println("in box");   
		String command="df -kh /dnbusr1";
	    String chkOut="";
	    String status = null;
	    int space1=0;
	    try{
	    	Channel channel=session.openChannel("exec");
	    	((ChannelExec)channel).setCommand(command);
	    	channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	    	
	        InputStream in=channel.getInputStream();
	        System.out.println(in);
	        channel.connect();
	       
	        byte[] tmp=new byte[1024];
	        while(true){
	          while(in.available()>0){
	            int i=in.read(tmp, 0, 1024);
	            chkOut=new String(tmp, 0, i);
	            
	            if(i<0)break;
	            System.out.print("Server "+new String(tmp, 0, i));
	           String space=new String(tmp, 0, i);
	           System.out.println("Space in server"+space.substring(space.length()-13,space.length()-11) );
	           space1=Integer.parseInt(space.substring(space.length()-13,space.length()-11));
	          }
	          if(channel.isClosed()){
	            System.out.println("exit-status: "+channel.getExitStatus());
	            break;
	          }
	          try{Thread.sleep(1000);}catch(Exception ee){}
	        }
	        channel.disconnect();
	        space1= 91;
	        if (space1 >90)
	        {
	        	status = "FULL";
	        }
        System.out.println("DONE");
               
    }catch(Exception e){
    	e.printStackTrace();
    }
	    return status;   
	  
	}
	public void cleanup(Session session,ArrayList<String> paths) 
	{	System.out.println("connection : cleanup");
		for (String path : paths) {
			String remoteFilePath ="/dnbusr1/z-cc-dad/testDirectory/testFile/*";
			//String remoteFilePath = path;
			String command = "find "+remoteFilePath+" -type f -name '*' -mtime +30 -delete";
			System.out.println("cmd1 :"+command);
				 try {
				    Channel channel = session.openChannel("exec");
					((ChannelExec)channel).setCommand(command);
			    	channel.setInputStream(null);
			        ((ChannelExec)channel).setErrStream(System.err);
			        channel.connect();
			        channel.disconnect();
			          
				} catch (JSchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
          
	}
	
	
}
