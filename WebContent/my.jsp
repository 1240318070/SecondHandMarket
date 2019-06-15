<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>my</title>
    <link rel="stylesheet" type="text/css" href="css/my.css"/>
    <link rel="stylesheet" href="css/alike.css" type="text/css">
    <script type="text/javascript" src="js/my.js"></script>
    <script type="text/javascript" src="js/alike.js"></script>
    <script type="text/javascript" src='js/ajax-pushlet-client.js'></script>
    <script type="text/javascript">
    	var Unumber = "${sessionScope.user.getUnumber()}";
    	var headimg;
    	var outUnumber;
    	begin(Unumber);
    </script>
</head>
<body>
    <div id="head">
        <div class="center">
            <div id="link">
                <a href="index.html">主页</a>
                <a id="out" href="#" onclick="out()">注销</a>
            </div>
        </div>
        <div class = "center">
            <div id="img">
                <img src="" alt="头像" height="200px" width="150px">
            </div>
            <div id = "div-left">
                <h1></h1>
                <p></p>
                <a>昵称</a>
                <p></p>
                <a>性别</a>
                <p></p>
                <a>个人设置</a>
                <P></P>
            </div>
        </div>

    </div>
    <div id="center">
        <div id="middle">
            <ul>
                <li>
                    <a id="mysell" class="a-down" onclick="getGoods('mysell','GetUserGoodsServlet','10')">我的商品</a>&nbsp;&nbsp;
                </li>
                <li>
                    <a id="wantbuy" class="a-down" onclick="getGoods('wantbuy','getWBgoods','9')">想买的商品</a>
                </li>
            </ul>
            <div class="select">
                <input type="submit" class="submit" value="添加商品" onclick="goodsCK()">
            </div>
            <table id="selltable" align="center" border="1px">
            </table><P></P>
        </div>
    </div>
    <div id="foot">
        <form action="${pageContext.request.contextPath}/servlet/UplodServlet" enctype="multipart/form-data" method="post" accept-charset="UTF-8">
            <div id="goodsinf">
                <div id="goodsOut" onclick="goodsClose()"></div>
                <h1 style="margin-top: 0">添加商品</h1>
                <div id="discr">
                    <div>
                        <span class="goodsSpan">商品名称：</span>
                        <textarea name="Sname" style="font-size: 25px; max-height: 30px; width: 300px"></textarea>
                    </div>
                    <div>
                        <span class="goodsSpan">商品分类：</span>
                        <select name="Sclassify" style="margin-left: 120px; display: block;height: 25px;">
                            <option value="m">男装</option>
                            <option value="w">女装</option>
                            <option value="b">书籍</option>
                            <option value="t">文具</option>
                            <option value="o">其它</option>
                        </select>
                    </div>
                    <div>
                        <span class="goodsSpan">商品描述：</span>
                        <textarea name="Sdescribe" style="min-height: 100px; max-height: 100px; width: 300px"></textarea>
                    </div>

                    <div id="goodspric">
                        <span class="goodsSpan">商品价格：</span>
                        <img class="money" src="images/money.jpg">
                        <textarea name="Sprice" class="price" style="max-height: 15px"></textarea>
                    </div>
                </div>
                <div class="div-pic">
                    <span class="goodsSpan">商品图片：(最多选择四张)</span>
                    <input class="input-pic" name="pic1" type="file">
                    <input class="input-pic" name="pic2" type="file">
                    <input class="input-pic" name="pic3" type="file">
                    <input class="input-pic" name="pic4" type="file">
                </div>
                <div id="butt">
                    <input type="submit" class="buttCh" value="确定">
                    <input type="button" class="buttCh" value="重置" onclick="goodsClear()">
                </div>

            </div>
        </form>
    <div id="chetbox">
        <div id="chet-head">
            <h3 class="chet-h3"></h3>&nbsp;&nbsp;&nbsp;&nbsp;
            <h3 class="chet-h3"></h3>
            <div id="chet-del" onclick="closechet()"></div>
        </div>
        <div id="chet">
        </div>
        <div id="div-ch-but">
            <textarea id="chetting"></textarea>
            <input type="button" id="ch-but" onclick="trigger()" value="发送">
        </div>
    </div>
	<P id="headimg" class="elm"></P>
	<P id="outUnumber" class="elm"></P>
	<P id="goodsName" class="elm"></P>
	<P id="myUnumber" class="elm"></P>
    </div>
</body>
</html>