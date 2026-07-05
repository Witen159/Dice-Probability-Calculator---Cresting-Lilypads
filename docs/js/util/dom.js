function number(id){

    return parseInt(
        document.getElementById(id).value);

}

function text(id){

    return document
        .getElementById(id)
        .value;

}

function checked(id){

    return document
        .getElementById(id)
        .checked;

}

function select(id) {
    return document
        .getElementById(id)
        .value;
}