// submit form when need refresh
function submitForm(action) {
    const form = document.getElementById("checkOutReviewForm");
    const formAction = document.getElementById("fromAction");

    formAction.value = action;
    form.submit();
}

