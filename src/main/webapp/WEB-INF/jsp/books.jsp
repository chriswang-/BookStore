<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<style>

tbody th.OK {
  background-color: #a0ca8f;
}
tbody th.NOT_IN_STOCK{
  background-color: #d9534f;
}
tbody th.DOES_NOT_EXIST {
  background-color: #d9534f;
}


</style>

<script>

$('.custom-control-input').change(function() {   	
	var prcStr = $(this).data("price") + ""; //stringfiy.
	var prc = Number(prcStr.replace(/[^0-9\.]+/g,""));

	var tPrcStr = $('strong').text();
	var tPrc = Number(tPrcStr.replace(/[^0-9\.]+/g,""));

	if(!this.checked) {
		prc = prc * -1;
		$(this).next().val(false); //spring form bean
    }else{
		$(this).next().val(true); //spring form bean
    }	
	tPrc = tPrc + prc;
	$('strong').text(tPrc+'($)');
	$('strong + input').val(tPrc);

});



$("#buyForm button").click(function( event ) {
	$('#booksViews').hide();
    $("#LoadingImage").show();

	var jqxhr = $.ajax( {
           type: "POST",
           url: "books/user/buy",
           data: $("#buyForm").serialize()
         } )
	  .done(function(o) {
	   	    $("#LoadingImage").fadeOut(600, function(){
	            $('#booksViews').html(o).fadeIn('slow');
	 	    });      })
	  .fail(function(e) {
		  	console.log(e);
	  });
});	


</script>

<form id="buyForm">

<div class="container">

  				<sec:authorize access="isAuthenticated()">
  				
					<div class="row">			
					  	<div class="col-9">
							<button  class="btn btn-warning" type="button" type="submit">Buy</button>		
							<c:if test="${errExist ne null}">
							
								<c:if test="${errExist == true}">
										<span style="color:#7d1111" >
											 Failed purchase, Please check the book list below!
				  						</span> 
								</c:if>
								<c:if test="${errExist == false}">
										<span style="color:#3c763d" >
											 Purchase success!
				  						</span> 
								</c:if>
							
							</c:if>
					  	</div>
					  	
	  					<div class="col-3" style="line-height:38px;" >
	  						<div class="alert alert-success" style="padding:0rem 1.25rem;text-align:right;vertical-align:middle">
	  							<strong>${booksForm.totalAmount}($)</strong>
	  							<form:hidden path="booksForm.totalAmount" value="${booksForm.totalAmount}"/>
	  						</div> 
	
	  					</div>	
					</div>
					
				</sec:authorize>
				
				
				<div class="row">
							<table class="table table-hover">
		
								<thead>
									<tr>
										<th>#</th>
										<th>Title</th>
										<th>Author</th>
										<th>Price</th>
										<th>Quantity</th>
										<c:if test="${showMeTheStatus ne null}">
												<th>Status</th>
										</c:if>
									  	<sec:authorize access="isAuthenticated()">
										   <th>Select</th>
										</sec:authorize>

									</tr>
								</thead>
								<tbody>
									<c:forEach var = "book" varStatus="item" items = "${booksForm.books}">
									    <tr>
									         <td>${book.id}<form:hidden path="booksForm.books[${item.index}].id" value="${book.id}"/></td>						    
			             					 <td>${book.title}<form:hidden path="booksForm.books[${item.index}].title" value="${book.title}"/></td>
			               					 <td>${book.author}<form:hidden path="booksForm.books[${item.index}].author" value="${book.author}"/></td>
			               					 <td>${book.price}<form:hidden path="booksForm.books[${item.index}].price" value="${book.price}"/></td>
			               					 <td>${book.quantity}<form:hidden path="booksForm.books[${item.index}].quantity" value="${book.quantity}"/></td>
			               					 <c:if test="${showMeTheStatus ne null}">
												<th class="${book.status}" >${book.status}</th>
											 </c:if>
										  	 <sec:authorize access="isAuthenticated()">
											         <td>		         
														<div class="has-success" style="text-align:center">
														  <label class="custom-control custom-checkbox">
														    <input  value="true" type="checkbox" class="custom-control-input"  ${booksForm.books[item.index].select ? 'checked' : ''}  data-price="${book.price}"   >
														    <form:hidden path="booksForm.books[${item.index}].select" value="${booksForm.books[item.index].select}"/>
														    <span class="custom-control-indicator"></span>
														  </label>

														</div>								         
													</td>
											 </sec:authorize>

									    </tr>
									  </c:forEach>
								</tbody>
							</table>				
				
				</div>
</div>


</form>





