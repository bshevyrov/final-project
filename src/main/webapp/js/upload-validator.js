    function validateForm() {
    // var input = document.getElementById("upload").querySelectorAll("[required]");
    var emptyNewTopic = document.getElementById("newTopics").value;
    var checkBox = document.querySelectorAll('input[name=topics]:checked');
    if ((emptyNewTopic) || (checkBox.length !== 0)) {

    console.log(emptyNewTopic);
    return true;
} else {
    document.getElementById("error-message").textContent = "Please check chosen topic or create new before submitting again";
    document.getElementById("error-message").scrollIntoView();
    console.log(emptyNewTopic)
    return false;
}

}
