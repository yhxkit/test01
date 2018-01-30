<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="../../menu/header.jsp" ></jsp:include>
 <jsp:include page="../../menu/menutest.jsp"></jsp:include>
 <!-- <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script> -->
 <script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
 <script src=" ${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>

<style>
	
#preview img {
	width: 100px;
	height: 100px;
}
#preview p {
	text-overflow: ellipsis;
	overflow: hidden;
}
.preview-box {
	border: 1px solid grey;
	padding: 5px;
	border-radius: 2px;
	margin-bottom: 10px;
}
</style>
 <script type="text/javascript" >


  var putnick = function(){
		
		 var id=document.createElement("input");
		 id.setAttribute("type","hidden");
		 id.setAttribute("name","id");
		 id.setAttribute("value",tempid);
		 
		 var nick=document.createElement("input");
		 nick.setAttribute("type","hidden");
		 nick.setAttribute("name","nickName");
		 nick.setAttribute("value",tempnick);
		 
		 var wform = document.getElementById('writeform');
			wform.appendChild(id);	
			wform.appendChild(nick);	
		};
		
		window.onload = function() {
			CKEDITOR.replace('content');
		}
		
		$('#writeform').validate();
		
		/*  $(function(){
			$("#writeform").validate();
			$.extend($.validator.messages, {
				required: "필수항목 입니다.",
				maxlength: $.validator.format("{0}자를 넘을 수 없습니다."),
				minlength: $.validator.format("{0}자 이상 입력하세요"),
				max: $.validator.format("{0} 이하의 값을 입력하세요"),
				min: $.validator.format("{0} 이상의 값을 입력하세요")
			});

		});		 */
		/* $(document).ready(function(){
			$("#writeform").validate({
				rules :{
					title:{required:true, minlength:2, maxlength: 100},
					bookName:{required:true, minlength:1},
					costPrice{required:true, min:100, max: 500000},
					salePrice{required:true, min:1, max:100000}
				},messages:{
					title:{
						 required:"필수입력사항",
	                     minlength: jQuery.format("글자는 {0}자 이상 입력해주세요!"),
	                     maxlength : jQuery.format("글자는 {0}자 이하 입력해주세요! ")
					},
					bookName:{
						required:"필수입력사항",
	                     minlength: jQuery.format("글자는 {0}자 이상 입력해주세요!")
					},
					costPrice{
						required:"필수입력사항",
	                     min: jQuery.format("값은 {0}원 이상 입력해주세요!"),
	                     max: jQuery.format("값은 {0}원 이하 입력해주세요!")
					},
					salePrice{
						required:"필수입력사항",
	                     min: jQuery.format("값은 {0}원 이상 입력해주세요!"),
	                     max: jQuery.format("값은 {0}원 이하 입력해주세요!")
					}
				},submitHandler : function(e){
					e.submit();
				}
			});
		}); */
		
 </script>
<script type="text/javascript">
//파일업로드

function addPreview(input) {
	
	var files = {};
	var previewIndex = 0;
	var input=document.querySelectorAll('input');
	// image preview 기능 구현
	// input = file object[]
	
	
	/* console.log(input);
alert("!!!!");


	alert(input[10].files[0].name); */
	
	if (input[10].files) {
		//파일 선택이 여러개였을 시의 대응
		for (var fileIndex = 0; fileIndex < input[10].files.length; fileIndex++) {
			var file = input[10].files[fileIndex];
			if (validation(file.name))
				continue;
			var reader = new FileReader();
			reader.onload = function(img) {
				//div id="preview" 내에 동적코드추가.
				//이 부분을 수정해서 이미지 링크 외 파일명, 사이즈 등의 부가설명을 할 수 있을 것이다.
				var imgNum = previewIndex++;
				$("#preview")
						.append(
								"<div class=\"preview-box\" value=\"" + imgNum +"\">"
										+ "<img class=\"thumbnail\" src=\"" + img.target.result + "\"\/>"
										+ "<p>"
										+ file.name
										+ "</p>"
										+ "<a href=\"#\" value=\""
										+ imgNum
										+ "\" onclick=\"deletePreview(this)\">"
										+ "삭제" + "</a>" + "</div>");
				files[imgNum] = file;
			};
			reader.readAsDataURL(file);
		}
	} else
		alert('invalid file input'); // 첨부클릭 후 취소시의 대응책은 세우지 않았다.
}
//preview 영역에서 삭제 버튼 클릭시 해당 미리보기이미지 영역 삭제
function deletePreview(obj) {
	var imgNum = obj.attributes['value'].value;
	delete files[imgNum];
	$("#preview .preview-box[value=" + imgNum + "]").remove();
	resizeHeight();
}
//client-side validation
//파일첨부시 확장자 검사
function validation(fileName) {
	fileName = fileName + "";
	var fileNameExtensionIndex = fileName.lastIndexOf('.') + 1;
	var fileNameExtension = fileName.toLowerCase().substring(
			fileNameExtensionIndex, fileName.length);
	if (!((fileNameExtension === 'jpg')
			|| (fileNameExtension === 'gif') || (fileNameExtension === 'png'))) {
		alert('jpg, gif, png 확장자만 업로드 가능합니다.');
		return true;
	} else {
		return false;
	}
}
 $(document).ready(function() {
	//submit 등록. 실제로 submit type은 아니다.
	$('.submit').on('click',function() {						
		var form = $('#writeform')[0];
		var formData = new FormData(form);
		alert(formData);
		for (var index = 0; index < Object.keys(files).length; index++) {
			//formData 공간에 files라는 이름으로 파일을 추가한다.
			//동일명으로 계속 추가할 수 있다.
			if(!Object.keys(files).length==0){
				alert(Object.keys(files));
			formxData.append('files',files[index]);
			}
		}
		//ajax 통신으로 multipart form을 전송한다.
		/* $.ajax({
			type : 'POST',
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			url : '/write/writecom',
			dataType : 'JSON',
			data : formData,
			success : function(result) {
				//이 부분을 수정해서 다양한 행동을 할 수 있으며,
				//여기서는 데이터를 전송받았다면 순수하게 OK 만을 보내기로 하였다.
				//-1 = 잘못된 확장자 업로드, -2 = 용량초과, 그외 = 성공(1)
				if (result === -1) {
					alert('jpg, gif, png, bmp 확장자만 업로드 가능합니다.');
					// 이후 동작 ...
				} else if (result === -2) {
					alert('파일이 10MB를 초과하였습니다.');
					// 이후 동작 ...
				} else {
					alert('이미지 업로드 성공');
					// 이후 동작 ...
				}
			}
			//전송실패에대한 핸들링은 고려하지 않음
		}); */
	});
	// <input type=file> 태그 기능 구현
	$('#attach input[type="file"]').change(function() {
		addPreview($(this)); //preview form 추가하기
	});
}); 

</script>
 
 </head>
 <body>
	<!-- 내용 -->	
	<div class="container">
		<div class="row content">
		  <div class="col-md-12">
		  	<div class="page-header">
			  <h1>글쓰기 <small>설명</small></h1>
			</div>
			<form action="write/writecom" method="post" id="writeform" onsubmit="putnick()" enctype="multipart/form-data">
			
				<div class="col-md-2"> 
					<div class="form-group"> 
						<label for="status">상태</label> 
						<select class="form-control" name="status">
						  <option value="팔래요">팔래요</option>
						  <option value="살래요">살래요</option>
						  <option value="거래완료">거래완료</option>
						</select> 
					</div> 
				</div> 
				<div class="col-md-10">
					<div class="form-group"> 
						<label for="title" id="title">제목</label> 
						<input type="text" class="form-control" name="title" id="title" placeholder="제목을 작성하세요" minlength="2" maxlength="100" required="required">  
					</div> 
				</div>
				<div class="col-md-6">
					<div class="form-group"> 
						<label for="university">대학교</label>
						<input type="text" class="form-control" name="university" id="university" placeholder="대학교를 작성하세요">  
					</div> 
				</div>
				<div class="col-md-6">
					<div class="form-group"> 
						<label for="dept">과</label> 
						<input type="text" class="form-control" name="dept" id="dept" placeholder="과를 작성하세요">  
					</div> 
				</div>				
				<div class="col-md-6">
					<div class="form-group"> 
						<label for="bookName">책이름</label> 
						<input type="text" class="form-control" name="bookName" id="bookName" placeholder="책이름을 작성하세요" minlength="1" maxlength="100" required="required">  
					</div> 
				</div>
				<div class="col-md-3">
					<div class="form-group"> 
						<label for="author">저자</label> 
						<input type="text" class="form-control" name="author" id="author" placeholder="책저자를 작성하세요">  
					</div> 
				</div>
				<div class="col-md-3">
					<div class="form-group"> 
						<label for="publisher">출판사</label> 
						<input type="text" class="form-control" name="publisher" id="publisher" placeholder="책출판사를 작성하세요">  
					</div> 
				</div>
				<div class="col-md-4">
					<div class="form-group"> 
						<label for="ISBN">고유번호</label> 
						<input type="text" class="form-control" name="ISBN" id="ISBN" placeholder="책고유번호를 작성하세요">  
					</div> 
				</div>
				<div class="col-md-4">
					<div class="form-group"> 
						<label for="costPrice">원가</label> 
						<input type="number" class="form-control" name="costPrice" id="costPrice" placeholder="책원가를 작성하세요"  min="100"  max="500000" required="required">  
					</div> 
				</div>
				<div class="col-md-4">
					<div class="form-group"> 
						<label for="salePrice">판매가</label> 
						<input type="number" class="form-control" name="salePrice" id="salePrice" placeholder="판매가를 작성하세요" min="1"  max="500000" required="required">  
					</div> 
				</div>
				<div class="col-md-12">
					<div class="form-group"> 
						<label for="content">내용</label> 
						<textarea class="form-control" rows="10" name="content" id="content" placeholder="책상태, 책사진 필히 첨부"></textarea>  
					</div> 
				</div>
				<div class="col-md-12">
						<div id="attach"> 
							<label for="imgAdd">사진첨부</label> 
							<input type="file" name="imgAdd" id="imgAdd" multiple />
						</div> 
					<div class="col-md-12">
						 
							<div id="preview" class="previewcontent"></div>
					</div>
				</div> 
				<div class="col-md-12 text-center">
				<button class="btn btn-primary" type="submit">완료</button>
				<button class="btn btn-default" type="reset">취소</button>
				</div>
			</form>
		<jsp:include page="../../menu/footer.jsp"></jsp:include>
