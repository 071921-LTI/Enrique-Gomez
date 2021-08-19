document.getElementById('sign-out').addEventListener('click', logout);
document.getElementById('request-form').addEventListener('submit', submitRequest);

const auth = document.cookie.split('=')[1];

async function submitRequest(e) {
    e.preventDefault();

    const amountInput = document.getElementById('amount');
    const typeSelect = document.getElementById('type');
    const descriptionInput = document.getElementById('description');

    const data = {
        amount: parseInt(amountInput.value.trim()),
        dateSubmitted: new Date(),
        type: {
            id: parseInt(typeSelect.value)
        },
        description: descriptionInput.value.trim(),
        status: {
            id: 1
        }

    }

    amountInput.value = '';
    typeSelect.value = 1;
    descriptionInput.value = '';

    const response = fetch('http://localhost:8080/project1-0.0.1-SNAPSHOT/reimbursement/', {
        method: 'POST',
        headers: {
            'Authorization': auth
        },
        body: JSON.stringify(data)
    })

    document.getElementById('alert').textContent = "Your request was submitted";

    setTimeout(() => {
        document.getElementById('alert').textContent = '';
    }, 2000);

}

function logout(e) {
    e.preventDefault();
    document.cookie = "auth=''; Max-Age=-100000";
    window.location.replace('index.html');
}