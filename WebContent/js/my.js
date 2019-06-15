wantbuydate = null;
window.onload = function () {
	getGoods('mysell','GetUserGoodsServlet','10');
	getUserMsg();
	begin(Unumber);
};



function waterFlow(id,date) {
    var select = document.getElementById(id);
    select.className = "a-down selected";
    if (id == "wantbuy"){
        var my = document.getElementById("mysell");
        var selc = document.getElementsByClassName("select");
        selc[0].style.display = "none";
        my.className = "a-down";
        if (date != null){
            var table = "<tr>" +
                "<td><span class='span-name'>商品名称</span></td>" +
                "<td><span class='span-price'>商品价格</span></td>" +
                "<td><span class='span-description'>商品描述</span></td>" +
                "<td><span class='span-contact'>商品图片</span></td>" +
                "</tr>";//"<p>什么都没有打算买！</p>"
            for (var i = 0; i < date.length; i++){//Unumber,Sname,Sprice,Sdescribe,Spicture1,Spicture2,Spicture3,Spicture4,goodsID
                var str = "<null>";/*没有意义的标签，消除nodeafne对页面影响*/
                var str =  str + "<td>" + date[i][1] + "</td>" + "<td>" + date[i][2] + "</td>" + "<td>" + date[i][3] + "</td>";
                table = table + "<tr>" + str + "<td><img src='" + date[i][4] + "'><img src='" + date[i][5] + "'><img src='" + date[i][6] + "'><img src='" + date[i][7] + "'>" +
                		"</td><td><img class='con-pic' src='images/connect.jpg' onclick=" + "\"" + "chetting('"+ 
                		date[i][1] + "','" + date[i][0] + "')"+ "\"" + ">" +
                		"<img class='con-pic' src='images/delete.jpg' onclick=" + "\"" + "CKout('" + 
                		date[i][8] + "'," + "'WB'" + "," + "'9'" +"," +"'wantbuy'"+"," +"'getWBgoods'"+ ")" + "\"" + "></td></tr>";
            }
        }else{
        	var table = "<tr>" +
            "<td><span class='span-name'>商品名称</span></td>" +
            "<td><span class='span-price'>商品价格</span></td>" +
            "<td><span class='span-description'>商品描述</span></td>" +
            "<td><span class='span-contact'>商品图片</span></td>" +
            "</tr>"+
        	"<p>什么都没有打算买！</p>";
        }
    } else if(date != null){
        var my = document.getElementById("wantbuy");
        var selc = document.getElementsByClassName("select");
        selc[0].style.display = "block";
        my.className = "a-down";
        var table = "<tr>" +
            "<td><span class='span-name'>商品名称</span></td>" +
            "<td><span class='span-price'>商品价格</span></td>" +
            "<td><span class='span-classification'>商品分类</span></td>" +
            "<td><span class='span-description'>商品描述</span></td>" +
            "<td><span class='span-img'>商品图片</span></td>" +"<td></td>"+
            "</tr>";
        for (var i = 0; i < date.length; i++){
        	switch (date[i][3]) {
			case "m":
				date[i][3] = "男装";
				break;
			case "w":
				date[i][3] = "女装";
				break;
			case "b":
				date[i][3] = "书籍";
				break;
			case "t":
				date[i][3] = "文具";
				break;
			case "o":
				date[i][3] = "其它";
				break;
	
			default:
				break;
			}
            var str = "<null>";/*没有意义的标签，消除nodeafne对页面影响*/
            var str =  str + "<td>" + date[i][1] + "</td>" + "<td>" + date[i][2] + "</td>" + "<td>" + date[i][3] + "</td>" + "<td>" + date[i][8] + "</td>";
            table = table + "<tr>" + str + "<td><img src='" + date[i][0] + "'><img src='" + date[i][4] + "'><img src='" + date[i][5] + "'><img src='" + date[i][6] + "'>" +
            		"</td><td><img class='con-pic' src='images/revise.jpg' onclick=" + "\"" + "goodsRevise('"+ 
            		date[i][0] + "','" + date[i][1] + "','" + date[i][2] + "','" + date[i][3] + "','" + date[i][4] + "','" + date[i][5] + "','" + date[i][6] + 
            		"','" + date[i][8] + "','" + date[i][9] + "')"+ "\"" + ">" +
            		"<img class='con-pic' src='images/delete.jpg' onclick=" + "\"" + 
            		"CKout('" + date[i][9] + "'," + "'SELL'" + "," + "'10'" +"," + "'mysell'"+"," +"'GetUserGoodsServlet'"+ ")" + "\"" + "></td></tr>";
        	}
    	}else{
    		var table = "<tr>" +
            "<td><span class='span-name'>商品名称</span></td>" +
            "<td><span class='span-price'>商品价格</span></td>" +
            "<td><span class='span-classification'>商品分类</span></td>" +
            "<td><span class='span-description'>商品描述</span></td>" +
            "<td><span class='span-img'>商品图片</span></td>" +"<td></td>"+
            "</tr>"+"<p>什么都没有打算卖！</p>";
    	}
    var tableid = document.getElementById("selltable");
    tableid.innerHTML = table;
 }


function goodsRevise(Spicture1, Sname, Sprice, Sclassify, Spicture2, Spicture3, Spicture4, Sdescribe, GoodsID){
	var text = document.getElementsByTagName("textarea");
	text[0].value = Sname;
	text[1].value = Sdescribe;
	text[2].value = Sprice;
	switch (Sclassify) {
	case "男装":
		Sclassify = 0;
		break;
	case "女装":
		Sclassify = 1;
		break;
	case "书籍":
		Sclassify = 2;
		break;
	case "文具":
		Sclassify = 3;
		break;
	case "其它":
		Sclassify = 4;
		break;

	default:
		break;
	}
	var classify = document.getElementsByName("Sclassify")[0];
	classify.options[Sclassify].selected = true;
//	var imgs = document.getElementsByClassName("input-pic");
//	imgs[0].innerHTML = Spicture1;
//	imgs[1].innerHTML = Spicture2;
//	imgs[2].innerHTML = Spicture3;
//	imgs[3].innerHTML = Spicture4;
	goodsCK();
}

function CKout(goodsID,gtype,num,classify,servl){
	if(window.confirm("确认删除这个商品吗？"))
		delgoods(goodsID,gtype,num,classify,servl);
}

function delgoods(goodsID,gtype,num,classify,servl){
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {//请求一切正常
            //alert(xhr.status);//查看路径名是否正确
            if (xhr.status == 200) {//服务器响应一切正常
                if (xhr.responseText == "true") {
                	getGoods(classify,servl,num);
                }
            } else {
                alert("服务器响应异常");
            }
        }
    };

    //创建连接
    xhr.open("get", "delGoods?goodsID="+goodsID+"&gtype="+gtype);
    //发送请求
    xhr.send(null);
}


function getUserMsg(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
	        if(xhr.readyState==4){//请求一切正常
	            if(xhr.status==200){//服务器响应一切正常
	                //alert(xhr.responseText);//得到响应结果
	            	var msg = xhr.responseText;
	            	var dates = msg.split("|");
	            	var Uhead = document.getElementById("img").children[0];
	            	Uhead.src = dates[0];
	            	var Unick = document.getElementById("div-left").children;
	            	Unick[2].innerHTML = dates[1];
	            	Unick[4].innerHTML = dates[2];
	            		
	            }else{
	                alert("服务器响应异常");
	            }
	        }
	    };

	    //创建连接
	    xhr.open("get","getUserMsgServlet?Unumber="+Unumber);
	    //发送请求
	    xhr.send(null);
}

function goodsCK() {
    var goods = document.getElementById("goodsinf");
    if (goods.style.display != "block")
        goods.style.display = "block";
}
function goodsClose() {
    var goods = document.getElementById("goodsinf");
    goods.style.display = "none";
    goodsClear();
}

function goodsClear() {
    var option = document.getElementsByTagName("textarea");
    var pic = document.getElementsByClassName("input-pic");
    for (i = 0; i < option.length; i++)
        option[i].value = "";
    var classify = document.getElementsByName("Sclassify")[0];
    classify.options[0].selected = ture;
    for (j = 0; j <= pic.length; j++)
        pic[j].value = "";
}
