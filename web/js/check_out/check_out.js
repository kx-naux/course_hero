// Get all radio buttons
const shippingRadios = document.querySelectorAll('div.shipping-method');

// Loop through each radio button and add an event listener
shippingRadios.forEach(shippingMethod => {
    shippingMethod.addEventListener('click', function () {
        // Find the radio button inside the clicked shipping method div
        const radioButton = this.querySelector('input[type="radio"]');
        radioButton.checked = true;
        
        submitForm("shipping method change");

    });
});

// submit form when need refresh
function submitForm(action) {
    const form = document.getElementById("checkOutForm");
    const formAction = document.getElementById("fromAction");
    
    formAction.value = action;
    form.submit();
}