// (1) 회원정보 수정
function update(userId) {
	event.preventDefault(); // 폼태그 액션을 막기!!

	let data = $("#profileUpdate").serialize(); // key=value

	console.log(data);

	$.ajax({
		type: "put",
		url : `/api/user/${userId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res=>{ // HttpStatus 상태코드 200번대
		console.log("성공");
	}).fail(error=>{ // HttpStatus 상태코드 200번대가 아닐 때
		console.log("실패");

	});
}