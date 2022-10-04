const input = document.getElementById("selectAvatar");
const avatar = document.getElementById("avatar");
// const textArea = document.getElementById("textArea");

const convertBase64 = (file) => {
    return new Promise((resolve, reject) => {
        const fileReader = new FileReader();
        fileReader.readAsDataURL(file);

        fileReader.onload = () => {
            resolve(fileReader.result);
        };

        fileReader.onerror = (error) => {
            reject(error);
        };
    });
};

const uploadImage = async (event) => {
    const file = event.target.files[0];
    const base64 = await convertBase64(file);
    avatar.src = base64;
    // textArea.innerText = base64;

    var thumbsnap_api_key = '00002037cb994e75ba716803db60bfe0';

    var xhr, formData;

    xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.open('POST', 'https://thumbsnap.com/api/upload');
    formData = new FormData();
    formData.append('key', thumbsnap_api_key);
    // const f = new File([file],Date.now()+'11');
    // console.log(f)
  formData.append('media', file);
    xhr.responseType = 'json';

    xhr.onload = function(e) {
        if (this.status === 200) {
            console.log('response', this.response); // JSON response
            const r = xhr.response.data.media;
            console.log('11',r);
            // const r = obj.media;
            document.getElementById("cover").setAttribute("value",r )
        }
    };

    xhr.send(formData);

    // console.log('HTTP Error: ' + xhr.status);

    // console.log('upload done', xhr);


    // var json2 = JSON.parse(xhr.response)
    // console.log(json2.media);

    // document.getElementById("img").setAttribute("value",base64 )

};

input.addEventListener("change", (e) => {
    uploadImage(e);
});

form.addEventListener('submit', function (e) {
    // prevent the form from submitting
    e.preventDefault();


   /* document.getElementById("img").setAttribute("value", base64)
    form.setAttribute('method', 'post');
    form.submit();*/

});



