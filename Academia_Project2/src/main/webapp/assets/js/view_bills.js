let get_pay = document.getElementById('view_bills');

get_pay.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();

    console.log(sessionStorage.getItem("id"));
    console.log("id ");
    console.log(document.getElementById('email').value);
        let response = await fetch('api/students/getpayment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                student_id : document.getElementById('email').value
            })
        });
        try {
            let result = await response.json();

            console.log(result);
            // console.log(response);

            //let data = response.json();
            console.log("found");
        }
        catch (err)
        {
            console.log("Not found");
        }
});