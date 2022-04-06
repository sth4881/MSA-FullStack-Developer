<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>Insert title here</title>
	<style>
		.span_del {
			cursor : pointer;
		}
		.uploadResult {
			width : 100%;
			background-color : gray;
		}
		.uploadResult ul {
			display : flex;
			flex-flow : row;
			justify-content : center;
			align-items : center;
		}
		.uploadResult ul li {
			list-style : none;
			padding : 10px;
		}
		.uploadResult ul li img {
			width : 20px;
		}
		.uploadResult ul li span {
			color : white;
		}
		.bigPictureWrapper {
			position : absolute;
			display : none;
			justify-content : center;
			align-items : center;
			top : 0%;
			width : 100%;
			height : 100%;
			background-color : gray;
			z-index : 100;
			background : rgba(255,255,255,0.5);
		}
		.bigPicture {
			position : relative;
			display : flex;
			justify-content : center;
			align-items : center;
		}
		.bigPicture img {
			width : 600px;
		}
	</style>
</head>
<body>
	<h1>Upload with Ajax</h1>
	<div class="upload">
		<input type="file" id="uploadFile" multiple />
	</div>
	<div class="uploadResult">
		<ul>
		
		</ul>
	</div>
	<div class="bigPictureWrapper">
		<div class="bigPicture">
		
		</div>
	</div>
	<button id="uploadBtn">Upload</button>
</body>
<script type="text/javascript" src="<c:url value="/webjars/jquery/3.6.0/dist/jquery.js" />"></script>
<script type="text/javascript">
	// <a> 태그에서 showImage()를 호출하는 방식으로 작성하기 위해서
	function showImage(fileCallPath) {
		$(".bigPictureWrapper").css("display", "flex").show();
		$(".bigPicture")
			.html("<img src='${app}/display?fileName=" + encodeURI(fileCallPath) + "'>")
			.animate({width : '100%', height : '100%'}, 1000);
	}
	$(document).ready(function() {
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880; // 5MB
		function checkExtension(fileName, fileSize) {
			if(fileSize >= maxSize) {
				alert("파일 크기 초과");
				return false;
			}
			if(regex.test(fileName)) {
				alert("해당 종류의 파일은 업로드할 수 없습니다.")
				return false;
			}
			return true;
		}
		
		var uploadResult = $(".uploadResult ul");
		function showUploadedFile(uploadResultArr) {
			var str = "";
			$(uploadResultArr).each(function(i, obj) {
				if(!obj.image) {
					var fileCallPath = obj.uploadPath + encodeURIComponent(obj.fileName);
					var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
					str += "<li><div><a href='${app}/download?fileName=" + fileCallPath + "'>" 
						+ "<img src='./resources/img/attach.png'>" + obj.fileName + "</a>"
						+ "<span data-file=\'" + fileCallPath + "\' data-type='file' class='span_del'> x </span><div></li>";
				} else {
					//str += "<li>" + obj.fileName + "</li>";
					var fileCallPath = obj.uploadPath + "/s_" + encodeURIComponent(obj.fileName);
					var originPath = obj.uploadPath + "\\" + obj.fileName;
					originPath = originPath.replace(new RegExp(/\\/g), "/");
					//str += "<li><img src='${app}/display?fileName=" +  encodedFileName + "'><li>";
					str += "<li><a href=\"javascript:showImage(\'" + originPath + "\')\">"
						+ "<img src='${app}/display?fileName=" + fileCallPath + "'></a>"
						+ "<span data-file=\'" + fileCallPath + "\' data-type='image' class='span_del'> x </span></li>";
				}
			});
			uploadResult.append(str);
		}
		
		var cloneObj = $(".upload").clone();
		$("#uploadBtn").on("click", function(e) {
			var inputFile = $("#uploadFile");
			var files = inputFile[0].files;			
			var formData = new FormData();
			for(var i=0; i<files.length; i++) {
				if(!checkExtension(files[i].name, files[i].size)) return false;
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				url : 'uploadAjaxAction',
				processData : false,
				contentType : false,
				data : formData,
				type : 'POST',
				dataType : 'JSON',
				success:function(result) {
					console.log(result);
					showUploadedFile(result);
					$(".upload").html(cloneObj.html());
				}
			});
		});
		
		$(".bigPictureWrapper").on("click", function(e) {
			$(".bigPicture").animate({width : '0%', height : '0%'}, 1000);
			setTimeout(() => {
				$(this).hide();
			}, 1000);
		});
		
		$(".uploadResult").on("click", "span", function(e) {
			var target = $(this).data("file");
			var type = $(this).data("type");
			var temp = $(this).parent("li");
			console.log(target);
			
			$.ajax({
				url : 'deleteFile',
				data : {
					fileName : target,
					type : type
				},
				dataType : 'text',
				type : 'POST',
				success:function(result) {
					alert(result);
					temp.remove();
				}
				error:function(result) {
					alert(result);
				}
			})
		});
	});
</script>
</html>