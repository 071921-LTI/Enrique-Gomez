document.getElementById('sign-out').addEventListener('click', logout);
const auth = document.cookie.split('=')[1];

let userInfo;

getUserInfo();

async function getUserInfo() {
    
    const response = await fetch('http://localhost:8080/project1-0.0.1-SNAPSHOT/employee/', {
        headers: {
            'Authorization': auth
        }
    });

    const profileInfo = await response.json();

    userInfo = profileInfo;

    const idElement = document.getElementById('id');
    const firstNameElement = document.getElementById('firstName');
    const lastNameElement = document.getElementById('lastName');
    const usernameElement = document.getElementById('username');
    const emailElement = document.getElementById('email');
    const roleElement = document.getElementById('role');

    idElement.textContent = profileInfo.id;
    firstNameElement.textContent = profileInfo.firstName;
    lastNameElement.textContent = profileInfo.lastName;
    usernameElement.textContent = profileInfo.username;
    emailElement.textContent = profileInfo.email;
    roleElement.textContent = profileInfo.role.role;

    const fields = document.getElementsByClassName('input');

    for (let i = 0; i < fields.length; i++) {
        fields[i].addEventListener('click', changeToInput);
    }
}

function changeToInput(e) {
    e.target.innerHTML = `<input value='${e.target.textContent}' id='${e.target.id}-input' class='finish-input'/>`;
    document.getElementById(`${e.target.id}-input`).focus();
    document.getElementById(`${e.target.id}-input`).addEventListener('keypress', event => changeProfile(event, e.target.id));
}

async function changeProfile(e, changedField) {

    if (e.keyCode === 13) {

        const response = fetch('http://localhost:8080/project1-0.0.1-SNAPSHOT/employee/', {
            method: 'PUT',
            headers: {
                'Authorization': auth
            },
            body: JSON.stringify({...userInfo, [changedField]: e.target.value})
        })

        getUserInfo();
    }
}

function logout(e) {
    e.preventDefault();
    document.cookie = "auth=''; Max-Age=-100000";
    window.location.replace('index.html');
}