<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
</head>
<body>
<h2>测试</h2>
<button id="btn" onclick="foo()">客单价</button>
<button id="btn1" onclick="ticket()">票务分析</button>
<%--<button id="btn2" onclick="income()">总收入</button>--%>
</body>

<script type="text/javascript">
    function foo() {
        location.href = "/pct"
    }
    function ticket() {
        location.href = "/checkTicket"
    }
    function income() {
        // location.href = "/income"
    }
</script>

</html>
