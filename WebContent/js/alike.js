function check(status) {
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {//请求一切正常
            //alert(xhr.status);//查看路径名是否正确
            if (xhr.status == 200) {//服务器响应一切正常
                if (document.getElementById("lr") != null) {
                    //alert(xhr.responseText);//得到响应结果
                    var alab = document.getElementById("lr");
                    if (xhr.responseText == "true") {
                        alab.innerHTML = "<a href='my.jsp'>个人</a> <a href='#' onclick=" + "out()" + ">注销</a>";
                    } else {
                        alab.innerHTML = "<a href='#' onclick="+"LogReg('logtype')"+">登录</a> <a href='#' onclick="+"LogReg('regtype')"+">注册</a>";
                    }
                }else {
                    window.location.href = "index.html";
                }
            } else {
                alert("服务器响应异常");
            }
        }
    };

    //创建连接
    xhr.open("get", "check?status="+status);
    //发送请求
    xhr.send(null);
}
function out() {
    if(window.confirm("确认退出吗？")){
        check("out");
    }else {
        check("log")
    }
}

function getGoods(classify,servl,goodslength){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
        if(xhr.readyState==4){//请求一切正常
            if(xhr.status==200){//服务器响应一切正常
            	var msg = xhr.responseText;
            	var dates = msg.split("|");
            	var num = parseInt(dates.length/goodslength);
            	var tArray = new Array(); 
            	for(var i = 0; i < num; i++){
            		tArray[i] = new Array();
            		for(var j = 0; j < goodslength; j++){
            			tArray[i][j] = dates[i*goodslength+j];
            		}
            	}
            	for(var i = 0, j = num-1; i < j; i++, j--){//逆序
            		var k = tArray[i];
            		tArray[i] = tArray[j];
            		tArray[j] = k;
            	}
            	waterFlow(classify,tArray);//i代表index
            	
            }else{
                alert("服务器响应异常");
            }
        }
    };
    //创建连接
    xhr.open("get",servl);
    //发送请求
    xhr.send(null);
}



function getUname(goodsname,Unickname) {
    var ifo = document.getElementsByClassName("chet-h3");
    ifo[0].innerHTML = goodsname;
    ifo[1].innerHTML = Unickname;
}
function addchet(headimg, msg, outmsg) {//头像路径，收到的消息，发送的消息
    var Par = document.getElementById("chet");
    var div = document.createElement("div");
    if (outmsg != null) {
        div.setAttribute("class","div-chet-right");
        div.innerHTML = "<img class=\"head-img-right\" src=\""+ headimg +"\">" +
            "<img class=\"chet-img-right\" src=\"images/rightchet.jpg\">" +
            "<p class=\"right-chat\">"+ outmsg +"</p>";
        Par.appendChild(div);
    }
    if (msg != null){
        div.setAttribute("class","div-chet-left");
        div.innerHTML = "<img class=\"head-img\" src=\""+ headimg +"\">" +
            "<img class=\"chet-img\" src=\"images/leftchet.jpg\">" +
            "<p class=\"left-chat\">"+ msg +"</p>";
        Par.appendChild(div);
    }
    img_length();
}
function img_length(){
    var left_p = document.getElementsByClassName("left-chat");
    var left_img = document.getElementsByClassName("chet-img");//32
    for (var i = 0; i < left_p.length; i++){
        left_img[i].style.width = left_p[i].offsetWidth + 32 + "px";
    }
    var right_p = document.getElementsByClassName("right-chat");
    var right_img = document.getElementsByClassName("chet-img-right");//32
    for (var j = 0; j < right_img.length; j++)
        right_img[j].style.width = right_p[j].offsetWidth + 32 + "px";
}

function closechet() {
    document.getElementById("chetbox").style.display = "none";
}
function openchet(goodsname,Unickname) {
    document.getElementById("chetbox").style.display = "block";
    getUname(goodsname,Unickname);
}


//启动监听
function begin(Unumber){
    PL.userId =Unumber;
    PL._init();
    PL.joinListen("/pushlet/onetoone");
    
}

//触发服务方法，使其向页面推送消息
function trigger(){
	var xhr = new XMLHttpRequest();
    var msg = document.getElementById("chetting");
    var outUnumber = document.getElementById("outUnumber").innerHTML;
    var outgoodsName = document.getElementById("goodsName").innerHTML;
    var headimg = document.getElementById("headimg").innerHTML;
    xhr.open("get", "OneToOneServlet?userID=" + encodeURI(outUnumber) + "&msg=" + encodeURI(msg.value)+"&goodsName="+encodeURI(outgoodsName), true);
    xhr.send(null);
    addchet(headimg, null, msg.value);
    msg.value = "";

}

//Pushlet的js中封装方法,产生消息
function onData(event) {
//	alert("你有新的消息！");
	document.getElementById("chetbox").style.display = "block";
	var outgoodsName = document.getElementById("goodsName");
	var outUnumber = document.getElementById("outUnumber")
	outgoodsName.innerHTML = decodeURIComponent(event.get("goodsName"));
	outUnumber.innerHTML = decodeURIComponent(event.get("myUnumber"));
	openchet(decodeURIComponent(event.get("goodsName")),decodeURIComponent(event.get("myUnickname")));
    //用decodeURIComponent方法解码
    addchet(decodeURIComponent(event.get("myUhead")), decodeURIComponent(event.get("message")), null);
}

function stop(){
    //离开
    PL.leave();
}

function chetting(goodsname,Unumber){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4){//请求一切正常
            if(xhr.status==200){//服务器响应一切正常
                //alert(xhr.responseText);//得到响应结果
                if(xhr.responseText != "false"){
                	var msg = xhr.responseText;
                	var dates = msg.split("|");
                	var outgoodsName = document.getElementById("goodsName");
                	var outUnumber = document.getElementById("outUnumber");
                	var headimg = document.getElementById("headimg");
                	var myUnumber = document.getElementById("myUnumber").innerHTML;
                	headimg.innerHTML = dates[0];
                	outUnumber.innerHTML = Unumber;
                	outgoodsName.innerHTML = goodsname;
                	openchet(goodsname,dates[1]);
                	begin(myUnumber);
                	
                }
                else
                    window.alert("亲，请先登录！");

            }else{
                alert("服务器响应异常");
            }
        }
    };
    //创建连接
    xhr.open("get","getUserMsgServlet?Unumber="+"chet"+"&outUnumber="+Unumber);
    //发送请求
    xhr.send(null);
}
