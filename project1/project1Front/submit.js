document.getElementById('request-form').addEventListener('submit', submitRequest);

async function submitRequest(e) {
    e.preventDefault();

    const amountInput = document.getElementById('amount');
    const typeSelect = document.getElementById('type');
    const descriptionInput = document.getElementById('description');

    const data = {
        amount: amountInput.value,
        type: typeSelect.value,
        description: descriptionInput.value

    }

    console.log(data)

}