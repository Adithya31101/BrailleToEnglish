module.exports =  textToBraille = (text) => {
    const charArr = text.split('');
    let bcode = [];
    charArr.forEach(letter => {
        if(!isNaN(letter)){
            bcode = bcode.concat(zero_nineBcode(letter));
        } else {
            bcode = bcode.concat(a_zBCode(letter));
        }
    });
    return bcode;
}

const a_zBCode = l => {
    let b = null;
    
    
    return b;
}

const zero_nineBcode = n => {
    let b = null;
    switch(n){
        case '0': b = [3456]; break;
        case '1': b = [3456,1]; break;
        case '2': b = [3456,12]; break;
        case '3': b = [3456,14]; break;
        case '4': b = [3456,145]; break;
        case '5': b = [3456,15]; break;
        case '6': b = [3456,124]; break;
        case '7': b = [3456,1245]; break;
        case '8': b = [3456,125]; break;
        case '9': b = [3456,24]; break;
        case '0': b = [3456,245]; break;
        case ' ': b = [0]; break;
        default : b = [0]; break;
    }
    return b;
}

