<%--
  Created by IntelliJ IDEA.
  User: Cxy
  Date: 2017/4/22
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <input id="test" type="button" value="密码登录测试"/>
    <input id="register" type="button" value="注册测试"/>
    <input id="fast" type="button" value="快捷登录测试"/>
    <input id="logout" type="button" value="退出登录"/>
    <input id="productlist" type="button" value="获取订单列表"/>
    <input id="sms" type="button" value="短信验证码测试"/>
    <input id="publicKey" type="button" value="获取公钥测试"/>
    <input id="update" type="button" value="更新用户详情"/>
    <input id="feedback" type="button" value="意见反馈"/>
    <p id="result"></p>
</body>

<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
    $('#test').click(function() {
        var timestamp = Date.parse(new Date());
        $.ajax({
            type: "POST",
            url: "/user/setSignal.do?userId=1&signal=helloWorld",//"/login?j_username=zhang&j_password=123",
            contentType: "application/json", //必须有
//            dataType: "json", //表示返回值类型，不必须
            data: JSON.stringify({
//                j_username:'zhang',
                  userId:1,
                  signal:"hello world!"
//                j_password:'123'
            }),  //相当于 //data: "{'str1':'foovalue', 'str2':'barvalue'}",
            success: function (jsonResult) {

                console.log(jsonResult,"jsonResult");
                console.log(jsonResult.code,"jsonResult");
                console.log(JSON.stringify(jsonResult));
                console.log(jsonResult.loginResult);
                //var info = jQuery.parseJSON(jsonResult);
//                alert("登录成功");
//                window.location.href="/jsp/user.jsp";
            },
            error: function (jsonResult) {
                console.log("what");
                console.log(jsonResult);
            }

        });
    });
    $('#register').click(function() {
        var timestamp = Date.parse(new Date());
        $.ajax({
            type: "GET",
            url: "/payment/getRuleVer.do?userName=admin&ruleType=1",
            dataType: "json", //表示返回值类型，不必须
            success: function (jsonResult) {
                console.log(jsonResult,"jsonResult");
                console.log(jsonResult.code,"jsonResult");
                console.log(JSON.stringify(jsonResult));
            },
            error: function (jsonResult) {
                console.log("what");
                console.log(jsonResult);
            }
        });
    });
    $('#update').click(function() {
        var timestamp = Date.parse(new Date());
        $.ajax({
            type: "POST",
            url: "/user/update",
            contentType: "application/json", //必须有
            dataType: "json", //表示返回值类型，不必须
            data: JSON.stringify({
                timestamp: 'sss',
                data: {
                    nickname: 'Colin',
                    city:'厦门',
                    avatar:'test'
                },
                sign: 'sdfksdjf'
            }),  //相当于 //data: "{'str1':'foovalue', 'str2':'barvalue'}",
            success: function (jsonResult) {

                console.log(jsonResult,"jsonResult");
                console.log(jsonResult.code,"jsonResult");
                console.log(JSON.stringify(jsonResult));

                //var info = jQuery.parseJSON(jsonResult);
            },
            error: function (jsonResult) {
                console.log("what");
                console.log(jsonResult);
            }

        });
    });
    $('#logout').click(function() {
        var timestamp = Date.parse(new Date());
        $.ajax({
            type: "POST",
            url: "/logout",
            contentType: "application/json", //必须有
            dataType: "json", //表示返回值类型，不必须
            data: JSON.stringify({
                timestamp: 'sss',
                data: {
                    phone: '15280126526',
                    password: 'e10adc3949ba59abbe56e057f20f883e'
                },
                sign: 'sdfksdjf'
            }),  //相当于 //data: "{'str1':'foovalue', 'str2':'barvalue'}",
            success: function (jsonResult) {

                console.log(jsonResult,"jsonResult");
                console.log(jsonResult.code,"jsonResult");
                console.log(JSON.stringify(jsonResult));

                //var info = jQuery.parseJSON(jsonResult);
            },
            error: function (jsonResult) {
                console.log("what");
                console.log(jsonResult);
            }

        });
    });
    $('#fast').click(function() {
        var timestamp = Date.parse(new Date());
        $.ajax({
            type: "POST",
            url: "/session/fast",
            contentType: "application/json", //必须有
            dataType: "json", //表示返回值类型，不必须
            data: JSON.stringify({
                timestamp: 'sss',
                data: {
                    phone: '15280126529'
                },
                sign: 'sdfksdjf'
            }),  //相当于 //data: "{'str1':'foovalue', 'str2':'barvalue'}",
            success: function (jsonResult) {

                console.log(jsonResult,"jsonResult");
                console.log(jsonResult.code,"jsonResult");
                console.log(JSON.stringify(jsonResult));

                //var info = jQuery.parseJSON(jsonResult);
            },
            error: function (jsonResult) {
                console.log("what");
                console.log(jsonResult);
            }
        });
    });
    $('#sms').click(function() {
        $.ajax({
            type: "POST",
            url: "/sendSms",
            contentType: "application/json", //必须有
            dataType: "json", //表示返回值类型，不必须
            data: JSON.stringify({
                timestamp: 'sss',
                data: {
                    phone: '15280126529',
                    password: 'e10adc3949ba59abbe56e057f20f883e'
                },
                sign: 'sdfksdjf'
            }),  //相当于 //data: "{'str1':'foovalue', 'str2':'barvalue'}",
            success: function (jsonResult) {

                console.log(jsonResult,"jsonResult");
                console.log(jsonResult.code,"jsonResult");
                console.log(JSON.stringify(jsonResult));

                //var info = jQuery.parseJSON(jsonResult);
            },
            error: function (jsonResult) {
                console.log("what");
                console.log(jsonResult);
            }

        });
    });
    $('#publicKey').click(function() {
        $.ajax({
            url: '/getPublicKey',
            type: 'GET',
            dataType: 'json',
            timeout: 1000,
            cache: false,
            beforeSend: LoadFunction, //加载执行方法
            error: erryFunction,  //错误执行方法
        })
        function LoadFunction() {
            $("#result").html('加载中...');
        }
        function erryFunction() {
            alert("error");
        }
    });
    $('#feedback').click(function() {
        var timestamp = Date.parse(new Date());
        $.ajax({
            type: "POST",
            url: "/userHome/advice",
            contentType: "application/json", //必须有
            dataType: "json", //表示返回值类型，不必须
            data: JSON.stringify({
                timestamp: 'sss',
                data: {
                    type: '功能故障',
                    content:'请加紧改进',
                    phone:'15280126529',

                },
                sign: 'sdfksdjf'
            }),  //相当于 //data: "{'str1':'foovalue', 'str2':'barvalue'}",
            success: function (jsonResult) {

                console.log(jsonResult,"jsonResult");
                console.log(jsonResult.code,"jsonResult");
                console.log(JSON.stringify(jsonResult));

                //var info = jQuery.parseJSON(jsonResult);
            },
            error: function (jsonResult) {
                console.log("what");
                console.log(jsonResult);
            }

        });
    });
</script>

</html>
