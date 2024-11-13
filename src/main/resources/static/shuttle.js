function dropOffStudent() {
    fetch('http://localhost:8080/dropOff')
        .then(response => response.text())
        .then(data => alert(data))
        .catch(error => console.error('Error:', error));
}

function viewStudentList() {
    fetch('http://localhost:8080/studentList')
        .then(response => response.text())
        .then(data => {
            document.getElementById('studentList').innerText = data;
        })
        .catch(error => console.error('Error:', error));
}
