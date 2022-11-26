function addToCart(itemId) {
    fetch(getContextPath() + '/cart/add?' + new URLSearchParams({
        itemId: itemId
    }), {
        method: 'post',
        headers: {
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
        .then((response) => {
            if (response.status !== 200) {
                showToastById('cart-add-error')
                return
            }
            showToastById('cart-add-success')
        })
        .catch(() => {showToastById('cart-add-error')})
}

function removeFromCart(cartItemRow, successCallback) {
    fetch(getContextPath() + '/cart/remove?' + new URLSearchParams({
        cartItemId: cartItemRow.dataset.cartItemId
    }), {
        method: 'post',
        headers: {
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
        .then((response) => {
            if (response.status !== 200) {
                showToastById('cart-remove-error')
                return
            }

            showToastById('cart-remove-success')
            successCallback()
        })
        .catch(() => {
            showToastById('cart-remove-error')
        })
}
function clearCart(successCallback) {
    fetch(getContextPath() + '/cart/clear', {
        method: 'post',
        headers: {
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
        .then((response) => {
            if (response.status !== 200) {
                showToastById('cart-clear-error')
                return
            }

            showToastById('cart-clear-success')
            successCallback()
        })
        .catch(() => {
            showToastById('cart-clear-error')
        })
}

function showToastById(elementId) {
    const toastElement = document.getElementById(elementId)
    showToast(toastElement)
}

function showToast(toastElement) {
    const toast = new bootstrap.Toast(toastElement)
    toast.show()
}