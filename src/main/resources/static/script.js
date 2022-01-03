var stompClient = null

$(document).ready((e) => {

	$("#connect").click(() => {

		connect()
	})
	
	$("#msgbtn").click(() => {
		sendMessage()
	})

})

function connect() {

	let socket = new SockJS("/server1")
	stompClient = Stomp.over(socket)

	stompClient.connect({}, function(frame) {

		console.log("Connected : " + frame)
		//subscribe
		stompClient.subscribe("/topic/list-employee", function(response) {
			showMessage(JSON.parse(response.body))
		})
	})
}

function showMessage(response) {

	$("#emp-list td").parent().remove();

	for (var i = 0; i < response.length; i++) {
		var employee = response[i];
		$('#emp-list > tbody:last').append('<tr><td>' + employee.employeeId + '</td><td>' + employee.employeeName + '</td><td>' + employee.employeeEmail + '</td><td>' + employee.employeeAge + '</td><td>' + employee.employeeSalary + '</td><td><button onClick="delEmployee(' + employee.employeeId + ')" id=btn' + i + '>Delete</button></td></tr>');
	}
}

function delEmployee(rowNum) {

	let jsonOb = {
		inputData: rowNum
	}

	stompClient.send("/app/del-employee", {}, JSON.stringify(jsonOb));
}

function sendMessage() {
	let jsonOb = {
		employeeId: $("#employee_id").val(),
		employeeName: $("#employee_name").val(),
		employeeAge: $("#employee_age").val(),
		employeeEmail: $("#employee_email").val(),
		employeeSalary: $("#employee_salary").val(),
	}

	stompClient.send("/app/add-employee", {}, JSON.stringify(jsonOb));
}