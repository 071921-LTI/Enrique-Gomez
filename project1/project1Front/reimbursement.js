document.getElementById('sign-out').addEventListener('click', logout);
const filterBtns = document.getElementsByClassName('filter-btn');

for (let i = 0; i < filterBtns.length; i++) {
    filterBtns[i].addEventListener('click', filter);
}

const auth = document.cookie.split('=')[1];
const id = auth.split(':')[0];
const role = auth.split(':')[1];
if (auth.split(':')[1] !== 'Manager') window.location.replace('index.html');

populateRequests('');

function logout(e) {
    e.preventDefault();
    document.cookie = "auth=''; Max-Age=-100000";
    window.location.replace('index.html');
}

async function populateRequests(filter) {
    const response = await fetch(`http://localhost:8080/project1-0.0.1-SNAPSHOT/reimbursement/${filter}`, {
        headers: {
            'Authorization': auth
        }
    });

    const parsedResponse = await response.json()
    
    const data = parsedResponse.sort((a, b) => {
        return b.dateSubmitted - a.dateSubmitted;
    });
    
    const requestsList = document.getElementById('requests');
    const dataHTMLArray = data.map(item => {
        // if (filter.all) {
            return `
            <tr data-id='${item.id}' ${item.status.status === 'pending' ? `onclick='resolveFunction(event, ${item.id})' style='cursor:pointer'` : ''}>
            <td>${item.id}</td>
            <td>$${item.amount}</td>
            <td>${new Date(item.dateSubmitted).toLocaleDateString()}</td>
            <td>${item.dateResolved ? new Date(item.dateResolved).toLocaleDateString() : 'N/A'}</td>
            <td>${item.description}</td>
            <td><a href='${item.receipt}'} target='_blank'>View Receipt</a><td>
            <td>${item.author.firstName} ${item.author.lastName}</td>
            <td>${item.resolver ? item.resolver.firstName + ' ' + item.resolver.lastName : 'N/A'}</td>
            <td>${item.status.status}</td>
            <td>${item.type.type}</td>
            </tr>
        `
    })

    const dataHTML = dataHTMLArray.join('');

    requestsList.innerHTML = dataHTML;

}

function filter(e) {
    e.preventDefault();
    let filter = e.target.textContent.toLowerCase();
    const buttons = document.getElementsByClassName('filter-btn');

    for (let i = 0; i < buttons.length; i++) {
        const btnClass = buttons[i].classList;
        if (!btnClass.contains('btn-secondary')) {
            btnClass.add('btn-secondary');
        }
        if (btnClass.contains('btn-warning') || btnClass.contains('btn-success') || btnClass.contains('btn-primary')) {
            btnClass.remove('btn-warning') || btnClass.remove('btn-success') || btnClass.remove('btn-primary');
        }
    }
    if (filter === 'pending') {
        e.target.classList.remove('btn-secondary');
        e.target.classList.add('btn-warning');
    } else if (filter === 'resolved') {
        e.target.classList.remove('btn-secondary');
        e.target.classList.add('btn-success');
    }
    else if (filter === 'all') {
        e.target.classList.remove('btn-secondary');
        e.target.classList.add('btn-primary');
        filter = '';
    }

    populateRequests(filter);
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

async function updateRequest(updatedRequest) {
    const response = await fetch(`http://localhost:8080/project1-0.0.1-SNAPSHOT/reimbursement/`, {
        method: 'PUT',
        headers: {
            'Authorization': auth
        },
        body: JSON.stringify(updatedRequest)
    })

    if (response.status === 200) {
        populateRequests('pending');
    }
}