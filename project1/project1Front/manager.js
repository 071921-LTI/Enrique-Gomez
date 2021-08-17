

const auth = document.cookie.split('=')[1];

populateEmployees();

async function populateEmployees() {
    const response = await fetch(`http://localhost:8080/project1-0.0.1-SNAPSHOT/employee/`, {
        headers: {
            'Authorization': auth
        }
    });

    const data = await response.json();
    
    const requestsList = document.getElementById('requests');
    const dataHTMLArray = data.map(item => {
        // if (filter.all) {
            return `
            <tr data-id='${item.id}'>
            <td>${item.id}</th>
            <td>${item.username}</td>
            <td>${item.firstName}</td>
            <td>${item.lastName}</td>
            <td>${item.email}</td>
            </tr>
        `
    })

    const dataHTML = dataHTMLArray.join('');

    requestsList.innerHTML = dataHTML;
}