module.exports = brailleToText = (text) => {
    const chars = text.split(',');
    let eng = "";
    let isNum = false;
    chars.forEach(bcode => {
        if(bcode === '3456'){
            isNum = true;
            return;
        }
        eng += getChar(bcode, isNum);
        isNum=false;
    });
    return eng;
}

const getChar = (bcode, isNum) => {
    if(isNum){
        switch(bcode){
            case '1': b = '1'; break;
            case '12': b = '2'; break;
            case '14': b = '3'; break;
            case '145': b = '4'; break;
            case '15': b = '5'; break;
            case '124': b = '6'; break;
            case '1245': b = '7'; break;
            case '125': b = '8'; break;
            case '24': b = '9'; break;
            case '245': b = '0'; break;
            default : b = ' '; break;
        }
    } else {
        switch(bcode){
            case '1': b = 'a'; break;
            case '12': b = 'b'; break;
            case '14': b = 'c'; break;
            case '145': b = 'd'; break;
            case '15': b = 'e'; break;
            case '124': b = 'f'; break;
            case '1245': b = 'g'; break;
            case '125': b = 'h'; break;
            case '24': b = 'i'; break;
            case '245': b = 'j'; break;
            case '13': b = 'k'; break;
            case '123': b = 'l'; break;
            case '134': b = 'm'; break;
            case '1345': b = 'n'; break;
            case '135': b = 'o'; break;
            case '1234': b = 'p'; break;
            case '12345': b = 'q'; break;
            case '1235': b = 'r'; break;
            case '234': b = 's'; break;
            case '2345': b = 't'; break;
            case '136': b = 'u'; break;
            case '1236': b = 'v'; break;
            case '2456': b = 'w'; break;
            case '1346': b = 'x'; break;
            case '13456': b = 'y'; break;
            case '1356': b = 'z'; break;
            default : b = ' '; break;
        }
    }
    return b;
}