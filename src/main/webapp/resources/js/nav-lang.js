const forceReloadCookieName = "force";
const langCookieName = "lang";

document.addEventListener('DOMContentLoaded', function() {
    // HACK!
    // for some reason, fmt:message changes locale after second reload
    if (getCookie(forceReloadCookieName) === "true") {
        setCookie(forceReloadCookieName, "false", 365);
        window.location.reload();
    }

    const options = document.getElementsByClassName("lang-option");

    for (let i = 0; i < options.length; i++) {
        options[i].onclick = function () {
            changeLanguage(options[i].dataset.lang);
            setCookie(forceReloadCookieName, "true", 365);
            window.location.reload();
        };
    }
})

function changeLanguage(lang) {
    setCookie(langCookieName, lang, 365);
}

function getCookie(name) {
    const nameEQ = name + "=";
    const ca = document.cookie.split(';');
    for(let i=0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0)===' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function setCookie(name,value,days) {
    let expires = "";
    if (days) {
        const date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}