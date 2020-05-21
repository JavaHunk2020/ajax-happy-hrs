package com.sql.conn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sql.dao.entity.UserEntity;

//Every servlet have a unique
@WebServlet("/mathieu")
public class UserJSONServlet  extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid=req.getParameter("uid");
		/*UserEntity entity=new UserEntity();
		entity.setUid(Integer.parseInt(uid));
		entity.setEmail("mathieu@gmail.com");
		entity.setImage("coming");
		entity.setMobile("23432432");
		entity.setName("Maks");
		entity.setPassword("Cool!");
		entity.setRole("Admin");
		entity.setSalutation("Mr.");
		entity.setUserid("cool28237373");*/
		
		UserEntity entity=new UserEntity();
		  try {
          	//Fetching all the rows no where class
      		   String sql="select uid,userid,password,name,email,mobile,salutation,image,createdate,role from users_tbl where uid = "+Integer.parseInt(uid);
      		   Connection connection=SQLConnUtil.getConnection();
				//compiling the query
				PreparedStatement pstmt=connection.prepareStatement(sql);
				//fire the  query
				ResultSet rs=pstmt.executeQuery();
				//ResultSet has multiple records
				if(rs.next()) {
					entity.setUid(rs.getInt(1));
					entity.setUserid(rs.getString(2));
					entity.setPassword(rs.getString(3));
					entity.setName(rs.getString(4));
					entity.setEmail(rs.getString(5));
					entity.setMobile(rs.getString(6));
					entity.setSalutation(rs.getString(7));
					entity.setImage(rs.getString(8));
					entity.setCreateDate(rs.getTimestamp(9));
					entity.setRole(rs.getString(10));
				}
		  }catch (Exception e) {
                e.printStackTrace();
    		}	
		
		Gson gson=new Gson();
		String jsonString = gson.toJson(entity); // converts to json
		//setting the content type
		resp.setContentType("application/json");
		 PrintWriter body=resp.getWriter();
		 //Writing data into response
		 body.println(jsonString);
		
	}

}
