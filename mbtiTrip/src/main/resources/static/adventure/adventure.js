// 지역 고르기
function localContent(content) {

    var contentA = document.getElementById("local_jinhae");
    var contentB = document.getElementById("local_daejeon");
    var contentC = document.getElementById("local_jeju");

    var cButtonALL = document.getElementById("all");
    var cButtonA = document.getElementById("jinhae");
    var cButtonB = document.getElementById("daejeon");
    var cButtonC = document.getElementById("jeju");
    if(content === "all"){
        contentA.style.display = "block";
        contentB.style.display = "block";
        contentC.style.display = "block";
        cButtonA.style.background= "none";
        cButtonB.style.background = "none";
        cButtonC.style.background = "none";

    }
    else if (content === "jinhae") {
        contentA.style.display = "block";
        contentB.style.display = "none";
        contentC.style.display = "none";
        cButtonA.style.backgroundColor = "gray";
        cButtonB.style.background = "none";
        cButtonC.style.background = "none";
    }
    else if (content === "daejeon"){
        contentA.style.display = "none";
        contentC.style.display = "none";
        contentB.style.display = "block";
        cButtonB.style.backgroundColor = "gray";
        cButtonA.style.background= "none";
        cButtonC.style.background = "none";

    }
    else if (content === "jeju"){
        contentA.style.display = "none";
        contentB.style.display = "none";
        contentC.style.display = "block";
        cButtonA.style.background = "none";
        cButtonB.style.background = "none";
        cButtonC.style.backgroundColor = "gray";
    }    
};

// 카테고리 버튼 클릭
function categoryContent(content) {

    var familyButton = document.getElementById("family");
    var coupleButton = document.getElementById("couple");
    var historyButton = document.getElementById("history");
    var natureButton = document.getElementById("nature");
    if(content === "family"){
        if(familyButton.style.background==="gray"){
            familyButton.style.background = "none";
        }
        else{
            familyButton.style.background="gray"
        }

    }
    if (content === "couple") {
        if(coupleButton.style.background==="gray"){
            coupleButton.style.background = "none";
        }
        else{
            coupleButton.style.background="gray"
        }
    }
    if (content === "history"){
        if(historyButton.style.background==="gray"){
            historyButton.style.background = "none";
        }
        else{
            historyButton.style.background="gray"
        }

    }
    if (content === "nature"){
        if(natureButton.style.background==="gray"){
            natureButton.style.background = "none";
        }
        else{
            natureButton.style.background="gray"
        }
    }    
};

// mbti 버튼 클릭
// 카테고리 버튼 클릭
function mbtiContent(content) {

    var I__JButton = document.getElementById("I--J");
    var I__PButton = document.getElementById("I--P");
    var E__PButton = document.getElementById("E--P");
    var E__JButton = document.getElementById("E--J");
    if(content === "I--J"){
        if(I__JButton.style.background==="gray"){
            I__JButton.style.background = "none";
        }
        else{
            I__JButton.style.background="gray"
        }

    }
    if (content === "I--P") {
        if(I__PButton.style.background==="gray"){
            I__PButton.style.background = "none";
        }
        else{
            I__PButton.style.background="gray"
        }
    }
    if (content === "E--P"){
        if(E__PButton.style.background==="gray"){
            E__PButton.style.background = "none";
        }
        else{
            E__PButton.style.background="gray"
        }

    }
    if (content === "nature"){
        if(E__JButton.style.background==="gray"){
            E__JButton.style.background = "none";
        }
        else{
            E__JButton.style.background="gray"
        }
    }    
};
