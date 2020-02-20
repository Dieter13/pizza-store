<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <style>
        p{
            color: #009c00;
        }
        p:hover {
            color: #7be900;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                            class="icon-bar"></span><span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href='<c:url value="/menu" />'>
                                <p>Menu</p>
                            </a>
                        </li>
                        <li>
                            <a href='<c:url value="/cart" />'>
                                <p>Cart</p>
                            </a>
                        </li>
                        <li>
                            <a href='<c:url value="/order" />'>
                                <p>Orders</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="panel panel-success">
            <div class="panel-heading"><h1>Orders</h1></div>
            <div class="panel-body">
                <div class="container" style="font-size: 18px">
                    <div class="col-md-12">
                        <c:if test="${fn:length(orders) lt 1}">
                            <div class="alert alert-warning">
                                There are no orders
                            </div>
                        </c:if>

                        <c:if test="${fn:length(orders) gt 0}">
                            <table class="table table-striped table-hover ">
                                <thead>
                                <th class="text-info">#</th>
                                <th class="text-info">Customer's name and surname</th>
                                <th class="text-info">Email</th>
                                <th class="text-info">Phone number</th>
                                <th class="text-info">Address</th>
                                <th class="text-info">Order's date</th>
                                <th class="text-info">Details</th>
                                </thead>
                                <tbody>
                                <c:forEach items="${orders}" var="order" varStatus="loop">
                                    <tr>
                                        <td class="text-success">${loop.index + 1}</td>
                                        <td>${order.recipient}</td>
                                        <td>${order.email}</td>
                                        <td>${order.phoneNumber}</td>
                                        <td>${order.address}</td>
                                        <td>${order.orderDate}</td>
                                        <td>
                                           <a href="<c:url value="/order/${order.id}" />">
                                               -> Details
                                           </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
