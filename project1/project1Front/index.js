document.getElementById('login').addEventListener('submit', login);

async function login(e) {
    e.preventDefault();
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');

    const info = {
        username: usernameInput.value,
        password: passwordInput.value
    }

    usernameInput.value = '';
    passwordInput.value = '';

    const response = await fetch('http://localhost:8080/project1-0.0.1-SNAPSHOT/authorize/', {
        method: 'POST',
        body: JSON.stringify(info)
    })

    const token = response.headers.get('Authorization');

    if (token) {
        document.cookie = 'auth=' + token;

        const role = token.split(':')[1];
        if (role === 'Manager') {
            window.location.href = 'manager.html';
        } else {
            window.location.href = 'employee.html';
        }
    } else {
        alert('Username and password do not match');
    }

}