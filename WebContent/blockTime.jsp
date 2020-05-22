<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Block Times</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style type="text/css">

.semere {
	display: block;
	margin-left: auto;
	margin-right: auto;
}
</style>

</head>
<body>
 <header  style="height: 30px;background-color: #21c9ff;">
   <b style="margin-left: 50px;">Hello! Mr. ${sessionScope.pdata.name}  - Email  =${sessionScope.pdata.email} </b>
   </header>
    <div  class="container">
         <%@include file="acommon.jsp" %>
		
		<h4>Block Times Data</h4>        
		<input  id="temp"  type="text" name="temp" class="form-control" style="width: 50%;display: inline;">
		     <button type="button" class="btn btn-warning" style="margin-top: -7px;">GO</button>    
		      <button type="button" class="btn btn-primary" style="margin-top: -7px;">Clear</button>    
		<hr/>
  <table class="table table-bordered">
    <thead>
      <tr style="background-color: maroon;color: white;">
        <th>Tid</th>
        <th>Start Time</th>
        <th>End Time</th>
        <th>Active</th>
      </tr>
    </thead>
    <tbody>
    
    <c:forEach items="${blockTimeList}"  var="blockTime">
      <tr
            <c:if test="${blockTime.active=='Yes'}">
      style="background-color:#73b5fd;"
        </c:if>
        
          id="rowdata_${blockTime.tid}"
      >
        <td>${blockTime.tid}</td>
        <td>${blockTime.startTime}</td>
        <td>${blockTime.endTime}</td>
             <td>
             
             <c:if test="${blockTime.active=='No'}">
            <%-- <a  href="unlockBlockTime?tid=${blockTime.tid}"> --%> 
           <a href="javascript:unlockBlockTime(${blockTime.tid});"> 
         <img  id="imageicon_${blockTime.tid}"   src="img/locked.png"  style="height: 40px">
         </a>
         </c:if>
             
             <c:if test="${blockTime.active=='Yes'}">
              <a href="javascript:unlockBlockTime(${blockTime.tid});"> 
         	<img  id="imageicon_${blockTime.tid}"   src="img/unlocked.png"  style="height: 40px">
           </a>
         </c:if>
             
             </td>
         </td>
      </tr>
   </c:forEach>
      
    
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>

        <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </tbody>
  </table>
		
	</div>
	
	<script type="text/javascript">
		function unlockBlockTime(ptid){
			
			var obj ={tid:ptid};
			//Make AJAX call - METHOD=POST - Sending JSON Data
			//Accepting JSON data
			const options = {
					  method: 'POST',
					  body: JSON.stringify(obj),
					  headers: {
					    'Content-Type': 'application/json',
					    'Accept': 'application/json'
					  }
					};

			 //name of oauth - servlet
			 fetch('unlockBlockTime', options)
					  .then(res => res.json())
					  .then(data => {
						  	console.log(data);
						  //  $("#imageicon_"+ptid)= image object	
						  //jQuery
						  //This is for all the images which starts with imageicon
						  $("img[id^=imageicon]").attr("src","img/locked.png");
						  $("#imageicon_"+ptid).attr("src","img/unlocked.png");
						  $("tr[id^=rowdata]").css("background-color", "white");
						  $("#rowdata_"+ptid).css("background-color", "#73b5fd");
						  $("#temp").val(data.message+" for the id = "+ptid);
						  $("#temp").css("background-color", "#e8f3ff");
		   });
		}
	
	
	</script>
	
</body>
</html>