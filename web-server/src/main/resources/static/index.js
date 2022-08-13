document.getElementById('blaa').addEventListener('click', () => {
    // myHeaders.append("Cookie", "JSESSIONID=47B92ED7A2B26F8575ECE1DB41AF5B5D; evo-token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb21wYW55IjoiQXBwbGUiLCJlbXBsb3llZSI6ImFtaXQiLCJleHAiOjE2NjAwMDAyNDh9.nkLaYQW1fUw5K0W-oP7ptB_0oFig3lzso7BztGPesZs");

    var raw = JSON.stringify({
        "username": "amit",
        "password": "1234"
    });

    var requestOptions = {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: raw
    };

    fetch("http://localhost:8080/api/doSignup", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
})