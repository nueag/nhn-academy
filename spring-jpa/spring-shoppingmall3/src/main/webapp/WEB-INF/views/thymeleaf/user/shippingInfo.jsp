<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <title>배송 정보</title>
</head>
<body>

<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">배송 정보</h1>
    </div>
</div>
<div class="container">
    <form method="post" class="form-horizontal"
          action="processShippingInfo.do">
        <input type="hidden" name="cartId" value="">
        <div class="form-group row">
            <label class="col-sm-2">성명</label>
            <div class="col-sm-3">
                <input type="text" name="name" class="form-control" required/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2">전화번호</label>
            <div class="col-sm-3">
                <input type="text" name="phoneNumber" class="form-control" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2">우편번호</label>
            <div class="col-sm-3">
                <input type="text" name="shippingNumber" class="form-control" required/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2">주소</label>
            <div class="col-sm-3">
                <input type="text" name="addressName" class="form-control"required />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2">최종 결제 금액</label>
            <div class="col-sm-3">
                <input type="text" name="payment" class="form-control" value="${sum}" readonly />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2">포인트 총액</label>
            <div class="col-sm-3">
                <input type="text" name="pointSum" class="form-control" value="${pointSum}" readonly/>
            </div>
        </div>
        <div class="form-group row">
            <label class ="col-sm-2">잔액</label>
            <div class="col-sm-3"?>
                <input type="text" name="balance" class="form-control" value="${pointSum - sum}" readonly/>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-offset2 col-sm-10">
                <a href="cart.jsp?cartId" class="btn btn-secondary" role="button">이전</a>
                <input type="submit" class="btn btn-primary" value="등록" />
                <a href="checkOutCancelled.jsp" class="btn btn-secondary" role="button">취소</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>