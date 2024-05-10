const errorMsg = document.getElementById("errorMsg");
if (errorMsg !== null && errorMsg.innerText !== "") {
    Swal.fire({
        title: "Record Is Not Created",
        text: errorMsg.innerText,
        icon: "error"
    });
}


const successMsg = document.getElementById("successMsg");
if (successMsg !== null && successMsg.innerText !== "") {
    Swal.fire({
        title: "Record Processed",
        text: successMsg.innerText,
        icon: "success"
    });
}
