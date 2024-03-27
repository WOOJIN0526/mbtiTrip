function showContent(content) {

    var contentA = document.getElementById("local_JINHAE");
    var contentB = document.getElementById("local_DAEJEON");
    var contentC = document.getElementById("local_JEJU");

    var cButtonALL = document.getElementById("ALL");
    var cButtonA = document.getElementById("JINHAE");
    var cButtonB = document.getElementById("DAEJEON");
    var cButtonC = document.getElementById("JEJU");
    if(content === "ALL"){
        contentA.style.display = "block";
        contentB.style.display = "block";
        contentC.style.display = "block";
        cButtonA.style.background= "none";
        cButtonB.style.background = "none";
        cButtonC.style.background = "none";

    }
    else if (content === "JINHAE") {
        contentA.style.display = "block";
        contentB.style.display = "none";
        contentC.style.display = "none";
        cButtonA.style.backgroundColor = "red";
        cButtonB.style.background = "none";
        cButtonC.style.background = "none";
    }
    else if (content === "DAEJEON"){
        contentA.style.display = "none";
        contentC.style.display = "none";
        contentB.style.display = "block";
        cButtonB.style.backgroundColor = "red";
        cButtonA.style.background= "none";
        cButtonC.style.background = "none";

    }
    else if (content === "JEJU"){
        contentA.style.display = "none";
        contentB.style.display = "none";
        contentC.style.display = "block";
        cButtonA.style.background = "none";
        cButtonB.style.background = "none";
        cButtonC.style.backgroundColor = "red";
    }    
}