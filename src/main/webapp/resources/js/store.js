document.addEventListener('DOMContentLoaded', function() {
    const cartButtons = document.getElementsByClassName("btn-cart");
    for (let i = 0; i < cartButtons.length; i++) {
        cartButtons[i].onclick = function (event) {
            addToCart(cartButtons[i].parentElement.parentElement.dataset.storeItemId);
            // don't open item details when clicking on add to cart button
            event.stopPropagation();
        };
    }
})