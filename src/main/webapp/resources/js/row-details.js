document.addEventListener('DOMContentLoaded', function() {
    setRowDetails()
})

function setRowDetails() {
    // set item click event
    const rows = document.getElementsByClassName("item-row");
    for (let i = 0; i < rows.length; i++) {
        rows[i].onclick = function () {
            const selection = window.getSelection();
            if(selection.toString().length === 0) {
                redirectToItemDetails(
                    rows[i].dataset.storeItemId
                )
            }
        };
    }
}

function redirectToItemDetails(storeItemId) {
    window.location = getContextPath() + '/item-details?storeItemId=' + storeItemId;
}