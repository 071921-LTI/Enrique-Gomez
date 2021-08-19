document.getElementById('sign-out').addEventListener('click', logout);

const auth = document.cookie.split('=')[1];

populateEmployees();

function logout(e) {
    e.preventDefault();
    document.cookie = "auth=''; Max-Age=-100000";
    window.location.replace('index.html');
}

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
            <tr id=${item.id} onclick='viewEmployeeRequests(${item.id})'>
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

async function viewEmployeeRequests(id) {
    if (document.getElementById('newTable')) {
        document.getElementById('newTable').remove();
    }

    const response = await fetch(`http://localhost:8080/project1-0.0.1-SNAPSHOT/reimbursement/employee/${id}`, {
        headers: {
            'Authorization': auth
        }
    })

    const parsedResponse = await response.json()
    
    const data = parsedResponse.sort((a, b) => {
        return b.dateSubmitted - a.dateSubmitted;
    });
    console.log(data)
    document.getElementById('request-table-title').textContent = `${data[0].author.firstName} ${data[0].author.lastName}'s Requests`
    const table = document.createElement('table');
    table.id = 'newTable'
    table.className = 'table table-striped table-dark rounded';
    table.innerHTML = `
        <thead>
        <tr>
        <th scope="col">ID</th>
        <th scope="col">Amount</th>
        <th scope="col">Submitted</th>
        <th scope="col">Resolved</th>
        <th scope='col'>Description</th>
        <th scope='col'>Receipt</th>
        <th scope='col'>Author</th>
        <th scope='col'>Resolver</th>
        <th scope='col'>Status</th>
        <th scope='col'>Type</th>
        </tr>
        <tbody id='requests'>${data.map(item => {
            return `
            <tr ${item.status.status === 'pending' ? `onclick='resolveFunction(event, ${item.id})'` : ''}'>
            <td>${item.id}</td>
            <td>$${item.amount}</td>
            <td>${new Date(item.dateSubmitted).toLocaleDateString()}</td>
            <td>${item.dateResolved ? new Date(item.dateResolved).toLocaleDateString() : 'N/A'}</td>
            <td>${item.description}</td>
            <td><a href='${item.receipt}'>View Receipt</a></td>
            <td>${item.author.firstName} ${item.author.lastName}</td>
            <td>${item.resolver ? `${item.resolver.firstName} ${item.resolver.lastName}` : 'N/A'}</td>
            <td>${item.status.status}</td>
            <td>${item.type.type}</td>
            </tr>
            `
        }).join('')}</tbody>
        </thead>
    `

    document.getElementById('table-div').appendChild(table);
}

function resolveFunction(e, id) {
    if (e.target.tagName !== 'A') {
        const input = prompt("Enter 'approve' or 'deny'");
        let decision;

        if (input.toLowerCase() === 'approve') {
            decision = 2;
        } else if (input.toLowerCase() === 'deny') {
            decision = 3;
        } else {
            alert("Please enter 'approve' or 'deny'");
        }

        

        const updatedRequest = {
            id,
            dateResolved: new Date(),
            status: {
                id: decision
            }
        }

        updateRequest(updatedRequest, id);
    }
}

async function updateRequest(updatedRequest, id) {
    const response = await fetch(`http://localhost:8080/project1-0.0.1-SNAPSHOT/reimbursement/`, {
        method: 'PUT',
        headers: {
            'Authorization': auth
        },
        body: JSON.stringify(updatedRequest)
    })

    if (response.status === 200) {
        viewEmployeeRequests(id)
    }
}