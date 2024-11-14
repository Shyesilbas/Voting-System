document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data.token) {
                localStorage.setItem('jwtToken', data.token);
                localStorage.setItem('username', username);
                localStorage.setItem('password', password);
                window.location.href = 'dashboard.html';
            } else {
                document.getElementById('errorMessage').textContent = "Invalid login credentials.";
            }
        })
        .catch(error => {
            document.getElementById('errorMessage').textContent = "An error occurred. Please try again.";
        });
});
