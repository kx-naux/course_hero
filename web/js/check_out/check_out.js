// submit form when need refresh
function submitForm(action) {
    const form = document.getElementById("checkOutForm");
    const formAction = document.getElementById("fromAction");

    formAction.value = action;
    form.submit();
}

// listener for shippiing method
const shippingRadios = document.querySelectorAll('div.shipping-method');

shippingRadios.forEach(shippingMethod => {
    shippingMethod.addEventListener('click', function () {
        // Find the radio button inside the clicked shipping method div
        const radioButton = this.querySelector('input[type="radio"]');
        radioButton.checked = true;

        submitForm("shipping method change");

    });
});

// listener for address
if (document.querySelector(".stored-address-div") !== null) {
    const addressRadios = document.querySelectorAll('div.address-div');

    addressRadios.forEach(addressDiv => {
        addressDiv.addEventListener('click', function () {
            // Find the radio button inside the clicked shipping method div
            const radioButton = this.querySelector('input[type="radio"]');
            radioButton.checked = true;

            console.log("onclick");

        });
    });
}

// listener for payment method
const paymentRadios = document.querySelectorAll('div.payment-method');
paymentRadios.forEach(paymentMethod => {
    paymentMethod.addEventListener('click', function () {
        // Find the radio button inside the clicked shipping method div
        const radioButton = this.querySelector('input[type="radio"]');
        radioButton.checked = true;


    });
});


