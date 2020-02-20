<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

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
            <div class="panel-heading"><h1>Order's details</h1></div>
            <div class="panel-body">
                <div class="container" style="font-size: 18px">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="row list-row form-group">
                                <div class="col-md-3 text-info text-right">Name and Surname</div>
                                <div class="col-md-6">
                                    <strong>${orderDetails.recipient}</strong>
                                </div>
                            </div>
                            <div class="row list-row form-group">
                                <div class="col-md-3 text-info text-right">email address</div>
                                <div class="col-md-6">
                                    <strong>${orderDetails.email}</strong>
                                </div>
                            </div>
                            <div class="row list-row form-group">
                                <div class="col-md-3 text-info text-right">Address for delivery</div>
                                <div class="col-md-6">
                                    <strong>${orderDetails.address}</strong>
                                </div>
                            </div>
                            <div class="row list-row form-group">
                                <div class="col-md-3 text-info text-right">Phone number</div>
                                <div class="col-md-6">
                                    <strong>${orderDetails.phoneNumber}</strong>
                                </div>
                            </div>
                            <div class="row list-row form-group">
                                <div class="col-md-3 text-info text-right">Order's date</div>
                                <div class="col-md-6">
                                    <strong>${orderDetails.orderDate}</strong>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <c:if test="${fn:length(orderDetails.pizzas) gt 0}">
                                <c:forEach items="${orderDetails.pizzas}" var="pizza" varStatus="loop">
                                    <table class="table table-striped table-hover ">
                                        <thead>
                                        <th class="text-info">#</th>
                                        <th class="text-info">Name</th>
                                        <th class="text-info">Description</th>
                                        <th class="text-info">Price</th>
                                        <th class="text-info">Size</th>
                                        <th class="text-info">Extra toppings</th>
                                        <th class="text-info">Total price</th>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${pizza.type.name}</td>
                                            <td>${pizza.type.description}</td>
                                            <td>${pizza.type.price}</td>
                                            <td>${pizza.size.name} (${pizza.size.extraPrice} $)</td>
                                            <td>
                                                <c:if test="${fn:length(pizza.toppings) gt 0}">
                                                    <ul>
                                                        <c:forEach items="${pizza.toppings}" var="topping">
                                                            <li>
                                                                    ${topping.name} ${topping.extraPrice} $
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </c:if>
                                            </td>
                                            <td class="text-success">${pizza.totalPrice}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </c:forEach>
                            </c:if>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                Razem do zapłaty:
                            </div>
                            <div class="col-md-6">
                                <strong>${orderDetails.totalPrice} $</strong>
                            </div>
                        </div>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
