<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:url value="/clear" var="clearURL"/>

<html>
<head>
    <style>
        p{
            color: #009c00;
        }
        p:hover {
            color: #7be900;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
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
            <div class="panel-heading"><h1>Cart</h1></div>
            <div class="panel-body">
                <div class="container" style="font-size: 18px">
                    <div class="col-md-12">
                        <c:url var="addOrderURL" value="/addOrder"/>
                        <form:form modelAttribute="newOrder" method="POST" action="${addOrderURL}">
                            <div class="row">
                                <div class="row list-row form-group">
                                    <div class="col-md-6">
                                        <ul>
                                            <c:forEach items="${pizzaCartList}" var="pizza" varStatus="loop">

                                                <c:url value="/remove" var="removeURL">
                                                    <c:param name="index" value="${loop.index}"/>
                                                </c:url>

                                                <li>
                                                    <span>${pizza.type.name}</span>
                                                    <span>${pizza.totalPrice}$</span>
                                                    <span>(${pizza.size.name})</span>
                                                    <a href='${removeURL}'>
                                                        <span class="glyphicon glyphicon-remove-circle"></span>
                                                        Remove
                                                    </a>
                                                    <c:if test="${fn:length(pizza.toppings) gt 0}">
                                                        <div class="alert alert-info">
                                                            <ul>
                                                                <c:forEach items="${pizza.toppings}" var="topping">
                                                                    <li>
                                                                            ${topping.name} ${topping.extraPrice} $
                                                                    </li>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                    </c:if>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <c:if test="${pizzaListIsEmpty}" >
                                    <div class="alert alert-danger">
                                        You have not chosen any pizza
                                    </div>
                                </c:if>
                            </div>
                            <div class="row">
                                <div class="col-md-3">
                                    Total:
                                </div>
                                <div class="col-md-9">
                                    <strong>${totalCount} $</strong>
                                </div>

                                <div class="col-md-3">
                                </div>
                                <div class="col-md-9">
                                    <a
                                            class="btn btn-lg btn-primary"
                                            href='${clearURL}'
                                    >
                                        Clear the List
                                    </a>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="row list-row form-group">
                                    <div class="col-md-3 text-info text-right">Name and Surname</div>
                                    <div class="col-md-6">
                                        <form:input
                                                class="form-control"
                                                type="text"
                                                path="recipient"
                                                placeholder="Name and Surname"
                                        />
                                        <form:errors path="recipient" cssClass="alert alert-danger" element="div"/>
                                    </div>
                                </div>
                                <div class="row list-row form-group">
                                    <div class="col-md-3 text-info text-right">Email address</div>
                                    <div class="col-md-6">
                                        <form:input
                                                type="text"
                                                class="form-control"
                                                path="email"
                                                placeholder="Email address"
                                        />
                                        <form:errors path="email" cssClass="alert alert-danger" element="div"/>
                                    </div>
                                </div>
                                <div class="row list-row form-group">
                                    <div class="col-md-3 text-info text-right">Address for delivery</div>
                                    <div class="col-md-6">
                                        <form:input
                                                type="text"
                                                class="form-control"
                                                path="address"
                                                placeholder="Address"
                                        />
                                        <form:errors path="address" cssClass="alert alert-danger" element="div"/>
                                    </div>
                                </div>
                                <div class="row list-row form-group">
                                    <div class="col-md-3 text-info text-right">Phone number</div>
                                    <div class="col-md-6">
                                        <form:input
                                                type="text"
                                                class="form-control"
                                                path="phoneNumber"
                                                placeholder="(+48) 000-000-000"
                                        />
                                        <form:errors path="phoneNumber" cssClass="alert alert-danger" element="div"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row list-row">
                                <div class="col-md-3 "></div>
                                <div class="col-md-6">
                                    <button type="submit" class="btn btn-lg btn-success">Place your order</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
