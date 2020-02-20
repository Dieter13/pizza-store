<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
                            <a href='<c:url value="/menu" />' >
                               <p>Menu</p>
                            </a>
                        </li>
                        <li>
                            <a href='<c:url value="/cart" />' >
                                <p>Cart</p>

                            </a>
                        </li>
                        <li>
                            <a href='<c:url value="/order" />' >
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
            <div class="panel-heading"><h1>Choose Pizza</h1></div>
            <div class="panel-body">
                <div class="container" style="font-size: 18px">
                    <form method="POST" action="<c:url value="/addPizza" />">
                    <c:url var="addCampURL" value="/addPizza"/>
                    <form:form modelAttribute="newPizza" method="POST" action="${addCampURL}">
                        <div class="row list-row form-group">
                            <div class="col-md-3 text-info text-right">Type:</div>
                            <div class="col-md-6">
                                <ul style="list-style: none;">
                                    <c:forEach items="${pizzaTypes}" var="pizzaType">
                                        <li>
<%--                                                <input type="radio" name="pizzaTypeId" value="${pizzaType.id}"/>--%>
                                            <label>
                                                <form:radiobutton path="pizzaTypeId" value="${pizzaType.id}"/>
                                                <strong>${pizzaType.name}</strong>
                                                <span style="color: crimson">${pizzaType.price}$</span>
                                            </label>
                                        </li>
                                    </c:forEach>
                                    <form:errors path="pizzaTypeId" cssClass="alert alert-danger" element="div"/>
                                    <c:if test="${pizzaNotSelected}">
                                        <div class="alert alert-warning">
                                            Choose pizza type:
                                        </div>
                                    </c:if>
                                </ul>
                            </div>
                        </div>

                        <div class="row list-row form-group">
                            <div class="col-md-3 text-info text-right">Size:</div>
                            <div class="col-md-6">
                                <ul style="list-style: none;">
                                    <c:forEach items="${pizzaSizes}" var="pizzaSize" varStatus="loop">
                                          <li>
<%--                                                <input type="radio" name="pizzaSizeId" value="${pizzaSize.id}"--%>
<%--                                                    ${loop.index eq 0 ? 'checked="checked"' : ""}--%>
<%--                                                />--%>
                                            <label>
                                                <form:radiobutton path="pizzaSizeId" value="${pizzaSize.id}"/>
                                                    ${pizzaSize.name}
                                                <c:if test="${pizzaSize.extraPrice ne 0}">
                                                    ( + ${pizzaSize.extraPrice} $ )
                                                </c:if>
                                            </label>
                                        </li>
                                    </c:forEach>
                                    <form:errors path="pizzaSizeId" cssClass="alert alert-danger" element="div"/>
                                </ul>
                            </div>
                        </div>

                        <div class="row list-row form-group">
                            <div class="col-md-3 text-info text-right">Extra toppings:</div>
                            <div class="col-md-6">
                                <ul style="list-style: none;">
                                    <c:forEach items="${pizzaToppings}" var="topping">
                                        <li>
                                                <input type="checkbox" name="pizzaToppingIds"
                                                       value="${topping.id}"/>
                                            <label>
                                                <form  path="pizzaToppingIds"
                                                               value="${topping.id}"/>
                                                    ${topping.name} (${topping.extraPrice} $)
                                            </label>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="row list-row">
                            <div class="col-md-3 "></div>
                            <div class="col-md-6">
                                <button type="submit" class="btn btn-lg btn-success">Add to the order</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
