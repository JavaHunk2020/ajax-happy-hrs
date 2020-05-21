package com.sql.conn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sql.dao.CubicDao;
import com.sql.dao.CubicDaoImpl;
import com.sql.dao.entity.BlockTimeEntity;


//This is unique name given to the servlet
@WebServlet("/findBlockTimes")
public class SearchJsonUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CubicDao cubicDao =new CubicDaoImpl();
		List<BlockTimeEntity>  blockTimeEntities=cubicDao.findBlockTimes();
		Gson gson=new Gson();
		String jsonString = gson.toJson(blockTimeEntities); // converts to json
		resp.setContentType("application/json");
		 PrintWriter body=resp.getWriter();
		 body.println(jsonString);
	}
}
