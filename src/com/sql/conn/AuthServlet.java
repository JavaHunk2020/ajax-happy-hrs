package com.sql.conn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sql.dao.CubicDao;
import com.sql.dao.CubicDaoImpl;
import com.sql.dao.entity.UserEntity;


//This is unique name given to the servlet
@WebServlet("/oauth")
public class AuthServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	              //String username=req.getParameter("username");
	              //String password=req.getParameter("password");
		          //Reading jsondata coming from client and storing in string
		          //{"username":"javatech1000@gmail.com","password":"ting"}
		 		  Gson gson=new Gson();
	              String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
	              //JSON data into Java Object using GSON library
	              LoginRequest  loginRequest=gson.fromJson(json, LoginRequest.class);
	              
	              CubicDao cubicDao=new CubicDaoImpl();
	              UserEntity userEntity=cubicDao.authUser(loginRequest.getUsername(), loginRequest.getPassword());

	              resp.setContentType("application/json");
	              PrintWriter body=resp.getWriter();
	              String jsonString ="";
	              if(userEntity==null) {
	            	  AjaxResponse ajaxResponse=new AjaxResponse();
	            	  ajaxResponse.setStatus("fail");
	            	  ajaxResponse.setMessage("Hmmm , your usename and password are not correct!");
	            	  jsonString = gson.toJson(ajaxResponse); // converts to json
	            	  //{"status":"fail","message":"Hmmm , your usename and password are not correct!"}
	              }else {
	            	  jsonString = gson.toJson(userEntity); // converts to json
	  	          }
	         	 //Writing data into response
	  	    	 body.println(jsonString);
	}
}
