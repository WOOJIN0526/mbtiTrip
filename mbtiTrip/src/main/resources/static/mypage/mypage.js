document.querySelector(".menu").addEventListener("click", function(){
    document.querySelector(".menu").classList.toggle("animate")
    document.querySelectorAll(".node").forEach((node, index) => {
        setTimeout(() => {
            node.classList.toggle("active");
        }, 100 * index);
    });
});
