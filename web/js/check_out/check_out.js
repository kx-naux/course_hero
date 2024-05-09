// submit form when need refresh
function submitForm(purpose,action) {
    const form = document.getElementById("checkOutForm");
    const formAction = document.getElementById("fromAction");

    formAction.value = purpose;
    form.action = action;
    form.submit();
}

// listener for shippiing method
const shippingRadios = document.querySelectorAll('div.shipping-method');

shippingRadios.forEach(shippingMethod => {
    shippingMethod.addEventListener('click', function () {
        // Find the radio button inside the clicked shipping method div
        const radioButton = this.querySelector('input[type="radio"]');
        radioButton.checked = true;

        submitForm("shipping method change","cout-ship-meth-chg");

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

        showPaymentInputDiv();

    });
});

function showPaymentInputDiv() {
    const paymentRadios = document.querySelectorAll('div.payment-method');
    
}

// form submission
document.getElementById("placeOrderBtn").addEventListener("click",()=>{
    submitCheckoutForm();
});

function submitCheckoutForm() {
    
    const form = document.getElementById("checkOutForm");
       form.submit();
}


document.addEventListener('DOMContentLoaded', function() {
    // Target the button by its ID
    const button = document.getElementById('promoApply');

    // Add a click event listener to the button
    button.addEventListener('click', function() {
        // Call the function when the button is clicked
        submitForm("checkout-apply-promo","check-out-apply-promo");
    });
});

