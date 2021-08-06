document.getElementsByTagName('button')[0].addEventListener('click', getImage);

async function getImage() {

    const response = await fetch('https://picsum.photos/200/300');

    const image = document.getElementsByTagName('img')[1];

    image.src = response.url;
}