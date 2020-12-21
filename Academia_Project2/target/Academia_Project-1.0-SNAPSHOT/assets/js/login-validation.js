let login_form = document.getElementById('login-validation');

login_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (login_form.checkValidity() === true) {
        let response = await fetch('api/students/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                email: document.getElementById('email').value,
            })
        });
        try {
        let result = await response.json();
        console.log(result);

            //if (result['status'] === 200) {
                let data = result;
                console.log(data);
                sessionStorage.setItem('id', data["id"]);
                sessionStorage.setItem('fname', data["firstname"]);
                sessionStorage.setItem('lname', data["lastname"]);
                console.log(sessionStorage.getItem("id"));
                location.href = "View_bills.html";
            //}
        }
        catch(err){
                document.getElementById("login-alert").style.display = "block";
            }
        }
});