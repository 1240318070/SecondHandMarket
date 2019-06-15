/*模拟数据库数据，m代表男装，w代表女装，b代表书籍，t代表文具，o代表其它*/
//var date;
//= [//路径，名称，价格，分类
//    ["images/clothes/men-shirt3.jpg","刚穿没多久，不想穿了，质量好","600","m"],
//    ["images/clothes/clothes1.jpg","十成新，XX牌子，绝对便宜","500","w"],
//    ["images/clothes/clothes3.jpg","300卖你绝对合适","300","m"],
//    ["images/clothes/clothes4.jpg","价格不低","100","m"],
//    ["images/clothes/clothes5.jpg","买大了，想要的价格好商量","150","w"],
//    ["images/clothes/wenju2.jpg","突然不想用了","20","t"],
//    ["images/clothes/wenju3.jpg","有了新的了，把旧的卖了","50","t"],
//    ["images/clothes/men-shirt1.jpg","穿着舒服，才买回来没多久","99","m"],
//    ["images/clothes/wenju4.jpg","书写流畅，百分百好用","40","t"],
//    ["images/clothes/woman-shirt1.jpg","不说了，当时买小了，想要的直接找我","89","w"],
//    ["images/clothes/woman-shirt2.jpg","同专业给优惠，快来","129","w"],
//    ["images/clothes/clothes1.jpg","爱要不要","229","m"],
//    ["images/clothes/woman-shirt3.jpg","XX牌子，绝对正品，假一赔十","119","w"],
//    ["images/clothes/clothes2.jpg","是阿尼玛的，信的来","59","m"],
//    ["images/clothes/men-shirt2.jpg","穿着贼拉好看，贼有回头率","99","m"],
//    ["images/clothes/wenju1.jpg","两个字，好用","49","t"]
//];

window.onload = function () {
	getGoods('i','CkGoodsServlet','10');
    check('log');
    getUnumber();
};


function waterFlow(classify,date) {/*id随机生成*/
    var ex = document.getElementById("selected");
    if (ex != null)
        ex.id = null;
    document.getElementById(classify).parentElement.id = "selected";/*找到、修改父标签id*/
    var ch = "<null>";/*没有意义的标签，消除nodeafne对页面影响*/
    var cont = document.getElementById("container");
    if (classify == "i") {
        for (var i = 0; i < date.length; i++)
            ch = ch + "<div class='child clear' onmouseover='goodsOver(this)' onmouseout='goodsOut(this)'>" +
                "<div style='margin: 0 auto;position: relative;'><img src='" + date[i][0] + "'style='width: 200px;'><p class='tag'>" + date[i][1]
                + "</p><div class='fir-child'><img class='money' src='images/money.jpg'><p class='price'>" + date[i][2] +
                "</p></div></div><div class='goodsSelct'><input type='submit' value='查看' onclick=" + "\"" + "goodsCK('" + 
                date[i][0] + "','" + date[i][1] + "','" + date[i][2] + "','" + date[i][3] + "','" + date[i][4] + "','" + date[i][5] + "','" + date[i][6] + "','" + date[i][7] + "','" + date[i][8] + "','" + date[i][9] + 
                "')" + "\"" + "><input type='submit' value='想买' onclick=" + "\"" + "wantBuy('" + date[i][7] + "','" + date[i][9] + "')" + "\"" +
                "style='margin-top: 20px'></div></div>";
    	cont.innerHTML = ch;
    }else {
        for (var i = 0; i < date.length; i++)
            if (date[i][3] == classify)
                ch = ch + "<div class='child clear' onmouseover='goodsOver(this)' onmouseout='goodsOut(this)'>" +
                    "<div style='margin: 0 auto;position: relative;'><img src='" + date[i][0] + "'style='width: 200px;'><p class='tag'>" + date[i][1]
                    + "</p><div class='fir-child'><img class='money' src='images/money.jpg'><p class='price'>" + date[i][2] + "</p></div></div><div class='goodsSelct'>" +
                    "<input type='submit' value='查看' onclick=" + "\"" + "goodsCK('" +
                    date[i][0] + "','" + date[i][1] + "','" + date[i][2] + "','" + date[i][3] + "','" + date[i][4] + "','" + date[i][5] + "','" + date[i][6] + "','" + date[i][7] + "','" + date[i][8] + "','" + date[i][9] +
                    "')" + "\"" + "' style='margin-top: 50%'><input type='submit' value='想买' onclick=" + "\"" + "wantBuy('" + date[i][7] + "','" + date[i][9] + "')" + "\"" +
                    "style='margin-top: 20px'></div></div>";
        cont.innerHTML = ch;
    }
}
function develop() {
    var goods = document.getElementById("goods");
    var value = document.getElementById("more");
    var spreadretract = document.getElementById("spreadretract");
    if (value.innerText == "点击展开"){
        goods.style.height = "auto";
        value.innerText = "点击收起";
        spreadretract.className = "retract";
    } else {
        value.innerText = "点击展开";
        goods.style.height = "600px";
        spreadretract.className = "spread";
    }
}

function LogReg(typeID) {
    document.getElementById(typeID).style.display = "block";
    if (typeID == "logtype")
        document.getElementById("regtype").style.display = "none";
    else
        document.getElementById("logtype").style.display = "none";
}
function LogRegdispear(typeID) {
    document.getElementById(typeID).style.display = "none";
    cleanInput();
}
function LogReglink(self, typeID) {
    var Id = document.getElementById(typeID);
    Id.style.display = "block";
    self.parentElement.style.display = "none";
    cleanInput();
}
function cleanInput() {
    var elems =  document.getElementsByTagName("input");
    for (var i = 0; i < elems.length; i++)
        if (elems[i].type == "text" || elems[i].type == "password")
            elems[i].value = "";
    document.getElementById("msg").innerHTML = "";
    document.getElementById("msg1").innerHTML = "";
    document.getElementById("msg2").innerHTML = "";
}
function ckName(){//检查用户名是否存在
    //获取用户名
    var Unumber = document.getElementsByName("Unumber")[0];
    //创建XMLHttpRequest对象
    var xhr = new XMLHttpRequest();
    //处理结果
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4){//请求一切正常
            if(xhr.status==200){//服务器响应一切正常
                //alert(xhr.responseText);//得到响应结果
                var msg1 = document.getElementById("msg1");
                if(xhr.responseText=="true"){
                    msg1.innerHTML =  "<font color='red'>该学号已被注册</font>";
                    //msg1.innerText = "<font color='red'>该学号已被注册</font>";
                    document.getElementById("ck1").value = "";
                }else{
                    msg1.innerHTML = "<font color='green'>该学号可以使用</font>";
                    document.getElementById("ck1").value = "true";
                }
            }
            else{
                alert("服务器响应异常");
            }
        }
    };
    //创建连接
    xhr.open("get","ckNameServlet?Unumber="+Unumber.value);
    //发送请求
    xhr.send(null);
}
function ckPassword() {//检查密码是否一致
    var UPassword = document.getElementById("rep1");
    var reUPassword = document.getElementById("rep2");
    var msg2 = document.getElementById("msg2");
    if(UPassword.value != ""){
        if (UPassword.value == reUPassword.value) {
            msg2.innerHTML = "<font color='green'>密码一致</font>";
            document.getElementById("ck2").value="true";
        } else {
            msg2.innerHTML = "<font color='red'>密码不一致，请重新输入</font>";
            document.getElementById("ck2").value="";
        }
    }
}

function login() {
    var Unumber = document.getElementById("lognum");
    var Upsw = document.getElementById("pword");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4){//请求一切正常
            if(xhr.status==200){//服务器响应一切正常
                //alert(xhr.responseText);//得到响应结果
                var msg = document.getElementById("msg");
                if(xhr.responseText=="false"){
                    msg.innerHTML = "<font color='red'>账号或者密码错误，请重新输入！</font>";
                }else{
                    alert("登录成功!");
                    msg.innerHTML = "<font color='red'></font>";
                    window.location.href = "my.jsp";
                }
            }else{
                alert("服务器响应异常");
            }
        }
    };

    //创建连接
    xhr.open("get","LoginServlet?Unumber="+Unumber.value+"&UPassword="+Upsw.value);
    //发送请求
    xhr.send(null);
}


function goodsOver(goods) {
    var child = goods.children;
    child[1].style.display = "block";
    child[1].style.border = "1px solid gray";
    var inp = child[1].children[0];
    var hei = (child[1].clientHeight - 110)/2;
    inp.style.marginTop = hei+"px";
}
function goodsOut(goods) {
    var child = goods.children;
    child[1].style.display = "none";
    var inp = child[1].children[0];
}

function overPic(picType) {
    var src = picType.src;
    if (picType.id == ""){
        var ch = document.getElementById("check");
        ch.removeAttribute("id");
        picType.setAttribute("id","check");
    }

    var CH = document.getElementById("pic-up").children[0];
    CH.src = src;

}
function outPic(picType) {
    picType.style.border = "1px solid gray";
}
function goodsCK(Spicture1, Sname, Sprice, Sclassify, Spicture2, Spicture3, Spicture4, Unumber, Sdescribe, GoodsID) {
    var goods = document.getElementById("goodsinf");
    if (goods.style.display != "block")
        goods.style.display = "block";
    var img1 = document.getElementById("pic-up").children[0];
    img1.src = Spicture1;
    var nd = document.getElementById("discr").children;
    nd[0].innerHTML = Sname;
    nd[1].innerHTML = Sdescribe;
    var img = document.getElementById("pic-diwn").children;
    img[0].src = Spicture1;
    img[1].src = Spicture2;
    img[2].src = Spicture3;
    img[3].src = Spicture4;
    var pric = document.getElementById("goodspric").children[1];
    pric.innerHTML = Sprice;
    var cont = document.getElementById("butt");
    cont.innerHTML = "<input type='button' class='buttCh' onclick=wantBuy('"+Unumber+"','"+GoodsID+"') value='想买'>" +
    		"<input type='button' class='buttCh' onclick=chetting('"+Sname+"','"+Unumber+"') value='联系ta'>";
    
}
function wantBuy(Bconnect,goodsID){
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4){//请求一切正常
            if(xhr.status==200){//服务器响应一切正常
                //alert(xhr.responseText);//得到响应结果
                if(xhr.responseText == "true")
                	window.alert("商品已经加入到你的“想买的商品中”");
                else
                	window.alert("亲，请先登录！");
                	
            }else{
                alert("服务器响应异常");
            }
        }
    };
    //创建连接
    xhr.open("get","WantBuyServlet?goodsID="+goodsID+"&Bconnect="+Bconnect);
    //发送请求
    xhr.send(null);
}

function goodsClose() {
    var goods = document.getElementById("goodsinf");
    goods.style.display = "none";
}

function getSelectGoods(type,keyword){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
        if(xhr.readyState==4){//请求一切正常
            if(xhr.status==200){//服务器响应一切正常
            	var msg = xhr.responseText;
            	var dates = msg.split("|");
            	var num = parseInt(dates.length/10);
            	var tArray = new Array(); 
            	for(var i = 0; i < num; i++){
            		tArray[i] = new Array();
            		for(var j = 0; j < 10; j++){
            			tArray[i][j] = dates[i*10+j];
            		}
            	}
            	for(var i = 0, j = num-1; i < j; i++, j--){//逆序
            		var k = tArray[i];
            		tArray[i] = tArray[j];
            		tArray[j] = k;
            	}
            	waterFlow("i",tArray);//i代表index
            	
            }else{
                alert("服务器响应异常");
            }
        }
    };
    //创建连接
    xhr.open("get","CkGoodsServlet?type="+type+"&keyword="+encodeURI(keyword));//encodeURI转码
    //发送请求
    xhr.send(null);
}

function SelectGoods(){
	var keyword = document.getElementById("select-text").value;
	getSelectGoods("getSelectGoods",keyword);
}


function getUnumber(){
	var Um;
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
        if(xhr.readyState==4){//请求一切正常
            if(xhr.status==200){//服务器响应一切正常
            	var msg = xhr.responseText;
            	var myUnumber = document.getElementById("myUnumber");
            	myUnumber.innerHTML = msg;
            	Um = msg;
            }else{
                alert("服务器响应异常");
            }
            begin(Um);
        }
    };
    //创建连接
    xhr.open("get","getUnumberServlet");//encodeURI转码
    //发送请求
    xhr.send(null);
}