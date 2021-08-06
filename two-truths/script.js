
async function getData() {
    const response = await fetch('https://picsum.photos/v2/list');

    if (response.status >= 200 && response.status < 300) {
        const data = await response.json();
        populateData(data);
    } else {
        console.log(response);
    }
}

function populateData(data) {
    console.log(data);
    
    const dataSection = document.getElementById('data');
    
    const dataHTML = data.map(item => `<div style='margin:20px'><img width=200 src='${item.download_url}'><h5>Author: ${item.author}</h5></div>`)

    dataSection.innerHTML = dataHTML.join('');
}
getData();