document.getElementById('sign-out').addEventListener('click', logout);
document.getElementById('request-form').addEventListener('submit', submitRequest);

const auth = document.cookie.split('=')[1];

async function submitRequest(e) {
    e.preventDefault();

    const amountInput = document.getElementById('amount');
    const typeSelect = document.getElementById('type');
    const descriptionInput = document.getElementById('description');

    const bucketName = 'a-project1-bucket';
    const bucketRegion = 'us-east-2';
    const IdentityPoolId = 'us-east-2:4c438bc8-7bea-47b9-8d47-30c0627fcf48'
    // Initialize the Amazon Cognito credentials provider
    await AWS.config.update({
        region: bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
            IdentityPoolId: IdentityPoolId
        })
    })

    const s3 = await new AWS.S3({
        apiVersion: '2006-03-01',
        params: {Bucket: bucketName}
    })

    const imageFile = document.getElementById('imageUpload').files[0];
    const fileName = imageFile.name;
    const filePath = `images/${fileName}`;

    s3.upload({
        Key: filePath,
        Body: imageFile,
        ACL: 'public-read'
    },
    function(err, res) {
        if(err) {
            console.log(err)
        }
            alert('Successfully Uploaded!');
            const data = {
                amount: parseInt(amountInput.value.trim()),
                dateSubmitted: new Date(),
                receipt: res.Location,
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
    })

}

function logout(e) {
    e.preventDefault();
    document.cookie = "auth=''; Max-Age=-100000";
    window.location.replace('index.html');
}

function uploadImage() {

}