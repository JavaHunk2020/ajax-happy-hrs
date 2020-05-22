package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sql.conn.AjaxResponse;
import com.sql.conn.KillerTid;
import com.sql.conn.SQLConnUtil;

@WebServlet("/unlockBlockTime")
public class UnlockBlockTimeServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// {
		// { "tid":"092092"
		// }
		// }
		Gson gson = new Gson();
		String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		KillerTid jsonTid = gson.fromJson(jsonData, KillerTid.class);
		int tid = jsonTid.getTid();
		try {

			Connection connection = SQLConnUtil.getConnection();
			String sql = "update open_time_tbl set active='Yes' where tid = " + tid;
			// compiling the query
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();

			String nosql = "update open_time_tbl set active='No' where tid <> " + tid;
			pstmt = connection.prepareStatement(nosql);
			pstmt.executeUpdate();

			
			//Below code is used to generate json response!!!!!!!!!!!!!!!!!!!!!!!
			
			AjaxResponse ajaxResponse = new AjaxResponse();
			ajaxResponse.setStatus("success");
			ajaxResponse.setMessage("Hey , your record is updated!!!!!!!!!!!!!!");
   
			 //Below code is used to generate json response!!!!!!!!!!!!!!!!!!!!!!!
			String jsonString = gson.toJson(ajaxResponse); // converts to json
			
			resp.setContentType("application/json");
			PrintWriter body = resp.getWriter();
			// Writing data into response
			body.println(jsonString);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
