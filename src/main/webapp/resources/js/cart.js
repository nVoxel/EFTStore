const currencyChar = "\u20bd";

document.addEventListener('DOMContentLoaded', function() {
    checkCartEmpty()
    setCartButtonsListeners();
    setTotalCost()
})

function setCartButtonsListeners() {
    const cartButtons = document.getElementsByClassName("btn-cart");
    for (let i = 0; i < cartButtons.length; i++) {
        cartButtons[i].onclick = function (event) {
            removeFromCart(cartButtons[i].parentElement.parentElement, function () {
                removeCartItem(cartButtons[i].parentElement.parentElement);
                setTotalCost()
                checkCartEmpty()
                setRowDetails() // reset row details because rows indexes changed
                setCartButtonsListeners() // reset listeners because rows indexes changed
            })
            // don't open item details when clicking on add to cart button
            event.stopPropagation();
        };
    }
}

function clearCartButtonClick() {
    clearCart(function () {
        let table = document.getElementById("cart-table")
        table.parentElement.removeChild(table)
        setTotalCost()
        checkCartEmpty()
    })
}

function checkCartEmpty() {
    const cartContainer = document.getElementById("cart-container");
    const cartEmptyText = document.getElementById("cart-empty-text");
    const buttonCheckout = document.getElementById("btn-checkout");
    const buttonClearCart = document.getElementById("btn-clear-cart");
    const cartTable = document.getElementById("cart-table");
    const cartItems = document.getElementsByClassName("item-row");

    if (cartTable === null || cartItems.length === 0) {
        cartContainer.classList.add("h600");
        cartEmptyText.classList.remove("invisible");
        buttonCheckout.classList.add("disabled");
        buttonClearCart.classList.add("disabled");
        return
    }

    cartContainer.classList.remove("h600");
    cartEmptyText.classList.add("invisible");
    buttonCheckout.classList.remove("disabled");
    buttonClearCart.classList.remove("disabled");
}

function removeCartItem(cartItemRow) {
    const table = document.getElementById("cart-table");
    table.deleteRow(cartItemRow.rowIndex);
}

function setTotalCost() {
    const total = countTotal();
    document.getElementById("total-cost").innerText = total + currencyChar;
}

function countTotal() {
    let total = 0;
    const itemsPrices = document.getElementsByClassName("table-price");
    for (let i = 0; i < itemsPrices.length; i++) {
        total += getPriceNumber(itemsPrices[i]);
    }
    return total;
}

function getPriceNumber(tablePrice) {
    let text = tablePrice.innerText;
    text = text.substring(0, text.length - 1).replaceAll(/[^0-9]+/g,'')

    return Number(text);
}